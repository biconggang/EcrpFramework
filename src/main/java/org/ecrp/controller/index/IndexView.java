package org.ecrp.controller.index;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.web.WebView;

import java.util.ResourceBundle;


public abstract class IndexView implements Initializable {

    protected ResourceBundle bundle;

    @FXML
    protected Button myButton;

    @FXML
    protected CheckBox singleWindowBootCheckBox;

    @FXML
    protected TextField myTextField;

    @FXML
    protected TabPane tabPaneMain;

    @FXML
    protected MenuBar mainMenuBar;

    @FXML
    protected Menu fileMenu;

    @FXML
    protected Menu toolsMenu;

    @FXML
    protected Menu moreToolsMenu;

    @FXML
    protected Menu helpMenu;

    @FXML
    protected WebView tongjiWebView;

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public Button getMyButton() {
        return myButton;
    }

    public void setMyButton(Button myButton) {
        this.myButton = myButton;
    }

    public CheckBox getSingleWindowBootCheckBox() {
        return singleWindowBootCheckBox;
    }

    public void setSingleWindowBootCheckBox(CheckBox singleWindowBootCheckBox) {
        this.singleWindowBootCheckBox = singleWindowBootCheckBox;
    }

    public TextField getMyTextField() {
        return myTextField;
    }

    public void setMyTextField(TextField myTextField) {
        this.myTextField = myTextField;
    }

    public TabPane getTabPaneMain() {
        return tabPaneMain;
    }

    public void setTabPaneMain(TabPane tabPaneMain) {
        this.tabPaneMain = tabPaneMain;
    }

    public MenuBar getMainMenuBar() {
        return mainMenuBar;
    }

    public void setMainMenuBar(MenuBar mainMenuBar) {
        this.mainMenuBar = mainMenuBar;
    }

    public Menu getFileMenu() {
        return fileMenu;
    }

    public void setFileMenu(Menu fileMenu) {
        this.fileMenu = fileMenu;
    }

    public Menu getToolsMenu() {
        return toolsMenu;
    }

    public void setToolsMenu(Menu toolsMenu) {
        this.toolsMenu = toolsMenu;
    }

    public Menu getMoreToolsMenu() {
        return moreToolsMenu;
    }

    public void setMoreToolsMenu(Menu moreToolsMenu) {
        this.moreToolsMenu = moreToolsMenu;
    }

    public Menu getHelpMenu() {
        return helpMenu;
    }

    public void setHelpMenu(Menu helpMenu) {
        this.helpMenu = helpMenu;
    }

    public WebView getTongjiWebView() {
        return tongjiWebView;
    }

    public void setTongjiWebView(WebView tongjiWebView) {
        this.tongjiWebView = tongjiWebView;
    }
}
