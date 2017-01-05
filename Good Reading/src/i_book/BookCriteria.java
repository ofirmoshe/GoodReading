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

public class BookCriteria extends AbstractORMCriteria {
	public final IntegerExpression ID;
	public final StringExpression title;
	public final StringExpression language;
	public final StringExpression summary;
	public final StringExpression table_of_contents;
	public final StringExpression image;
	public final StringExpression pdf;
	public final StringExpression doc;
	public final StringExpression fb2;
	public final FloatExpression price;
	public final StringExpression status;
	public final CollectionExpression view;
	public final CollectionExpression user_Books;
	public final CollectionExpression author;
	public final CollectionExpression keyword;
	public final CollectionExpression field;
	public final CollectionExpression subject;
	
	public BookCriteria(Criteria criteria) {
		super(criteria);
		ID = new IntegerExpression("ID", this);
		title = new StringExpression("title", this);
		language = new StringExpression("language", this);
		summary = new StringExpression("summary", this);
		table_of_contents = new StringExpression("table_of_contents", this);
		image = new StringExpression("image", this);
		pdf = new StringExpression("pdf", this);
		doc = new StringExpression("doc", this);
		fb2 = new StringExpression("fb2", this);
		price = new FloatExpression("price", this);
		status = new StringExpression("status", this);
		view = new CollectionExpression("ORM_View", this);
		user_Books = new CollectionExpression("ORM_User_Books", this);
		author = new CollectionExpression("ORM_Author", this);
		keyword = new CollectionExpression("ORM_Keyword", this);
		field = new CollectionExpression("ORM_Field", this);
		subject = new CollectionExpression("ORM_Subject", this);
	}
	
	public BookCriteria(PersistentSession session) {
		this(session.createCriteria(Book.class));
	}
	
	public BookCriteria() throws PersistentException {
		this(i_book.IBookIncPersistentManager.instance().getSession());
	}
	
	public Views_DateCriteria createViewCriteria() {
		return new Views_DateCriteria(createCriteria("ORM_View"));
	}
	
	public User_BookCriteria createUser_BooksCriteria() {
		return new User_BookCriteria(createCriteria("ORM_User_Books"));
	}
	
	public AuthorCriteria createAuthorCriteria() {
		return new AuthorCriteria(createCriteria("ORM_Author"));
	}
	
	public KeywordCriteria createKeywordCriteria() {
		return new KeywordCriteria(createCriteria("ORM_Keyword"));
	}
	
	public FieldCriteria createFieldCriteria() {
		return new FieldCriteria(createCriteria("ORM_Field"));
	}
	
	public SubjectCriteria createSubjectCriteria() {
		return new SubjectCriteria(createCriteria("ORM_Subject"));
	}
	
	public Book uniqueBook() {
		return (Book) super.uniqueResult();
	}
	
	public Book[] listBook() {
		java.util.List list = super.list();
		return (Book[]) list.toArray(new Book[list.size()]);
	}
}

