package org.ecrp.framework.plugin;

public class AddPluginResult {

    private PluginJarInfo pluginJarInfo;

    private boolean newPlugin;

    public AddPluginResult(PluginJarInfo pluginJarInfo, boolean newPlugin) {
        this.pluginJarInfo = pluginJarInfo;
        this.newPlugin = newPlugin;
    }

    public PluginJarInfo getPluginJarInfo() {
        return pluginJarInfo;
    }

    public void setPluginJarInfo(PluginJarInfo pluginJarInfo) {
        this.pluginJarInfo = pluginJarInfo;
    }

    public boolean isNewPlugin() {
        return newPlugin;
    }

    public void setNewPlugin(boolean newPlugin) {
        this.newPlugin = newPlugin;
    }
}
