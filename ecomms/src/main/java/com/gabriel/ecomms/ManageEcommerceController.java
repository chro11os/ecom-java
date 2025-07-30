package com.gabriel.ecomms;

import com.gabriel.ecomms.model.Ecommerce;
import com.gabriel.ecomms.service.EcommerceService;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lombok.Setter;

import java.io.IOException;

public class ManageEcommerceController extends GenericEcommerceController {

    @Setter
    Stage stage;

    @Setter
    Scene createViewScene;

    @Setter
    Scene editViewScene;

    @Setter
    Scene deleteViewScene;

    public ImageView ecommerceImage;
    @FXML
    public Button btnCreate;

    @FXML
    public Button btnEdit;

    @FXML
    public Button btnDelete;

    @FXML
    public Button btnClose;

    @FXML
    private ListView<Ecommerce> lvEcommerces;

    public void refresh() {
        Ecommerce[] ecommerces = EcommerceService.getService().getAll();
        lvEcommerces.getItems().clear();
        lvEcommerces.getItems().addAll(ecommerces);
        enableFields(false);
    }

    @Override
    public void init() {
        try {
            refresh();
            setupAnimations();
        } catch (Exception e) {
            showErrorDialog("Message: ", e.getMessage());
        }
    }

    private void setupAnimations() {
        // Product card hover animation
        lvEcommerces.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Ecommerce item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getName());
                    setOnMouseEntered(event -> {
                        setScaleX(1.02);
                        setScaleY(1.02);
                        setEffect(new DropShadow(10, Color.rgb(0, 0, 0, 0.2)));
                    });
                    setOnMouseExited(event -> {
                        setScaleX(1.0);
                        setScaleY(1.0);
                        setEffect(null);
                    });
                }
            }
        });
    }

    public void onAction(MouseEvent mouseEvent) {
        GenericEcommerceController.selectedItem = lvEcommerces.getSelectionModel().getSelectedItem();
        if (GenericEcommerceController.selectedItem == null) {
            return;
        }
        setFields("Manage");
        if (selectedItem.getImageUrl() != null && !selectedItem.getImageUrl().isEmpty()) {
            ecommerceImage.setImage(new javafx.scene.image.Image(selectedItem.getImageUrl()));
        }
    }

    public void onCreate(ActionEvent actionEvent) throws Exception {
        transitionToScene(actionEvent, "create-ecom-view.fxml", "Create Ecommerce", createViewScene);
    }

    public void onEdit(ActionEvent actionEvent) throws Exception {
        if (GenericEcommerceController.selectedItem == null) {
            showErrorDialog("Please select an ecommerce from the list", "Cannot edit");
            return;
        }
        transitionToScene(actionEvent, "edit-ecom-view.fxml", "Edit Ecommerce", editViewScene);
    }

    public void onDelete(ActionEvent actionEvent) throws Exception {
        if (GenericEcommerceController.selectedItem == null) {
            showErrorDialog("Please select an ecommerce from the list", "Cannot delete");
            return;
        }
        transitionToScene(actionEvent, "delete-ecom-view.fxml", "Delete Ecommerce", deleteViewScene);
    }

    private void transitionToScene(ActionEvent actionEvent, String fxmlFile, String title, Scene scene) throws Exception {
        final Scene targetScene = scene;
        Node source = (Node) actionEvent.getSource();
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.2), source.getScene().getRoot());
        fadeOut.setToValue(0);
        fadeOut.setOnFinished(e -> {
            try {
                Scene newScene = targetScene;
                if (newScene == null) {
                    FXMLLoader fxmlLoader = new FXMLLoader(ManageEcommerceJFXApp.class.getResource(fxmlFile));
                    Parent root = fxmlLoader.load();
                    GenericEcommerceController controller = fxmlLoader.getController();
                    controller.setStage(stage);
                    newScene = new Scene(root, 800, 600);
                    newScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
                    controller.setManageEcommerceController(this);
                    controller.setManageScene(manageScene);
                    controller.setSplashScene(splashScene);
                }

                stage.setTitle(title);
                stage.setScene(newScene);

                Parent root = newScene.getRoot();
                root.setOpacity(0);
                FadeTransition fadeIn = new FadeTransition(Duration.seconds(0.2), root);
                fadeIn.setToValue(1);
                fadeIn.play();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        fadeOut.play();
    }

    public void onClose(ActionEvent actionEvent) {
        super.onClose(actionEvent);
    }
}