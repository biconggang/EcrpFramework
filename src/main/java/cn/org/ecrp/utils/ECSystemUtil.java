package cn.org.ecrp.utils;


import cn.org.ecrp.controller.plugin.PluginManageService;
import cn.org.ecrp.plugin.PluginManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @ClassName: ECSystemUtil
 * @Description: javafx系统层工具类
 * @author: xufeng
 * @date: 2017年11月10日 下午4:35:17
 */
public class ECSystemUtil {

    final static Logger logger = LoggerFactory.getLogger("ECSystemUtil");

    /**
     * @Title: initSystemLocal
     * @Description: 初始化本地语言
     */
    public static ResourceBundle initSystemLocal() {
        ResourceBundle res = null;
        try {
            String localeString = Config.get(Config.Keys.Locale, "");

            if (StringUtils.isNotEmpty(localeString)) {
                String[] locale1 = localeString.split("_");
                Config.defaultLocale = new Locale(locale1[0], locale1[1]);
            }

            res = ResourceBundle.getBundle("locale.Menu", Config.defaultLocale);
        } catch (Exception e) {
            logger.error("初始化本地语言失败", e);
        }

        return res;
    }


    /**
     * @Title: addJarByLibs
     * @Description: 添加libs中jar包到系统中
     */
    public static void addJarByLibs() {
        try {
            // 系统类库路径
            File libPath = new File("libs/");
            // 获取所有的.jar和.zip文件
            File[] jarFiles = libPath.listFiles(
                    (dir, name) -> name.endsWith(".jar")
            );
            if (jarFiles != null) {
                for (File file : jarFiles) {
                    if (!PluginManageService.isPluginEnabled(file.getName())) {
                        continue;
                    }
                    addJarClass(file);
                }
            }
            PluginManager.getInstance().loadLocalPlugins();
        } catch (Exception e) {
            logger.error("添加libs中jar包到系统中异常:", e);
        }
    }

    /**
     * @Title: addJarClass
     * @Description: 添加jar包到系统中
     */
    public static void addJarClass(File jarFile) {
        try {
            logger.info("Reading lib file: " + jarFile.getName());
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            // 设置方法的访问权限
            method.setAccessible(true);
            // 获取系统类加载器
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            URL url = jarFile.toURI().toURL();
            method.invoke(classLoader, url);
        } catch (Exception e) {
            logger.error("添加libs中jar包到系统中异常:", e);
        }
    }
}
