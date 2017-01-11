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

public class BookDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final StringExpression title;
	public final StringExpression language;
	public final StringExpression summary;
	public final StringExpression table_of_contents;
	public final ByteArrayExpression image;
	public final StringExpression pdf;
	public final StringExpression doc;
	public final StringExpression fb2;
	public final FloatExpression price;
	public final StringExpression status;
	public final CollectionExpression view;
	public final CollectionExpression user_Books;
	public final CollectionExpression author;
	public final CollectionExpression keyword;
	public final CollectionExpression paymentRequest;
	public final CollectionExpression field;
	public final CollectionExpression subject;
	
	public BookDetachedCriteria() {
		super(i_book.Book.class, i_book.BookCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		title = new StringExpression("title", this.getDetachedCriteria());
		language = new StringExpression("language", this.getDetachedCriteria());
		summary = new StringExpression("summary", this.getDetachedCriteria());
		table_of_contents = new StringExpression("table_of_contents", this.getDetachedCriteria());
		image = new ByteArrayExpression("image", this.getDetachedCriteria());
		pdf = new StringExpression("pdf", this.getDetachedCriteria());
		doc = new StringExpression("doc", this.getDetachedCriteria());
		fb2 = new StringExpression("fb2", this.getDetachedCriteria());
		price = new FloatExpression("price", this.getDetachedCriteria());
		status = new StringExpression("status", this.getDetachedCriteria());
		view = new CollectionExpression("ORM_View", this.getDetachedCriteria());
		user_Books = new CollectionExpression("ORM_User_Books", this.getDetachedCriteria());
		author = new CollectionExpression("ORM_Author", this.getDetachedCriteria());
		keyword = new CollectionExpression("ORM_Keyword", this.getDetachedCriteria());
		paymentRequest = new CollectionExpression("ORM_PaymentRequest", this.getDetachedCriteria());
		field = new CollectionExpression("ORM_Field", this.getDetachedCriteria());
		subject = new CollectionExpression("ORM_Subject", this.getDetachedCriteria());
	}
	
	public BookDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, i_book.BookCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		title = new StringExpression("title", this.getDetachedCriteria());
		language = new StringExpression("language", this.getDetachedCriteria());
		summary = new StringExpression("summary", this.getDetachedCriteria());
		table_of_contents = new StringExpression("table_of_contents", this.getDetachedCriteria());
		image = new ByteArrayExpression("image", this.getDetachedCriteria());
		pdf = new StringExpression("pdf", this.getDetachedCriteria());
		doc = new StringExpression("doc", this.getDetachedCriteria());
		fb2 = new StringExpression("fb2", this.getDetachedCriteria());
		price = new FloatExpression("price", this.getDetachedCriteria());
		status = new StringExpression("status", this.getDetachedCriteria());
		view = new CollectionExpression("ORM_View", this.getDetachedCriteria());
		user_Books = new CollectionExpression("ORM_User_Books", this.getDetachedCriteria());
		author = new CollectionExpression("ORM_Author", this.getDetachedCriteria());
		keyword = new CollectionExpression("ORM_Keyword", this.getDetachedCriteria());
		paymentRequest = new CollectionExpression("ORM_PaymentRequest", this.getDetachedCriteria());
		field = new CollectionExpression("ORM_Field", this.getDetachedCriteria());
		subject = new CollectionExpression("ORM_Subject", this.getDetachedCriteria());
	}
	
	public Views_DateDetachedCriteria createViewCriteria() {
		return new Views_DateDetachedCriteria(createCriteria("ORM_View"));
	}
	
	public User_BookDetachedCriteria createUser_BooksCriteria() {
		return new User_BookDetachedCriteria(createCriteria("ORM_User_Books"));
	}
	
	public AuthorDetachedCriteria createAuthorCriteria() {
		return new AuthorDetachedCriteria(createCriteria("ORM_Author"));
	}
	
	public KeywordDetachedCriteria createKeywordCriteria() {
		return new KeywordDetachedCriteria(createCriteria("ORM_Keyword"));
	}
	
	public PaymentRequestDetachedCriteria createPaymentRequestCriteria() {
		return new PaymentRequestDetachedCriteria(createCriteria("ORM_PaymentRequest"));
	}
	
	public FieldDetachedCriteria createFieldCriteria() {
		return new FieldDetachedCriteria(createCriteria("ORM_Field"));
	}
	
	public SubjectDetachedCriteria createSubjectCriteria() {
		return new SubjectDetachedCriteria(createCriteria("ORM_Subject"));
	}
	
	public Book uniqueBook(PersistentSession session) {
		return (Book) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Book[] listBook(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Book[]) list.toArray(new Book[list.size()]);
	}
}

