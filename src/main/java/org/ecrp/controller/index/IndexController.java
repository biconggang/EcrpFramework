package org.ecrp.controller.index;

import org.ecrp.controller.plugin.PluginManageController;
import org.ecrp.controller.plugin.PluginManageService;
import org.ecrp.controller.syssetting.SystemSettingService;
import org.ecrp.plugin.PluginManager;
import org.ecrp.ui.util.AlertUtil;
import org.ecrp.ui.util.JavaFxSystemUtil;
import org.ecrp.ui.util.JavaFxViewUtil;
import org.ecrp.ui.util.ToolFxmlLoaderConfiguration;
import org.ecrp.utils.Config;
import org.ecrp.utils.ConfigureUtil;
import org.ecrp.utils.HttpClientUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.ecrp.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static org.ecrp.utils.Config.Keys.NotepadEnabled;

/**
 * @ClassName: IndexController
 * @Description: 主页
 * @author: xufeng
 * @date: 2017年7月20日 下午1:50:00
 */
public class IndexController extends IndexView {

    final static Logger logger = LoggerFactory.getLogger("IndexController");

    public static final String QQ_URL = "https://support.qq.com/product/127577";

    public static final String STATISTICS_URL = "https://xwintop.gitee.io/maven/tongji/xJavaFxTool.html";

    private Map<String, Menu> menuMap = new HashMap<>();

    private Map<String, MenuItem> menuItemMap = new HashMap<>();

    private IndexService indexService = new IndexService(this);

    private ContextMenu contextMenu = new ContextMenu();

    public static FXMLLoader getFXMLLoader() {
        URL url = Object.class.getResource("/org/ecrp/fxmlView/Index.fxml");
        return new FXMLLoader(url, Main.RESOURCE_BUNDLE);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.bundle = resources;

        initView();
        initEvent();
        initService();
        initNotepad();

        this.indexService.addWebView(Main.RESOURCE_BUNDLE.getString("feedback"), QQ_URL, null);
        this.tongjiWebView.getEngine().load(STATISTICS_URL);
    }

    private void initNotepad() {
        if (Config.getBoolean(NotepadEnabled, true)) {
            addNodepadAction(null);
        }
    }

    private void initView() {
        menuMap.put("toolsMenu", toolsMenu);
        menuMap.put("moreToolsMenu", moreToolsMenu);
        File libPath = new File("libs/");
        // 获取所有的.jar和.zip文件
        File[] jarFiles = libPath.listFiles((dir, name) -> name.endsWith(".jar"));
        if (jarFiles != null) {
            for (File jarFile : jarFiles) {
                if (!PluginManageService.isPluginEnabled(jarFile.getName())) {
                    continue;
                }
                try {
                    this.addToolMenu(jarFile);
                } catch (Exception e) {
                    logger.error("加载工具出错：", e);
                }
            }
        }
    }

    private void initEvent() {
        myTextField.textProperty().addListener((observable, oldValue, newValue) -> selectAction(newValue));
        myButton.setOnAction(arg0 -> {
            selectAction(myTextField.getText());
        });
    }

    private void initService() {
    }

    public void addToolMenu(File file) throws Exception {
        Map<String, ToolFxmlLoaderConfiguration> toolMap = new HashMap<>();
        List<ToolFxmlLoaderConfiguration> toolList = new ArrayList<>();

        try (JarFile jarFile = new JarFile(file)) {
            JarEntry entry = jarFile.getJarEntry("config/toolFxmlLoaderConfiguration.xml");
            if (entry == null) {
                return;
            }
            InputStream input = jarFile.getInputStream(entry);
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(input);
            Element root = document.getRootElement();
            List<Element> elements = root.elements("ToolFxmlLoaderConfiguration");
            for (Element configurationNode : elements) {
                ToolFxmlLoaderConfiguration toolFxmlLoaderConfiguration = new ToolFxmlLoaderConfiguration();
                List<Attribute> attributes = configurationNode.attributes();
                for (Attribute configuration : attributes) {
                    BeanUtils.copyProperty(toolFxmlLoaderConfiguration, configuration.getName(), configuration.getValue());
                }
                List<Element> childrenList = configurationNode.elements();
                for (Element configuration : childrenList) {
                    BeanUtils.copyProperty(toolFxmlLoaderConfiguration, configuration.getName(), configuration.getStringValue());
                }
                if (StringUtils.isEmpty(toolFxmlLoaderConfiguration.getMenuParentId())) {
                    toolFxmlLoaderConfiguration.setMenuParentId("moreToolsMenu");
                }
                if (toolFxmlLoaderConfiguration.getIsMenu()) {
                    if (menuMap.get(toolFxmlLoaderConfiguration.getMenuId()) == null) {
                        toolMap.putIfAbsent(toolFxmlLoaderConfiguration.getMenuId(), toolFxmlLoaderConfiguration);
                    }
                } else {
                    toolList.add(toolFxmlLoaderConfiguration);
                }
            }
        }
        toolList.addAll(toolMap.values());
        this.addMenu(toolList);
    }

