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

public class User_MembershipCriteria extends AbstractORMCriteria {
	public final DateExpression s_date;
	public final DateExpression e_date;
	public final StringExpression status;
	public final IntegerExpression membershipId;
	public final AssociationExpression membership;
	public final StringExpression userId;
	public final AssociationExpression user;
	
	public User_MembershipCriteria(Criteria criteria) {
		super(criteria);
		s_date = new DateExpression("s_date", this);
		e_date = new DateExpression("e_date", this);
		status = new StringExpression("status", this);
		membershipId = new IntegerExpression("ORM_Membership.null", this);
		membership = new AssociationExpression("ORM_Membership", this);
		userId = new StringExpression("ORM_User.null", this);
		user = new AssociationExpression("ORM_User", this);
	}
	
	public User_MembershipCriteria(PersistentSession session) {
		this(session.createCriteria(User_Membership.class));
	}
	
	public User_MembershipCriteria() throws PersistentException {
		this(i_book.IBookIncPersistentManager.instance().getSession());
	}
	
	public MembershipCriteria createMembershipCriteria() {
		return new MembershipCriteria(createCriteria("ORM_Membership"));
	}
	
	public UserCriteria createUserCriteria() {
		return new UserCriteria(createCriteria("ORM_User"));
	}
	
	public User_Membership uniqueUser_Membership() {
		return (User_Membership) super.uniqueResult();
	}
	
	public User_Membership[] listUser_Membership() {
		java.util.List list = super.list();
		return (User_Membership[]) list.toArray(new User_Membership[list.size()]);
	}
}

