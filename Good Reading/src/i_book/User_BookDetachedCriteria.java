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

public class User_BookDetachedCriteria extends AbstractORMDetachedCriteria {
	public final DateExpression pDate;
	public final StringExpression status;
	public final IntegerExpression bookId;
	public final AssociationExpression book;
	public final StringExpression userId;
	public final AssociationExpression user;
	public final CollectionExpression review;
	
	public User_BookDetachedCriteria() {
		super(i_book.User_Book.class, i_book.User_BookCriteria.class);
		pDate = new DateExpression("pDate", this.getDetachedCriteria());
		status = new StringExpression("status", this.getDetachedCriteria());
		bookId = new IntegerExpression("ORM_Book.null", this.getDetachedCriteria());
		book = new AssociationExpression("ORM_Book", this.getDetachedCriteria());
		userId = new StringExpression("ORM_User.null", this.getDetachedCriteria());
		user = new AssociationExpression("ORM_User", this.getDetachedCriteria());
		review = new CollectionExpression("ORM_Review", this.getDetachedCriteria());
	}
	
	public User_BookDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, i_book.User_BookCriteria.class);
		pDate = new DateExpression("pDate", this.getDetachedCriteria());
		status = new StringExpression("status", this.getDetachedCriteria());
		bookId = new IntegerExpression("ORM_Book.null", this.getDetachedCriteria());
		book = new AssociationExpression("ORM_Book", this.getDetachedCriteria());
		userId = new StringExpression("ORM_User.null", this.getDetachedCriteria());
		user = new AssociationExpression("ORM_User", this.getDetachedCriteria());
		review = new CollectionExpression("ORM_Review", this.getDetachedCriteria());
	}
	
	public BookDetachedCriteria createBookCriteria() {
		return new BookDetachedCriteria(createCriteria("ORM_Book"));
	}
	
	public UserDetachedCriteria createUserCriteria() {
		return new UserDetachedCriteria(createCriteria("ORM_User"));
	}
	
	public ReviewDetachedCriteria createReviewCriteria() {
		return new ReviewDetachedCriteria(createCriteria("ORM_Review"));
	}
	
	public User_Book uniqueUser_Book(PersistentSession session) {
		return (User_Book) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public User_Book[] listUser_Book(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (User_Book[]) list.toArray(new User_Book[list.size()]);
	}
}

