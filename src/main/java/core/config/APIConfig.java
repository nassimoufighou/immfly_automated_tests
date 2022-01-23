package core.config;


/**
 * Class to modelize the API settings.
 */
public class APIConfig {

    private static String url;

    public static String getBaseUri() {
        return url;
    }

    public static void setUrl(String url) {
        APIConfig.url = url;
    }
}
