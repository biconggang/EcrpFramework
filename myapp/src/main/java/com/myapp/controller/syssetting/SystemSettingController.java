package com.myapp.controller.syssetting;

import javafx.stage.Stage;
import org.ecrp.framework.utils.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ClassName: SystemSettingController
 * @Description: 设置页面
 * @author: xufeng
 * @date: 2020/2/25 0025 16:44
 */

public class SystemSettingController extends SystemSettingView {

    final static Logger logger = LoggerFactory.getLogger("SystemSettingController");

    private Stage newStage = null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initView();
    }

    private void initView() {
        try {
            exitShowAlertCheckBox.setSelected(Config.getBoolean(Config.Keys.ConfirmExit, true));
            addNotepadCheckBox.setSelected(Config.getBoolean(Config.Keys.NotepadEnabled, true));
            saveStageBoundCheckBox.setSelected(Config.getBoolean(Config.Keys.RememberWindowLocation, true));
            chkNewLauncher.setSelected(Config.getBoolean(Config.Keys.NewLauncher, false));
        } catch (Exception e) {
            logger.error("加载配置失败：", e);
        }
    }

    public void applySettings() {
        try {
            Config.set(Config.Keys.ConfirmExit, exitShowAlertCheckBox.isSelected());
            Config.set(Config.Keys.NotepadEnabled, addNotepadCheckBox.isSelected());
            Config.set(Config.Keys.RememberWindowLocation, saveStageBoundCheckBox.isSelected());
            Config.set(Config.Keys.NewLauncher, chkNewLauncher.isSelected());

            if (newStage != null) {
                newStage.close();
            }
        } catch (Exception e) {
            logger.error("保存配置失败：", e);
        }
    }
}
