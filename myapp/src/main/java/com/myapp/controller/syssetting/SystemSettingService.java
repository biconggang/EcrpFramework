package com.myapp.controller.syssetting;

import com.myapp.Main;
import javafx.scene.control.ButtonType;
import org.ecrp.framework.ui.base.FxApp;
import org.ecrp.framework.ui.dialog.FxDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: SystemSettingService
 * @Description: 设置页面
 * @author: xufeng
 * @date: 2020/2/25 0025 16:44
 */
public class SystemSettingService {

    final static Logger logger = LoggerFactory.getLogger("SystemSettingService");

    public static void openSystemSettings(String title) {

        FxDialog<SystemSettingController> dialog = new FxDialog<SystemSettingController>()
            .setResourceBundle(Main.RESOURCE_BUNDLE)
            .setTitle(title)
            .setBodyFxml("/com/myapp/fxmlView/index/SystemSetting.fxml")
            .setOwner(FxApp.primaryStage)
            .setButtonTypes(ButtonType.OK, ButtonType.CANCEL);

        SystemSettingController controller = dialog.show();

        dialog
            .setButtonHandler(ButtonType.OK, (actionEvent, stage) -> {
                controller.applySettings();
                stage.close();
            })
            .setButtonHandler(ButtonType.CANCEL, (actionEvent, stage) -> stage.close());
    }
}
