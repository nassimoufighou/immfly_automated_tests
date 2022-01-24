package core.request;

import core.model.Charger;
import org.json.simple.JSONObject;

public class CreateChargerRequest extends Request {

    private static final String CHARGER_ENDPOINT = "/charger";

    private CreateChargerRequest(Charger charger, HttpMethod method, String endpoint) {
        this.endpoint = endpoint;
        this.method = method;
        this.body = "";
        if (charger != null) {
            JSONObject jsonCharger = new JSONObject();
            jsonCharger.put("serialNumber", charger.getSerialNumber());
            jsonCharger.put("model", charger.getModel());
            jsonCharger.put("stationId", charger.getStationId());
            jsonCharger.put("enabled", true);
            jsonCharger.put("statusCode", charger.getStatusCode());
            body = jsonCharger.toJSONString();
        }
    }

    public static CreateChargerRequest createCorrectChargerRequest(Charger charger) {
        return new CreateChargerRequest(charger, HttpMethod.POST, CHARGER_ENDPOINT);
    }

    public static CreateChargerRequest createChargerRequestWithoutBody(Charger charger) {
        return new CreateChargerRequest(null, HttpMethod.POST, CHARGER_ENDPOINT);
    }

    public static CreateChargerRequest createChargerWithWrongHttpMethod(Charger charger) {
        return new CreateChargerRequest(charger, HttpMethod.PUT, CHARGER_ENDPOINT);
    }
}
