package com.gabriel.ecomms;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {

    @FXML
    private ListView<String> tableListView;
    @FXML
    private Label selectedTableLabel;
    @FXML
    private TableView<?> dataTableView;
    @FXML
    private TextField descriptionTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Populate the list of tables
        tableListView.getItems().addAll("Categories", "Artists", "Brands", "Statuses", "Inventories");

        // Add listener to handle table selection
        tableListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            selectedTableLabel.setText("Selected Table: " + newVal);
            // In a real application, you would load data for the selected table here
            // For now, it's just a placeholder.
        });
    }
}
