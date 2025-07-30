package com.gabriel.ecomms;

import com.gabriel.ecomms.model.Ecommerce;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;

public class ProductCardController implements Initializable {

    @FXML
    private ImageView productImage;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label productPriceLabel;

    private Ecommerce product;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization logic if needed
    }

    public void setProduct(Ecommerce product) {
        this.product = product;
        productNameLabel.setText(product.getName());
        productPriceLabel.setText(String.format("$%.2f", product.getPrice()));
        if (product.getImageUrl() != null && !product.getImageUrl().isEmpty()) {
            try {
                productImage.setImage(new Image(product.getImageUrl()));
            } catch (Exception e) {
                System.err.println("Error loading image: " + product.getImageUrl() + " - " + e.getMessage());
                // Set a placeholder image if loading fails
                productImage.setImage(new Image(getClass().getResourceAsStream("placeholder.png")));
            }
        } else {
            // Set a placeholder image if no URL is provided
            productImage.setImage(new Image(getClass().getResourceAsStream("placeholder.png")));
        }
    }
}
