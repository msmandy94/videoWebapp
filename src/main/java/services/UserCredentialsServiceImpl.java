package services;

import DAO.MongoClientPool;
import beens.UserBeen;
import beens.UserType;
import com.mongodb.BasicDBObject;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class UserCredentialsServiceImpl implements UserCredentialsService {
    private static Logger logger = LoggerFactory.getLogger(UserCredentialsServiceImpl.class.getName());

    private UserCredentialsServiceImpl() {
    }

    private static class UserCredentialsSingletonHelper {
        private static final UserCredentialsServiceImpl INSTANCE = new UserCredentialsServiceImpl();
    }

    public static UserCredentialsServiceImpl getInstance() {
        return UserCredentialsSingletonHelper.INSTANCE;
    }

    @Override
    public UserBeen validateUserCred(UserBeen userBeen) {
        String userId = userBeen.getUserId();
        String password = userBeen.getPassword();

        UserBeen validUser = new UserBeen();

        validUser.setUserId(userId);

        BasicDBObject bsonObject = new BasicDBObject();
        bsonObject.append("userId", userId).append("password", password);
        ArrayList<Document> userCredential = MongoClientPool.getUserCredentialsCollection().find(bsonObject).into(new ArrayList<Document>());
        if (userCredential == null || userCredential.isEmpty()) {
            logger.error("user:" + userId + " not found");
            return validUser;
        }
        if (userCredential.get(0).containsKey("userType")) {
            if ((userCredential.get(0).get("userType")).equals(UserType.CONTRIBUTOR.name())) {
                validUser.setUserType(UserType.CONTRIBUTOR);
            } else {
                validUser.setUserType(UserType.VIEWER);
            }

        }

        return validUser;
    }
}
