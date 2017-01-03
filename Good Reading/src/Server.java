import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.cfg.JDBCConnectionSetting;

import common.Message;
import i_book.GeneralUser;
import i_book.IBookIncPersistentManager;
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
		Message m = (Message)msg;
		if(m.getFunc()==1){
			GeneralUser login =(GeneralUser) m.getMsg();
			GeneralUser u = login;
			try {
				u = GeneralUser.loadGeneralUserByORMID(session,login.getID());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				sendToAllClients("wrong username");
			}
			if(u.getPassword().equals(login.getPassword())){
				sendToAllClients(u.getFname());
				return;
			}
			sendToAllClients("wrong password");
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
