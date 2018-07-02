package services;

import beens.UserBeen;

/**
 * Created by mandeepsingh on 16/06/18.
 */
public interface UserCredentialsService {
    UserBeen validateUserCred(UserBeen userBeen);
}
