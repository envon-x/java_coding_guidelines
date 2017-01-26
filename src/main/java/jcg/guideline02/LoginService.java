package jcg.guideline02;

public interface LoginService {
    boolean isUserValid(String username, char[] password);

    /**
     *
     * @param username
     * @param secureRandomString
     * @return true if mapping exists between username and secureRandomString; false otherwise.
     */
    boolean mappingExists(String username, String secureRandomString);

    String getRandomString();

    void mapUserForRememberMe(String username, String newRandom);

    String getUserName();
}