    private void addMenu(List<ToolFxmlLoaderConfiguration> toolList) {
        for (ToolFxmlLoaderConfiguration toolConfig : toolList) {
            try {
                if (StringUtils.isEmpty(toolConfig.getResourceBundleName())) {
                    if (StringUtils.isNotEmpty(bundle.getString(toolConfig.getTitle()))) {
                        toolConfig.setTitle(bundle.getString(toolConfig.getTitle()));
                    }
                } else {
                    ResourceBundle resourceBundle = ResourceBundle.getBundle(toolConfig.getResourceBundleName(), Config.defaultLocale);
                    if (StringUtils.isNotEmpty(resourceBundle.getString(toolConfig.getTitle()))) {
                        toolConfig.setTitle(resourceBundle.getString(toolConfig.getTitle()));
                    }
                }
            } catch (Exception e) {
                logger.error("加载菜单失败", e);
            }
            if (toolConfig.getIsMenu()) {
                Menu menu = new Menu(toolConfig.getTitle());
                if (StringUtils.isNotEmpty(toolConfig.getIconPath())) {
                    ImageView imageView = new ImageView(new Image(toolConfig.getIconPath()));
                    imageView.setFitHeight(18);
                    imageView.setFitWidth(18);
                    menu.setGraphic(imageView);
                }
                menuMap.put(toolConfig.getMenuId(), menu);
            }
        }

        for (ToolFxmlLoaderConfiguration toolConfig : toolList) {
            if (toolConfig.getIsMenu()) {
                menuMap.get(toolConfig.getMenuParentId()).getItems().add(menuMap.get(toolConfig.getMenuId()));
            }
        }

        for (ToolFxmlLoaderConfiguration toolConfig : toolList) {
            if (toolConfig.getIsMenu()) {
                continue;
            }
            MenuItem menuItem = new MenuItem(toolConfig.getTitle());
            if (StringUtils.isNotEmpty(toolConfig.getIconPath())) {
                ImageView imageView = new ImageView(new Image(toolConfig.getIconPath()));
                imageView.setFitHeight(18);
                imageView.setFitWidth(18);
                menuItem.setGraphic(imageView);
            }
            if ("Node".equals(toolConfig.getControllerType())) {
                menuItem.setOnAction((ActionEvent event) -> {
                    indexService.addContent(menuItem.getText(), toolConfig.getUrl(), toolConfig.getResourceBundleName(), toolConfig.getIconPath());
                });
                if (toolConfig.getIsDefaultShow()) {
                    indexService.addContent(menuItem.getText(), toolConfig.getUrl(), toolConfig.getResourceBundleName(), toolConfig.getIconPath());
                }
            } else if ("WebView".equals(toolConfig.getControllerType())) {
                menuItem.setOnAction((ActionEvent event) -> {
                    indexService.addWebView(menuItem.getText(), toolConfig.getUrl(), toolConfig.getIconPath());
                });
                if (toolConfig.getIsDefaultShow()) {
                    indexService.addWebView(menuItem.getText(), toolConfig.getUrl(), toolConfig.getIconPath());
                }
            }
            menuMap.get(toolConfig.getMenuParentId()).getItems().add(menuItem);
            menuItemMap.put(menuItem.getText(), menuItem);
        }
    }

    public void selectAction(String selectText) {
        if (contextMenu.isShowing()) {
            contextMenu.hide();
        }
        contextMenu = indexService.getSelectContextMenu(selectText);
        contextMenu.show(myTextField, null, 0, myTextField.getHeight());
    }

    @FXML
    private void exitAction() {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void closeAllTabAction() {
        tabPaneMain.getTabs().clear();
    }

    @FXML
    private void openAllTabAction() {
        for (MenuItem value : menuItemMap.values()) {
            value.fire();
        }
    }

    @FXML
    private void addNodepadAction(ActionEvent event) {
        indexService.addNodepadAction(event);
    }

    @FXML
    private void addLogConsoleAction(ActionEvent event) {
        indexService.addLogConsoleAction(event);
    }

    @FXML
    private void pluginManageAction() throws Exception {
        FXMLLoader fXMLLoader = PluginManageController.getFXMLLoader();
        Parent root = fXMLLoader.load();
        PluginManageController pluginManageController = fXMLLoader.getController();
        pluginManageController.setOnPluginDownloaded(jarFile -> {
            try {
                this.addToolMenu(jarFile);
                PluginManager.getInstance().loadLocalPlugins();
            } catch (Exception e) {
                logger.error("加载工具出错：", e);
            }
        });
        JavaFxViewUtil.openNewWindow(bundle.getString("plugin_manage"), root);
    }

    @FXML
    private void SettingAction() {
        SystemSettingService.openSystemSettings(bundle.getString("Setting"));
    }

    @FXML
    private void aboutAction() {
        AlertUtil.showInfoAlert(bundle.getString("aboutText") + Config.EcrpVersions);
    }

    @FXML
    private void setLanguageAction(ActionEvent event) throws Exception {
        MenuItem menuItem = (MenuItem) event.getSource();
        indexService.setLanguageAction(menuItem.getText());
    }

    @FXML
    private void openLogFileAction() {
        String filePath = "logs/logFile." + DateFormatUtils.format(new Date(), "yyyy-MM-dd") + ".log";
        JavaFxSystemUtil.openDirectory(filePath);
    }

    @FXML
    private void openLogFolderAction() {
        JavaFxSystemUtil.openDirectory("logs/");
    }

    @FXML
    private void openConfigFolderAction() {
        JavaFxSystemUtil.openDirectory(ConfigureUtil.getConfigurePath());
    }

    @FXML
    private void openPluginFolderAction() {
        JavaFxSystemUtil.openDirectory("libs/");
    }

    @FXML
    private void xwintopLinkOnAction() throws Exception {
        HttpClientUtil.openBrowseURLThrowsException("https://gitee.com/xwintop/xJavaFxTool");
    }

    @FXML
    private void userSupportAction() throws Exception {
        HttpClientUtil.openBrowseURLThrowsException("https://support.qq.com/product/127577");
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //
    //  成员访问
    ///////////////////////////////////////////////////////////////////////////////////////
    public Map<String, Menu> getMenuMap() {
        return menuMap;
    }

    public Map<String, MenuItem> getMenuItemMap() {
        return menuItemMap;
    }

    public IndexService getIndexService() {
        return indexService;
    }

    public ContextMenu getContextMenu() {
        return contextMenu;
    }
}
