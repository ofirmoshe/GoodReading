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

public class MembershipCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression type;
	public final FloatExpression price;
	public final IntegerExpression days;
	public final CollectionExpression user_Memberships;
	public final CollectionExpression paymentRequest;
	
	public MembershipCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		type = new StringExpression("type", this);
		price = new FloatExpression("price", this);
		days = new IntegerExpression("days", this);
		user_Memberships = new CollectionExpression("ORM_User_Memberships", this);
		paymentRequest = new CollectionExpression("ORM_PaymentRequest", this);
	}
	
	public MembershipCriteria(PersistentSession session) {
		this(session.createCriteria(Membership.class));
	}
	
	public MembershipCriteria() throws PersistentException {
		this(i_book.IBookIncPersistentManager.instance().getSession());
	}
	
	public User_MembershipCriteria createUser_MembershipsCriteria() {
		return new User_MembershipCriteria(createCriteria("ORM_User_Memberships"));
	}
	
	public PaymentRequestCriteria createPaymentRequestCriteria() {
		return new PaymentRequestCriteria(createCriteria("ORM_PaymentRequest"));
	}
	
	public Membership uniqueMembership() {
		return (Membership) super.uniqueResult();
	}
	
	public Membership[] listMembership() {
		java.util.List list = super.list();
		return (Membership[]) list.toArray(new Membership[list.size()]);
	}
}

