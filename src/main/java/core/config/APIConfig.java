package core.config;

import core.model.User;

/**
 * Class to modelize the API settings.
 */
public class APIConfig {

    private static String url;
    private static User user;

    public static User getUser() {
        return APIConfig.user;
    }

    public static void setUser(User user) {
        APIConfig.user = user;
    }

    public static String getBaseUri() {
        return url;
    }

    public static void setUrl(String url) {
        APIConfig.url = url;
    }
}
