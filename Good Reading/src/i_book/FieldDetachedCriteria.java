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

public class FieldDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final StringExpression field;
	public final CollectionExpression book;
	public final CollectionExpression subject;
	
	public FieldDetachedCriteria() {
		super(i_book.Field.class, i_book.FieldCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		field = new StringExpression("field", this.getDetachedCriteria());
		book = new CollectionExpression("ORM_Book", this.getDetachedCriteria());
		subject = new CollectionExpression("ORM_Subject", this.getDetachedCriteria());
	}
	
	public FieldDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, i_book.FieldCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		field = new StringExpression("field", this.getDetachedCriteria());
		book = new CollectionExpression("ORM_Book", this.getDetachedCriteria());
		subject = new CollectionExpression("ORM_Subject", this.getDetachedCriteria());
	}
	
	public BookDetachedCriteria createBookCriteria() {
		return new BookDetachedCriteria(createCriteria("ORM_Book"));
	}
	
	public SubjectDetachedCriteria createSubjectCriteria() {
		return new SubjectDetachedCriteria(createCriteria("ORM_Subject"));
	}
	
	public Field uniqueField(PersistentSession session) {
		return (Field) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Field[] listField(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Field[]) list.toArray(new Field[list.size()]);
	}
}

