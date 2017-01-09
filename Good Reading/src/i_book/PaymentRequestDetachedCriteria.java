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

public class PaymentRequestDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final IntegerExpression membershipId;
	public final AssociationExpression membership;
	public final IntegerExpression bookId;
	public final AssociationExpression book;
	public final StringExpression userId;
	public final AssociationExpression user;
	public final DateExpression date;
	public final StringExpression paymentInfo;
	public final StringExpression status;
	
	public PaymentRequestDetachedCriteria() {
		super(i_book.PaymentRequest.class, i_book.PaymentRequestCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		membershipId = new IntegerExpression("membership.ID", this.getDetachedCriteria());
		membership = new AssociationExpression("membership", this.getDetachedCriteria());
		bookId = new IntegerExpression("book.ID", this.getDetachedCriteria());
		book = new AssociationExpression("book", this.getDetachedCriteria());
		userId = new StringExpression("user.ID", this.getDetachedCriteria());
		user = new AssociationExpression("user", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		paymentInfo = new StringExpression("paymentInfo", this.getDetachedCriteria());
		status = new StringExpression("status", this.getDetachedCriteria());
	}
	
	public PaymentRequestDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, i_book.PaymentRequestCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		membershipId = new IntegerExpression("membership.ID", this.getDetachedCriteria());
		membership = new AssociationExpression("membership", this.getDetachedCriteria());
		bookId = new IntegerExpression("book.ID", this.getDetachedCriteria());
		book = new AssociationExpression("book", this.getDetachedCriteria());
		userId = new StringExpression("user.ID", this.getDetachedCriteria());
		user = new AssociationExpression("user", this.getDetachedCriteria());
		date = new DateExpression("date", this.getDetachedCriteria());
		paymentInfo = new StringExpression("paymentInfo", this.getDetachedCriteria());
		status = new StringExpression("status", this.getDetachedCriteria());
	}
	
	public MembershipDetachedCriteria createMembershipCriteria() {
		return new MembershipDetachedCriteria(createCriteria("membership"));
	}
	
	public BookDetachedCriteria createBookCriteria() {
		return new BookDetachedCriteria(createCriteria("book"));
	}
	
	public UserDetachedCriteria createUserCriteria() {
		return new UserDetachedCriteria(createCriteria("user"));
	}
	
	public PaymentRequest uniquePaymentRequest(PersistentSession session) {
		return (PaymentRequest) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public PaymentRequest[] listPaymentRequest(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (PaymentRequest[]) list.toArray(new PaymentRequest[list.size()]);
	}
}

