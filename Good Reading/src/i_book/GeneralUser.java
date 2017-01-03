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

public class GeneralUser implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GeneralUser() {
	}
	
	public static GeneralUser loadGeneralUserByORMID(String ID) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadGeneralUserByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static GeneralUser getGeneralUserByORMID(String ID) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return getGeneralUserByORMID(session, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static GeneralUser loadGeneralUserByORMID(String ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadGeneralUserByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static GeneralUser getGeneralUserByORMID(String ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return getGeneralUserByORMID(session, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static GeneralUser loadGeneralUserByORMID(PersistentSession session, String ID) throws PersistentException {
		try {
			return (GeneralUser) session.load(i_book.GeneralUser.class, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static GeneralUser getGeneralUserByORMID(PersistentSession session, String ID) throws PersistentException {
		try {
			return (GeneralUser) session.get(i_book.GeneralUser.class, ID);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static GeneralUser loadGeneralUserByORMID(PersistentSession session, String ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (GeneralUser) session.load(i_book.GeneralUser.class, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static GeneralUser getGeneralUserByORMID(PersistentSession session, String ID, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			return (GeneralUser) session.get(i_book.GeneralUser.class, ID, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryGeneralUser(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return queryGeneralUser(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryGeneralUser(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return queryGeneralUser(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static GeneralUser[] listGeneralUserByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return listGeneralUserByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static GeneralUser[] listGeneralUserByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return listGeneralUserByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryGeneralUser(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.GeneralUser as GeneralUser");
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
	
	public static List queryGeneralUser(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.GeneralUser as GeneralUser");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("GeneralUser", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static GeneralUser[] listGeneralUserByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryGeneralUser(session, condition, orderBy);
			return (GeneralUser[]) list.toArray(new GeneralUser[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static GeneralUser[] listGeneralUserByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryGeneralUser(session, condition, orderBy, lockMode);
			return (GeneralUser[]) list.toArray(new GeneralUser[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static GeneralUser loadGeneralUserByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadGeneralUserByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static GeneralUser loadGeneralUserByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadGeneralUserByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static GeneralUser loadGeneralUserByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		GeneralUser[] generalUsers = listGeneralUserByQuery(session, condition, orderBy);
		if (generalUsers != null && generalUsers.length > 0)
			return generalUsers[0];
		else
			return null;
	}
	
	public static GeneralUser loadGeneralUserByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		GeneralUser[] generalUsers = listGeneralUserByQuery(session, condition, orderBy, lockMode);
		if (generalUsers != null && generalUsers.length > 0)
			return generalUsers[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateGeneralUserByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return iterateGeneralUserByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateGeneralUserByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return iterateGeneralUserByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateGeneralUserByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.GeneralUser as GeneralUser");
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
	
	public static java.util.Iterator iterateGeneralUserByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.GeneralUser as GeneralUser");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("GeneralUser", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static GeneralUser loadGeneralUserByCriteria(GeneralUserCriteria generalUserCriteria) {
		GeneralUser[] generalUsers = listGeneralUserByCriteria(generalUserCriteria);
		if(generalUsers == null || generalUsers.length == 0) {
			return null;
		}
		return generalUsers[0];
	}
	
	public static GeneralUser[] listGeneralUserByCriteria(GeneralUserCriteria generalUserCriteria) {
		return generalUserCriteria.listGeneralUser();
	}
	
	public boolean equals(Object aObj) {
		if (aObj == this)
			return true;
		if (!(aObj instanceof GeneralUser))
			return false;
		GeneralUser generaluser = (GeneralUser)aObj;
		if ((getID() != null && !getID().equals(generaluser.getID())) || (getID() == null && generaluser.getID() != null))
			return false;
		return true;
	}
	
	public int hashCode() {
		int hashcode = 0;
		hashcode = hashcode + (getID() == null ? 0 : getID().hashCode());
		return hashcode;
	}
	
	public static GeneralUser createGeneralUser() {
		return new i_book.GeneralUser();
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
	
	private String ID;
	
	private String Fname;
	
	private String Lname;
	
	private String password;
	
	public void setID(String value) {
		this.ID = value;
	}
	
	public String getID() {
		return ID;
	}
	
	public String getORMID() {
		return getID();
	}
	
	public void setFname(String value) {
		this.Fname = value;
	}
	
	public String getFname() {
		return Fname;
	}
	
	public void setLname(String value) {
		this.Lname = value;
	}
	
	public String getLname() {
		return Lname;
	}
	
	public void setPassword(String value) {
		this.password = value;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String toString() {
		return String.valueOf(getID());
	}
	
}
