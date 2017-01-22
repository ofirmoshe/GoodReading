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
public class User_Membership implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public User_Membership() {
	}
	
	public static User_Membership loadUser_MembershipByORMID(i_book.Membership membership, i_book.User user) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadUser_MembershipByORMID(session, membership, user);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Membership getUser_MembershipByORMID(i_book.Membership membership, i_book.User user) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return getUser_MembershipByORMID(session, membership, user);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Membership loadUser_MembershipByORMID(i_book.Membership membership, i_book.User user, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadUser_MembershipByORMID(session, membership, user, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Membership getUser_MembershipByORMID(i_book.Membership membership, i_book.User user, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return getUser_MembershipByORMID(session, membership, user, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Membership loadUser_MembershipByORMID(PersistentSession session, i_book.Membership membership, i_book.User user) throws PersistentException {
		try {
			User_Membership user_membership = new i_book.User_Membership();
			user_membership.setORM_Membership(membership);
			user_membership.setORM_User(user);
			
			return (User_Membership) session.load(i_book.User_Membership.class, user_membership);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Membership getUser_MembershipByORMID(PersistentSession session, i_book.Membership membership, i_book.User user) throws PersistentException {
		try {
			User_Membership user_membership = new i_book.User_Membership();
			user_membership.setORM_Membership(membership);
			user_membership.setORM_User(user);
			
			return (User_Membership) session.get(i_book.User_Membership.class, user_membership);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Membership loadUser_MembershipByORMID(PersistentSession session, i_book.Membership membership, i_book.User user, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			User_Membership user_membership = new i_book.User_Membership();
			user_membership.setORM_Membership(membership);
			user_membership.setORM_User(user);
			
			return (User_Membership) session.load(i_book.User_Membership.class, user_membership, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Membership getUser_MembershipByORMID(PersistentSession session, i_book.Membership membership, i_book.User user, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			User_Membership user_membership = new i_book.User_Membership();
			user_membership.setORM_Membership(membership);
			user_membership.setORM_User(user);
			
			return (User_Membership) session.get(i_book.User_Membership.class, user_membership, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryUser_Membership(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return queryUser_Membership(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryUser_Membership(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return queryUser_Membership(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Membership[] listUser_MembershipByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return listUser_MembershipByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Membership[] listUser_MembershipByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return listUser_MembershipByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static List queryUser_Membership(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.User_Membership as User_Membership");
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
	
	public static List queryUser_Membership(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.User_Membership as User_Membership");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("User_Membership", lockMode);
			return query.list();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Membership[] listUser_MembershipByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		try {
			List list = queryUser_Membership(session, condition, orderBy);
			return (User_Membership[]) list.toArray(new User_Membership[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Membership[] listUser_MembershipByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			List list = queryUser_Membership(session, condition, orderBy, lockMode);
			return (User_Membership[]) list.toArray(new User_Membership[list.size()]);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Membership loadUser_MembershipByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadUser_MembershipByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Membership loadUser_MembershipByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return loadUser_MembershipByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Membership loadUser_MembershipByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		User_Membership[] user_Memberships = listUser_MembershipByQuery(session, condition, orderBy);
		if (user_Memberships != null && user_Memberships.length > 0)
			return user_Memberships[0];
		else
			return null;
	}
	
	public static User_Membership loadUser_MembershipByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		User_Membership[] user_Memberships = listUser_MembershipByQuery(session, condition, orderBy, lockMode);
		if (user_Memberships != null && user_Memberships.length > 0)
			return user_Memberships[0];
		else
			return null;
	}
	
	public static java.util.Iterator iterateUser_MembershipByQuery(String condition, String orderBy) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return iterateUser_MembershipByQuery(session, condition, orderBy);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateUser_MembershipByQuery(String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		try {
			PersistentSession session = i_book.IBookIncPersistentManager.instance().getSession();
			return iterateUser_MembershipByQuery(session, condition, orderBy, lockMode);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static java.util.Iterator iterateUser_MembershipByQuery(PersistentSession session, String condition, String orderBy) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.User_Membership as User_Membership");
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
	
	public static java.util.Iterator iterateUser_MembershipByQuery(PersistentSession session, String condition, String orderBy, org.hibernate.LockMode lockMode) throws PersistentException {
		StringBuffer sb = new StringBuffer("From i_book.User_Membership as User_Membership");
		if (condition != null)
			sb.append(" Where ").append(condition);
		if (orderBy != null)
			sb.append(" Order By ").append(orderBy);
		try {
			Query query = session.createQuery(sb.toString());
			query.setLockMode("User_Membership", lockMode);
			return query.iterate();
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public static User_Membership loadUser_MembershipByCriteria(User_MembershipCriteria user_MembershipCriteria) {
		User_Membership[] user_Memberships = listUser_MembershipByCriteria(user_MembershipCriteria);
		if(user_Memberships == null || user_Memberships.length == 0) {
			return null;
		}
		return user_Memberships[0];
	}
	
	public static User_Membership[] listUser_MembershipByCriteria(User_MembershipCriteria user_MembershipCriteria) {
		return user_MembershipCriteria.listUser_Membership();
	}
	
	public boolean equals(Object aObj) {
		if (aObj == this)
			return true;
		if (!(aObj instanceof User_Membership))
			return false;
		User_Membership user_membership = (User_Membership)aObj;
		if (getMembership() == null) {
			if (user_membership.getMembership() != null)
				return false;
		}
		else if (!getMembership().equals(user_membership.getMembership()))
			return false;
		if (getUser() == null) {
			if (user_membership.getUser() != null)
				return false;
		}
		else if (!getUser().equals(user_membership.getUser()))
			return false;
		return true;
	}
	
	public int hashCode() {
		int hashcode = 0;
		if (getMembership() != null) {
			hashcode = hashcode + (int) getMembership().getORMID();
		}
		if (getUser() != null) {
			hashcode = hashcode + (getUser().getORMID() == null ? 0 : getUser().getORMID().hashCode());
		}
		return hashcode;
	}
	
	public static User_Membership createUser_Membership() {
		return new i_book.User_Membership();
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
			i_book.Membership membership = getMembership();
			if(getMembership() != null) {
				getMembership().user_Memberships.remove(this);
			}
			setORM_Membership(membership);
			
			i_book.User user = getUser();
			if(getUser() != null) {
				getUser().user_Memberships.remove(this);
			}
			setORM_User(user);
			
			return delete();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new PersistentException(e);
		}
	}
	
	public boolean deleteAndDissociate(org.orm.PersistentSession session)throws PersistentException {
		try {
			i_book.Membership membership = getMembership();
			if(getMembership() != null) {
				getMembership().user_Memberships.remove(this);
			}
			setORM_Membership(membership);
			
			i_book.User user = getUser();
			if(getUser() != null) {
				getUser().user_Memberships.remove(this);
			}
			setORM_User(user);
			
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
		if (key == i_book.ORMConstants.KEY_USER_MEMBERSHIP_USER) {
			this.user = (i_book.User) owner;
		}
		
		else if (key == i_book.ORMConstants.KEY_USER_MEMBERSHIP_MEMBERSHIP) {
			this.membership = (i_book.Membership) owner;
		}
	}
	
	org.orm.util.ORMAdapter _ormAdapter = new org.orm.util.AbstractORMAdapter() {
		public void setOwner(Object owner, int key) {
			this_setOwner(owner, key);
		}
		
	};
	
	private java.util.Date s_date;
	
	private java.util.Date e_date;
	
	private String status;
	
	private i_book.Membership membership;
	
	private int membershipId;
	
	private void setMembershipId(int value) {
		this.membershipId = value;
	}
	
	public int getMembershipId() {
		return membershipId;
	}
	
	private i_book.User user;
	
	private String userId;
	
	private void setUserId(String value) {
		this.userId = value;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setS_date(java.util.Date value) {
		this.s_date = value;
	}
	
	public java.util.Date getS_date() {
		return s_date;
	}
	
	public void setE_date(java.util.Date value) {
		this.e_date = value;
	}
	
	public java.util.Date getE_date() {
		return e_date;
	}
	
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setUser(i_book.User value) {
		if (user != null) {
			user.user_Memberships.remove(this);
		}
		if (value != null) {
			value.user_Memberships.add(this);
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
	
	public void setMembership(i_book.Membership value) {
		if (membership != null) {
			membership.user_Memberships.remove(this);
		}
		if (value != null) {
			value.user_Memberships.add(this);
		}
	}
	
	public i_book.Membership getMembership() {
		return membership;
	}
	
	/**
	 * This method is for internal use only.
	 */
	private void setORM_Membership(i_book.Membership value) {
		this.membership = value;
	}
	
	private i_book.Membership getORM_Membership() {
		return membership;
	}
	
	public String toString() {
		return String.valueOf(((getMembership() == null) ? "" : String.valueOf(getMembership().getORMID())) + " " + ((getUser() == null) ? "" : String.valueOf(getUser().getORMID())));
	}
	
}
