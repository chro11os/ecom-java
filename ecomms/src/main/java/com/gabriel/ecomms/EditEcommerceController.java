package com.gabriel.ecomms;
import com.gabriel.ecomms.model.Ecommerce;
import com.gabriel.ecomms.service.EcommerceService;
import com.gabriel.ecomms.model.Product;
import com.gabriel.ecomms.service.ProductService;
import com.gabriel.ecomms.model.Category;
import com.gabriel.ecomms.service.CategoryService;
import com.gabriel.ecomms.model.Status;
import com.gabriel.ecomms.service.StatusService;
import com.gabriel.ecomms.model.Inventory;
import com.gabriel.ecomms.service.InventoryService;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Window;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import java.net.URL;
import java.util.ResourceBundle;
import lombok.Setter;

public class EditEcommerceController extends GenericEcommerceController {
	public ImageView imgEcommerce;
	@Override
	public void init() {
		setFields("Edit");
		enableFields(true);
	}
	public void onSubmit(ActionEvent actionEvent) {
		try {
			Ecommerce ecommerce = toObject(true);
			Ecommerce newEcommerce = EcommerceService.getService().update(ecommerce);
			Node node = ((Node) (actionEvent.getSource()));
			Window window = node.getScene().getWindow();
			window.hide();
			stage.setTitle("Manage Ecommerce");
			stage.setScene(manageScene);
			stage.show();
		}
		catch (Exception e){
			showErrorDialog("Error encountered creating ecommerce", e.getMessage());
		}
	}
	public void onClose(ActionEvent actionEvent) {
		super.onClose(actionEvent);
	}
}
