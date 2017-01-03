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

public class MembershipDetachedCriteria extends AbstractORMDetachedCriteria {
	public final IntegerExpression ID;
	public final StringExpression type;
	public final FloatExpression price;
	public final CollectionExpression user_Memberships;
	
	public MembershipDetachedCriteria() {
		super(i_book.Membership.class, i_book.MembershipCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		type = new StringExpression("type", this.getDetachedCriteria());
		price = new FloatExpression("price", this.getDetachedCriteria());
		user_Memberships = new CollectionExpression("ORM_User_Memberships", this.getDetachedCriteria());
	}
	
	public MembershipDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, i_book.MembershipCriteria.class);
		ID = new IntegerExpression("ID", this.getDetachedCriteria());
		type = new StringExpression("type", this.getDetachedCriteria());
		price = new FloatExpression("price", this.getDetachedCriteria());
		user_Memberships = new CollectionExpression("ORM_User_Memberships", this.getDetachedCriteria());
	}
	
	public User_MembershipDetachedCriteria createUser_MembershipsCriteria() {
		return new User_MembershipDetachedCriteria(createCriteria("ORM_User_Memberships"));
	}
	
	public Membership uniqueMembership(PersistentSession session) {
		return (Membership) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public Membership[] listMembership(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (Membership[]) list.toArray(new Membership[list.size()]);
	}
}

