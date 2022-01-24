package core.request;
public abstract class Request {

    protected HttpMethod method;
    protected String body = "";
    protected String endpoint;
    protected String authorizationToken;

    public HttpMethod getMethod() { return  method; }

    public String getEndpoint() {
        return endpoint;
    }

    public String getBody() { return body; }

    public boolean hasToken() { return  authorizationToken != null && !authorizationToken.isEmpty(); }

    public String getAuthorizationToken() {
        return authorizationToken;
    }
}
