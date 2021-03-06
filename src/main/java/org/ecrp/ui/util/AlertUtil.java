package org.ecrp.ui.util;

import org.ecrp.ui.base.FxApp;
import org.ecrp.ui.dialog.FxDialog;
import org.ecrp.ui.helper.LayoutHelper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.ecrp.ui.helper.LayoutHelper.label;
import static org.ecrp.ui.helper.LayoutHelper.vbox;

@Deprecated
public class AlertUtil {

    public static final int LABEL_MAX_WIDTH = 300;

    /**
     * 信息提示框
     *
     * @deprecated 使用 FxAlerts
     */
    public static void showInfoAlert(String message) {
        showInfoAlert("提示", message);
    }

    /**
     * 信息提示框
     *
     * @deprecated 使用 FxAlerts
     */
    public static void showInfoAlert(String title, String message) {
        new FxDialog<>()
                .setOwner(FxApp.primaryStage)
                .setTitle(title)
                .setBody(LayoutHelper.vbox(20, 0, Pos.CENTER, label(message, LABEL_MAX_WIDTH)))
                .setButtonTypes(ButtonType.OK)
                .setButtonHandler(ButtonType.OK, (actionEvent, stage) -> stage.close())
                .showAndWait();
    }

    /**
     * 确定提示框
     *
     * @deprecated 使用 FxAlerts
     */
    public static boolean confirmYesNo(String title, String message) {
        return confirm(title, message, ButtonType.YES, ButtonType.NO) == ButtonType.YES;
    }

    /**
     * 确定提示框
     *
     * @deprecated 使用 FxAlerts
     */
    public static boolean confirmOkCancel(String title, String message) {
        return confirm(title, message, ButtonType.OK, ButtonType.CANCEL) == ButtonType.OK;
    }

    /**
     * 确定提示框
     *
     * @deprecated 使用 FxAlerts
     */
    public static ButtonType confirmYesNoCancel(String title, String message) {
        return confirm(title, message, ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
    }

    /**
     * 确定提示框
     *
     * @deprecated 使用 FxAlerts
     */
    public static ButtonType confirm(
            String title, String message, ButtonType positiveButtonType, ButtonType... negativeButtonTypes
    ) {

        // 构造 buttonTypes
        ButtonType[] buttonTypes = new ButtonType[
                (negativeButtonTypes == null ? 1 : negativeButtonTypes.length) + 1];

        buttonTypes[0] = positiveButtonType;

        if (negativeButtonTypes != null) {
            System.arraycopy(negativeButtonTypes, 0, buttonTypes, 1, negativeButtonTypes.length);
        }

        // 构造对话框
        FxDialog<Object> dialog = new FxDialog<>()
                .setTitle(title)
                .setButtonTypes(buttonTypes)
                .setOwner(FxApp.primaryStage)
                .setBody(vbox(10, 0, label(message)));

        ButtonType[] result = new ButtonType[]{ButtonType.CANCEL};

        dialog.setButtonHandler(positiveButtonType, (actionEvent, stage) -> {
            result[0] = positiveButtonType;
            stage.close();
        });

        if (negativeButtonTypes != null) {
            for (ButtonType negativeButtonType : negativeButtonTypes) {
                dialog.setButtonHandler(negativeButtonType, (actionEvent, stage) -> {
                    result[0] = negativeButtonType;
                    stage.close();
                });
            }
        }

        // 显示对话框
        dialog.showAndWait();
        return result[0];
    }

    /**
     * 输入提示框，如果点击确定则返回文本框内容，点击取消或关闭则返回 null
     */
    public static String showInputAlertDefaultValue(String message, String defaultValue) {
        String[] result = new String[]{null};

        TextField textField = LayoutHelper.textField(defaultValue, 200);
        VBox body = vbox(10, 10, label(message, LABEL_MAX_WIDTH), textField);

        new FxDialog<>()
                .setOwner(FxApp.primaryStage)
                .setBody(body)
                .setTitle("提示")
                .setButtonTypes(ButtonType.OK, ButtonType.CANCEL)
                .setButtonHandler(ButtonType.OK, (actionEvent, stage) -> {
                    result[0] = textField.getText();
                    stage.close();
                })
                .setButtonHandler(ButtonType.CANCEL, (actionEvent, stage) -> stage.close())
                .showAndWait();

        return result[0];
    }

    //////////////////////////////////////////////////////////////

    /**
     * @deprecated 使用 FxAlerts
     */
    public static boolean showConfirmAlert(String message) {
        VBox vBox = new VBox(15);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(15, 15, 15, 15));
        Label textArea = new Label(message);
        textArea.setFont(Font.font(18));
        vBox.getChildren().add(textArea);
        Button button = new Button("确定");
        button.setFont(new Font(16));
        vBox.getChildren().add(button);
        Stage newStage = JavaFxViewUtil.getNewStageNull("提示", null, vBox, -1, -1, false, false, false);
        newStage.initModality(Modality.APPLICATION_MODAL);
        AtomicBoolean isOk = new AtomicBoolean(false);
        button.setOnMouseClicked(event -> {
            isOk.set(true);
            newStage.close();
        });
        newStage.showAndWait();
        return isOk.get();
    }
}
