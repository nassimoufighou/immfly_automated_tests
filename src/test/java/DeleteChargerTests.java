
import core.model.Charger;
import core.request.CreateChargerRequest;
import core.request.DeleteChargerRequest;
import core.request.RequestExecutor;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteChargerTests extends BaseAPITest {

    private Charger createdCharger, nonExistingCharger;

    @BeforeMethod
    public void setup() {
        Charger preconditionCharger = new Charger("SN123524012022", "MN24012022G", "ST001", true, 200);
        createdCharger = new Charger("SN123524012022", "MN24012022G", "ST023", false, 404);
        nonExistingCharger = new Charger("123", "SN123524012022", "MN24012022G", "ST023", false, 404);

        CreateChargerRequest createChargerRequest = CreateChargerRequest.createCorrectChargerRequest(preconditionCharger);
        Response response = RequestExecutor.execute(createChargerRequest);
        createdCharger = response.as(Charger.class);
    }

    /**
     * GIVEN a charger {C} with id {ID} does exist
     * WHEN a DELETE request to the “/charger/{ID}” endpoint with a charger {C1} object in the body request
     * THEN the response code is 200
     *  AND the {C} charger is deleted
     */
    @Test
    public void deleteExistingChargerTest() {
        DeleteChargerRequest createChargerRequest = DeleteChargerRequest.createCorrectDeleteChargerRequest(createdCharger);
        Response response = RequestExecutor.execute(createChargerRequest);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }

    /**
     * GIVEN a charger {C} with id {ID} does not exist
     * WHEN a DELETE request to the “/charger/{ID}” endpoint with a charger {C1} object in the body request
     * THEN the response code is 404 (not found)
     */
    @Test
    public void deleteNonExistingChargerTest() {
        DeleteChargerRequest createChargerRequest = DeleteChargerRequest.createCorrectDeleteChargerRequest(nonExistingCharger);
        Response response = RequestExecutor.execute(createChargerRequest);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_NOT_FOUND);
    }
}
