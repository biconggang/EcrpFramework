package org.ecrp.framework.plugin;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.File;

/**
 * 插件信息
 * @author xufeng
 */

public class PluginJarInfo {

    ///////////////////////////////////////// 下面的属性在远程插件列表和本地配置中都存在

    private String name;            // 名称

    private String synopsis;        // 简介

    private String jarName;         // jar包名称

    private String version;         // 版本名称

    private Integer versionNumber;  // 版本号（用来判断更新）

    private String downloadUrl;     // 下载地址

    ///////////////////////////////////////// 下面的属性在远程插件列表中不存在

    private Boolean isDownload;     // 是否下载

    private Boolean isEnable;       // 是否启用

    private Boolean isFavorite;     // 是否置顶

    private Integer localVersionNumber;     // 插件本地版本

    private String localPath;       // 插件本地文件路径（如果是本地插件）

    ///////////////////////////////////////// 下面的属性来自插件描述文件 toolFxmlLoaderConfiguration.xml

    private String fxmlPath;                // FXML 资源路径

    private String pagePath;                // 网页资源路径

    private String iconPath;                // 图标资源路径（可选）

    private String bundleName;              // 配置资源路径

    private String className;               // ？

    private String menuId;                  // （当是菜单时）菜单ID

    private String menuParentId;            // 上级菜单ID

    private String menuParentTitle;         // 上级菜单标题（资源名）

    private Boolean isMenu = false;         // 是否为菜单

    private String title;                   // （Tab 页或窗体）标题

    private String controllerType = "Node"; // 内容类型（Node/WebView）

    @JSONField(serialize = false)
    public File getFile() {
        return localPath == null? new File("libs/", getJarName() + "-" + getVersion() + ".jar"): new File(localPath);
    }

    @JSONField(serialize = false)
    public String getDefaultIconPath() {
        return bundleName == null ? "" :
            (bundleName.replace("locale.", "/logo/") + ".png");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Integer versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Boolean getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(Boolean download) {
        isDownload = download;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean enable) {
        isEnable = enable;
    }

    public boolean getIsFavorite() {
        return this.isFavorite != null && this.isFavorite;
    }

    public void setIsFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public Integer getLocalVersionNumber() {
        return localVersionNumber;
    }

    public void setLocalVersionNumber(Integer localVersionNumber) {
        this.localVersionNumber = localVersionNumber;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    public String getFxmlPath() {
        return fxmlPath;
    }

    public void setFxmlPath(String fxmlPath) {
        this.fxmlPath = fxmlPath;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(String menuParentId) {
        this.menuParentId = menuParentId;
    }

    public String getMenuParentTitle() {
        return menuParentTitle;
    }

    public void setMenuParentTitle(String menuParentTitle) {
        this.menuParentTitle = menuParentTitle;
    }

    public Boolean getMenu() {
        return isMenu;
    }

    public void setMenu(Boolean menu) {
        isMenu = menu;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getControllerType() {
        return controllerType;
    }

    public void setControllerType(String controllerType) {
        this.controllerType = controllerType;
    }
}
