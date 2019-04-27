package fr.epita.quiz.services.dataaccess;

import org.springframework.stereotype.Repository;
import fr.epita.quiz.datamodel.User;

@Repository
public class UserJPADAO extends GenericDAO<User> {
	@Override
	public void prepareSearch(User criteria, QueryHolder<User> holder) {
		holder.setQueryString("from User as user where user.nick = :nick");
		holder.setClassName(User.class);
		holder.putParameter("nick", criteria.getNick());
	}
}
