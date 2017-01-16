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

import java.io.Serializable;
import java.util.List;

public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Book() {
	}
	
	public static Book loadBookByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadBookByORMID(session, ID);
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Book getBookByORMID(int ID) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return getBookByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Book loadBookByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadBookByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Book getBookByORMID(int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return getBookByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Book loadBookByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Book) session.load(i_book.Book.class, new Integer(ID));
		}
		catch (Exception e) {
			throw new PersistentException(e);
		}
	}
	
	public static Book getBookByORMID(PersistentSession session, int ID) throws PersistentException {
		try {
			return (Book) session.get(i_book.Book.class, new Integer(ID));
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Book loadBookByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Book) session.load(i_book.Book.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Book getBookByORMID(PersistentSession session, int ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Book) session.get(i_book.Book.class, new Integer(ID), lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryBook(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return queryBook(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryBook(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return queryBook(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Book[] listBookByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return listBookByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Book[] listBookByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return listBookByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryBook(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.Book as Book");
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
	
	public static List queryBook(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.Book as Book");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Book", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Book[] listBookByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryBook(session, condition, orderBy);
			return (Book[]) list.toArray(new Book[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Book[] listBookByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryBook(session, condition, orderBy, lockMode);
			return (Book[]) list.toArray(new Book[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Book loadBookByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadBookByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Book loadBookByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadBookByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Book loadBookByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Book[] books = listBookByQuery(session, condition, orderBy);
		if (books != null && books.length > 0)
			return books[0];
		else
			return null;
	}
	
	public static Book loadBookByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Book[] books = listBookByQuery(session, condition, orderBy, lockMode);
		if (books != null && books.length > 0)
			return books[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateBookByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return iterateBookByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateBookByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return iterateBookByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateBookByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.Book as Book");
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
	
	public static java.util.Iterator iterateBookByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.Book as Book");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Book", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Book loadBookByCriteria(BookCriteria bookCriteria) {
		Book[] books = listBookByCriteria(bookCriteria);
		if(books == null || books.length == 0) {
			return null;
		}
		return books[0];
	}
	
	public static Book[] listBookByCriteria(BookCriteria bookCriteria) {
		return bookCriteria.listBook();
	}
	
	public static Book createBook() {
		return new i_book.Book();
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
			i_book.Views_Date[] lViews = view.toArray();
			for(int i = 0; i < lViews.length; i++) {
				lViews[i].setBook(null);
			}
			i_book.User_Book[] lUser_Bookss = user_Books.toArray();
			for(int i = 0; i < lUser_Bookss.length; i++) {
				lUser_Bookss[i].setBook(null);
			}
			i_book.Author[] lAuthors = author.toArray();
			for(int i = 0; i < lAuthors.length; i++) {
				lAuthors[i].book.remove(this);
			}
			i_book.Keyword[] lKeywords = keyword.toArray();
			for(int i = 0; i < lKeywords.length; i++) {
				lKeywords[i].book.remove(this);
			}
			i_book.PaymentRequest[] lPaymentRequests = paymentRequest.toArray();
			for(int i = 0; i < lPaymentRequests.length; i++) {
				lPaymentRequests[i].setBook(null);
			}
			i_book.Field[] lFields = field.toArray();
			for(int i = 0; i < lFields.length; i++) {
				lFields[i].book.remove(this);
			}
			i_book.Subject[] lSubjects = subject.toArray();
			for(int i = 0; i < lSubjects.length; i++) {
				lSubjects[i].book.remove(this);
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
			i_book.Views_Date[] lViews = view.toArray();
			for(int i = 0; i < lViews.length; i++) {
				lViews[i].setBook(null);
			}
			i_book.User_Book[] lUser_Bookss = user_Books.toArray();
			for(int i = 0; i < lUser_Bookss.length; i++) {
				lUser_Bookss[i].setBook(null);
			}
			i_book.Author[] lAuthors = author.toArray();
			for(int i = 0; i < lAuthors.length; i++) {
				lAuthors[i].book.remove(this);
			}
			i_book.Keyword[] lKeywords = keyword.toArray();
			for(int i = 0; i < lKeywords.length; i++) {
				lKeywords[i].book.remove(this);
			}
			i_book.PaymentRequest[] lPaymentRequests = paymentRequest.toArray();
			for(int i = 0; i < lPaymentRequests.length; i++) {
				lPaymentRequests[i].setBook(null);
			}
			i_book.Field[] lFields = field.toArray();
			for(int i = 0; i < lFields.length; i++) {
				lFields[i].book.remove(this);
			}
			i_book.Subject[] lSubjects = subject.toArray();
			for(int i = 0; i < lSubjects.length; i++) {
				lSubjects[i].book.remove(this);
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
		if (key == i_book.ORMConstants.KEY_BOOK_VIEW) {
			return ORM_view;
		}
		else if (key == i_book.ORMConstants.KEY_BOOK_USER_BOOKS) {
			return ORM_user_Books;
		}
		else if (key == i_book.ORMConstants.KEY_BOOK_AUTHOR) {
			return ORM_author;
		}
		else if (key == i_book.ORMConstants.KEY_BOOK_KEYWORD) {
			return ORM_keyword;
		}
		else if (key == i_book.ORMConstants.KEY_BOOK_PAYMENTREQUEST) {
			return ORM_paymentRequest;
		}
		else if (key == i_book.ORMConstants.KEY_BOOK_FIELD) {
			return ORM_field;
		}
		else if (key == i_book.ORMConstants.KEY_BOOK_SUBJECT) {
			return ORM_subject;
		}
		
		return null;
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}
		
	};
	
	private int ID;
	
	private String title;
	
	private String language;
	
	private String summary;
	
	private String table_of_contents;
	
	private byte[] image;
	
	private String pdf;
	
	private String doc;
	
	private String fb2;
	
	private float price;
	
	private String status;
	
	private java.util.Set ORM_view = new java.util.HashSet();
	
	private java.util.Set ORM_user_Books = new java.util.HashSet();
	
	private java.util.Set ORM_author = new java.util.HashSet();
	
	private java.util.Set ORM_keyword = new java.util.HashSet();
	
	private java.util.Set ORM_paymentRequest = new java.util.HashSet();
	
	private java.util.Set ORM_field = new java.util.HashSet();
	
	private java.util.Set ORM_subject = new java.util.HashSet();
	
	private void setID(int value) {
		this.ID = value;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getORMID() {
		return getID();
	}
	
	public void setTitle(String value) {
		this.title = value;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setLanguage(String value) {
		this.language = value;
	}
	
	public String getLanguage() {
		return language;
	}
	
	public void setSummary(String value) {
		this.summary = value;
	}
	
	public String getSummary() {
		return summary;
	}
	
	public void setTable_of_contents(String value) {
		this.table_of_contents = value;
	}
	
	public String getTable_of_contents() {
		return table_of_contents;
	}
	
	public void setImage(byte[] value) {
		this.image = value;
	}
	
	public byte[] getImage() {
		return image;
	}
	
	public void setPdf(String value) {
		this.pdf = value;
	}
	
	public String getPdf() {
		return pdf;
	}
	
	public void setDoc(String value) {
		this.doc = value;
	}
	
	public String getDoc() {
		return doc;
	}
	
	public void setFb2(String value) {
		this.fb2 = value;
	}
	
	public String getFb2() {
		return fb2;
	}
	
	public void setPrice(float value) {
		this.price = value;
	}
	
	public float getPrice() {
		return price;
	}
	
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getStatus() {
		return status;
	}
	
	private void setORM_View(java.util.Set value) {
		this.ORM_view = value;
	}
	
	private java.util.Set getORM_View() {
		return ORM_view;
	}
	
	public final i_book.Views_DateSetCollection view = new i_book.Views_DateSetCollection(this, _ormAdapter, i_book.ORMConstants.KEY_BOOK_VIEW, i_book.ORMConstants.KEY_VIEWS_DATE_BOOK, i_book.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	public i_book.User[] getUsers() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for(java.util.Iterator lIter = user_Books.getIterator();lIter.hasNext();) {
			lValues.add(((i_book.User_Book)lIter.next()).getUser());
		}
		return (i_book.User[])lValues.toArray(new i_book.User[lValues.size()]);
	}
	
	public void removeUser(i_book.User aUser) {
		i_book.User_Book[] lUser_Books = user_Books.toArray();
		for(int i = 0; i < lUser_Books.length; i++) {
			if(lUser_Books[i].getUser().equals(aUser)) {
				user_Books.remove(lUser_Books[i]);
			}
		}
	}
	
	public void addUser(i_book.User_Book aUser_Book, i_book.User aUser) {
		aUser_Book.setUser(aUser);
		user_Books.add(aUser_Book);
	}
	
	public i_book.User_Book getUser_BookByUser(i_book.User aUser) {
		i_book.User_Book[] lUser_Books = user_Books.toArray();
		for(int i = 0; i < lUser_Books.length; i++) {
			if(lUser_Books[i].getUser().equals(aUser)) {
				return lUser_Books[i];
			}
		}
		return null;
	}
	
	private void setORM_User_Books(java.util.Set value) {
		this.ORM_user_Books = value;
	}
	
	private java.util.Set getORM_User_Books() {
		return ORM_user_Books;
	}
	
	public final i_book.User_BookSetCollection user_Books = new i_book.User_BookSetCollection(this, _ormAdapter, i_book.ORMConstants.KEY_BOOK_USER_BOOKS, i_book.ORMConstants.KEY_USER_BOOK_BOOK, i_book.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Author(java.util.Set value) {
		this.ORM_author = value;
	}
	
	private java.util.Set getORM_Author() {
		return ORM_author;
	}
	
	public final i_book.AuthorSetCollection author = new i_book.AuthorSetCollection(this, _ormAdapter, i_book.ORMConstants.KEY_BOOK_AUTHOR, i_book.ORMConstants.KEY_AUTHOR_BOOK, i_book.ORMConstants.KEY_MUL_MANY_TO_MANY);
	
	private void setORM_Keyword(java.util.Set value) {
		this.ORM_keyword = value;
	}
	
	private java.util.Set getORM_Keyword() {
		return ORM_keyword;
	}
	
	public final i_book.KeywordSetCollection keyword = new i_book.KeywordSetCollection(this, _ormAdapter, i_book.ORMConstants.KEY_BOOK_KEYWORD, i_book.ORMConstants.KEY_KEYWORD_BOOK, i_book.ORMConstants.KEY_MUL_MANY_TO_MANY);
	
	private void setORM_PaymentRequest(java.util.Set value) {
		this.ORM_paymentRequest = value;
	}
	
	private java.util.Set getORM_PaymentRequest() {
		return ORM_paymentRequest;
	}
	
	public final i_book.PaymentRequestSetCollection paymentRequest = new i_book.PaymentRequestSetCollection(this, _ormAdapter, i_book.ORMConstants.KEY_BOOK_PAYMENTREQUEST, i_book.ORMConstants.KEY_PAYMENTREQUEST_BOOK, i_book.ORMConstants.KEY_MUL_ONE_TO_MANY);
	
	private void setORM_Field(java.util.Set value) {
		this.ORM_field = value;
	}
	
	private java.util.Set getORM_Field() {
		return ORM_field;
	}
	
	public final i_book.FieldSetCollection field = new i_book.FieldSetCollection(this, _ormAdapter, i_book.ORMConstants.KEY_BOOK_FIELD, i_book.ORMConstants.KEY_FIELD_BOOK, i_book.ORMConstants.KEY_MUL_MANY_TO_MANY);
	
	private void setORM_Subject(java.util.Set value) {
		this.ORM_subject = value;
	}
	
	private java.util.Set getORM_Subject() {
		return ORM_subject;
	}
	
	public final i_book.SubjectSetCollection subject = new i_book.SubjectSetCollection(this, _ormAdapter, i_book.ORMConstants.KEY_BOOK_SUBJECT, i_book.ORMConstants.KEY_SUBJECT_BOOK, i_book.ORMConstants.KEY_MUL_MANY_TO_MANY);
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
