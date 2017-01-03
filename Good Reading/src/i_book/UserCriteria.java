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

import org.hibernate.Criteria;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class UserCriteria extends AbstractORMCriteria {
	public final StringExpression ID;
	public final StringExpression Fname;
	public final StringExpression Lname;
	public final StringExpression password;
	public final StringExpression paymentInfo;
	public final StringExpression status;
	public final CollectionExpression user_Books;
	public final CollectionExpression user_Memberships;
	public final CollectionExpression paymentrequest;
	
	public UserCriteria(Criteria criteria) {
		super(criteria);
		ID = new StringExpression("ID", this);
		Fname = new StringExpression("Fname", this);
		Lname = new StringExpression("Lname", this);
		password = new StringExpression("password", this);
		paymentInfo = new StringExpression("paymentInfo", this);
		status = new StringExpression("status", this);
		user_Books = new CollectionExpression("ORM_User_Books", this);
		user_Memberships = new CollectionExpression("ORM_User_Memberships", this);
		paymentrequest = new CollectionExpression("ORM_Paymentrequest", this);
	}
	
	public UserCriteria(PersistentSession session) {
		this(session.createCriteria(User.class));
	}
	
	public UserCriteria() throws PersistentException {
		this(i_book.IBookIncPersistentManager.instance().getSession());
	}
	
	public User_BookCriteria createUser_BooksCriteria() {
		return new User_BookCriteria(createCriteria("ORM_User_Books"));
	}
	
	public User_MembershipCriteria createUser_MembershipsCriteria() {
		return new User_MembershipCriteria(createCriteria("ORM_User_Memberships"));
	}
	
	public PaymentRequestCriteria createPaymentrequestCriteria() {
		return new PaymentRequestCriteria(createCriteria("ORM_Paymentrequest"));
	}
	
	public User uniqueUser() {
		return (User) super.uniqueResult();
	}
	
	public User[] listUser() {
		java.util.List list = super.list();
		return (User[]) list.toArray(new User[list.size()]);
	}
}

