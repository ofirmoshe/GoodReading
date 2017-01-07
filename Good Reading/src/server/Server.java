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
import i_book.Author;
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
		case "system":
			systemMessageHandler(m);
			break;
		case "book page":
			bookPageMessageHandler(m);
			break;
		case "search book":
			searchBookMessageHandler(m);
		}
	}

	public void systemMessageHandler(Message msg) {
		switch (msg.getFunc()) {
		case 1:
			User u = (User) msg.getMsg();
			u.setStatus("offline");
			try {
				PersistentTransaction t = session.beginTransaction();
				session.update(u);
				t.commit();
				t = session.beginTransaction();
				session.evict(u);
				t.commit();
				session = IBookIncPersistentManager.instance().getSession();
			} catch (PersistentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	public void loginMessageHandler(Message msg) {
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
			u = GeneralUser.loadGeneralUserByORMID(session,login.getID());
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
			if (u instanceof User) {
				User user = (User) u;
				if (user.getStatus().equals("offline")) {
					user.setStatus("online");
					try {
						PersistentTransaction t = session.beginTransaction();
						session.update(user);
						t.commit();
						t = session.beginTransaction();
						session.evict(user);
						t.commit();
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

	public void bookPageMessageHandler(Message msg) {
		switch (msg.getFunc()) {
		case 1:
			try {
				Book b = Book.getBookByORMID((int) msg.getMsg());
				Author[] a = b.author.toArray();
				msg.setMsg(a);
				sendToAllClients(msg);
			} catch (PersistentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void searchBookMessageHandler(Message msg) {
		switch (msg.getFunc()) {
		case 1:
			String[] query = (String[]) msg.getMsg();
			int[] counter = new int[Integer.parseInt(query[1])+1];
			ArrayList<Book> searchResult = new ArrayList<Book>();
			Book[] importer;
			int queryCounter = 0;
			// Books by title
			if (!query[2].equals("")) {
				queryCounter++;
				try {
					importer = Book.listBookByQuery("Title=" +"'"+ query[2]+"'", "ID");
					for (int i = 0; i < importer.length; i++) {
						counter[importer[i].getID()]++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// Books by language
			if (!query[3].equals("")) {
				queryCounter++;
				try {
					importer = Book.listBookByQuery("Language="+"'"+query[3]+"'", "ID");
					System.out.println("before for");
					for (int i = 0; i < importer.length; i++) {
						counter[importer[i].getID()]++;
					}
					System.out.println("after for");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// Books by author
			if (!query[4].equals("")) {
				queryCounter++;
				try {
					Author a = Author.loadAuthorByQuery("Name='"+query[4]+"'","ID");
					importer = a.book.toArray();
					for (int i = 0; i < importer.length; i++) {
						counter[importer[i].getID()]++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// Check AND/OR
			if (query[0].equals("AND")) {
				System.out.println("if and");
				for (int i = 1; i < counter.length; i++) {
					if (counter[i] == queryCounter)
						try {
							System.out.println("before import");
							searchResult.add(Book.getBookByORMID(i));
							System.out.println("after import");
						} catch (PersistentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

				}
			} else {
				for (int i = 1; i < counter.length; i++) {
					if (counter[i] > 0)
						try {
							searchResult.add(Book.getBookByORMID(i));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
			msg.setMsg(searchResult);
			System.out.println("before send");
			sendToAllClients(msg);
			System.out.println("after send");
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
