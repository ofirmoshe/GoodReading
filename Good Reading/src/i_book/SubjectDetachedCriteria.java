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

public class SubjectDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final StringExpression sub;
	public final IntegerExpression fieldId;
	public final AssociationExpression field;
	public final CollectionExpression book;
	
	public SubjectDetachedCriteria() {
		super(i_book.Subject.class, i_book.SubjectCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		sub = new StringExpression("sub", this.getDetachedCriteria());
		fieldId = new IntegerExpression("field.ID", this.getDetachedCriteria());
		field = new AssociationExpression("field", this.getDetachedCriteria());
		book = new CollectionExpression("ORM_Book", this.getDetachedCriteria());
	}
	
	public SubjectDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, i_book.SubjectCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		sub = new StringExpression("sub", this.getDetachedCriteria());
		fieldId = new IntegerExpression("field.ID", this.getDetachedCriteria());
		field = new AssociationExpression("field", this.getDetachedCriteria());
		book = new CollectionExpression("ORM_Book", this.getDetachedCriteria());
	}
	
	public FieldDetachedCriteria createFieldCriteria() {
		return new FieldDetachedCriteria(createCriteria("field"));
	}
	
	public BookDetachedCriteria createBookCriteria() {
		return new BookDetachedCriteria(createCriteria("ORM_Book"));
	}
	
	public Subject uniqueSubject(PersistentSession session) {
		return (Subject) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Subject[] listSubject(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Subject[]) list.toArray(new Subject[list.size()]);
	}
}

