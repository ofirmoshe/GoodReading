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

public class User extends i_book.GeneralUser {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public User() {
	}

	public static User loadUserByORMID(String ID) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadUserByORMID(session, ID);
		} catch (Exception e) {
			throw new PersistentException(e);
		}
	}

	public static User getUserByORMID(String ID) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return getUserByORMID(session, ID);
		} catch (Exception e) {
			throw new PersistentException(e);
		}
	}

	public static User loadUserByORMID(String ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadUserByORMID(session, ID, lockMode);
		} catch (Exception e) {
			throw new PersistentException(e);
		}
	}

	public static User getUserByORMID(String ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return getUserByORMID(session, ID, lockMode);
		} catch (Exception e) {
			throw new PersistentException(e);
		}
	}

	public static User loadUserByORMID(PersistentSession session, String ID) throws PersistentException {
		try {
			return (User) session.load(i_book.User.class, ID);
		} catch (Exception e) {
			throw new PersistentException(e);
		}
	}

	public static User getUserByORMID(PersistentSession session, String ID) throws PersistentException {
		try {
			return (User) session.get(i_book.User.class, ID);
		} catch (Exception e) {
			throw new PersistentException(e);
		}
	}

	public static User loadUserByORMID(PersistentSession session, String ID, org.hibernate.LockMode lockMode)
			throws PersistentException {
		try {
			return (User) session.load(i_book.User.class, ID, lockMode);
		} catch (Exception e) {
			throw new PersistentException(e);
		}
	}

	public static User getUserByORMID(PersistentSession session, String ID, org.hibernate.LockMode lockMode)
			throws PersistentException {
		try {
			return (User) session.get(i_book.User.class, ID, lockMode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}

	public static List queryUser(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return queryUser(session, condition, orderBy);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}

	public static List queryUser(String condition, String orderBy, org.hibernate.LockMode lockMode)
			throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return queryUser(session, condition, orderBy, lockMode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}

	public static User[] listUserByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return listUserByQuery(session, condition, orderBy);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}

	public static User[] listUserByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode)
			throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return listUserByQuery(session, condition, orderBy, lockMode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}

	public static List queryUser(PersistentSession session, String condition, String orderBy)
			throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.User as User");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}

	public static List queryUser(PersistentSession session, String condition, String orderBy,
			org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.User as User");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("User", lockMode);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}

	public static User[] listUserByQuery(PersistentSession session, String condition, String orderBy)
			throws PersistentException {
		try {
			List list = queryUser(session, condition, orderBy);
			return (User[]) list.toArray(new User[list.size()]);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}

	public static User[] listUserByQuery(PersistentSession session, String condition, String orderBy,
			org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryUser(session, condition, orderBy, lockMode);
			return (User[]) list.toArray(new User[list.size()]);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}

	public static User loadUserByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadUserByQuery(session, condition, orderBy);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}

	public static User loadUserByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode)
			throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadUserByQuery(session, condition, orderBy, lockMode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}

	public static User loadUserByQuery(PersistentSession session, String condition, String orderBy)
			throws PersistentException {
		User[] users = listUserByQuery(session, condition, orderBy);
		if (users != null && users.length > 0)
			return users[0];
		else
			return null;
	}

	public static User loadUserByQuery(PersistentSession session, String condition, String orderBy,
			org.hibernate.LockMode lockMode) throws PersistentException {
		User[] users = listUserByQuery(session, condition, orderBy, lockMode);
		if (users != null && users.length > 0)
			return users[0];
		else
			return null;
	}

	public static java.util.Iterator iterateUserByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return iterateUserByQuery(session, condition, orderBy);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}

	public static java.util.Iterator iterateUserByQuery(String condition, String orderBy,
			org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return iterateUserByQuery(session, condition, orderBy, lockMode);
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}

	public static java.util.Iterator iterateUserByQuery(PersistentSession session, String condition, String orderBy)
			throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.User as User");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			return query.iterate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}

	public static java.util.Iterator iterateUserByQuery(PersistentSession session, String condition, String orderBy,
			org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.User as User");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("User", lockMode);
			return query.iterate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}

	public static User loadUserByCriteria(UserCriteria userCriteria) {
		User[] users = listUserByCriteria(userCriteria);
		if (users == null || users.length == 0) {
			return null;
		}
		return users[0];
	}

	public static User[] listUserByCriteria(UserCriteria userCriteria) {
		return userCriteria.listUser();
	}

	public static User createUser() {
		return new i_book.User();
	}

	public boolean deleteAndDissociate() throws PersistentException {
		try {
			i_book.User_Book[] lUser_Bookss = user_Books.toArray();
			for (int i = 0; i < lUser_Bookss.length; i++) {
				lUser_Bookss[i].setUser(null);
			}
			i_book.User_Membership[] lUser_Membershipss = user_Memberships.toArray();
			for (int i = 0; i < lUser_Membershipss.length; i++) {
				lUser_Membershipss[i].setUser(null);
			}
			i_book.PaymentRequest[] lPaymentrequests = paymentrequest.toArray();
			for (int i = 0; i < lPaymentrequests.length; i++) {
				lPaymentrequests[i].setUser(null);
			}
			return delete();
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}

	public boolean deleteAndDissociate(org.orm.PersistentSession session) throws PersistentException {
		try {
			i_book.User_Book[] lUser_Bookss = user_Books.toArray();
			for (int i = 0; i < lUser_Bookss.length; i++) {
				lUser_Bookss[i].setUser(null);
			}
			i_book.User_Membership[] lUser_Membershipss = user_Memberships.toArray();
			for (int i = 0; i < lUser_Membershipss.length; i++) {
				lUser_Membershipss[i].setUser(null);
			}
			i_book.PaymentRequest[] lPaymentrequests = paymentrequest.toArray();
			for (int i = 0; i < lPaymentrequests.length; i++) {
				lPaymentrequests[i].setUser(null);
			}
			try {
				session.delete(this);
				return true;
			} catch (Exception e) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}

	private java.util.Set this_getSet(int key) {
		if (key == i_book.ORMConstants.KEY_USER_USER_BOOKS) {
			return ORM_user_Books;
		} else if (key == i_book.ORMConstants.KEY_USER_USER_MEMBERSHIPS) {
			return ORM_user_Memberships;
		} else if (key == i_book.ORMConstants.KEY_USER_PAYMENTREQUEST) {
			return ORM_paymentrequest;
		}

		return null;
	}

	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public java.util.Set getSet(int key) {
			return this_getSet(key);
		}

	};

	private String paymentInfo;

	private String status;

	private java.util.Set ORM_user_Books = new java.util.HashSet();

	private java.util.Set ORM_user_Memberships = new java.util.HashSet();

	private java.util.Set ORM_paymentrequest = new java.util.HashSet();

	public void setPaymentInfo(String value) {
		this.paymentInfo = value;
	}

	public String getPaymentInfo() {
		return paymentInfo;
	}

	public void setStatus(String value) {
		this.status = value;
	}

	public String getStatus() {
		return status;
	}

	public i_book.Book[] getBooks() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for (java.util.Iterator lIter = user_Books.getIterator(); lIter.hasNext();) {
			lValues.add(((i_book.User_Book) lIter.next()).getBook());
		}
		return (i_book.Book[]) lValues.toArray(new i_book.Book[lValues.size()]);
	}

	public void removeBook(i_book.Book aBook) {
		i_book.User_Book[] lUser_Books = user_Books.toArray();
		for (int i = 0; i < lUser_Books.length; i++) {
			if (lUser_Books[i].getBook().equals(aBook)) {
				user_Books.remove(lUser_Books[i]);
			}
		}
	}

	public void addBook(i_book.User_Book aUser_Book, i_book.Book aBook) {
		aUser_Book.setBook(aBook);
		user_Books.add(aUser_Book);
	}

	public i_book.User_Book getUser_BookByBook(i_book.Book aBook) {
		i_book.User_Book[] lUser_Books = user_Books.toArray();
		for (int i = 0; i < lUser_Books.length; i++) {
			if (lUser_Books[i].getBook().equals(aBook)) {
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

	public final i_book.User_BookSetCollection user_Books = new i_book.User_BookSetCollection(this, _ormAdapter,
			i_book.ORMConstants.KEY_USER_USER_BOOKS, i_book.ORMConstants.KEY_USER_BOOK_USER,
			i_book.ORMConstants.KEY_MUL_ONE_TO_MANY);

	public i_book.Membership[] getMemberships() {
		java.util.ArrayList lValues = new java.util.ArrayList(5);
		for (java.util.Iterator lIter = user_Memberships.getIterator(); lIter.hasNext();) {
			lValues.add(((i_book.User_Membership) lIter.next()).getMembership());
		}
		return (i_book.Membership[]) lValues.toArray(new i_book.Membership[lValues.size()]);
	}

	public void removeMembership(i_book.Membership aMembership) {
		i_book.User_Membership[] lUser_Memberships = user_Memberships.toArray();
		for (int i = 0; i < lUser_Memberships.length; i++) {
			if (lUser_Memberships[i].getMembership().equals(aMembership)) {
				user_Memberships.remove(lUser_Memberships[i]);
			}
		}
	}

	public void addMembership(i_book.User_Membership aUser_Membership, i_book.Membership aMembership) {
		aUser_Membership.setMembership(aMembership);
		user_Memberships.add(aUser_Membership);
	}

	public i_book.User_Membership getUser_MembershipByMembership(i_book.Membership aMembership) {
		i_book.User_Membership[] lUser_Memberships = user_Memberships.toArray();
		for (int i = 0; i < lUser_Memberships.length; i++) {
			if (lUser_Memberships[i].getMembership().equals(aMembership)) {
				return lUser_Memberships[i];
			}
		}
		return null;
	}

	private void setORM_User_Memberships(java.util.Set value) {
		this.ORM_user_Memberships = value;
	}

	private java.util.Set getORM_User_Memberships() {
		return ORM_user_Memberships;
	}

	public final i_book.User_MembershipSetCollection user_Memberships = new i_book.User_MembershipSetCollection(this,
			_ormAdapter, i_book.ORMConstants.KEY_USER_USER_MEMBERSHIPS, i_book.ORMConstants.KEY_USER_MEMBERSHIP_USER,
			i_book.ORMConstants.KEY_MUL_ONE_TO_MANY);

	private void setORM_Paymentrequest(java.util.Set value) {
		this.ORM_paymentrequest = value;
	}

	private java.util.Set getORM_Paymentrequest() {
		return ORM_paymentrequest;
	}

	public final i_book.PaymentRequestSetCollection paymentrequest = new i_book.PaymentRequestSetCollection(this,
			_ormAdapter, i_book.ORMConstants.KEY_USER_PAYMENTREQUEST, i_book.ORMConstants.KEY_PAYMENTREQUEST_USER,
			i_book.ORMConstants.KEY_MUL_ONE_TO_MANY);

	public String toString() {
		return super.toString();
	}

}
