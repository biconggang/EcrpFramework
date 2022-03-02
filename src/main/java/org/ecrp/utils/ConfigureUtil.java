package org.ecrp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class ConfigureUtil {
    final static Logger logger = LoggerFactory.getLogger("ConfigureUtil");

    public static String getConfigurePath() {
        String res = System.getProperty("user.dir") + "/config/";
        logger.info(res);
        return res;
    }

    public static String getConfigurePath(String fileName) {
        return getConfigurePath() + fileName;
    }

    public static File getConfigureFile(String fileName) {
        return new File(getConfigurePath(fileName));
    }
}
