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

public class User_BookCriteria extends AbstractORMCriteria {
	public final DateExpression pDate;
	public final StringExpression status;
	public final IntegerExpression bookId;
	public final AssociationExpression book;
	public final StringExpression userId;
	public final AssociationExpression user;
	public final CollectionExpression review;
	
	public User_BookCriteria(Criteria criteria) {
		super(criteria);
		pDate = new DateExpression("pDate", this);
		status = new StringExpression("status", this);
		bookId = new IntegerExpression("ORM_Book.null", this);
		book = new AssociationExpression("ORM_Book", this);
		userId = new StringExpression("ORM_User.null", this);
		user = new AssociationExpression("ORM_User", this);
		review = new CollectionExpression("ORM_Review", this);
	}
	
	public User_BookCriteria(PersistentSession session) {
		this(session.createCriteria(User_Book.class));
	}
	
	public User_BookCriteria() throws PersistentException {
		this(i_book.IBookIncPersistentManager.instance().getSession());
	}
	
	public BookCriteria createBookCriteria() {
		return new BookCriteria(createCriteria("ORM_Book"));
	}
	
	public UserCriteria createUserCriteria() {
		return new UserCriteria(createCriteria("ORM_User"));
	}
	
	public ReviewCriteria createReviewCriteria() {
		return new ReviewCriteria(createCriteria("ORM_Review"));
	}
	
	public User_Book uniqueUser_Book() {
		return (User_Book) super.uniqueResult();
	}
	
	public User_Book[] listUser_Book() {
		java.util.List list = super.list();
		return (User_Book[]) list.toArray(new User_Book[list.size()]);
	}
}

