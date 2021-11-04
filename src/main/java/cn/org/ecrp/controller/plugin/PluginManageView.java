package cn.org.ecrp.controller.plugin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.Map;

/**
 * @ClassName: PluginManageView
 * @Description: 插件管理
 * @author: xufeng
 * @date: 2020/1/19 17:41
 */

public abstract class PluginManageView implements Initializable {

    @FXML
    protected TextField selectPluginTextField;

    @FXML
    protected Button selectPluginButton;

    @FXML
    protected Button addLocalPluginButton;

    @FXML
    protected TableView<Map<String, String>> pluginDataTableView;

    @FXML
    protected TableColumn<Map<String, String>, String> nameTableColumn;

    @FXML
    protected TableColumn<Map<String, String>, String> synopsisTableColumn;

    @FXML
    protected TableColumn<Map<String, String>, String> versionTableColumn;

    @FXML
    protected TableColumn<Map<String, String>, String> isDownloadTableColumn;

    @FXML
    protected TableColumn<Map<String, String>, String> isEnableTableColumn;

    @FXML
    protected TableColumn<Map<String, String>, String> downloadTableColumn;

    public TextField getSelectPluginTextField() {
        return selectPluginTextField;
    }

    public void setSelectPluginTextField(TextField selectPluginTextField) {
        this.selectPluginTextField = selectPluginTextField;
    }

    public Button getSelectPluginButton() {
        return selectPluginButton;
    }

    public void setSelectPluginButton(Button selectPluginButton) {
        this.selectPluginButton = selectPluginButton;
    }

    public Button getAddLocalPluginButton() {
        return addLocalPluginButton;
    }

    public void setAddLocalPluginButton(Button addLocalPluginButton) {
        this.addLocalPluginButton = addLocalPluginButton;
    }

    public TableView<Map<String, String>> getPluginDataTableView() {
        return pluginDataTableView;
    }

    public void setPluginDataTableView(TableView<Map<String, String>> pluginDataTableView) {
        this.pluginDataTableView = pluginDataTableView;
    }

    public TableColumn<Map<String, String>, String> getNameTableColumn() {
        return nameTableColumn;
    }

    public void setNameTableColumn(TableColumn<Map<String, String>, String> nameTableColumn) {
        this.nameTableColumn = nameTableColumn;
    }

    public TableColumn<Map<String, String>, String> getSynopsisTableColumn() {
        return synopsisTableColumn;
    }

    public void setSynopsisTableColumn(TableColumn<Map<String, String>, String> synopsisTableColumn) {
        this.synopsisTableColumn = synopsisTableColumn;
    }

    public TableColumn<Map<String, String>, String> getVersionTableColumn() {
        return versionTableColumn;
    }

    public void setVersionTableColumn(TableColumn<Map<String, String>, String> versionTableColumn) {
        this.versionTableColumn = versionTableColumn;
    }

    public TableColumn<Map<String, String>, String> getIsDownloadTableColumn() {
        return isDownloadTableColumn;
    }

    public void setIsDownloadTableColumn(TableColumn<Map<String, String>, String> isDownloadTableColumn) {
        this.isDownloadTableColumn = isDownloadTableColumn;
    }

    public TableColumn<Map<String, String>, String> getIsEnableTableColumn() {
        return isEnableTableColumn;
    }

    public void setIsEnableTableColumn(TableColumn<Map<String, String>, String> isEnableTableColumn) {
        this.isEnableTableColumn = isEnableTableColumn;
    }

    public TableColumn<Map<String, String>, String> getDownloadTableColumn() {
        return downloadTableColumn;
    }

    public void setDownloadTableColumn(TableColumn<Map<String, String>, String> downloadTableColumn) {
        this.downloadTableColumn = downloadTableColumn;
    }
}
