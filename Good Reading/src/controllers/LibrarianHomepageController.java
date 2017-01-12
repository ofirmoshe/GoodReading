package controllers;

import boundary.ClientUI;
import common.Message;
import i_book.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class LibrarianHomepageController extends SystemController {

	@FXML
	private Button addUserButton;
	@FXML
	private Label posLabel;

	public void initialize() {
		super.initialize();
		Employee emp = (Employee) ClientUI.user;
		if (emp.getPosition().equals("Librarian")) {
			addUserButton.setVisible(true);
		}else{
			posLabel.setText("Library Manager");
		}
	}

	public void addBookOnClick() {
		ClientUI.setScene("AddNewBookGUI.fxml");
	}

	public void inventoryOnClick() {
		ClientUI.setScene("InventoryManagementGUI.fxml");
	}

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub

	}

}
