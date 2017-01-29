package server;

import java.io.IOException;
import java.rmi.server.UID;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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
import i_book.User_Membership;
import i_book.Views_Date;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ocsf.server.*;

/**
 * This class extends the AbstractServer class 
 * @author Noy
 * @author Ofir
 *
 */
public class Server extends AbstractServer {

	final public static int DEFAULT_PORT = 5555;
	static PersistentSession session;
	static Connection con;

	public Server(int port) {
		super(port);
		// TODO Auto-generated constructor stub
	}

	/**
	 * This method implements the abstract server method.
	 */
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
			break;
		case "edit book":
			editBookMessageHandler(m, client);
			break;
		case "librarian homepage":
			librarianHomepageMessageHandler(m, client);
			break;
		case "manage review":
			manageReviewMessageHandler(m, client);
			break;
		case "add user":
			addUserMessageHandler(m, client);
			break;
		case "employee homepage":
			employeeHomepageMessageHandler(m, client);
			break;
		case "user membership":
			userMembershipMessageHandler(m, client);
			break;
		case "user report":
			userReportMessageHandler(m, client);
			break;
		case "search user":
			searchUserMessageHandler(m, client);
			break;
		case "manage user":
			manageUserMessageHandler(m, client);
			break;
		case "histogram report":
			histogramReportMessageHandler(m, client);
			break;
		}
	}

	/**
	 * This method sets user status to "offline" when user "logout".
	 * @param msg
	 * @param client
	 */
	public void systemMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			try {
				User u = (User) msg.getMsg();
				Statement stmt = con.createStatement();
				String sql = "UPDATE generaluser " + "SET Status='offline' WHERE ID='" + u.getID() + "'";
				stmt.executeUpdate(sql);
			} catch (Exception e) { // TODO Auto-generated catch block
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			break;
		}
	}

	/**
	 * This method handle the messages from "login" controller 
	 * @param msg
	 * @param client
	 * 
	 * The method check if the user id and password are match and send proper message back to the controller.
	 * in case of match - set the user status to "online" and check if the user has membership
	 * returns to controller a list of all the books in BD.
	 */
	public void loginMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			Object[] a = new Object[3];
			try {
				Book[] books = Book.listBookByQuery("Status='visible'", "-ID");
				a[0] = books;
			} catch (PersistentException e1) {
				System.out.println("Can't load book list.");
			}
			GeneralUser login = (GeneralUser) msg.getMsg();
			GeneralUser u = null;
			try {
				u = GeneralUser.loadGeneralUserByORMID(login.getID());
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
				if (u instanceof User) {
					User user = (User) u;
					User_Membership[] um = user.user_Memberships.toArray();
					if (um.length != 0) {
						if (um[um.length - 1].getE_date().before(new Date()))
							a[2] = null;
						else
							a[2] = um[um.length - 1];
					} else
						a[2] = null;
				}
				msg.setMsg(a);
				try {
					client.sendToClient(msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (u instanceof User) {
					User user = (User) u;
					if (user.getStatus().equals("offline")) {
						try {
							Statement stmt = con.createStatement();
							String sql = "UPDATE generaluser " + "SET Status='online' WHERE ID='" + u.getID() + "'";
							stmt.executeUpdate(sql);
						} catch (Exception e) {
							e.printStackTrace();
							session.getTransaction().rollback();
						}
					}
				}
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

	/**
	 * This method handle messages from "book page" controller.
	 * @param msg
	 * @param client
	 * 
	 * 		case 1: the method gets the book id from the controller and sends back all the book info.
	 * 				the method increases the views counter for this book in his views_date table (or create new table with the current date)
	 * 				check if the user owns membership and "allow" download the book without payment request
	 * 		case 3: check if the book's status is "paid" and changes his status to "download".
	 * 		case 10: when user adds a review to this book the server sends popup message to the "online" employees in the position 
	 * 				of reviews management.  
	 */
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
				String canDownload = "no";
				String canReview = "no";
				String userID = (String) o[1];
				user = User.loadUserByORMID(userID);
				User_Book[] ub = user.user_Books.toArray();
				for (int i = 0; i < ub.length; i++) {
					if (ub[i].getBook().getID() == b.getID()) {
						canDownload = "yes";
						if (ub[i].getStatus().equals("downloaded"))
							canReview = "yes";
						Review[] ur = ub[i].review.toArray();
						if (ur.length != 0) {
							if (ur[0].getStatus().equals("waiting"))
								canReview = "waiting";
							if (ur[0].getStatus().equals("approved"))
								canReview = "no";

						}
					}
				}

				User_Membership[] um = user.user_Memberships.toArray();
				if (um.length != 0) {
					if (um[um.length - 1].getE_date().after(new Date()))
						canDownload = "yes";
				}
				session.beginTransaction();
				Views_Date v;
				Date d = new Date();
				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String today = formatter.format(d);
				Statement stmt = con.createStatement();
				ResultSet result = null;
				result = stmt.executeQuery("select ViewCount from views_date where Date='"+today+"' AND BookID='"+b.getID()+"'");
				if (!result.isBeforeFirst()) {
					stmt = con.createStatement();
					String sql = "INSERT INTO views_date " + "VALUES ('" + today + "', '" + b.getID() + "', '1')";
					stmt.executeUpdate(sql);
				} else {
					result.next();
					stmt = con.createStatement();
					String sql = "UPDATE views_date " + "SET ViewCount='" + (result.getInt(1) + 1) + "' WHERE Date='"
							+ today + "' AND BookID='" + b.getID() + "'";
					stmt.executeUpdate(sql);
				}

				session.getTransaction().commit();
				o = new Object[7];
				o[0] = a;
				o[1] = f;
				o[2] = s;
				o[3] = r;
				o[4] = u;
				o[5] = canReview;
				o[6] = canDownload;
				msg.setMsg(o);
				client.sendToClient(msg);
			} catch (Exception e) {
				session.getTransaction().rollback();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 10:
			try {
				Object[] o = (Object[]) msg.getMsg();
				Book b = Book.loadBookByORMID((int) o[0]);
				User u = User.loadUserByORMID((String) o[1]);
				User_Book ub = User_Book.loadUser_BookByORMID(b, u);
				session.beginTransaction();
				Review rev = Review.createReview();
				rev.setUsersbook(ub);
				rev.setText((String) o[2]);
				// rev.setStatus("waiting");
				rev.setStatus("waiting");
				rev.save();
				session.getTransaction().commit();
				msg.setMsg("s");
				sendToAllClients(msg);
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
			break;
		case 3:
			Object[] o = (Object[]) msg.getMsg();
			try {
				session.beginTransaction();
				int bookID = (int) o[0];
				String userID = (String) o[1];
				User_Book ub = User_Book.loadUser_BookByQuery("BookID=" + bookID + " AND GeneralUserID=" + userID,
						"BookID");
				if (ub == null) // user is a member and he is downloading this
								// book for the first time.
				{
					ub = User_Book.createUser_Book();
					ub.setBook(Book.loadBookByORMID(bookID));
					ub.setUser(User.getUserByORMID(userID));
					ub.setStatus("downloaded");
					ub.setpDate(new Date());
					ub.save();
				}
				if (ub.getStatus().equals("paid"))
					ub.setStatus("downloaded");
				ub.save();
				session.getTransaction().commit();
			} catch (Exception e) {
				session.getTransaction().rollback();
				e.printStackTrace();
			}

		}

	}

	/**
	 * This method handle the messages from "search book" controller.
	 * @param msg
	 * @param client
	 * 
	 * 			case 1: the method gets a search query, look for match books ant send message back with the: books, book's fields,
	 * 					book's subjects and book's authors.
	 * 			case 2: the method sends back a list of all the books that match the query with total rank and "rank by field". 
	 */
	public void searchBookMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			String[] query = (String[]) msg.getMsg();
			int maxID = 0;
			try {
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select MAX(ID) from book");
				rs.next();
				maxID = rs.getInt(1);
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			int[] counter = new int[maxID + 1];
			ArrayList<Book> searchResult = new ArrayList<Book>();
			Book[] importer;
			int queryCounter = 0;
			// Books by title
			if (!query[2].equals("")) {
				queryCounter++;
				try {
					importer = Book.listBookByQuery("ID>0", "ID");
					String title = (String) query[2];
					for (int i = 0; i < importer.length; i++) {
						if (importer[i].getTitle().toLowerCase().contains(title.toLowerCase()))
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
					Author[] a = Author.listAuthorByQuery("ID>0", "ID");
					String name = (String) query[4];
					for (int i = 0; i < a.length; i++) {
						if (a[i].getName().toLowerCase().contains(name.toLowerCase())) {
							importer = a[i].book.toArray();
							for (int j = 0; j < importer.length; j++) {
								counter[importer[j].getID()]++;
							}
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
							Book b = Book.getBookByORMID(i);
							if (query[8].equals("yes") || b.getStatus().equals("visible"))
								searchResult.add(b);
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
				Object[] ob = new Object[4];
				ob[0] = fields;
				ob[1] = subjects;
				Book[] books = Book.listBookByQuery("ID>0", "ID");
				int[][] purchases = new int[books.length][2];
				for (int i = 0; i < books.length; i++) {
					purchases[i][0] = books[i].getID();
					purchases[i][1] = books[i].user_Books.size();
				}
				Arrays.sort(purchases, new Comparator<int[]>() {
					@Override
					public int compare(int[] o1, int[] o2) {
						return Integer.compare(o2[1], o1[1]);
					}
				});
				int[] ranks = new int[books[books.length - 1].getID() + 1];
				ranks[0]=books.length;
				for (int i = 0; i < books.length; i++) {
					ranks[purchases[i][0]] = i + 1;
				}
				ob[2] = ranks;
				int[][] fieldRanks = new int[fields.length][ranks.length];
				for (int i = 0; i < fields.length; i++) {
					Book[] fbooks = fields[i].book.toArray();
					purchases = new int[fbooks.length][2];
					for (int j = 0; j < fbooks.length; j++) {
						purchases[j][0] = fbooks[j].getID();
						purchases[j][1] = fbooks[j].user_Books.size();
					}
					Arrays.sort(purchases, new Comparator<int[]>() {
						@Override
						public int compare(int[] o1, int[] o2) {
							return Integer.compare(o2[1], o1[1]);
						}
					});
					fieldRanks[i][0]= fbooks.length;
					for (int j = 0; j < fbooks.length; j++)
						fieldRanks[i][purchases[j][0]] = j + 1;
				}
				ob[3] = fieldRanks;
				msg.setMsg(ob);
				client.sendToClient(msg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * This method handle the messages from "user home page" controller.
	 * @param msg
	 * @param client
	 * 
	 * This method sends a list of all the "visible" books in DB back to the controller.
	 */
	public void userHomepageMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			try {
				Book[] books = Book.listBookByQuery("Status='visible'", "-ID");
				msg.setMsg(books);
				client.sendToClient(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}

	/**
	 * This method handle the messages from "book payment" controller.
	 * @param msg
	 * @param client
	 * 
	 * The method gets the user id and load all his payment requests and check if there is already a request for this book
	 * in case there isn't the method creates a new payment request with status "waiting".
	 * The method  sends a proper message to the controller.
	 */
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

	/**
	 * This method handle the messages from "membership payment" controller.
	 * @param msg
	 * @param client
	 * 
	 * The method gets the user id and load all his payment requests and check if there is already a request for this membership
	 * in case there isn't the method creates a new payment request with status "waiting".
	 * The method  sends a proper message to the controller.
	 */
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

	/**
	 * This method handle the messages from "membership" controller.
	 * @param msg
	 * @param client
	 * 
	 * The method sends back a list of all the memberships in BD.
	 */
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

	/**
	 * This method handle the messages from "add book" controller.
	 * @param msg
	 * @param client
	 * 
	 * 			case 1: the method sends back lists of all the fields, subjects and authors in BD.
	 * 			case 2: the method gets the new book info from the controller and creates a new book with this info.
	 * 					this method adds new author to DB in case "Add New Author" field was not empty.
	 */
	public void addBookMessageHandler(Message msg, ConnectionToClient client) {

		switch (msg.getFunc()) {
		case 1:
			try {
				Field[] fields = Field.listFieldByQuery("ID>0", "ID");
				Author[] authors = Author.listAuthorByQuery("ID>0", "ID");
				Subject[] s = Subject.listSubjectByQuery("ID>0", "ID");
				Subject[][] subjects = new Subject[fields.length][];
				for (int i = 0; i < fields.length; i++) {
					subjects[i] = fields[i].subject.toArray();
				}
				Object[] ob = new Object[4];
				ob[0] = fields;
				ob[1] = subjects;
				ob[2] = authors;
				ob[3]=s;
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
				newBook.setStatus("visible");
				String[] newauthor = null;
				boolean authorFlag = true;
				// PDF
				byte[] format = (byte[]) o[10];
				if (format != null) {
					newBook.setPdf(format);
				}
				// DOC
				format = (byte[]) o[11];
				if (format != null) {
					newBook.setDoc(format);
				}
				// FB2
				format = (byte[]) o[12];
				if (format != null) {
					newBook.setFb2(format);
				}

				newBook.save();
				session.getTransaction().commit();
				if (!((String) o[13]).equals("")) {
					String author = (String) o[13];
					newauthor = (String[]) author.split(", ");
					Author a = new Author();
					for (int i = 0; i < newauthor.length; i++) {
						a = Author.loadAuthorByQuery("Name=" + "'" + newauthor[i] + "'", "ID");
						if (a == null) {
							session.beginTransaction();
							a = Author.createAuthor();
							a.setName(newauthor[i]);
							a.save();
							session.getTransaction().commit();
						}
						Statement stmt = con.createStatement();
						String sql = "INSERT INTO author_book " + "VALUES (" + a.getID() + ", " + newBook.getID() + ")";
						stmt.execute(sql);
					}
				}
				Author[] authors = (Author[]) o[4];
				for (int i = 0; i < authors.length; i++) {
					if (newauthor.length != 0) {
						for (int j = 0; j < newauthor.length; j++)
							if (authors[i].getName().equals(newauthor[j]))
								authorFlag = false;
						break;
					}
					if (authorFlag) {
						Statement stmt = con.createStatement();
						String sql = "INSERT INTO author_book " + "VALUES (" + authors[i].getID() + ", "
								+ newBook.getID() + ")";
						stmt.executeUpdate(sql);
					}
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

				msg.setMsg("s");
				client.sendToClient(msg);

			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}

			break;
		}

	}

	/**
	 * This method handle the messages from "inventory management" controller.
	 * @param msg
	 * @param client
	 * 
	 * 			the method checks if new inventory data already exists in DB. If not-It adds new data to DB.
	 * 			the method checks if user inserted new subject and a new field. If so, it check if the user requested to connect between new subject an fields.
	 * 			The method  sends a proper message to the controller.
	 */
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
			Field[]cf =(Field[])o[4];
			Subject[]cs =(Subject[])o[5];
			Author[]ca =(Author[])o[6];
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
			session.beginTransaction();
			if(cf.length!=0){
				Field delField;
				for(int i=0;i<cf.length;i++){
					delField = Field.loadFieldByORMID(cf[i].getID());
					delField.delete();
				}
			}
			session.getTransaction().commit();
			session.beginTransaction();
			if(cs.length!=0){
				Subject delSubject;
				for(int i=0;i<cs.length;i++){
					delSubject = Subject.loadSubjectByORMID(cs[i].getID());
					delSubject.delete();
				}
			}
			if(ca.length!=0){
				Author delAuthor;
				for(int i=0;i<ca.length;i++){
					delAuthor = Author.loadAuthorByORMID(ca[i].getID());
					delAuthor.delete();
				}
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

	/**
	 *  This method handle the messages from "search review" controller.
	 * @param msg
	 * @param client
	 * 			
	 * 			case 1: the method sends back an object array that contains results to the query.
	 * 			The array contains the review, review author and book name for this review.
	 */
	private void searchReviewMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			try {
				Review[] reviews = Review.listReviewByQuery("Status = 'approved'", "ID");
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
					String title = (String) query[1];
					if (b.getStatus().equals("hidden"))
						inFlag = false;
					if (!query[1].equals("") && !b.getTitle().toLowerCase().contains(title.toLowerCase()))
						inFlag = false;
					if (!query[2].equals("") && !b.author.contains(author))
						inFlag = false;
					for (int j = 0; j < keywords.length; j++) {
						if (!b.keyword.contains(keywords[j]))
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
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}

	}

	/**
	 * This method handle the messages from "edit book" controller.
	 * @param msg
	 * @param client
	 * 
	 * 			case 1: the method sends back an object array that includes: a list of all authors exists in DB,
	 * 			a list of all subjects exists in DB,a list of all fields exists in DB and book's keywords.
	 * 
	 * 			case 2: the method gets the new info of the book after all the changes and update the changes in DB.
	 * 
	 * 			case 3: the method deletes the book from DB.
	 * 
	 * 			case 4: the method hides the book by changing the status from "visible" to "hidden".
	 */
	private void editBookMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			try {
				Field[] fields = Field.listFieldByQuery("ID>0", "ID");
				Author[] authors = Author.listAuthorByQuery("ID>0", "ID");
				Subject[][] subjects = new Subject[fields.length][];
				for (int i = 0; i < fields.length; i++) {
					subjects[i] = fields[i].subject.toArray();
				}
				Object[] ob = new Object[8];
				ob[0] = fields;
				ob[1] = subjects;
				ob[2] = authors;
				Book book = Book.loadBookByORMID((int) msg.getMsg());
				book.refresh();
				ob[3] = book.field.toArray();
				ob[4] = book.subject.toArray();
				ob[5] = book.author.toArray();
				ob[6] = book;
				ob[7] = book.keyword.toArray();
				msg.setMsg(ob);
				client.sendToClient(msg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				session.beginTransaction();
				Object[] o = (Object[]) msg.getMsg();
				Book b = Book.loadBookByORMID((int) o[13]);
				b.setTitle((String) o[0]);
				b.setLanguage((String) o[1]);
				b.setImage((byte[]) o[6]);
				b.setSummary((String) o[7]);
				b.setTable_of_contents((String) o[8]);
				b.setPrice((float) o[9]);
				// PDF
				byte[] format = (byte[]) o[10];
				if (format != null) {
					b.setPdf(format);
				}
				// DOC
				format = (byte[]) o[11];
				if (format != null) {
					b.setDoc(format);
				}
				// FB2
				format = (byte[]) o[12];
				if (format != null) {
					b.setFb2(format);
				}
				b.save();
				session.getTransaction().commit();
				Statement stmt = con.createStatement();
				String sql = "DELETE FROM author_book WHERE BookID=" + b.getID();
				stmt.execute(sql);
				Author[] SelectedAuthors = (Author[]) o[4];
				for (int i = 0; i < SelectedAuthors.length; i++) {
					stmt = con.createStatement();
					sql = "INSERT INTO author_book " + "VALUES (" + SelectedAuthors[i].getID() + ", " + b.getID() + ")";
					stmt.execute(sql);
				}
				stmt = con.createStatement();
				sql = "DELETE FROM book_field WHERE BookID=" + b.getID();
				stmt.execute(sql);
				Field[] SelectedFields = (Field[]) o[2];
				for (int i = 0; i < SelectedFields.length; i++) {
					stmt = con.createStatement();
					sql = "INSERT INTO book_field " + "VALUES (" + b.getID() + ", " + SelectedFields[i].getID() + ")";
					stmt.execute(sql);
				}
				stmt = con.createStatement();
				sql = "DELETE FROM book_subject WHERE BookID=" + b.getID();
				stmt.execute(sql);
				Subject[] SelectedSubjects = (Subject[]) o[3];
				for (int i = 0; i < SelectedSubjects.length; i++) {
					stmt = con.createStatement();
					sql = "INSERT INTO book_subject " + "VALUES (" + b.getID() + ", " + SelectedSubjects[i].getID()
							+ ")";
					stmt.execute(sql);
				}
				String words = (String) o[5];
				if (!words.equals("")) {
					String[] keywords = (String[]) words.split(", ");
					sql = "DELETE FROM keyword_book WHERE BookID=" + b.getID();
					stmt.execute(sql);
					for (int i = 0; i < keywords.length; i++) {
						stmt = con.createStatement();
						Keyword kw = Keyword.loadKeywordByQuery("Word=" + "'" + keywords[i] + "'", "ID");
						if (kw == null) {
							session.beginTransaction();
							kw = Keyword.createKeyword();
							kw.setWord(keywords[i]);
							kw.save();
						}
						sql = "INSERT INTO keyword_book " + "VALUES (" + kw.getID() + ", " + b.getID() + ")";
						stmt.execute(sql);
					}
				}
				msg.setMsg("s");
				client.sendToClient(msg);
			} catch (Exception e) {
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
			break;

		case 3:
			try {
				Book b = Book.loadBookByORMID((int) msg.getMsg());
				Statement stmt = con.createStatement();
				String sql = "DELETE FROM book WHERE ID=" + b.getID();
				stmt.execute(sql);
				msg.setMsg("s");
			} catch (Exception e) {
				// TODO ]Auto-generated catch blockS
				e.printStackTrace();
				session.getTransaction().rollback();
				msg.setMsg("f");
			}
			try {
				client.sendToClient(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 4:
			try {
				Book b = Book.loadBookByORMID((int) msg.getMsg());
				session.beginTransaction();
				if (b.getStatus().equals("visible"))
					b.setStatus("hidden");
				else
					b.setStatus("visible");
				b.save();
				session.getTransaction().commit();
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}

		}

	}

	/**
	 * This method handle the messages from "librarian home page" controller.
	 * @param msg
	 * @param client
	 * 
	 * The method checks if the book exists in DB.
	 */
	private void librarianHomepageMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			try {
				Book.loadBookByORMID((int) msg.getMsg());
				msg.setMsg("s");
				client.sendToClient(msg);
			} catch (Exception e) {
				msg.setMsg("f");
				try {
					client.sendToClient(msg);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			break;
		}
	}

	/**
	 * This method handle the messages from "manage review" controller.
	 * @param msg
	 * @param client
	 * 
	 * 			case 1: the method sends back a list of: reviews with status "waiting", the name of the user, the book's title
	 * 			case 2: the method save the review after the changes.
	 * 			case 3: the method changes the review's status to "Approve".
	 * 			case 4: the method deletes the review from DB ("Deny").
	 */
	private void manageReviewMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			try {
				Object[] o = new Object[3];
				Review[] reviews = Review.listReviewByQuery("Status='waiting'", "ID");
				String[] unames = new String[reviews.length];
				String[] titles = new String[reviews.length];
				for (int i = 0; i < reviews.length; i++) {
					unames[i] = reviews[i].getUsersbook().getUser().getFname() + " "
							+ reviews[i].getUsersbook().getUser().getLname();
					titles[i] = reviews[i].getUsersbook().getBook().getTitle();
				}
				o[0] = reviews;
				o[1] = unames;
				o[2] = titles;
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
				String text = (String) o[0];
				session.beginTransaction();
				Review r = Review.loadReviewByORMID((int) o[1]);
				r.setText(text);
				r.save();
				session.getTransaction().commit();
			} catch (PersistentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		case 3:
			Object o = (Object) msg.getMsg();
			try {
				session.beginTransaction();
				Review r = Review.loadReviewByORMID((int) o);
				r.setStatus("approved");
				r.save();
				session.getTransaction().commit();
				msg.setMsg("s");
				client.sendToClient(msg);

			} catch (Exception e) {
				msg.setMsg("f");
				try {
					client.sendToClient(msg);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			break;
		case 4:
			try {
				Object ob = (Object) msg.getMsg();
				session.beginTransaction();
				Review r = Review.loadReviewByORMID((int) ob);
				r.delete();
				session.getTransaction().commit();
				msg.setMsg("s");
				client.sendToClient(msg);

			} catch (Exception e) {
				msg.setMsg("f");
				try {
					client.sendToClient(msg);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			break;
		}
	}

	/**
	 * This method handle the messages from "add user" controller.
	 * @param msg
	 * @param client
	 * 
	 * The method gets user's info check if this user is already exist in DB, 
	 * if he is not in DB the method creates a new user with this info.
	 */
	private void addUserMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			Object[] o = (Object[]) msg.getMsg();
			String id = (String) o[2];
			GeneralUser gn = null;
			try {
				gn = GeneralUser.loadGeneralUserByORMID(id);
			} catch (PersistentException e2) {
			}
			if (gn != null) {
				msg.setMsg("e");
				try {
					client.sendToClient(msg);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				return;
			}
			try {
				session.beginTransaction();
				User user = User.createUser();
				user.setFname((String) o[0]);
				user.setLname((String) o[1]);
				user.setID(id);
				user.setPassword((String) o[3]);
				if (!o[4].equals(""))
					user.setPaymentInfo((String) o[4]);
				user.setStatus("offline");
				user.save();
				session.getTransaction().commit();
				msg.setMsg("s");
			} catch (Exception e) {
				e.printStackTrace();
				session.getTransaction().rollback();
				msg.setMsg("f");
			}
			try {
				client.sendToClient(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}

	}

	/**
	 * This method handle the messages from "employee home page" controller.
	 * @param msg
	 * @param client
	 * 
	 * 			case 1: the method returns a lists of: payment request with status "waiting", books, memberships and users with request.
	 * 			case 2: the method check if the request is for book or membership: if it is book the method create new user_book
	 * 					and if it is membership the method creates new user_membership. the method also changes the payment request
	 * 					status to "Approve"
	 * 			case 3: the method deletes the payment request from DB.
	 */
	@SuppressWarnings("deprecation")
	private void employeeHomepageMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			try {
				PaymentRequest[] pr = PaymentRequest.listPaymentRequestByQuery("Status='waiting'", "ID");
				Book[] books = new Book[pr.length];
				GeneralUser[] users = new GeneralUser[pr.length];
				Membership[] memberships = new Membership[pr.length];

				for (int i = 0; i < pr.length; i++) {
					users[i] = GeneralUser.loadGeneralUserByORMID(pr[i].getUser().getID());
					if (pr[i].getBook() != null)
						books[i] = Book.loadBookByORMID(pr[i].getBook().getID());
					else
						books[i] = null;
					if (pr[i].getMembership() != null)
						memberships[i] = Membership.loadMembershipByORMID(pr[i].getMembership().getID());
					else
						memberships[i] = null;
				}

				Object[] o = new Object[4];
				o[0] = pr;
				o[1] = users;
				o[2] = books;
				o[3] = memberships;
				msg.setMsg(o);
				client.sendToClient(msg);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			int pid = (int) msg.getMsg();
			try {
				session.beginTransaction();
				PaymentRequest pr = PaymentRequest.loadPaymentRequestByORMID(pid);
				if (pr.getBook() != null) {
					User_Book ub = User_Book.createUser_Book();
					ub.setBook(pr.getBook());
					ub.setUser(pr.getUser());
					ub.setpDate(new Date());
					ub.setStatus("paid");
					ub.save();
				} else {
					User_Membership um = User_Membership.createUser_Membership();
					um.setMembership(pr.getMembership());
					um.setS_date(new Date());
					Date d = new Date();
					d.setDate(d.getDate() + pr.getMembership().getDays());
					um.setE_date(d);
					um.setUser(pr.getUser());
					um.setStatus("active");
					um.save();
				}
				pr.setStatus("approved");
				User user = User.loadUserByORMID(pr.getUser().getID());
				user.setPaymentInfo(pr.getPaymentInfo());
				user.save();
				pr.save();
				session.getTransaction().commit();
				msg.setFunc(1);
				employeeHomepageMessageHandler(msg, client);
			} catch (PersistentException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			break;
		case 3:
			int prid = (int) msg.getMsg();
			PaymentRequest pr;
			try {
				session.beginTransaction();
				pr = PaymentRequest.loadPaymentRequestByORMID(prid);
				pr.setStatus("denied");
				pr.save();
				session.getTransaction().commit();
				msg.setFunc(1);
				employeeHomepageMessageHandler(msg, client);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	/**
	 * This method handle messages from "user membership" controller.
	 * @param msg
	 * @param client
	 */
	private void userMembershipMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			break;
		}
	}

	/**
	 * This method handle the messages from "user report" controller.
	 * @param m
	 * @param client
	 * 
	 * The method returns a lists of: all the user's book and the book's fields, subjects and authors of each book.
	 */
	private void userReportMessageHandler(Message m, ConnectionToClient client) {
		switch (m.getFunc()) {
		case 1:
			try {
				User u = User.loadUserByORMID((String) m.getMsg());
				User_Book[] ub = u.user_Books.toArray();
				Book[] books = new Book[ub.length];
				for (int i = 0; i < ub.length; i++)
					books[i] = ub[i].getBook();
				Author[][] a = new Author[books.length][];
				Field[][] f = new Field[books.length][];
				Subject[][] s = new Subject[books.length][];
				for (int i = 0; i < books.length; i++) {
					a[i] = books[i].author.toArray();
					f[i] = books[i].field.toArray();
					s[i] = books[i].subject.toArray();
				}
				Object[] o = new Object[4];
				o[0] = books;
				o[1] = a;
				o[2] = f;
				o[3] = s;
				m.setMsg(o);
				client.sendToClient(m);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * This method handle the messages from "search user" controller.
	 * @param msg
	 * @param client
	 * 
	 * The method returns a list of all the users in DB.
	 */
	private void searchUserMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			try {
				User[] users = User.listUserByQuery("ID>0", "ID");
				msg.setMsg(users);
				client.sendToClient(msg);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			break;
		}
	}

	/**
	 * This method handle the messages from "manage user" controller.
	 * @param msg
	 * @param client
	 * 
	 * 			case 1: the method gets the user's new info and update the changes on DB.
	 * 					changes the membership status by the request ("active" to "blocked" or "blocked" to "active")
	 * 					changes the user status by the request to "banned".
	 * 			case 2: the method returns a list of all the user_memberships in DB.
	 */
	private void manageUserMessageHandler(Message msg, ConnectionToClient client) {
		// TODO Auto-generated method stub
		switch (msg.getFunc()) {
		case 1:
			Object[] o = (Object[]) msg.getMsg();
			String uid = (String) o[2];
			try {
				session.beginTransaction();
				User u = User.loadUserByORMID(uid);
				u.setFname((String) o[0]);
				u.setLname((String) o[1]);
				u.setPassword((String) o[3]);
				u.setPaymentInfo((String) o[4]);
				User_Membership[] um = u.user_Memberships.toArray();
				if (um.length != 0) {
					if (um[um.length - 1].getE_date().after(new Date())) {
						if (o[5] != null)
							if ((boolean) o[5] == true)
								um[um.length - 1].setStatus("active");
							else
								um[um.length - 1].setStatus("blocked");
					}
					if (o[6] != null)
						if ((boolean) o[6] == true)
							u.setStatus("banned");
						else if (u.getStatus().equals("banned"))
							u.setStatus("offline");
					um[um.length - 1].save();
				}
				u.save();
				session.getTransaction().commit();
				msg.setMsg("s");
			} catch (PersistentException e) {
				msg.setMsg("f");
				e.printStackTrace();
			}
			try {
				client.sendToClient(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			try {
				User u = User.loadUserByORMID((String) msg.getMsg());
				User_Membership[] um = u.user_Memberships.toArray();
				msg.setMsg(um);
				msg.setFunc(2);
			} catch (PersistentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				client.sendToClient(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method handle the messages from "histogram report" controller.
	 * @param msg
	 * @param client
	 * 
	 * The method returns lists of all Views_date and User_book of this book.
	 */
	private void histogramReportMessageHandler(Message msg, ConnectionToClient client) {
		switch (msg.getFunc()) {
		case 1:
			try {
				Book book = Book.loadBookByORMID((int) msg.getMsg());
				Views_Date[] vd = book.view.toArray();
				User_Book[] ub = book.user_Books.toArray();
				Object[] o = new Object[2];
				o[0] = vd;
				o[1] = ub;
				msg.setMsg(o);
				client.sendToClient(msg);
			} catch (Exception e) {
				e.printStackTrace();
			}

			break;
		}

	}

	/**
	 * This method notifies that the server has started to listen.
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port " + getPort());
	}

	/**
	 * This method notifies that the server has stopped listening.
	 */
	protected void serverStopped() {
		try {
			session.close();
		} catch (PersistentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Server has stopped listening for connections.");
	}

	/**
	 * This is the main method of the server.
	 * @param args
	 */
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
