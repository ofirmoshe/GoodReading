package server;

import java.io.IOException;
import java.rmi.server.UID;
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
import i_book.Review;
import i_book.Subject;
import i_book.User;
import i_book.User_Book;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ocsf.server.*;

public class Server extends AbstractServer {

	final public static int DEFAULT_PORT = 5555;
	static PersistentSession session;
	static Connection con;

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
			break;
		case "add book":
			addBookMessageHandler(m, client);
			break;
		case "inventory management":
			inventoryManagementMessageHandler(m, client);
			break;
		case "search review":
			searchReviewMessageHandler(m, client);
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
			GeneralUser login = (GeneralUser) msg.getMsg();
			GeneralUser u = null;
			try {
				u = GeneralUser.loadGeneralUserByORMID(session, login.getID());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				a[1] = "wrong username";
				msg.setMsg(a);
				try {
					client.sendToClient(msg);
				} catch (Exception e1) {
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

		case 2:
			Object[] o = (Object[]) msg.getMsg();
			String name = (String) o[0];
			name = name.substring(0, name.length() - 4);
			System.out.println(name);
			byte[] res = (byte[]) o[1];
			try {
				Book b = Book.loadBookByQuery("Title='" + name + "'", "ID");
				session.beginTransaction();
				b.setImage(res);
				b.save();
				session.getTransaction().commit();
				b = Book.loadBookByQuery("Title='" + name + "'", "ID");
				if (b.getImage() == null)
					System.out.println("null");
				System.out.println(b.getImage().toString());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.getTransaction().rollback();
			}

		}
	}

