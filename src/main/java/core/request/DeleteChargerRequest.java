package core.request;

import core.model.Charger;

public class DeleteChargerRequest extends Request {

    private static final String CHARGER_ENDPOINT = "/charger";

    public DeleteChargerRequest(Charger charger, HttpMethod method, String endpoint) {
        if (charger == null) this.endpoint = endpoint.concat("/");
        else this.endpoint = endpoint.concat("/").concat(charger.get_id());
        this.method = method;
    }

    public static DeleteChargerRequest createCorrectDeleteChargerRequest(Charger charger) {
        return new DeleteChargerRequest(charger, HttpMethod.DELETE, CHARGER_ENDPOINT);
    }
}
