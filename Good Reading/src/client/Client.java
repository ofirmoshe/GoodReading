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
	public static String host;
	public static final int DEFAULT_PORT =5555;

	public Client(String host, int port) throws IOException {
		super(host, port);
		instance=this;
		Client.host=host;
		openConnection();
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		AbstractController.instance.handleMessage((Message)msg);
	}
	
	public static void refresh(){
		try {
			new Client(Client.host,DEFAULT_PORT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}
}
