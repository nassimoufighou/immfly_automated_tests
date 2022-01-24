package core.request;

import core.model.Charger;

public class GetChargerRequest extends Request {

    private static final String CHARGER_ENDPOINT = "/charger";

    private GetChargerRequest(Charger charger, HttpMethod method, String endpoint) {
        this.endpoint = endpoint.concat("/").concat(charger.get_id());
        this.method = method;
    }

    public static GetChargerRequest createCorrectGetChargerRequest(Charger charger){
        return new GetChargerRequest(charger, HttpMethod.GET, CHARGER_ENDPOINT);
    }

    public static GetChargerRequest createGetChargerRequestWithWrongHttpMethod(Charger charger){
        return new GetChargerRequest(charger, HttpMethod.POST, CHARGER_ENDPOINT);
    }
}