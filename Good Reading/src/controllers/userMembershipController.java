package controllers;

import java.io.IOException;

import boundary.ClientUI;
import client.Client;
import common.Message;
import i_book.Membership;
import i_book.User_Membership;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class userMembershipController extends SystemController {

	@FXML
	private Label nameLabel;
	@FXML
	private Label startDateLabel;
	@FXML
	private Label endDateLabel;

	public void initialize() {
		super.initialize();
		Message msg = new Message("user membership", 1, ClientUI.user.getID());
		try {
			Client.instance.sendToServer(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Override
	public void goBackOnClick() {
		ClientUI.setScene("UserHomepageGUI.fxml");
	}
	
	@Override
	public void handleMessage(Message msg) {
		User_Membership um=(User_Membership)msg.getMsg();
		Platform.runLater(new Runnable(){
			@Override
			public void run()
			{
				nameLabel.setText(ClientUI.user.getFname()+" "+ClientUI.user.getLname()+",");
				startDateLabel.setText("Since: "+um.getS_date().toString());
				endDateLabel.setText("Until: "+um.getE_date().toString());
			}
		});
	}

}
