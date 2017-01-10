package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.PersistentTransaction;
import org.orm.cfg.JDBCConnectionSetting;

import common.Message;
import i_book.Author;
import i_book.Book;
import i_book.Field;
import i_book.GeneralUser;
import i_book.IBookIncPersistentManager;
import i_book.Keyword;
import i_book.Membership;
import i_book.PaymentRequest;
import i_book.Subject;
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
			loginMessageHandler(m, client);
			break;
		case "user homepage":
			userHomepageMessageHandler(m, client);
			break;
		case "system":
			systemMessageHandler(m, client);
			break;
		case "book page":
			bookPageMessageHandler(m, client);
			break;
		case "search book":
			searchBookMessageHandler(m, client);
			break;
		case "book payment":
			bookPaymentMessageHandler(m, client);
			break;
		case "membership payment":
			membershipPaymentMessageHandler(m, client);
			break;
		case "membership":
			membershipMessageHandler(m, client);
		}
	}

	public void systemMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			/*
			 * User u = (User) msg.getMsg(); u.setStatus("offline"); try {
			 * PersistentTransaction t = session.beginTransaction();
			 * session.update(u); t.commit(); t = session.beginTransaction();
			 * session.evict(u); t.commit(); session =
			 * IBookIncPersistentManager.instance().getSession(); } catch
			 * (PersistentException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); } break;
			 */
		}
	}

	public void loginMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			Object[] a = new Object[2];
			try {
				Book[] books = Book.listBookByQuery("ID>0", "ID");
				a[0] = books;
			} catch (PersistentException e1) {
				System.out.println("Can't load book list.");
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
				try {
					client.sendToClient(msg);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return;
			}
			if (u.getPassword().equals(login.getPassword())) {
				a[1] = u;
				msg.setMsg(a);
				try {
					client.sendToClient(msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/*
				 * if (u instanceof User) { User user = (User) u; if
				 * (user.getStatus().equals("offline")) {
				 * user.setStatus("online"); try { PersistentTransaction t =
				 * session.beginTransaction(); session.update(user); t.commit();
				 * t = session.beginTransaction(); session.evict(user);
				 * t.commit(); } catch (PersistentException e) { // TODO
				 * Auto-generated catch block e.printStackTrace(); } } }
				 */
				return;
			}
			a[1] = "wrong password";
			msg.setMsg(a);
			try {
				client.sendToClient(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

	public void userHomepageMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
		}
	}

	public void bookPageMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			try {
				Book b = Book.getBookByORMID((int) msg.getMsg());
				Author[] a = b.author.toArray();
				Field[] f = b.field.toArray();
				Subject[] s = b.subject.toArray();
				Object[] o = new Object[3];
				o[0] = a;
				o[1] = f;
				o[2] = s;
				msg.setMsg(o);
				client.sendToClient(msg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void searchBookMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			String[] query = (String[]) msg.getMsg();
			int[] counter = new int[Integer.parseInt(query[1]) + 1];
			ArrayList<Book> searchResult = new ArrayList<Book>();
			Book[] importer;
			int queryCounter = 0;
			// Books by title
			if (!query[2].equals("")) {
				queryCounter++;
				try {
					importer = Book.listBookByQuery("Title=" + "'" + query[2] + "'", "ID");
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
					importer = Book.listBookByQuery("Language=" + "'" + query[3] + "'", "ID");
					for (int i = 0; i < importer.length; i++) {
						counter[importer[i].getID()]++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// Books by author
			if (!query[4].equals("")) {
				queryCounter++;
				try {
					Author a = Author.loadAuthorByQuery("Name='" + query[4] + "'", "ID");
					if (a != null) {
						importer = a.book.toArray();
						for (int i = 0; i < importer.length; i++) {
							counter[importer[i].getID()]++;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (!query[5].equals("")) {
				String[] s = query[5].split(" ");
				queryCounter += s.length;
				for (int i = 0; i < s.length; i++) {
					try {
						Keyword k = Keyword.loadKeywordByQuery("Word='" + s[i] + "'", "ID");
						if (k != null) {
							importer = k.book.toArray();
							for (int j = 0; j < importer.length; j++) {
								counter[importer[j].getID()]++;
							}
						}
					} catch (PersistentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (!query[6].equals("0")) {
				queryCounter++;
				try {
					Field f = Field.loadFieldByORMID(Integer.parseInt(query[6]));
					if (f != null) {
						importer = f.book.toArray();
						for (int i = 0; i < importer.length; i++) {
							counter[importer[i].getID()]++;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (!query[7].equals("None")) {
				queryCounter++;
				try {
					Subject s = Subject.loadSubjectByQuery("Sub='" + query[7] + "'", "ID");
					if (s != null) {
						importer = s.book.toArray();
						for (int i = 0; i < importer.length; i++) {
							counter[importer[i].getID()]++;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// Check AND/OR
			if (query[0].equals("AND")) {
				for (int i = 1; i < counter.length; i++) {
					if (counter[i] >= queryCounter)
						try {
							searchResult.add(Book.getBookByORMID(i));
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
			Book[] b = new Book[searchResult.size()];
			for (int i = 0; i < searchResult.size(); i++) {
				b[i] = searchResult.get(i);
			}
			Author[][] a = new Author[b.length][];
			Field[][] f = new Field[b.length][];
			Subject[][] s = new Subject[b.length][];
			for (int i = 0; i < b.length; i++) {
				a[i] = b[i].author.toArray();
				f[i] = b[i].field.toArray();
				s[i] = b[i].subject.toArray();
			}
			Object[] o = new Object[4];
			o[0] = b;
			o[1] = a;
			o[2] = f;
			o[3] = s;
			msg.setMsg(o);
			try {
				client.sendToClient(msg);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;

		case 2:
			try {
				Field[] fields = Field.listFieldByQuery("ID>0", "ID");
				Subject[][] subjects = new Subject[fields.length][];
				for (int i = 0; i < fields.length; i++) {
					subjects[i] = fields[i].subject.toArray();
				}
				Object[] ob = new Object[2];
				ob[0] = fields;
				ob[1] = subjects;
				msg.setMsg(ob);
				client.sendToClient(msg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void bookPaymentMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			try {
				Object[] o = (Object[]) msg.getMsg();
				User u = User.loadUserByORMID((String) o[0]);
				PaymentRequest[] p = u.paymentrequest.toArray();
				for (int i = 0; i < p.length; i++) {
					Book b = p[i].getBook();
					if (b != null) {
						if (b.getID() == (int) o[1] && p[i].getStatus().equals("waiting")) {
							msg.setMsg("d");
							client.sendToClient(msg);
							return;
						}
					}
				}
				session.beginTransaction();
				PaymentRequest pr = PaymentRequest.createPaymentRequest();
				pr.setUser(u);
				pr.setBook(Book.loadBookByORMID((int) o[1]));
				pr.setPaymentInfo((String) o[2]);
				pr.setDate(new Date());
				pr.setStatus("waiting");
				pr.save();
				session.getTransaction().commit();
				msg.setMsg("s");
				client.sendToClient(msg);

			} catch (Exception e) {
				try {
					e.printStackTrace();
					session.getTransaction().rollback();
					msg.setMsg("f");
					client.sendToClient(msg);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

		}
	}

	private void membershipPaymentMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			try {
				Object[] o = (Object[]) msg.getMsg();
				User u = User.loadUserByORMID((String) o[0]);
				PaymentRequest[] p = u.paymentrequest.toArray();
				for (int i = 0; i < p.length; i++) {
					Membership m = p[i].getMembership();
					if (m != null) {
						if (p[i].getStatus().equals("waiting")) {
							msg.setMsg("d");
							client.sendToClient(msg);
							return;
						}
					}
				}
				session.beginTransaction();
				PaymentRequest pr = PaymentRequest.createPaymentRequest();
				pr.setUser(u);
				pr.setMembership(Membership.loadMembershipByORMID((int) o[1]));
				pr.setPaymentInfo((String) o[2]);
				pr.setDate(new Date());
				pr.setStatus("waiting");
				pr.save();
				session.getTransaction().commit();
				msg.setMsg("s");
				client.sendToClient(msg);

			} catch (Exception e) {
				try {
					e.printStackTrace();
					session.getTransaction().rollback();
					msg.setMsg("f");
					client.sendToClient(msg);
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

		}
	}

	private void membershipMessageHandler(Message msg, ConnectionToClient client) {
		try {
			Membership[] m = Membership.listMembershipByQuery("ID>0", "ID");
			msg.setMsg(m);
			client.sendToClient(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
