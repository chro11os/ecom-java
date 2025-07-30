package com.gabriel.ecomms;

import com.gabriel.ecomms.model.Ecommerce;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductDetailController implements Initializable {

    @FXML
    private ImageView mainProductImage;
    @FXML
    private HBox imageGalleryHBox;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label artistLabel;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private ComboBox<String> sizeComboBox;
    @FXML
    private Label priceLabel;
    @FXML
    private Button addToCartButton;

    private Ecommerce product;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // This controller will be populated when a product is selected
    }

    public void setProduct(Ecommerce product) {
        this.product = product;
        productNameLabel.setText(product.getName());
        artistLabel.setText(product.getProductName()); // Assuming product name is artist for now
        descriptionTextArea.setText(product.getDescription());
        priceLabel.setText(String.format("$%.2f", product.getPrice()));

        if (product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
            try {
                mainProductImage.setImage(new Image(product.getImageUrl()));
            } catch (Exception e) {
                System.err.println("Error loading image: " + product.getImageUrl() + " - " + e.getMessage());
                mainProductImage.setImage(new Image(getClass().getResourceAsStream("placeholder.png")));
            }
        } else {
            mainProductImage.setImage(new Image(getClass().getResourceAsStream("placeholder.png")));
        }

        // Populate size/options if available in the product model
        // For now, just a placeholder
        sizeComboBox.getItems().addAll("Small", "Medium", "Large");
        sizeComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleAddToCart() {
        System.out.println("Added to cart: " + product.getName());
        // Implement add to cart animation and logic here
    }
}
