package IntegrationTests;

import core.BaseAPITest;
import core.model.Charger;
import core.request.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;

public class ChargerIntegrationTests extends BaseAPITest {

    private Charger existingCharger, chargerWithoutChargingStation, chargerToMove, updateToCharger, disabledCharger;
    private String chargingStationId, moveToChargingStationCodeId;

    /**
     * Creates the user objects to be used in the tests.
     * As this objects are modified in the tests, it's needed to create before every testrun.
     * For this reason the below method is tagged with the @BeforeMethod.
     * Otherwise, the below method would be tagged with @BeforeClass.
     */
    @BeforeMethod
    public void setup() {
        chargingStationId = "CHST001";
        moveToChargingStationCodeId = "CHST002";

        Charger preconditionChargerWithoutStationId = new Charger("SN123524012022", "MN24012022G", "", true, 200);
        CreateChargerRequest createChargerRequest = CreateChargerRequest.createCorrectChargerRequest(preconditionChargerWithoutStationId);
        Response response = RequestExecutor.execute(createChargerRequest);
        chargerWithoutChargingStation = response.as(Charger.class);

        Charger preconditionEnabledCharger = new Charger("SN184324012022", "MN24012022G", chargingStationId, true, 200);
        CreateChargerRequest createEnableChargerRequest = CreateChargerRequest.createCorrectChargerRequest(preconditionEnabledCharger);
        response = RequestExecutor.execute(createEnableChargerRequest);
        existingCharger = response.as(Charger.class);

        updateToCharger = new Charger("SN123524012022", "MN24012022G", chargingStationId, true, 200);
        disabledCharger = new Charger("SN184324012022", "MN24012022G", "ST003", false, 200);
        chargerToMove = new Charger("SN184324012022", "MN24012022G", moveToChargingStationCodeId, false, 200);
    }

    /**
     * Deletes the chargers created in the @BeforeMethod method.
     * As this chargers are modified within the test, it's needed to clean them up after each testrun.
     * For this reason the below method is tagged with the @AfterMethod.
     * Otherwise, the below method would be tagged with @AfterClass.
     */
    @AfterMethod
    public void teardown() {
        DeleteChargerRequest deleteChargerRequest = DeleteChargerRequest.createCorrectDeleteChargerRequest(chargerWithoutChargingStation);
        RequestExecutor.execute(deleteChargerRequest);

        deleteChargerRequest = DeleteChargerRequest.createCorrectDeleteChargerRequest(existingCharger);
        RequestExecutor.execute(deleteChargerRequest);
    }

    /**
     * Test: A new charger is added to a charging station
     * GIVEN a charger {C} is not in a charging station with {CS_ID}
     * WHEN the charger {C} is created with charging station {CS_ID}
     * THEN the charger {C} belongs to charging station {CS_ID}
     */
    @Test
    public void newChargerIsAddedToAChargingStationTest() {
        //GET CHARGER
        GetChargerRequest getChargerRequest = GetChargerRequest.createCorrectGetChargerRequest(chargerWithoutChargingStation);
        Response response = RequestExecutor.execute(getChargerRequest);
        chargerWithoutChargingStation = response.as(Charger.class);

        //UPDATE
        UpdateChargerRequest updateChargerRequest = UpdateChargerRequest.createCorrectUpdateChargerRequest(chargerWithoutChargingStation, updateToCharger);
        RequestExecutor.execute(updateChargerRequest);

        //GET CHARGER
        getChargerRequest = GetChargerRequest.createCorrectGetChargerRequest(chargerWithoutChargingStation);
        response = RequestExecutor.execute(getChargerRequest);
        Charger updatedCharger = response.as(Charger.class);
        Assert.assertEquals(updatedCharger.getStationId(), chargingStationId);
    }

    /**
     * Test: Disable charger
     * GIVEN a charger enabled {C} with id {ID} does exist
     * WHEN the charger is disasbled
     * THEN the system marks the charger as disabled
     */
    @Test
    private void enabledChargerIsDisabledTest() {
        //GET CHARGER
        GetChargerRequest getChargerRequest = GetChargerRequest.createCorrectGetChargerRequest(existingCharger);
        Response response = RequestExecutor.execute(getChargerRequest);
        existingCharger = response.as(Charger.class);

        //UPDATE
        UpdateChargerRequest updateChargerRequest = UpdateChargerRequest.createCorrectUpdateChargerRequest(existingCharger, disabledCharger);
        RequestExecutor.execute(updateChargerRequest);

        //GET CHARGER
        getChargerRequest = GetChargerRequest.createCorrectGetChargerRequest(existingCharger);
        response = RequestExecutor.execute(getChargerRequest);
        Charger disabledCharger = response.as(Charger.class);
        Assert.assertFalse(disabledCharger.isEnabled());
    }

    /**
     * Test: A charger is removed from a charging station
     * GIVEN a charger {C} belongs to a charging station with {CS_ID}
     * WHEN the charger {C} removed from the charging station {CS_ID}
     * THEN the charger {C} does not belong to any charging station
     */
    @Test
    public void moveChargerToADifferentChargingStationTest() {
        //GET CHARGER
        GetChargerRequest getChargerRequest = GetChargerRequest.createCorrectGetChargerRequest(existingCharger);
        Response response = RequestExecutor.execute(getChargerRequest);
        existingCharger = response.as(Charger.class);

        //UPDATE
        UpdateChargerRequest updateChargerRequest = UpdateChargerRequest.createCorrectUpdateChargerRequest(existingCharger, chargerToMove);
        RequestExecutor.execute(updateChargerRequest);

        //GET CHARGER
        getChargerRequest = GetChargerRequest.createCorrectGetChargerRequest(existingCharger);
        response = RequestExecutor.execute(getChargerRequest);
        Charger movedCharger = response.as(Charger.class);
        Assert.assertEquals(movedCharger.getStationId(), moveToChargingStationCodeId);
    }
}
