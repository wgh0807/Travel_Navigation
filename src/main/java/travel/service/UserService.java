package travel.service;

import travel.obj.User;

public interface UserService extends GenericService<User,Integer> {
    User signIn(User user);
    int signUp(User user);
    int emailCheck(String email);
    int changePassword(User user,String newPassword);
}
