package com.myapp.controller.syssetting;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;

/**
 * @ClassName: SystemSettingView
 * @Description: 设置页面
 * @author: xufeng
 * @date: 2020/2/25 0025 16:44
 */
public abstract class SystemSettingView implements Initializable {

    @FXML
    protected CheckBox exitShowAlertCheckBox;

    @FXML
    protected CheckBox addNotepadCheckBox;

    @FXML
    protected CheckBox saveStageBoundCheckBox;

    @FXML
    protected CheckBox chkNewLauncher;

    @FXML
    protected Button saveButton;

    @FXML
    protected Button cancelButton;

    public CheckBox getExitShowAlertCheckBox() {
        return exitShowAlertCheckBox;
    }

    public void setExitShowAlertCheckBox(CheckBox exitShowAlertCheckBox) {
        this.exitShowAlertCheckBox = exitShowAlertCheckBox;
    }

    public CheckBox getAddNotepadCheckBox() {
        return addNotepadCheckBox;
    }

    public void setAddNotepadCheckBox(CheckBox addNotepadCheckBox) {
        this.addNotepadCheckBox = addNotepadCheckBox;
    }

    public CheckBox getSaveStageBoundCheckBox() {
        return saveStageBoundCheckBox;
    }

    public void setSaveStageBoundCheckBox(CheckBox saveStageBoundCheckBox) {
        this.saveStageBoundCheckBox = saveStageBoundCheckBox;
    }

    public CheckBox getChkNewLauncher() {
        return chkNewLauncher;
    }

    public void setChkNewLauncher(CheckBox chkNewLauncher) {
        this.chkNewLauncher = chkNewLauncher;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(Button saveButton) {
        this.saveButton = saveButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(Button cancelButton) {
        this.cancelButton = cancelButton;
    }
}
