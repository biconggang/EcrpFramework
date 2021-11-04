package cn.org.ecrp.ui.util;

public class ToolFxmlLoaderConfiguration {

    /**
     * 资源url
     */
    private String url;

    /**
     * 国际化资源文件
     */
    private String resourceBundleName;

    /**
     * class名称
     */
    private String className;

    /**
     * 标题（配合国际化资源文件，无则默认显示原字符）
     */
    private String title;

    /**
     * 图标路径
     */
    private String iconPath;

    /**
     * 是否在启动时自动加载
     */
    private Boolean isDefaultShow = false;

    /**
     * 菜单id
     */
    private String menuId;

    /**
     * 菜单父id
     */
    private String menuParentId;

    /**
     * 是否为菜单
     */
    private Boolean isMenu = false;

    /**
     * 内容类型
     */
    private String controllerType = "Node";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getResourceBundleName() {
        return resourceBundleName;
    }

    public void setResourceBundleName(String resourceBundleName) {
        this.resourceBundleName = resourceBundleName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public Boolean getIsDefaultShow() {
        return isDefaultShow;
    }

    public void setIsDefaultShow(Boolean defaultShow) {
        isDefaultShow = defaultShow;
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

    public Boolean getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Boolean menu) {
        isMenu = menu;
    }

    public String getControllerType() {
        return controllerType;
    }

    public void setControllerType(String controllerType) {
        this.controllerType = controllerType;
    }
}
