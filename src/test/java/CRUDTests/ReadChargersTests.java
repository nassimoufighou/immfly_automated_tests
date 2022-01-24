package CRUDTests;

import core.BaseAPITest;
import core.model.Charger;
import core.request.*;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class ReadChargersTests extends BaseAPITest {

    private Charger preconditionCharger001, preconditionCharger002;
    private List<Charger> preconditionChargers;

    /**
     * Creates the charger object to be used in the tests.
     * Persists the charger objects in the system.
     * As this charger object is persisted, after each testrun will be deleted.
     * For this reason the below method is tagged with the @BeforeMethod.
     * Otherwise, the below method would need to be tagged with @BeforeClass.
     */
    @BeforeMethod
    public void setup() {
        preconditionChargers = new ArrayList<>();
        Charger charger001 = new Charger("SN123524012022", "MN24012022G", "ST001", true, 200);
        CreateChargerRequest createChargerRequest = CreateChargerRequest.createCorrectChargerRequest(charger001);
        Response response = createPreconditions(createChargerRequest);
        preconditionCharger001 = response.as(Charger.class);
        preconditionChargers.add(preconditionCharger001);

        Charger charger002 = new Charger("SN144224012022", "MN24012022G", "ST001", true, 200);
        createChargerRequest = CreateChargerRequest.createCorrectChargerRequest(charger002);
        response = createPreconditions(createChargerRequest);
        preconditionCharger002 = response.as(Charger.class);
        preconditionChargers.add(preconditionCharger002);
    }

    /**
     * Deletes the chargers created in the @Test methods.
     * As this chargers are persisted, it's needed to clean them up after each testrun.
     * For this reason the below method is tagged with the @AfterMethod.
     * Otherwise, the below method would be tagged with @AfterClass.
     */
    @AfterMethod
    public void teardown() {
        for (Charger preconditionCharger : preconditionChargers) {
            if (preconditionCharger != null) {
                DeleteChargerRequest deleteChargerRequest = DeleteChargerRequest.createCorrectDeleteChargerRequest(preconditionCharger);
                deletePreconditions(deleteChargerRequest);
            }
        }
    }

    /**
     * GIVEN a charger {C} with id {ID} does exist
     * WHEN a GET request to the “/charger/{ID}” endpoint with a {C} charger object in the body request
     * THEN the response code is 200
     * AND the response body contains the {C} charger with an “_id” field
     */
    @Test
    public void aExistingChargerIsReadTest(){
        GetChargerRequest getChargerRequest = GetChargerRequest.createCorrectGetChargerRequest(preconditionCharger001);
        Response response = RequestExecutor.execute(getChargerRequest);

        Charger chargerFromResponse = response.as(Charger.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertNotNull(chargerFromResponse.get_id());
        softAssert.assertEquals(chargerFromResponse.getSerialNumber(), preconditionCharger001.getSerialNumber());
        softAssert.assertEquals(chargerFromResponse.getModel(), preconditionCharger001.getModel());
        softAssert.assertEquals(chargerFromResponse.getStationId(), preconditionCharger001.getStationId());
        softAssert.assertEquals(chargerFromResponse.isEnabled(), preconditionCharger001.isEnabled());
        softAssert.assertEquals(chargerFromResponse.getStatusCode(), preconditionCharger001.getStatusCode());
        softAssert.assertAll();
    }

    /**
     * GIVEN a charger {C} with id {ID} does exis
     * WHEN a non-GET request to the “/charger/{ID}” endpoint with a {C} charger object in the body reques
     * THEN the response code is 405 (method not allowed)
     */
    @Test
    public void aExistingChargerNotReadWithWrongHttpMethodTest(){
        GetChargerRequest getChargerRequest = GetChargerRequest.createGetChargerRequestWithWrongHttpMethod(preconditionCharger001);
        Response response = RequestExecutor.execute(getChargerRequest);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_METHOD_NOT_ALLOWED);
    }

    /**
     * GIVEN N chargers does exist
     * WHEN a GET request to the “/charger/” endpoint
     * THEN the response code is 200
     * AND the response body contains the N {C} chargers
     */
    @Test
    public void existingChargersAreReadTest(){
        GetAllChargersRequest getAllChargerRequest = GetAllChargersRequest.createCorrectGetAllChargersRequest();
        Response response = RequestExecutor.execute(getAllChargerRequest);

        List<Charger> chargersFromResponse = response.getBody().jsonPath().getList(".", Charger.class);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
        softAssert.assertEquals(chargersFromResponse.size(), preconditionChargers.size());
        softAssert.assertEquals(chargersFromResponse, preconditionChargers);
        //softAssert.assertTrue(chargersFromResponse.contains(preconditionCharger001));
        //softAssert.assertTrue(chargersFromResponse.contains(preconditionCharger002));
        softAssert.assertAll();
    }

    /**
     * GIVEN N chargers does exist
     * WHEN a non-GET request to the “/charger/” endpoint
     * THEN the response code is 405 (method not allowed)
     */
    @Test
    public void existingChargerAreNotReadWithWrongHttpMethodTest(){
        GetAllChargersRequest getAllChargerRequest = GetAllChargersRequest.createGetAllChargersRequestWithWrongHttpMethod();
        Response response = RequestExecutor.execute(getAllChargerRequest);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_METHOD_NOT_ALLOWED);
    }

}
