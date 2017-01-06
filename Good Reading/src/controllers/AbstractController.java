package controllers;

import java.io.Serializable;

import boundary.ClientUI;
import common.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public abstract class AbstractController{
	
	public static AbstractController instance;
	
	public void initialize(){
		instance=this;
	}

	public abstract void handleMessage(Message msg);

	public String toString(){
		return "Abstract Controller";
	}

}
