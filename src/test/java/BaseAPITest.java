import core.config.APIConfig;
import core.config.TestSuiteConfig;

/**
 * This class is the base class for all the test classes. Here is where the API settings are provided, like the baseUri.
 */
public class BaseAPITest {

    public BaseAPITest() {
        //Both user and URI could be consumed from environment variable or properties file
        APIConfig.setUrl("https://crudcrud.com/api/c7426b14482f4e7c923ab305b964e19e");

        //Report path could be consumed from environment variable or properties file
        TestSuiteConfig.setPathReport("./reports/report.html");
    }
}
