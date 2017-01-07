package controllers;

import java.io.IOException;

import boundary.ClientUI;
import client.Client;
import common.Message;
import graphics.GraphicsImporter;
import i_book.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;

public abstract class SystemController extends AbstractController {
	
	@FXML
	private AnchorPane toggleMenuAnchor;
	@FXML
	private AnchorPane menuAnchor;
	@FXML
	private Label arrowLabel;
	@FXML
	private Label userLabel;
	@FXML
	private ImageView goBackImage;
	@FXML
	private AnchorPane searchBookButton;
	@FXML
	private AnchorPane searchReviewButton;
	@FXML
	private AnchorPane membershipButton;
	@FXML
	private ScrollPane scrollPane;

	public void initialize() {
		super.initialize();
		userLabel.setText(ClientUI.user.getFname() + " " + ClientUI.user.getLname());
	}

	public void goBackOnClick() {
		ClientUI.goBack();
	}

	public void goBackOnHover() {
		goBackImage.setImage(new Image(GraphicsImporter.class.getResource("button_hover.png").toString()));
	}

	public void goBackOffHover() {
		goBackImage.setImage(new Image(GraphicsImporter.class.getResource("orange_button.png").toString()));
	}

	public void searchBookOnClick() {
		ClientUI.setScene("SearchBookGUI.fxml");
	}

	public void searchReviewOnClick() {
		System.out.println("review");
	}

	public void membershipOnClick() {
		System.out.println("membership");
	}

	public void toggleMenuOnHover() {
		arrowLabel.setVisible(false);
		toggleMenuAnchor.setVisible(true);
		if (menuAnchor.getLayoutX() < 0) {
			menuAnchor.setLayoutX(menuAnchor.getLayoutX() + 15);
		}
	}

	public void searchBookOnHover() {
		if (searchBookButton.getLayoutX() < 20) {
			searchBookButton.setLayoutX(searchBookButton.getLayoutX() + 5);
		}
	}

	public void searchBookOffHover() {
		searchBookButton.setLayoutX(0);
	}

	public void searchReviewOnHover() {
		if (searchReviewButton.getLayoutX() < 20) {
			searchReviewButton.setLayoutX(searchReviewButton.getLayoutX() + 5);
		}
	}

	public void searchReviewOffHover() {
		searchReviewButton.setLayoutX(0);
	}

	public void membershipOnHover() {
		if (membershipButton.getLayoutX() < 20) {
			membershipButton.setLayoutX(membershipButton.getLayoutX() + 5);
		}
	}

	public void membershipOffHover() {
		membershipButton.setLayoutX(0);
	}

	public void toggleMenuOffHover() {
		// scrollPane.setLayoutX(0);
		toggleMenuAnchor.setVisible(false);
		menuAnchor.setLayoutX(-175);
		arrowLabel.setVisible(true);
	}

	public void logoutOnClick() throws IOException {
		ClientUI.setScene("LoginGUI.fxml");
		if (ClientUI.user instanceof User) {
			Message msg = new Message("system", 1, ClientUI.user);
			Client.instance.sendToServer(msg);
		}
	}

}
