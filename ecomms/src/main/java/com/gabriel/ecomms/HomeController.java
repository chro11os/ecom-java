package com.gabriel.ecomms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import com.gabriel.ecomms.model.Ecommerce;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private GridPane popularItemsGrid;

    private ObservableList<Ecommerce> popularProducts;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadPopularProducts();
        displayPopularProducts();
    }

    private void loadPopularProducts() {
        // Load sample popular products (you can modify this to load from database later)
        popularProducts = FXCollections.observableArrayList();
        
        // Create sample products using the existing Ecommerce structure
        Ecommerce lip = new Ecommerce();
        lip.setName("Laneige Lip Sleeping Mask");
        lip.setDescription("Moisturizing lip mask for overnight use");
        lip.setPrice(24.99);
        lip.setImageUrl("https://via.placeholder.com/200x200/lip");

        Ecommerce bpshirt = new Ecommerce();
        bpshirt.setName("BlackPink T-Shirt");
        bpshirt.setDescription("Latest BlackPink T-Shirt");
        bpshirt.setPrice(699.99);
        bpshirt.setImageUrl("https://via.placeholder.com/200x200/phone");

        Ecommerce twiceshirt = new Ecommerce();
        twiceshirt.setName("Twice T-Shirt");
        twiceshirt.setDescription("Latest Twice T-Shirt");
        twiceshirt.setPrice(149.99);
        twiceshirt.setImageUrl("https://via.placeholder.com/200x200/coffee");

        Ecommerce pants = new Ecommerce();
        pants.setName("BlackPink Pants");
        pants.setDescription("Stylish BlackPink Pants");
        pants.setPrice(199.99);
        pants.setImageUrl("https://via.placeholder.com/200x200/pants");

        Ecommerce bplightstick = new Ecommerce();
        bplightstick.setName("BlackPink Lightstick");
        bplightstick.setDescription("Official BlackPink Lightstick");
        bplightstick.setPrice(89.99);
        bplightstick.setImageUrl("https://via.placeholder.com/200x200/shoes");

        Ecommerce btslightstick = new Ecommerce();
        btslightstick.setName("BTS Lightstick");
        btslightstick.setDescription("Official BTS Lightstick");
        btslightstick.setPrice(59.99);
        btslightstick.setImageUrl("https://via.placeholder.com/200x200/mouse");

        popularProducts.addAll(lip, bpshirt, twiceshirt, pants, bplightstick, btslightstick);
    }

    private void displayPopularProducts() {
        popularItemsGrid.getChildren().clear();
        
        int column = 0;
        int row = 0;
        int maxColumns = 3; // Display 3 products per row

        try {
            for (int i = 0; i < Math.min(6, popularProducts.size()); i++) { // Show max 6 popular items
                Ecommerce product = popularProducts.get(i);

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("product-card.fxml"));
                Node productCard = fxmlLoader.load();

                ProductCardController productCardController = fxmlLoader.getController();
                productCardController.setProduct(product);

                popularItemsGrid.add(productCard, column, row);

                column++;
                if (column == maxColumns) {
                    column = 0;
                    row++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
