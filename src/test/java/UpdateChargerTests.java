
import core.model.Charger;
import core.request.CreateChargerRequest;
import core.request.RequestExecutor;
import core.request.UpdateChargerRequest;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UpdateChargerTests extends BaseAPITest {

    private Charger createdCharger, updateCharger, nonExistingCharger;

    @BeforeMethod
    public void setup() {
        Charger preconditionCharger = new Charger("SN123524012022", "MN24012022G", "ST001", true, 200);
        updateCharger = new Charger("SN123524012022", "MN24012022G", "ST023", false, 404);
        nonExistingCharger = new Charger("123", "SN123524012022", "MN24012022G", "ST023", false, 404);

        CreateChargerRequest createChargerRequest = CreateChargerRequest.createCorrectChargerRequest(preconditionCharger);
        Response response = RequestExecutor.execute(createChargerRequest);
        createdCharger = response.as(Charger.class);
    }

    /**
     * GIVEN a charger {C} with id {ID} does exist
     * WHEN a PUT request to the “/charger/{ID}” endpoint with a charger {C1} object in the body request
     * THEN the response code is 200
     */
    @Test
    public void updateChargerTest() {
        UpdateChargerRequest createChargerRequest = UpdateChargerRequest.createCorrectUpdateChargerRequest(createdCharger, updateCharger);
        Response response = RequestExecutor.execute(createChargerRequest);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }

    /**
     * GIVEN a charger {C} with id {ID} does not exist
     * WHEN a PUT request to the “/charger/{ID}” endpoint with a charger {C1} object in the body request
     * THEN the response code is 404 (not found)
     */
    @Test
    public void nonExistingChargerIsNotUpdatedTest() {
        UpdateChargerRequest updateChargerRequest = UpdateChargerRequest.createCorrectUpdateChargerRequest(nonExistingCharger, updateCharger);
        Response response = RequestExecutor.execute(updateChargerRequest);

        int responseCode = response.getStatusCode();
        Assert.assertEquals(responseCode, HttpStatus.SC_NOT_FOUND);
    }

    /**
     * GIVEN a charger {C} with id {ID} does exist
     * WHEN a non-PUT request to the “/charger/{ID}” endpoint with a charger {C1} object in the body request
     * THEN the response code is 200
     */
    @Test
    public void updateChargerTestWithWrongHttpMethodTest() {
        UpdateChargerRequest updateChargerRequest = UpdateChargerRequest.createUpdateChargerWithWrongHttpMethod(createdCharger, updateCharger);
        Response response = RequestExecutor.execute(updateChargerRequest);

        int responseCode = response.getStatusCode();
        Assert.assertEquals(responseCode, HttpStatus.SC_METHOD_NOT_ALLOWED);
    }

    /**
     * GIVEN a charger {C} with id {ID} does exist
     * WHEN a PUT request to the “/charger/{ID}” endpoint with an empty body request
     * THEN the response code is 400 (bad request)
     */
    @Test
    public void createChargerTestWithEnEmptyBodyTest() {
        UpdateChargerRequest updateChargerRequest = UpdateChargerRequest.createUpdateChargerRequestWithoutBody(createdCharger, updateCharger);
        Response response = RequestExecutor.execute(updateChargerRequest);
        int responseCode = response.getStatusCode();
        Assert.assertEquals(responseCode, HttpStatus.SC_BAD_REQUEST);
    }
}
