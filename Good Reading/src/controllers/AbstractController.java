package controllers;

import java.io.Serializable;

import boundary.ClientUI;
import common.Message;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * Abstract controller is an abstract class and the parent of all controllers.
 * 
 * @author guyzi
 *
 */

public abstract class AbstractController {

	public static AbstractController instance;

	/**
	 * This method saves the current controller when initialized.
	 * The extending controllers call this initializer and updates the current controller instance to be itself.
	 * The Client class uses that instance variable at handleMessageFromServer method, and routing the message to its correct destination.
	 */
	public void initialize() {
		instance = this;
	}

	/**
	 * This method is implemented differently at different controllers.
	 * 
	 * @param msg
	 *            the message received from server, transported by the current
	 *            client instance.
	 */
	public abstract void handleMessage(Message msg);

	/**
	 * This method is overriding "toString" method.
	 */
	public String toString() {
		return "Abstract Controller";
	}

}
