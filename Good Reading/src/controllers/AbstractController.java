package controllers;

import java.io.Serializable;

import boundary.ClientUI;
import common.Message;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public abstract class AbstractController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static AbstractController instance;
	
	private Button goBackButton;
	
	public void goBackOnClick(){
		ClientUI.goBack();
	}
	

	public abstract void handleMessage(Message msg);

	public String toString(){
		return "Abstract Controller";
	}

}
