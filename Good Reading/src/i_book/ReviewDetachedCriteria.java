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

public class ReviewDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final StringExpression text;
	public final StringExpression status;
	public final AssociationExpression usersbook;
	
	public ReviewDetachedCriteria() {
		super(i_book.Review.class, i_book.ReviewCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		text = new StringExpression("text", this.getDetachedCriteria());
		status = new StringExpression("status", this.getDetachedCriteria());
		usersbook = new AssociationExpression("usersbook", this.getDetachedCriteria());
	}
	
	public ReviewDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, i_book.ReviewCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		text = new StringExpression("text", this.getDetachedCriteria());
		status = new StringExpression("status", this.getDetachedCriteria());
		usersbook = new AssociationExpression("usersbook", this.getDetachedCriteria());
	}
	
	public User_BookDetachedCriteria createUsersbookCriteria() {
		return new User_BookDetachedCriteria(createCriteria("usersbook"));
	}
	
	public Review uniqueReview(PersistentSession session) {
		return (Review) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Review[] listReview(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Review[]) list.toArray(new Review[list.size()]);
	}
}

