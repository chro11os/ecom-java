package com.gabriel.ecomms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NavBarController implements Initializable {

    @FXML
    private Button homeButton;
    @FXML
    private Button shopButton;
    @FXML
    private Button cartButton;
    @FXML
    private Button adminButton;

    private MainAppController mainAppController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // This controller will be initialized by MainAppController, which will pass itself.
        // We can't directly get the MainAppController here, so we'll rely on a setter.
    }

    public void setMainAppController(MainAppController mainAppController) {
        this.mainAppController = mainAppController;
    }

    @FXML
    private void handleHomeClick(ActionEvent event) throws IOException {
        if (mainAppController != null) {
            mainAppController.loadView("home-view.fxml");
        }
    }

    @FXML
    private void handleShopClick(ActionEvent event) throws IOException {
        if (mainAppController != null) {
            mainAppController.loadView("product-browse-view.fxml");
        }
    }

    @FXML
    private void handleCartClick(ActionEvent event) throws IOException {
        if (mainAppController != null) {
            mainAppController.loadView("cart-view.fxml");
        }
    }

    @FXML
    private void handleAdminClick(ActionEvent event) throws IOException {
        if (mainAppController != null) {
            mainAppController.loadView("admin-view.fxml");
        }
    }
}
