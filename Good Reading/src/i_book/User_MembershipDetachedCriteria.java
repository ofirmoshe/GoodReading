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

public class User_MembershipDetachedCriteria extends AbstractORMDetachedCriteria {
	public final DateExpression s_date;
	public final DateExpression e_date;
	public final StringExpression status;
	public final IntegerExpression membershipId;
	public final AssociationExpression membership;
	public final StringExpression userId;
	public final AssociationExpression user;
	
	public User_MembershipDetachedCriteria() {
		super(i_book.User_Membership.class, i_book.User_MembershipCriteria.class);
		s_date = new DateExpression("s_date", this.getDetachedCriteria());
		e_date = new DateExpression("e_date", this.getDetachedCriteria());
		status = new StringExpression("status", this.getDetachedCriteria());
		membershipId = new IntegerExpression("ORM_Membership.null", this.getDetachedCriteria());
		membership = new AssociationExpression("ORM_Membership", this.getDetachedCriteria());
		userId = new StringExpression("ORM_User.null", this.getDetachedCriteria());
		user = new AssociationExpression("ORM_User", this.getDetachedCriteria());
	}
	
	public User_MembershipDetachedCriteria(DetachedCriteria aDetachedCriteria) {
		super(aDetachedCriteria, i_book.User_MembershipCriteria.class);
		s_date = new DateExpression("s_date", this.getDetachedCriteria());
		e_date = new DateExpression("e_date", this.getDetachedCriteria());
		status = new StringExpression("status", this.getDetachedCriteria());
		membershipId = new IntegerExpression("ORM_Membership.null", this.getDetachedCriteria());
		membership = new AssociationExpression("ORM_Membership", this.getDetachedCriteria());
		userId = new StringExpression("ORM_User.null", this.getDetachedCriteria());
		user = new AssociationExpression("ORM_User", this.getDetachedCriteria());
	}
	
	public MembershipDetachedCriteria createMembershipCriteria() {
		return new MembershipDetachedCriteria(createCriteria("ORM_Membership"));
	}
	
	public UserDetachedCriteria createUserCriteria() {
		return new UserDetachedCriteria(createCriteria("ORM_User"));
	}
	
	public User_Membership uniqueUser_Membership(PersistentSession session) {
		return (User_Membership) super.createExecutableCriteria(session).uniqueResult();
	}
	
	public User_Membership[] listUser_Membership(PersistentSession session) {
		List list = super.createExecutableCriteria(session).list();
		return (User_Membership[]) list.toArray(new User_Membership[list.size()]);
	}
}

