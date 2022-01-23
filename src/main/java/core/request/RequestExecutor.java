package core.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RequestExecutor {

    public static Response execute(Request request) {
        RestAssured.baseURI = "";
        Response response = null;
        RequestSpecification requestSpecification = given();
        requestSpecification.header("Content-type", "application/json");
        if (request.hasToken()) requestSpecification.header("Authorization", "Bearer ".concat(request.getAuthorizationToken()));

        switch (request.getMethod()) {
            case GET:
                response = requestSpecification.when().get(request.getEndpoint()).then().extract().response();
                break;
            case POST:
                response = requestSpecification.body(request.getBody().toString()).when().post(request.getEndpoint()).then().extract().response();
                break;
            case DELETE:
                response = requestSpecification.when().delete(request.getEndpoint()).then().extract().response();
                break;
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return response;
    }
}
