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

public class PaymentRequestCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression userId;
	public final AssociationExpression user;
	public final DateExpression date;
	public final StringExpression paymentInfo;
	public final StringExpression status;
	
	public PaymentRequestCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		userId = new StringExpression("user.ID", this);
		user = new AssociationExpression("user", this);
		date = new DateExpression("date", this);
		paymentInfo = new StringExpression("paymentInfo", this);
		status = new StringExpression("status", this);
	}
	
	public PaymentRequestCriteria(PersistentSession session) {
		this(session.createCriteria(PaymentRequest.class));
	}
	
	public PaymentRequestCriteria() throws PersistentException {
		this(i_book.IBookIncPersistentManager.instance().getSession());
	}
	
	public UserCriteria createUserCriteria() {
		return new UserCriteria(createCriteria("user"));
	}
	
	public PaymentRequest uniquePaymentRequest() {
		return (PaymentRequest) super.uniqueResult();
	}
	
	public PaymentRequest[] listPaymentRequest() {
		java.util.List list = super.list();
		return (PaymentRequest[]) list.toArray(new PaymentRequest[list.size()]);
	}
}

