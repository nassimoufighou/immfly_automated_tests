package core.request;


public class GetAllChargersRequest extends Request {

    private static final String CHARGER_ENDPOINT = "/charger";

    private GetAllChargersRequest(HttpMethod method, String endpoint) {
        this.endpoint = endpoint.concat("/");
        this.method = method;
    }

    public static GetAllChargersRequest createCorrectGetAllChargersRequest(){
        return new GetAllChargersRequest(HttpMethod.GET, CHARGER_ENDPOINT);
    }

    public static GetAllChargersRequest createGetAllChargersRequestWithWrongHttpMethod(){
        return new GetAllChargersRequest(HttpMethod.DELETE, CHARGER_ENDPOINT);
    }
}