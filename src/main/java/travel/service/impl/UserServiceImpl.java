package travel.service.impl;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.GenericDao;
import travel.obj.User;
import travel.service.UserService;

@Service
public class UserServiceImpl extends GenericServiceImpl<User,Integer> implements UserService {
    @Override
    @Autowired
    void setGenericDao(GenericDao<User, Integer> genericDao) {
        this.genericDao = genericDao;
    }

    private StrongPasswordEncryptor spe = new StrongPasswordEncryptor();
    @Override
    public User signIn(User user) {
        System.out.println("user service Impl -> signIn");
        User trueUser = genericDao.queryOne("selectByEmail",user.getMail());
        System.out.println("true user : "+trueUser);
        if (trueUser == null) {
            return null;
        }
        boolean result = spe.checkPassword(user.getPassword(), trueUser.getPassword());
//        boolean result = user.getPassword().equals(trueUser.getPassword());
        if (result) {
            return trueUser;
        }
        return null;
    }

    @Override
    public int signUp(User user) {
        String password = spe.encryptPassword(user.getPassword());
        user.setPassword(password);
        return genericDao.create(user);
    }

    @Override
    public int emailCheck(String email) {
         User user = genericDao.queryOne("selectByEmail", email);
        if (user != null && user.getMail().equalsIgnoreCase(email)) {
            return 1;
        }else {
            return 0;
        }
    }

    @Override
    public int changePassword(User user,String newPassword) {
        String password = spe.encryptPassword(newPassword);
        if (spe.checkPassword(user.getPassword(),queryOne(user.getId()).getPassword())){
            user.setPassword(password);
            return genericDao.modify("changePasswd", user);
        }else{
            return 0;
        }
    }
}
