package core.request;

import com.aventstack.extentreports.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.config.APIConfig;
import core.reporting.Report;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class RequestExecutor {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static Response execute(Request request) {
        RestAssured.baseURI = APIConfig.getBaseUri();
        Response response = null;
        RequestSpecification requestSpecification = given();
        requestSpecification.header("Content-type", "application/json");
        if (request.hasToken()) requestSpecification.header("Authorization", "Bearer ".concat(request.getAuthorizationToken()));

        switch (request.getMethod()) {
            case GET:
                response = requestSpecification.when().get(request.getEndpoint()).then().extract().response();
                Report.logStepInReport(Status.PASS, "GET request with body: <code>" + gson.toJson(request.getBody()) + "</code>");
                break;
            case POST:
                response = requestSpecification.body(request.getBody()).when().post(request.getEndpoint()).then().extract().response();
                Report.logStepInReport(Status.PASS, "POST request with body: <code>" + gson.toJson(request.getBody()) + "</code>");
                break;
            case PUT:
                response = requestSpecification.body(request.getBody()).when().put(request.getEndpoint()).then().extract().response();
                Report.logStepInReport(Status.PASS, "PUT request with body: <code>" + gson.toJson(request.getBody()) + "</code>");
                break;
            case DELETE:
                response = requestSpecification.when().delete(request.getEndpoint()).then().extract().response();
                Report.logStepInReport(Status.PASS, "DELETE request with body: <code>" + gson.toJson(request.getBody()) + "</code>");
                break;
        }
        Report.logStepInReport(Status.PASS, request.getMethod() + " response: <code>" + gson.toJson(response.getBody().asString() + "</code>"));
        return response;
    }

    public static Response executePreconditionRequest(Request request, String action) {
        RestAssured.baseURI = APIConfig.getBaseUri();
        Response response = null;
        RequestSpecification requestSpecification = given();
        requestSpecification.header("Content-type", "application/json");
        if (request.hasToken()) requestSpecification.header("Authorization", "Bearer ".concat(request.getAuthorizationToken()));

        switch (request.getMethod()) {
            case GET:
                response = requestSpecification.when().get(request.getEndpoint()).then().extract().response();
                Report.logStepInReport(Status.PASS, "<b>[" + action + "]</b> GET request with body: <code>" + gson.toJson(request.getBody()) + "</code>");
                break;
            case POST:
                response = requestSpecification.body(request.getBody()).when().post(request.getEndpoint()).then().extract().response();
                Report.logStepInReport(Status.PASS, "<b>[" + action + "]</b> POST request with body: <code>" + gson.toJson(request.getBody()) + "</code>");
                break;
            case PUT:
                response = requestSpecification.body(request.getBody()).when().put(request.getEndpoint()).then().extract().response();
                Report.logStepInReport(Status.PASS, "<b>[" + action + "]</b> PUT request with body: <code>" + gson.toJson(request.getBody()) + "</code>");
                break;
            case DELETE:
                response = requestSpecification.when().delete(request.getEndpoint()).then().extract().response();
                Report.logStepInReport(Status.PASS, "<b>[" + action + "]</b> DELETE request with body: <code>" + gson.toJson(request.getBody()) + "</code>");
                break;
        }
        Report.logStepInReport(Status.PASS, "<b>[" + action + "]</b> " + request.getMethod() + " response: <code>" + gson.toJson(response.getBody().asString() + "</code>"));
        return response;
    }
}
