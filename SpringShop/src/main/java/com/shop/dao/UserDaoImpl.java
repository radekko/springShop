package com.shop.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.shop.model.entity.persistent.User;

@Repository
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	public void addUser(User user) {
		persist(user);
	}

	@Override
	public boolean findIfUserExist(User user) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("username", user.getUsername()));
		return (User) criteria.uniqueResult() == null ? false: true;
	}
	@Override
	public User getByUsername(String username) {
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("username", username));
		return (User) criteria.uniqueResult();
	}
}
