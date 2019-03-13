package travel.dao.impl;

import org.springframework.stereotype.Repository;
import travel.dao.UserDao;
import travel.obj.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User,Integer> implements UserDao {
}
