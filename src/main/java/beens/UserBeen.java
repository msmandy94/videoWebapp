package beens;

/**
 * Created by mandeepsingh on 16/06/18.
 */

public class UserBeen {
    String userId;
    String password;
    UserType userType;

    public UserBeen() {
    }

    public UserBeen(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
