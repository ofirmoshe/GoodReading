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

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.orm.PersistentSession;
import org.orm.criteria.*;

public class UserDetachedCriteria extends AbstractORMDetachedCriteria {
	public final StringExpression ID;
	public final StringExpression Fname;
	public final StringExpression Lname;
	public final StringExpression password;
	public final StringExpression paymentInfo;
	public final StringExpression status;
	public final CollectionExpression user_Books;
	public final CollectionExpression user_Memberships;
	public final CollectionExpression paymentrequest;
	
	public UserDetachedCriteria() {
		super(i_book.User.class, i_book.UserCriteria.class);
		ID = new StringExpression("ID", this.getDetachedCriteria());
		Fname = new StringExpression("Fname", this.getDetachedCriteria());
		Lname = new StringExpression("Lname", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		paymentInfo = new StringExpression("paymentInfo", this.getDetachedCriteria());
		status = new StringExpression("status", this.getDetachedCriteria());
		user_Books = new CollectionExpression("ORM_User_Books", this.getDetachedCriteria());
		user_Memberships = new CollectionExpression("ORM_User_Memberships", this.getDetachedCriteria());
		paymentrequest = new CollectionExpression("ORM_Paymentrequest", this.getDetachedCriteria());
	}
	
	public UserDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, i_book.UserCriteria.class);
		ID = new StringExpression("ID", this.getDetachedCriteria());
		Fname = new StringExpression("Fname", this.getDetachedCriteria());
		Lname = new StringExpression("Lname", this.getDetachedCriteria());
		password = new StringExpression("password", this.getDetachedCriteria());
		paymentInfo = new StringExpression("paymentInfo", this.getDetachedCriteria());
		status = new StringExpression("status", this.getDetachedCriteria());
		user_Books = new CollectionExpression("ORM_User_Books", this.getDetachedCriteria());
		user_Memberships = new CollectionExpression("ORM_User_Memberships", this.getDetachedCriteria());
		paymentrequest = new CollectionExpression("ORM_Paymentrequest", this.getDetachedCriteria());
	}
	
	public User_BookDetachedCriteria createUser_BooksCriteria() {
		return new User_BookDetachedCriteria(createCriteria("ORM_User_Books"));
	}
	
	public User_MembershipDetachedCriteria createUser_MembershipsCriteria() {
		return new User_MembershipDetachedCriteria(createCriteria("ORM_User_Memberships"));
	}
	
	public PaymentRequestDetachedCriteria createPaymentrequestCriteria() {
		return new PaymentRequestDetachedCriteria(createCriteria("ORM_Paymentrequest"));
	}
	
	public User uniqueUser(PersistentSession session) {
		return (User) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public User[] listUser(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (User[]) list.toArray(new User[list.size()]);
	}
}

