package cn.org.ecrp.controller.syssetting;

import cn.org.ecrp.Main;
import cn.org.ecrp.ui.base.FxApp;
import cn.org.ecrp.ui.dialog.FxDialog;
import javafx.scene.control.ButtonType;
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
            .setBodyFxml("/cn/org/ecrp/fxmlView/index/SystemSetting.fxml")
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
