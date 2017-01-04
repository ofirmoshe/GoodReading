package controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import i_book.GeneralUser;
import i_book.IBookIncPersistentManager;
import org.orm.*;

import boundary.ClientUI;
import client.Client;
import common.Message;

import java.io.IOException;
import java.io.Serializable;

import org.hibernate.*;

public class LoginController extends AbstractController{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static LoginController instance;
	final public static int DEFAULT_PORT = 5555;
	
	@FXML public TextField hostField;
	@FXML public Button loginButton;
	@FXML public TextField idField;
	@FXML public PasswordField passField;
	@FXML public ImageView idx;
	@FXML public ImageView passx;
	
	public LoginController(){
		instance=this;
	}
	
	public void loginOnClick(ActionEvent event) throws Exception{
		Client client;
		String host = hostField.getText();
		String id = idField.getText();
		String pass = passField.getText();
		GeneralUser u = new GeneralUser();
		if(Client.instance == null){
			client=new Client(host,DEFAULT_PORT);
			Client.instance=client;
		}
		else{
			client = Client.instance;
		}
		u.setID(id);
		u.setPassword(pass);  
		Message msg =  new Message(1,u);
		AbstractController.instance = this;
		client.sendToServer(msg);
	}

	@Override
	public void handleMessage(Message msg) {
		// TODO Auto-generated method stub
		if(msg.getMsg().equals("wrong username")){
			try {
				Client.instance.closeConnection();
				Client.instance=new Client(hostField.getText(),DEFAULT_PORT);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			idx.setVisible(true);
		}
		else if(msg.getMsg().equals("wrong password")) passx.setVisible(true);
		else ClientUI.setScene("userHomepage.fxml");
	}
	
	@Override
	public String toString(){
		return "Login Controller";
	}
	
	
	
	

	

}
