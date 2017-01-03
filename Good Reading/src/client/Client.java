package client;
import ocsf.client.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import common.Message;
import controllers.AbstractController;
import controllers.LoginController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Client extends AbstractClient {

	public static Client instance;

	public Client(String host, int port) throws IOException {
		super(host, port);
		// TODO Auto-generated constructor stub
		instance=this;
		openConnection();
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		// TODO Auto-generated method stub
		Message m = new Message(0,msg);
		AbstractController.instance.handleMessage(m);
	}


	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}
}
