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

public class Views_Date  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Views_Date() {
	}
	
	public static Views_Date loadViews_DateByORMID(java.util.Date date) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadViews_DateByORMID(session, date);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Views_Date getViews_DateByORMID(java.util.Date date) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return getViews_DateByORMID(session, date);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Views_Date loadViews_DateByORMID(java.util.Date date, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadViews_DateByORMID(session, date, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Views_Date getViews_DateByORMID(java.util.Date date, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return getViews_DateByORMID(session, date, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Views_Date loadViews_DateByORMID(PersistentSession session, java.util.Date date) throws PersistentException {
		try {
			return (Views_Date) session.load(i_book.Views_Date.class, date);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Views_Date getViews_DateByORMID(PersistentSession session, java.util.Date date) throws PersistentException {
		try {
			return (Views_Date) session.get(i_book.Views_Date.class, date);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Views_Date loadViews_DateByORMID(PersistentSession session, java.util.Date date, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Views_Date) session.load(i_book.Views_Date.class, date, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Views_Date getViews_DateByORMID(PersistentSession session, java.util.Date date, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (Views_Date) session.get(i_book.Views_Date.class, date, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryViews_Date(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return queryViews_Date(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryViews_Date(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return queryViews_Date(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Views_Date[] listViews_DateByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return listViews_DateByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Views_Date[] listViews_DateByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return listViews_DateByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryViews_Date(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.Views_Date as Views_Date");
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
	
	public static List queryViews_Date(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.Views_Date as Views_Date");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Views_Date", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Views_Date[] listViews_DateByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryViews_Date(session, condition, orderBy);
			return (Views_Date[]) list.toArray(new Views_Date[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Views_Date[] listViews_DateByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryViews_Date(session, condition, orderBy, lockMode);
			return (Views_Date[]) list.toArray(new Views_Date[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Views_Date loadViews_DateByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadViews_DateByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Views_Date loadViews_DateByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadViews_DateByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Views_Date loadViews_DateByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		Views_Date[] views_Dates = listViews_DateByQuery(session, condition, orderBy);
		if (views_Dates != null && views_Dates.length > 0)
			return views_Dates[0];
		else
			return null;
	}
	
	public static Views_Date loadViews_DateByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		Views_Date[] views_Dates = listViews_DateByQuery(session, condition, orderBy, lockMode);
		if (views_Dates != null && views_Dates.length > 0)
			return views_Dates[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateViews_DateByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return iterateViews_DateByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateViews_DateByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return iterateViews_DateByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateViews_DateByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.Views_Date as Views_Date");
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
	
	public static java.util.Iterator iterateViews_DateByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.Views_Date as Views_Date");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("Views_Date", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static Views_Date loadViews_DateByCriteria(Views_DateCriteria views_DateCriteria) {
		Views_Date[] views_Dates = listViews_DateByCriteria(views_DateCriteria);
		if(views_Dates == null || views_Dates.length == 0) {
			return null;
		}
		return views_Dates[0];
	}
	
	public static Views_Date[] listViews_DateByCriteria(Views_DateCriteria views_DateCriteria) {
		return views_DateCriteria.listViews_Date();
	}
	
	public boolean equals(Object aObj) {
		if (aObj == this)
			return true;
		if (!(aObj instanceof Views_Date))
			return false;
		Views_Date views_date = (Views_Date)aObj;
		if ((getDate() != null && !getDate().equals(views_date.getDate())) || (getDate() == null && views_date.getDate() != null))
			return false;
		return true;
	}
	
	public int hashCode() {
		int hashcode = 0;
		hashcode = hashcode + (getDate() == null ? 0 : getDate().hashCode());
		return hashcode;
	}
	
	public static Views_Date createViews_Date() {
		return new i_book.Views_Date();
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
			if(getBook() != null) {
				getBook().view.remove(this);
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
			if(getBook() != null) {
				getBook().view.remove(this);
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
	
	private void this_setOwner(Object owner, int key) {
		if (key == i_book.ORMConstants.KEY_VIEWS_DATE_BOOK) {
			this.book = (i_book.Book) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private java.util.Date date;
	
	private i_book.Book book;
	
	private int viewCount;
	
	public void setViewCount(int value) {
		this.viewCount = value;
	}
	
	public int getViewCount() {
		return viewCount;
	}
	
	public void setDate(java.util.Date value) {
		this.date = value;
	}
	
	public java.util.Date getDate() {
		return date;
	}
	
	public java.util.Date getORMID() {
		return getDate();
	}
	
	public void setBook(i_book.Book value) {
		if (book != null) {
			book.view.remove(this);
		}
		if (value != null) {
			value.view.add(this);
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
		return String.valueOf(getDate());
	}
	
}
