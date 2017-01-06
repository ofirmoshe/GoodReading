package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.PersistentTransaction;
import org.orm.cfg.JDBCConnectionSetting;

import common.Message;
import i_book.Book;
import i_book.GeneralUser;
import i_book.IBookIncPersistentManager;
import i_book.User;
import ocsf.server.*;

public class Server extends AbstractServer {

	final public static int DEFAULT_PORT = 5555;
	static PersistentSession session;

	public Server(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		// TODO Auto-generated method stub
		Message m = (Message) msg;
		switch (m.getCont()) {
		case "login":
			loginMessageHandler(m);
			break;
		case "user homepage":
			userHomepageMessageHandler(m);
			break;
		}
	}

	public void loginMessageHandler(Message msg){
		Object[] a = new Object[2];
		if (msg.getFunc() == 1) {
			try {
				Book[] books = Book.listBookByQuery("ID>0", "ID");
				a[0] = books;
			} catch (PersistentException e1) {
				System.out.println("Can't load book list.");
			}
		}
		msg.setFunc(1);
		GeneralUser login = (GeneralUser) msg.getMsg();
		GeneralUser u = null;
		try {
			u = GeneralUser.loadGeneralUserByORMID(session, login.getID());
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			a[1] = "wrong username";
			msg.setMsg(a);
			sendToAllClients(msg);
			return;
		}
		if (u.getPassword().equals(login.getPassword())) {
			a[1] = u;
			msg.setMsg(a);
			sendToAllClients(msg);
			if(u instanceof User){
				User user = (User)u;
				if(user.getStatus().equals("offline") || user.getStatus()==null){
					user.setStatus("online");
					try {
						PersistentTransaction t = session.beginTransaction();
						session.update(user);
					} catch (PersistentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return;
		}
		a[1] = "wrong password";
		msg.setMsg(a);
		sendToAllClients(msg);

	}

	public void userHomepageMessageHandler(Message msg) {
		switch (msg.getFunc()) {
		case 1:
		}
	}

	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
	}

	protected void serverStopped() {
		try {
			session.close();
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Server has stopped listening for connections.");
	}

	public static void main(String[] args) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 5555
		}

		Server sv = new Server(port);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}

		try {
			session = IBookIncPersistentManager.instance().getSession();
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
