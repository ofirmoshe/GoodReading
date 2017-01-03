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

public class ReviewCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression text;
	public final StringExpression status;
	public final AssociationExpression usersbook;
	
	public ReviewCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		text = new StringExpression("text", this);
		status = new StringExpression("status", this);
		usersbook = new AssociationExpression("usersbook", this);
	}
	
	public ReviewCriteria(PersistentSession session) {
		this(session.createCriteria(Review.class));
	}
	
	public ReviewCriteria() throws PersistentException {
		this(i_book.IBookIncPersistentManager.instance().getSession());
	}
	
	public User_BookCriteria createUsersbookCriteria() {
		return new User_BookCriteria(createCriteria("usersbook"));
	}
	
	public Review uniqueReview() {
		return (Review) super.uniqueResult();
	}
	
	public Review[] listReview() {
		java.util.List list = super.list();
		return (Review[]) list.toArray(new Review[list.size()]);
	}
}

