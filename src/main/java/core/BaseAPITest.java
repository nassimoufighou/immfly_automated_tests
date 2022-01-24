package core;

import core.config.APIConfig;
import core.request.Request;
import io.restassured.response.Response;

/**
 * This class is the base class for all the test classes. Here is where the API settings are provided, like the baseUri.
 */
public class BaseAPITest {

    public BaseAPITest() {
        //URI could be consumed from environment variable or properties file
        //The ID could be consumed from environment variable or properties file (see Notes section in README.md)
        APIConfig.setUrl("https://crudcrud.com/api/7593ef7822184f0aaf0c84aa906ae2f7");
    }

    public Response createPreconditions(Request request) {
        return PreconditionManager.createPreconditions(request);
    }

    public Response deletePreconditions(Request request) {
        return PreconditionManager.deletePreconditions(request);
    }
}
