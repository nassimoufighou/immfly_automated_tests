package core;

import core.config.APIConfig;
import core.config.TestSuiteConfig;
import core.request.Request;
import io.restassured.response.Response;

/**
 * This class is the base class for all the test classes. Here is where the API settings are provided, like the baseUri.
 */
public class BaseAPITest {

    public BaseAPITest() {
        //Both user and URI could be consumed from environment variable or properties file
        APIConfig.setUrl("https://crudcrud.com/api/bc87cd424cef4a338a08b7e368413c74");

        //Report path could be consumed from environment variable or properties file
        TestSuiteConfig.setPathReport("./reports/report.html");
    }

    public Response createPreconditions(Request request) {
        return PreconditionManager.createPreconditions(request);
    }

    public Response deletePreconditions(Request request) {
        return PreconditionManager.deletePreconditions(request);
    }
}
