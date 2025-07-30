package com.gabriel.ecomms;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainAppController implements Initializable {

    @FXML
    private VBox navBarContainer;

    @FXML
    private StackPane contentArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Load navigation bar
            FXMLLoader navLoader = new FXMLLoader(getClass().getResource("nav-bar.fxml"));
            VBox navBar = navLoader.load();
            NavBarController navBarController = navLoader.getController(); // Get the controller
            navBarController.setMainAppController(this); // Pass this controller to the NavBarController
            navBarContainer.getChildren().add(navBar);

            // Load initial view (e.g., HomeView)
            loadView("home-view.fxml");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadView(String fxmlFileName) throws IOException {
        Parent view = FXMLLoader.load(getClass().getResource(fxmlFileName));
        view.getStylesheets().add(getClass().getResource("style.css").toExternalForm()); // Apply stylesheet
        contentArea.getChildren().setAll(view);
    }
}
