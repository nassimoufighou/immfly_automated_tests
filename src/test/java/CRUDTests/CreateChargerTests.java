package CRUDTests;

import core.BaseAPITest;
import core.model.Charger;
import core.request.CreateChargerRequest;
import core.request.DeleteChargerRequest;
import core.request.RequestExecutor;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateChargerTests extends BaseAPITest {

    private Charger preconditionCharger, createdCharger;

    /**
     * Creates the charger object to be used in the tests.
     * As this charger object is not modified (read-only) in the tests, it's enough to create it 1 time.
     * For this reason the below method is tagged with the @BeforeClass.
     * Otherwise, the below method would need to be tagged with @BeforeMethod.
     */
    @BeforeClass
    public void setup() {
        preconditionCharger = new Charger("SN123524012022", "MN24012022G", "ST001", true, 200);
    }

    /**
     * Deletes the chargers created in the @Test methods.
     * As this chargers are created within the test, it's needed to clean them up after each testrun.
     * For this reason the below method is tagged with the @AfterMethod.
     * Otherwise, the below method would be tagged with @AfterClass.
     */
    @AfterMethod
    public void teardown() {
        if (createdCharger != null) {
            DeleteChargerRequest deleteChargerRequest = DeleteChargerRequest.createCorrectDeleteChargerRequest(createdCharger);
            deletePreconditions(deleteChargerRequest);
        }
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
        CreateChargerRequest createChargerRequest = CreateChargerRequest.createChargerRequestWithoutBody(preconditionCharger);
        Response response = RequestExecutor.execute(createChargerRequest);

        int responseCode = response.getStatusCode();
        Assert.assertEquals(responseCode, HttpStatus.SC_BAD_REQUEST);
    }

}