	public void bookPageMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			try {
				Object[] o = (Object[]) msg.getMsg();
				Book b = Book.getBookByORMID((int) o[0]);
				Author[] a = b.author.toArray();
				Field[] f = b.field.toArray();
				Subject[] s = b.subject.toArray();
				Review[] r = Review.listReviewByQuery("User_BookBookID=" + b.getID() + " AND Status='approved'", "ID");
				String[] u = new String[r.length];
				User user = null;
				for (int i = 0; i < r.length; i++) {
					user = r[i].getUsersbook().getUser();
					u[i] = user.getFname() + " " + user.getLname();
				}
				String canReview = "no";
				String userID = (String) o[1];
				user = User.loadUserByORMID(userID);
				User_Book[] ub = user.user_Books.toArray();
				for (int i = 0; i < ub.length; i++) {
					if (ub[i].getBook().getID() == b.getID()) {
						canReview = "yes";
						Review[] ur = ub[i].review.toArray();
						if (ur.length != 0) {
							// Only the last review can be active.
							canReview = ur[ur.length - 1].getStatus();
						}
					}
				}

				o = new Object[6];
				o[0] = a;
				o[1] = f;
				o[2] = s;
				o[3] = r;
				o[4] = u;
				o[5] = canReview;
				msg.setMsg(o);
				client.sendToClient(msg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case 2:
			try {
				Object[] o = (Object[]) msg.getMsg();
				Book b = Book.loadBookByORMID((int) o[0]);
				User u = User.loadUserByORMID((String) o[1]);
				User_Book ub = User_Book.loadUser_BookByORMID(b, u);
				session.beginTransaction();
				Review rev = Review.createReview();
				rev.setUsersbook(ub);
				rev.setText((String) o[2]);
				//rev.setStatus("waiting");
				rev.setStatus("approved");
				rev.save();
				session.getTransaction().commit();
				msg.setMsg("s");
				client.sendToClient(msg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				session.getTransaction().rollback();
				msg.setMsg("f");
				try {
					client.sendToClient(msg);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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

	public void userHomepageMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			try {
				Book[] books = Book.listBookByQuery("ID>0", "ID");
				msg.setMsg(books);
				client.sendToClient(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
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

	public void addBookMessageHandler(Message msg, ConnectionToClient client) {

		switch (msg.getFunc()) {
		case 1:
			try {
				Field[] fields = Field.listFieldByQuery("ID>0", "ID");
				Author[] authors = Author.listAuthorByQuery("ID>0", "ID");
				Subject[][] subjects = new Subject[fields.length][];
				for (int i = 0; i < fields.length; i++) {
					subjects[i] = fields[i].subject.toArray();
				}
				Object[] ob = new Object[3];
				ob[0] = fields;
				ob[1] = subjects;
				ob[2] = authors;
				msg.setMsg(ob);
				client.sendToClient(msg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				Object[] o = (Object[]) msg.getMsg();
				session.beginTransaction();
				Book newBook = Book.createBook();
				newBook.setTitle((String) o[0]);
				newBook.setLanguage((String) o[1]);
				newBook.setImage((byte[]) o[6]);
				newBook.setSummary((String) o[7]);
				newBook.setTable_of_contents((String) o[8]);
				newBook.setPrice((float) o[9]);
				// PDF
				String downloadLink = (String) o[10];
				if (!downloadLink.equals("")) {
					newBook.setPdf(downloadLink);
				}
				// DOC
				downloadLink = (String) o[11];
				if (!downloadLink.equals("")) {
					newBook.setDoc(downloadLink);
				}
				// FB2
				downloadLink = (String) o[12];
				if (!downloadLink.equals("")) {
					newBook.setFb2(downloadLink);
				}
				newBook.save();
				session.getTransaction().commit();
				Author[] authors = (Author[]) o[4];
				for (int i = 0; i < authors.length; i++) {
					Statement stmt = con.createStatement();
					String sql = "INSERT INTO author_book " + "VALUES (" + authors[i].getID() + ", " + newBook.getID()
							+ ")";
					stmt.executeUpdate(sql);
				}
				Field[] fields = (Field[]) o[2];
				for (int i = 0; i < fields.length; i++) {
					Statement stmt = con.createStatement();
					String sql = "INSERT INTO book_field " + "VALUES (" + newBook.getID() + ", " + fields[i].getID()
							+ ")";
					stmt.executeUpdate(sql);
				}
				Subject[] subjects = (Subject[]) o[3];
				if (subjects.length != 0) {
					for (int i = 0; i < subjects.length; i++) {
						Statement stmt = con.createStatement();
						String sql = "INSERT INTO book_subject " + "VALUES (" + newBook.getID() + ", "
								+ subjects[i].getID() + ")";
						stmt.executeUpdate(sql);
					}
				}
				String words = (String) o[5];
				if (!words.equals("")) {
					String[] keywords = (String[]) words.split(" ");
					for (int i = 0; i < keywords.length; i++) {
						Keyword kw = Keyword.loadKeywordByQuery("Word=" + "'" + keywords[i] + "'", "ID");
						if (kw == null) {
							session.beginTransaction();
							kw = Keyword.createKeyword();
							kw.setWord(keywords[i]);
							kw.save();
							session.getTransaction().commit();
						}
						Statement stmt = con.createStatement();
						String sql = "INSERT INTO keyword_book " + "VALUES (" + kw.getID() + ", " + newBook.getID()
								+ ")";
						stmt.executeUpdate(sql);
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}

		}
	}

	private void inventoryManagementMessageHandler(Message msg, ConnectionToClient client) {
		Field f = null;
		boolean af = true;
		boolean ff = true;
		boolean sf = true;

		try {
			Field[] fields = Field.listFieldByQuery("ID>0", "ID");
			Author[] authors = Author.listAuthorByQuery("ID>0", "ID");
			Subject[][] subjects = new Subject[fields.length][];
			for (int i = 0; i < fields.length; i++) {
				subjects[i] = fields[i].subject.toArray();
			}
			Object[] o = (Object[]) msg.getMsg();
			for (int i = 0; i < authors.length; i++) {
				if (authors[i].getName().equals(o[0])) {
					af = false;
					msg.setMsg("ad");
					sendToAllClients(msg);
					return;
				}
			}
			for (int i = 0; i < fields.length; i++) {
				if (fields[i].getField().equals(o[1])) {
					msg.setMsg("fd");
					ff = false;
					sendToAllClients(msg);
					return;
				}
			}
			for (int j = 0; j < fields.length; j++)
				for (int i = 0; i < subjects[j].length; i++) {
					if (subjects[j][i].getSub().equals(o[2])) {
						sf = false;
						msg.setMsg("sd");
						sendToAllClients(msg);
						return;
					}
				}
			session.beginTransaction();
			if (!o[0].equals("") && af) {
				Author a = Author.createAuthor();
				a.setName(o[0].toString());
				a.save();
			}
			if (!o[1].equals("") && ff) {
				f = Field.createField();
				f.setField(o[1].toString());
				f.save();
				session.getTransaction().commit();
				session.beginTransaction();
			}
			if (!o[2].equals("") && sf) {

				Subject s = Subject.createSubject();
				s.setSub(o[2].toString());
				if (msg.getFunc() == 2)
					s.setField((Field) o[3]);
				else if (f != null && msg.getFunc() == 3) {
					s.setField(f);

				}

				s.save();
			}
			session.getTransaction().commit();
			msg.setMsg("s");
			client.sendToClient(msg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
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

	private void searchReviewMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			try {
				System.out.println("server");
				Review[] reviews = Review.listReviewByQuery("ID>0", "ID");
				System.out.println("reviws");
				ArrayList<Review> searchResult = new ArrayList<Review>();
				String[] query = (String[]) msg.getMsg();
				Author author = null;
				if (!query[2].equals("")) {
					author = Author.loadAuthorByQuery("Name='" + query[2] + "'", "ID");
					if (author == null) {
						msg.setMsg(null);
						client.sendToClient(msg);
						return;
					}
				}
				String[] words = new String[0];
				if (!query[3].equals(""))
					words = query[3].split(" ");
				Keyword[] keywords = new Keyword[words.length];
				for (int i = 0; i < words.length; i++) {
					keywords[i] = Keyword.loadKeywordByQuery("Word='" + words[i] + "'", "ID");
				}
				for (int i = 0; i < reviews.length; i++) {
					boolean inFlag = true;
					Book b = reviews[i].getUsersbook().getBook();
					if (!reviews[i].getStatus().equals("approved"))
						inFlag = false;
					if (!query[1].equals("") && !b.getTitle().equalsIgnoreCase(query[1]))
						inFlag = false;
					if (!query[2].equals("") && !b.author.contains(author))
						inFlag = false;
					for (int j = 0; j < keywords.length; j++) {
						if (keywords[j] != null && !b.keyword.contains(keywords[j]))
							inFlag = false;
					}
					if (inFlag)
						searchResult.add(reviews[i]);
				}
				String[] rev = new String[searchResult.size()];
				String[] titles = new String[rev.length];
				String[] unames = new String[rev.length];
				for (int i = 0; i < rev.length; i++) {
					rev[i] = searchResult.get(i).getText();
					titles[i] = searchResult.get(i).getUsersbook().getBook().getTitle();
					unames[i] = searchResult.get(i).getUsersbook().getUser().getFname() + " "
							+ searchResult.get(i).getUsersbook().getUser().getLname();
				}
				String[][] s = new String[3][];
				s[0] = rev;
				s[1] = titles;
				s[2] = unames;
				msg.setMsg(s);
				client.sendToClient(msg);
				System.out.println("sent");
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
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

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/i-book", "root", "Braude");
			System.out.println("SQL connection succeed");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}

}
