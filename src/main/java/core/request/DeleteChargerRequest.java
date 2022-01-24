package core.request;

import com.aventstack.extentreports.Status;
import core.model.Charger;
import core.reporting.Report;

public class DeleteChargerRequest extends Request {

    private static final String CHARGER_ENDPOINT = "/charger";

    public DeleteChargerRequest(Charger charger, HttpMethod method, String endpoint) {
        if (charger == null) this.endpoint = endpoint.concat("/");
        else this.endpoint = endpoint.concat("/").concat(charger.get_id());
        this.method = method;
        Report.logStepInReport(Status.PASS, "GET request to: " + endpoint);
    }

    public static DeleteChargerRequest createCorrectDeleteChargerRequest(Charger charger) {
        return new DeleteChargerRequest(charger, HttpMethod.DELETE, CHARGER_ENDPOINT);
    }
}
