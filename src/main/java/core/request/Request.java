package core.request;

import org.json.simple.JSONObject;

import java.util.HashMap;

public abstract class Request {

    protected HttpMethod method;
    protected String url;
    protected JSONObject body = new JSONObject();
    protected String host;
    protected String endpoint;
    protected String authorizationToken;
    protected String bodyParameters;
    protected HashMap<String, String> urlParameters =  new HashMap<>();

    public HttpMethod getMethod() { return  method; }

    public String getUrl() { return url; }

    public String getEndpoint() {
        return endpoint;
    }

    public JSONObject getBody() { return body; }

    public boolean hasToken() { return  authorizationToken != null && !authorizationToken.isEmpty(); }

    public String getAuthorizationToken() {
        return authorizationToken;
    }
}
