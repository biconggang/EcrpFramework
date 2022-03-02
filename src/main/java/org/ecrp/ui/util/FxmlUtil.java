package org.ecrp.ui.util;

import org.ecrp.ui.base.UIException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ResourceBundle;

public class FxmlUtil {

    final static Logger logger = LoggerFactory.getLogger("FxmlUtil");

    public static FXMLLoader loadFxmlFromResource(String resourcePath) {
        return loadFxmlFromResource(resourcePath, null);
    }

    public static FXMLLoader loadFxmlFromResource(String resourcePath, ResourceBundle resourceBundle) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(FxmlUtil.class.getResource(resourcePath));
            fxmlLoader.setResources(resourceBundle);
            fxmlLoader.load();
            return fxmlLoader;
        } catch (IOException e) {
            throw new UIException(e);
        }
    }

    /**
     * Load fxml without throwing exceptions
     */
    public static Parent load(String source) {
        try {
            return FXMLLoader.load(FxmlUtil.class.getResource(source));
        } catch (IOException e) {
            logger.error("", e);
            new Alert(Alert.AlertType.ERROR, "Unable to load " + source + "\n" + e.toString(), ButtonType.OK).show();
            return new Pane();
        }
    }
}
