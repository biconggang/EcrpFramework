package com.myapp;

import javafx.fxml.FXML;

import java.io.IOException;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        Main.setRoot("secondary");
    }
}
