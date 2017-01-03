/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 * 
 * This is an automatic generated file. It will be regenerated every time 
 * you generate persistence class.
 * 
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: Ort Braude College
 * License Type: Academic
 */
package i_book;

import org.orm.*;
import org.hibernate.Query;
import org.hibernate.LockMode;
import java.util.List;

import java.io.Serializable;
public class User_Book implements Serializable {
	public User_Book() {
	}
	
	public static User_Book loadUser_BookByORMID(i_book.Book book, i_book.User user) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadUser_BookByORMID(session, book, user);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Book getUser_BookByORMID(i_book.Book book, i_book.User user) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return getUser_BookByORMID(session, book, user);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Book loadUser_BookByORMID(i_book.Book book, i_book.User user, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadUser_BookByORMID(session, book, user, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Book getUser_BookByORMID(i_book.Book book, i_book.User user, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return getUser_BookByORMID(session, book, user, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Book loadUser_BookByORMID(PersistentSession session, i_book.Book book, i_book.User user) throws PersistentException {
		try {
			User_Book user_book = new i_book.User_Book();
			user_book.setORM_Book(book);
			user_book.setORM_User(user);
			
			return (User_Book) session.load(i_book.User_Book.class, user_book);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Book getUser_BookByORMID(PersistentSession session, i_book.Book book, i_book.User user) throws PersistentException {
		try {
			User_Book user_book = new i_book.User_Book();
			user_book.setORM_Book(book);
			user_book.setORM_User(user);
			
			return (User_Book) session.get(i_book.User_Book.class, user_book);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Book loadUser_BookByORMID(PersistentSession session, i_book.Book book, i_book.User user, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			User_Book user_book = new i_book.User_Book();
			user_book.setORM_Book(book);
			user_book.setORM_User(user);
			
			return (User_Book) session.load(i_book.User_Book.class, user_book, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Book getUser_BookByORMID(PersistentSession session, i_book.Book book, i_book.User user, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			User_Book user_book = new i_book.User_Book();
			user_book.setORM_Book(book);
			user_book.setORM_User(user);
			
			return (User_Book) session.get(i_book.User_Book.class, user_book, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryUser_Book(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return queryUser_Book(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryUser_Book(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return queryUser_Book(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Book[] listUser_BookByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return listUser_BookByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Book[] listUser_BookByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return listUser_BookByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryUser_Book(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.User_Book as User_Book");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryUser_Book(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.User_Book as User_Book");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("User_Book", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Book[] listUser_BookByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryUser_Book(session, condition, orderBy);
			return (User_Book[]) list.toArray(new User_Book[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Book[] listUser_BookByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryUser_Book(session, condition, orderBy, lockMode);
			return (User_Book[]) list.toArray(new User_Book[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Book loadUser_BookByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadUser_BookByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Book loadUser_BookByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadUser_BookByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Book loadUser_BookByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		User_Book[] user_Books = listUser_BookByQuery(session, condition, orderBy);
		if (user_Books != null && user_Books.length > 0)
			return user_Books[0];
		else
			return null;
	}
	
	public static User_Book loadUser_BookByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		User_Book[] user_Books = listUser_BookByQuery(session, condition, orderBy, lockMode);
		if (user_Books != null && user_Books.length > 0)
			return user_Books[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateUser_BookByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return iterateUser_BookByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateUser_BookByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return iterateUser_BookByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateUser_BookByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.User_Book as User_Book");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateUser_BookByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.User_Book as User_Book");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("User_Book", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Book loadUser_BookByCriteria(User_BookCriteria user_BookCriteria) {
		User_Book[] user_Books = listUser_BookByCriteria(user_BookCriteria);
		if(user_Books == null || user_Books.length == 0) {
			return null;
		}
		return user_Books[0];
	}
	
	public static User_Book[] listUser_BookByCriteria(User_BookCriteria user_BookCriteria) {
		return user_BookCriteria.listUser_Book();
	}
	
	public boolean equals(Object aObj) {
		if (aObj == this)
			return true;
		if (!(aObj instanceof User_Book))
			return false;
		User_Book user_book = (User_Book)aObj;
		if (getBook() == null) {
			if (user_book.getBook() != null)
				return false;
		}
		else if (!getBook().equals(user_book.getBook()))
			return false;
		if (getUser() == null) {
			if (user_book.getUser() != null)
				return false;
		}
		else if (!getUser().equals(user_book.getUser()))
			return false;
		return true;
	}
	
	public int hashCode() {
		int hashcode = 0;
		if (getBook() != null) {
			hashcode = hashcode + (int) getBook().getORMID();
		}
		if (getUser() != null) {
			hashcode = hashcode + (getUser().getORMID() == null ? 0 : getUser().getORMID().hashCode());
		}
		return hashcode;
	}
	
	public static User_Book createUser_Book() {
		return new i_book.User_Book();
	}
	
	public boolean save() throws PersistentException {
		try {
			i_book.IBookIncPersistentManager.instance().saveObject(this);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public boolean delete() throws PersistentException {
		try {
			i_book.IBookIncPersistentManager.instance().deleteObject(this);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public boolean refresh() throws PersistentException {
		try {
			i_book.IBookIncPersistentManager.instance().getSession().refresh(this);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public boolean evict() throws PersistentException {
		try {
			i_book.IBookIncPersistentManager.instance().getSession().evict(this);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate()throws PersistentException {
		try {
			i_book.Book book = getBook();
			if(getBook() != null) {
				getBook().user_Books.remove(this);
			}
			setORM_Book(book);
			
			i_book.User user = getUser();
			if(getUser() != null) {
				getUser().user_Books.remove(this);
			}
			setORM_User(user);
			
			i_book.Review[] lReviews = review.toArray();
			for(int i = 0; i < lReviews.length; i++) {
				lReviews[i].setUsersbook(null);
			}
			return delete();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(org.orm.PersistentSession session)throws PersistentException {
		try {
			i_book.Book book = getBook();
			if(getBook() != null) {
				getBook().user_Books.remove(this);
			}
			setORM_Book(book);
			
			i_book.User user = getUser();
			if(getUser() != null) {
				getUser().user_Books.remove(this);
			}
			setORM_User(user);
			
			i_book.Review[] lReviews = review.toArray();
			for(int i = 0; i < lReviews.length; i++) {
				lReviews[i].setUsersbook(null);
			}
			try {
				session.delete(this);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	private java.util.Set this_getSet (int key) {
		if (key == i_book.ORMConstants.KEY_USER_BOOK_REVIEW) {
			return ORM_review;
		}
		
		return null;
	}
	
	private void this_setOwner(Object owner, int key) {
		if (key == i_book.ORMConstants.KEY_USER_BOOK_USER) {
			this.user = (i_book.User) owner;
		}
		
		else if (key == i_book.ORMConstants.KEY_USER_BOOK_BOOK) {
			this.book = (i_book.Book) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private java.util.Date pDate;
	
	private String status;
	
	private i_book.Book book;
	
	private int bookId;
	
	private void setBookId(int value) {
		this.bookId = value;
	}
	
	public int getBookId() {
		return bookId;
	}
	
	private i_book.User user;
	
	private String userId;
	
	private void setUserId(String value) {
		this.userId = value;
	}
	
	public String getUserId() {
		return userId;
	}
	
	private java.util.Set ORM_review = new java.util.HashSet();
	
	public void setpDate(java.util.Date value) {
		this.pDate = value;
	}
	
	public java.util.Date getpDate() {
		return pDate;
	}
	
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getStatus() {
		return status;
	}
	
	private void setORM_Review(java.util.Set value) {
		this.ORM_review = value;
	}
	
	private java.util.Set getORM_Review() {
		return ORM_review;
	}
	
	public final i_book.ReviewSetCollection review = new i_book.ReviewSetCollection(this, _ormAdapter, i_book.ORMConstants.KEY_USER_BOOK_REVIEW, i_book.ORMConstants.KEY_REVIEW_USERSBOOK, i_book.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public void setUser(i_book.User value) {
		if (user != null) {
			user.user_Books.remove(this);
		}
		if (value != null) {
			value.user_Books.add(this);
		}
	}
	
	public i_book.User getUser() {
		return user;
	}
	
	/**
	 * This method is for internal use only.
	 */
	private void setORM_User(i_book.User value) {
		this.user = value;
	}
	
	private i_book.User getORM_User() {
		return user;
	}
	
	public void setBook(i_book.Book value) {
		if (book != null) {
			book.user_Books.remove(this);
		}
		if (value != null) {
			value.user_Books.add(this);
		}
	}
	
	public i_book.Book getBook() {
		return book;
	}
	
	/**
	 * This method is for internal use only.
	 */
	private void setORM_Book(i_book.Book value) {
		this.book = value;
	}
	
	private i_book.Book getORM_Book() {
		return book;
	}
	
	public String toString() {
		return String.valueOf(((getBook() == null) ? "" : String.valueOf(getBook().getORMID())) + " " + ((getUser() == null) ? "" : String.valueOf(getUser().getORMID())));
	}
	
}
