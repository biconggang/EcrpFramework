package cn.org.ecrp.controller.plugin;

import cn.org.ecrp.common.AppException;
import cn.org.ecrp.plugin.PluginJarInfo;
import cn.org.ecrp.plugin.PluginManager;
import cn.org.ecrp.ui.dialog.FxProgressDialog;
import cn.org.ecrp.ui.dialog.ProgressTask;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static org.apache.commons.lang3.StringUtils.substringBeforeLast;

/**
 * 插件管理
 *
 * @author xufeng
 */
public class PluginManageService {

    public static final String PLUGIN_LIST_URL = "https://xwintop.gitee.io/maven/plugin-libs/plugin-list.json";
    public static final String PLUGIN_LIST_PATH = "system_plugin_list.json";
    final static Logger logger = LoggerFactory.getLogger("PluginManageService");
    private PluginManageController pluginManageController;

    private PluginManager pluginManager = PluginManager.getInstance();

    private Consumer<File> onPluginDownloaded;

    public PluginManageService(PluginManageController pluginManageController) {
        this.pluginManageController = pluginManageController;
    }

    /**
     * 判断插件是否启用
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean isPluginEnabled(String fileName) {
        String jarName = substringBeforeLast(fileName, "-");
        PluginJarInfo pluginJarInfo = PluginManager.getInstance().getPlugin(jarName);
        if (pluginJarInfo == null) {
            return false;
        }
        Boolean isEnable = pluginJarInfo.getIsEnable();
        return isEnable != null && isEnable;
    }

    public void getPluginList() {
        pluginManager.loadServerPlugins();
        pluginManager.getPluginList().forEach(this::addDataRow);
    }

    public void addDataRow(PluginJarInfo plugin) {

        Map<String, String> dataRow = new HashMap<>();
        dataRow.put("nameTableColumn", plugin.getName());
        dataRow.put("synopsisTableColumn", plugin.getSynopsis());
        dataRow.put("versionTableColumn", plugin.getVersion());
        dataRow.put("jarName", plugin.getJarName());
        dataRow.put("downloadUrl", plugin.getDownloadUrl());
        dataRow.put("versionNumber", String.valueOf(plugin.getVersionNumber()));

        if (plugin.getIsDownload() == null || !plugin.getIsDownload()) {
            dataRow.put("isDownloadTableColumn", "下载");
            dataRow.put("isEnableTableColumn", "false");
        } else {
            if (plugin.getLocalVersionNumber() != null &&
                    plugin.getVersionNumber() > plugin.getLocalVersionNumber()) {
                dataRow.put("isDownloadTableColumn", "更新");
            } else {
                dataRow.put("isDownloadTableColumn", "已下载");
            }
            dataRow.put("isEnableTableColumn", plugin.getIsEnable().toString());
        }

        pluginManageController.getOriginPluginData().add(dataRow);
    }

    public PluginJarInfo downloadPluginJar(Map<String, String> dataRow) throws Exception {

        String jarName = dataRow.get("jarName");
        PluginJarInfo pluginJarInfo = pluginManager.getPlugin(jarName);

        ProgressTask progressTask = new ProgressTask() {
            @Override
            protected void execute() throws Exception {
                File file = pluginManager.downloadPlugin(
                        pluginJarInfo, (total, current) -> updateProgress(current, total)
                );

                if (onPluginDownloaded != null) {
                    onPluginDownloaded.accept(file);
                }
            }
        };

        progressTask.setOnCancelled(event -> {
            throw new AppException("下载被取消。");
        });

        FxProgressDialog
                .create(pluginManageController.getWindow(), progressTask, "正在下载插件 " + pluginJarInfo.getName() + "...")
                .showAndWait();

        return pluginJarInfo;
    }

    public void setIsEnableTableColumn(Integer index) {
        Map<String, String> dataRow = pluginManageController.getOriginPluginData().get(index);
        String jarName = dataRow.get("jarName");
        PluginJarInfo pluginJarInfo = this.pluginManager.getPlugin(jarName);
        if (pluginJarInfo != null) {
            pluginJarInfo.setIsEnable(Boolean.parseBoolean(dataRow.get("isEnableTableColumn")));
        }
    }

    public void searchPlugin(String keyword) {
        pluginManageController.getPluginDataTableData().setPredicate(map -> {
            if (StringUtils.isBlank(keyword)) {
                return true;
            } else {
                return isPluginDataMatch(map, keyword);
            }
        });
    }

    private boolean isPluginDataMatch(Map<String, String> map, String keyword) {
        return map.entrySet().stream().anyMatch(
                entry ->
                        !entry.getKey().equals("downloadUrl") &&
                                entry.getValue().toLowerCase().contains(keyword.toLowerCase())
        );
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    //
    //  成员访问
    ///////////////////////////////////////////////////////////////////////////////////////

    public Consumer<File> getOnPluginDownloaded() {
        return onPluginDownloaded;
    }

    public void setOnPluginDownloaded(Consumer<File> onPluginDownloaded) {
        this.onPluginDownloaded = onPluginDownloaded;
    }
}
