package controllers;

import boundary.ClientUI;
import graphics.GraphicsImporter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public abstract class SystemController extends AbstractController{
	
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
	
	public void initialize(){
		userLabel.setText(ClientUI.user.getFname() + " " + ClientUI.user.getLname());
	}
	
	public void goBackOnClick(){
		ClientUI.goBack();
	}
	
	public void goBackOnHover(){
		goBackImage.setImage(new Image(GraphicsImporter.class.getResource("button_hover.png").toString()));
	}
	
	public void goBackOffHover(){
		goBackImage.setImage(new Image(GraphicsImporter.class.getResource("button.png").toString()));
	}
	
	public void searchBookOnClick() {
		System.out.println("book");
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
	
	public void searchBookOnHover(){
		if (searchBookButton.getLayoutX() < 20) {
			searchBookButton.setLayoutX(searchBookButton.getLayoutX() + 5);
		}
	}
	
	public void searchBookOffHover(){
		searchBookButton.setLayoutX(0);
	}
	
	public void searchReviewOnHover(){
		if (searchReviewButton.getLayoutX() < 20) {
			searchReviewButton.setLayoutX(searchReviewButton.getLayoutX() + 5);
		}
	}
	
	public void searchReviewOffHover(){
		searchReviewButton.setLayoutX(0);
	}
	
	public void membershipOnHover(){
		if (membershipButton.getLayoutX() < 20) {
			membershipButton.setLayoutX(membershipButton.getLayoutX() + 5);
		}
	}
	
	public void membershipOffHover(){
		membershipButton.setLayoutX(0);
	}

	public void toggleMenuOffHover() {
		// scrollPane.setLayoutX(0);
		toggleMenuAnchor.setVisible(false);
		menuAnchor.setLayoutX(-175);
		arrowLabel.setVisible(true);
	}
	
	public void logoutOnClick(){
		ClientUI.setScene("LoginGUI.fxml");
	}

	

}
