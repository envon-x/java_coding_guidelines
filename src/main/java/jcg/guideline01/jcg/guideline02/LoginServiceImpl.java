package jcg.guideline01.jcg.guideline02;

/**
 *
 */
public class LoginServiceImpl implements LoginService {
    @Override
    public boolean isUserValid(String username, char[] password) {
        return true;
    }

    @Override
    public boolean mappingExists(String username, String secureRandomString) {
        return true;
    }

    @Override
    public String getRandomString() {
        // FIXME: WTF?  No example of what a "secure random string" is?
        return "";
    }

    @Override
    public void mapUserForRememberMe(String username, String newRandom) {
        // FIXME: store newRandom in some Map keyed by username.
    }

    @Override
    public String getUserName() {
        // FIXME: Implement this
        return null;
    }

}
