package org.ecrp.controller.index;

import org.ecrp.log.ConsoleLogAppender;
import org.ecrp.plugin.PluginJarInfo;
import org.ecrp.plugin.PluginLoader;
import org.ecrp.plugin.PluginManager;
import org.ecrp.ui.dialog.FxAlerts;
import org.ecrp.ui.util.FxmlUtil;
import org.ecrp.ui.util.JavaFxViewUtil;
import org.ecrp.utils.Config;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.util.Locale;


public class IndexService {

    private IndexController indexController;

    public IndexService(IndexController indexController) {
        this.indexController = indexController;
    }

    public void setLanguageAction(String languageType) throws Exception {
        if ("简体中文".equals(languageType)) {
            Config.set(Config.Keys.Locale, Locale.SIMPLIFIED_CHINESE);
        } else if ("English".equals(languageType)) {
            Config.set(Config.Keys.Locale, Locale.US);
        }

        FxAlerts.info("", indexController.getBundle().getString("SetLanguageText"));
    }

    public ContextMenu getSelectContextMenu(String selectText) {
        selectText = selectText.toLowerCase();
        ContextMenu contextMenu = new ContextMenu();
        for (MenuItem menuItem : indexController.getMenuItemMap().values()) {
            if (menuItem.getText().toLowerCase().contains(selectText)) {
                MenuItem menu_tab = new MenuItem(menuItem.getText(), menuItem.getGraphic());
                menu_tab.setOnAction(event1 -> {
                    menuItem.fire();
                });
                contextMenu.getItems().add(menu_tab);
            }
        }
        return contextMenu;
    }

    public void addNodepadAction(ActionEvent event) {
        Parent notepad = FxmlUtil.load("/org/ecrp/fxmlView/notepad.fxml");
        if (indexController.getSingleWindowBootCheckBox().isSelected()) {
            JavaFxViewUtil.getNewStage(indexController.getBundle().getString("addNodepad"), null, notepad);
        } else {
            Tab tab = new Tab(indexController.getBundle().getString("addNodepad"));
            tab.setContent(notepad);
            indexController.getTabPaneMain().getTabs().add(tab);
            if (event != null) {
                indexController.getTabPaneMain().getSelectionModel().select(tab);
            }
        }
    }

    public void addLogConsoleAction(ActionEvent event) {
        TextArea textArea = new TextArea();
        textArea.setFocusTraversable(true);
        ConsoleLogAppender.textAreaList.add(textArea);
        if (indexController.getSingleWindowBootCheckBox().isSelected()) {
            Stage newStage = JavaFxViewUtil
                    .getNewStage(indexController.getBundle().getString("addLogConsole"), null, textArea);
            newStage.setOnCloseRequest(event1 -> {
                ConsoleLogAppender.textAreaList.remove(textArea);
            });
        } else {
            Tab tab = new Tab(indexController.getBundle().getString("addLogConsole"));
            tab.setContent(textArea);
            indexController.getTabPaneMain().getTabs().add(tab);
            if (event != null) {
                indexController.getTabPaneMain().getSelectionModel().select(tab);
            }
            tab.setOnCloseRequest((Event event1) -> {
                ConsoleLogAppender.textAreaList.remove(textArea);
            });
        }
    }

    /**
     * 添加Content内容
     */
    public void addContent(String title, String fxmlPath, String resourceBundleName, String iconPath) {

        PluginJarInfo pluginJarInfo = PluginManager.getInstance().getPluginByFxmlPath(fxmlPath);
        if (pluginJarInfo == null) {
            FxAlerts.error("打开失败", "没有找到指定的插件");
            return;
        }

        if (indexController.getSingleWindowBootCheckBox().isSelected()) {
            PluginLoader.loadPluginAsWindow(pluginJarInfo);
        } else {
            PluginLoader.loadPluginAsTab(pluginJarInfo, indexController.getTabPaneMain());
        }
    }

    /**
     * @Title: addWebView
     * @Description: 添加WebView视图
     */
    public void addWebView(String title, String url, String iconPath) {
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        if (url.startsWith("http")) {
            webEngine.load(url);
        } else {
            webEngine.load(IndexController.class.getResource(url).toExternalForm());
        }
        if (indexController.getSingleWindowBootCheckBox().isSelected()) {
            JavaFxViewUtil.getNewStage(title, iconPath, new BorderPane(browser));
            return;
        }
        Tab tab = new Tab(title);
        if (StringUtils.isNotEmpty(iconPath)) {
            ImageView imageView = new ImageView(new Image(iconPath));
            imageView.setFitHeight(18);
            imageView.setFitWidth(18);
            tab.setGraphic(imageView);
        }
        tab.setContent(browser);
        indexController.getTabPaneMain().getTabs().add(tab);
        indexController.getTabPaneMain().getSelectionModel().select(tab);
    }
}
