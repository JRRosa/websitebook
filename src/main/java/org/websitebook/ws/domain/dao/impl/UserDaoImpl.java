package org.websitebook.ws.domain.dao.impl;

import javax.enterprise.context.Dependent;

import org.websitebook.ws.domain.dao.AbstractDao;
import org.websitebook.ws.domain.dao.UserDao;
import org.websitebook.ws.domain.dao.entities.User;

@Dependent
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {

	@Override
	protected Class<User> getTypeClass() {
		return User.class;
	}

}

