package core.request;

import core.model.Charger;
import org.json.simple.JSONObject;

public class UpdateChargerRequest extends Request {

    private static final String CHARGER_ENDPOINT = "/charger";

    private UpdateChargerRequest(Charger oldCharger, Charger newCharger, HttpMethod method, String endpoint) {
        this.method = method;
        this.endpoint = endpoint.concat("/").concat(oldCharger.get_id());
        if (oldCharger == null) this.endpoint = endpoint.concat("/");
        if (newCharger == null) this.body = "";
        if (oldCharger != null && newCharger != null) {
            JSONObject jsonCharger = new JSONObject();
            jsonCharger.put("serialNumber", newCharger.getSerialNumber());
            jsonCharger.put("model", newCharger.getModel());
            jsonCharger.put("stationId", newCharger.getStationId());
            jsonCharger.put("enabled", newCharger.isEnabled());
            jsonCharger.put("statusCode", newCharger.getStatusCode());
            body = jsonCharger.toJSONString();
        }
    }

    public static UpdateChargerRequest createCorrectUpdateChargerRequest(Charger oldCharger, Charger newCharger) {
        return new UpdateChargerRequest(oldCharger, newCharger, HttpMethod.PUT, CHARGER_ENDPOINT);
    }

    public static UpdateChargerRequest createUpdateChargerRequestWithoutBody(Charger oldCharger, Charger newCharger) {
        return new UpdateChargerRequest(oldCharger, null, HttpMethod.PUT, CHARGER_ENDPOINT);
    }

    public static UpdateChargerRequest createUpdateChargerWithWrongHttpMethod(Charger oldCharger, Charger newCharger) {
        return new UpdateChargerRequest(oldCharger, newCharger, HttpMethod.POST, CHARGER_ENDPOINT);
    }
}
