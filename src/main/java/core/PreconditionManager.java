package core;

import core.request.Request;
import core.request.RequestExecutor;
import io.restassured.response.Response;

public class PreconditionManager {

    private static Response processPreconditionRequests(Request request) {
        Response response = RequestExecutor.execute(request);
        return response;
    }

    public static Response createPreconditions(Request request) {
        return processPreconditionRequests(request);
    }

    public static Response deletePreconditions(Request request) {
        return processPreconditionRequests(request);
    }
}
