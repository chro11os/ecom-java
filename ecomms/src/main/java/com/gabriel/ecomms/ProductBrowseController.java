package com.gabriel.ecomms;

import com.gabriel.ecomms.model.Category;
import com.gabriel.ecomms.model.Ecommerce;
import com.gabriel.ecomms.model.Product;
import com.gabriel.ecomms.service.CategoryService;
import com.gabriel.ecomms.service.EcommerceService;
import com.gabriel.ecomms.service.ProductService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ProductBrowseController implements Initializable {

    @FXML
    private ComboBox<String> categoryFilterComboBox;
    @FXML
    private ComboBox<String> artistFilterComboBox;
    @FXML
    private TextField searchField;
    @FXML
    private GridPane productGrid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        populateFilters();
        populateProductGrid();
    }

    private void populateFilters() {
        // Populate Category Filter
        Category[] categories = CategoryService.getService().getAll();
        if (categories != null) {
            categoryFilterComboBox.getItems().add("All Categories");
            categoryFilterComboBox.getItems().addAll(Arrays.stream(categories)
                    .map(Category::getName)
                    .collect(Collectors.toList()));
        }
        categoryFilterComboBox.getSelectionModel().selectFirst();

        // Populate Artist Filter (using Product names as a proxy for artists for now)
        Product[] products = ProductService.getService().getAll();
        if (products != null) {
            artistFilterComboBox.getItems().add("All Artists");
            artistFilterComboBox.getItems().addAll(Arrays.stream(products)
                    .map(Product::getName) // Assuming product name can represent artist for simplicity
                    .distinct()
                    .collect(Collectors.toList()));
        }
        artistFilterComboBox.getSelectionModel().selectFirst();

        // Add listeners for filtering
        categoryFilterComboBox.setOnAction(event -> filterProducts());
        artistFilterComboBox.setOnAction(event -> filterProducts());
        searchField.textProperty().addListener((obs, oldText, newText) -> filterProducts());
    }

    private void populateProductGrid() {
        productGrid.getChildren().clear();
        Ecommerce[] allProducts = EcommerceService.getService().getAll();

        if (allProducts != null) {
            int row = 0;
            int col = 0;
            for (Ecommerce product : allProducts) {
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("product-card.fxml"));
                    VBox productCard = fxmlLoader.load();
                    ProductCardController cardController = fxmlLoader.getController();
                    cardController.setProduct(product);

                    productGrid.add(productCard, col, row);
                    col++;
                    if (col == 3) { // 3 columns per row
                        col = 0;
                        row++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void filterProducts() {
        // This is a simplified filter. In a real app, you'd send these to the backend.
        String selectedCategory = categoryFilterComboBox.getSelectionModel().getSelectedItem();
        String selectedArtist = artistFilterComboBox.getSelectionModel().getSelectedItem();
        String searchText = searchField.getText().toLowerCase();

        Ecommerce[] allProducts = EcommerceService.getService().getAll();
        productGrid.getChildren().clear();

        if (allProducts != null) {
            int row = 0;
            int col = 0;
            for (Ecommerce product : allProducts) {
                boolean matchesCategory = (selectedCategory == null || selectedCategory.equals("All Categories") || product.getCategoryName().equals(selectedCategory));
                boolean matchesArtist = (selectedArtist == null || selectedArtist.equals("All Artists") || product.getProductName().contains(selectedArtist)); // Simplified artist match
                boolean matchesSearch = (searchText.isEmpty() || product.getName().toLowerCase().contains(searchText) || product.getDescription().toLowerCase().contains(searchText));

                if (matchesCategory && matchesArtist && matchesSearch) {
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("product-card.fxml"));
                        VBox productCard = fxmlLoader.load();
                        ProductCardController cardController = fxmlLoader.getController();
                        cardController.setProduct(product);

                        productGrid.add(productCard, col, row);
                        col++;
                        if (col == 3) {
                            col = 0;
                            row++;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
