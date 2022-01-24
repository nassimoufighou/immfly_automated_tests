
import core.model.Charger;
import core.request.CreateChargerRequest;
import core.request.RequestExecutor;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateChargerTests extends BaseAPITest {

    private Charger preconditionCharger, createdCharger;

    @BeforeMethod
    public void setup() {
        preconditionCharger = new Charger("SN123524012022", "MN24012022G", "ST001", true, 200);
    }

    /**
     * GIVEN a charger does not exist
     * WHEN a POST request to the “/charger” endpoint with a {C} charger object in the body request
     * THEN the response code is 200
     *   AND the response body contains the {C} charger with an “_id” field
     */
    @Test
    public void createChargerTest() {
        CreateChargerRequest createChargerRequest = CreateChargerRequest.createCorrectChargerRequest(preconditionCharger);
        Response response = RequestExecutor.execute(createChargerRequest);

        createdCharger = response.as(Charger.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
        softAssert.assertNotNull(createdCharger.get_id());
        softAssert.assertEquals(createdCharger.getSerialNumber(), preconditionCharger.getSerialNumber());
        softAssert.assertEquals(createdCharger.getModel(), preconditionCharger.getModel());
        softAssert.assertEquals(createdCharger.getStationId(), preconditionCharger.getStationId());
        softAssert.assertEquals(createdCharger.isEnabled(), preconditionCharger.isEnabled());
        softAssert.assertEquals(createdCharger.getStatusCode(), preconditionCharger.getStatusCode());
        softAssert.assertAll();
    }

    /**
     * GIVEN a charger does not exist
     * WHEN a non-POST request to the “/charger” endpoint with a {C} charger object in the body request
     * THEN the response code is 405 (method not allowed)
     */
    @Test
    public void createChargerTestWithWrongHttpMethodTest() {
        CreateChargerRequest createChargerRequest = CreateChargerRequest.createChargerWithWrongHttpMethod(preconditionCharger);
        Response response = RequestExecutor.execute(createChargerRequest);

        int responseCode = response.getStatusCode();
        Assert.assertEquals(responseCode, HttpStatus.SC_METHOD_NOT_ALLOWED);
    }

    /**
     * GIVEN a charger does not exist
     * WHEN a POST request to the “/charger” endpoint with an empty body request
     * THEN the response code is 415
     *   AND the charger is not created
     */
    @Test
    public void createChargerTestWithEnEmptyBodyTest() {
        //TOFIX
        CreateChargerRequest createChargerRequest = CreateChargerRequest.createChargerRequestWithoutBody(preconditionCharger);
        Response response = RequestExecutor.execute(createChargerRequest);

        int responseCode = response.getStatusCode();
        Assert.assertEquals(responseCode, HttpStatus.SC_BAD_REQUEST);
    }

}
