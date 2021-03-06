package org.ecrp.ui.helper;

import org.ecrp.ui.base.UIException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * description <br>
 *
 * @author bicg
 * @date 2021-12-16 16:12
 */
public class FxmlHelper {

    public static FXMLLoader loadFromResource(String resourcePath) {
        return loadFromResource(resourcePath, null);
    }

    public static FXMLLoader loadFromResource(String resourcePath, String bundleName) {
        try {
            URL resource = FxmlHelper.class.getResource(resourcePath);
            if (resource == null) {
                throw new IllegalArgumentException("invalid fxml path " + resourcePath);
            }

            FXMLLoader fxmlLoader;
            if (StringUtils.isNotBlank(bundleName)) {
                fxmlLoader = new FXMLLoader(resource, ResourceBundle.getBundle(bundleName));
            } else {
                fxmlLoader = new FXMLLoader(resource);
            }

            fxmlLoader.load();
            return fxmlLoader;
        } catch (IOException e) {
            throw new UIException(e);
        }
    }

    public static Stage loadIntoStage(String fxml, String bundleName, Stage stage) {
        stage.setScene(new Scene(loadFromResource(fxml, bundleName).getRoot()));
        return stage;
    }

    public static Stage loadIntoStage(String fxml, Stage stage) {
        stage.setScene(new Scene(loadFromResource(fxml).getRoot()));
        return stage;
    }
}
