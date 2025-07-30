package com.gabriel.ecomms;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class CartController implements Initializable {

    @FXML
    private TableView<?> cartTableView;
    @FXML
    private Label totalPriceLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Cart logic will go here
    }
}
