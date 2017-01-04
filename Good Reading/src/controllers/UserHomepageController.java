package controllers;

import boundary.ClientUI;
import common.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class UserHomepageController extends AbstractController{

	@FXML private AnchorPane searchBookButton;
	@FXML private AnchorPane searchReviewButton;
	@FXML private AnchorPane membershipButton;
	@FXML private Label userLabel;
	@FXML private AnchorPane menuAnchor;
	@FXML private AnchorPane toggleMenuAnchor;
	@FXML private AnchorPane searchBookClick;
	@FXML private AnchorPane searchReviewClick;
	@FXML private AnchorPane membershipClick;
	
	@FXML
	public void initialize(){
		userLabel.setText(ClientUI.user.getFname()+ " " + ClientUI.user.getLname());
	}
	public void searchBookOnClick()
	{
		System.out.println("book");
	}
	
	public void searchReviewOnClick()
	{
		System.out.println("review");
	}
	
	public void membershipOnClick()
	{
		System.out.println("membership");
	}
	public void toggleMenuOnHover(){
		toggleMenuAnchor.setScaleX(5);
		if(menuAnchor.getLayoutX()<0){
			menuAnchor.setLayoutX(menuAnchor.getLayoutX()+10);
		}
		searchBookClick.setVisible(true);
		searchReviewClick.setVisible(true);
		membershipClick.setVisible(true);
		
	}
	
	public void toggleMenuOffHover(){
		toggleMenuAnchor.setScaleX(1);
		menuAnchor.setLayoutX(-175);
		searchBookClick.setVisible(false);
		searchReviewClick.setVisible(false);
		membershipClick.setVisible(false);
	}
	
	
	
	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		
	}

}
