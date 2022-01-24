import core.model.Charger;
import core.request.GetChargerRequest;
import core.request.RequestExecutor;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 extends BaseAPITest {

    @Test
    public void test() {
        GetChargerRequest getChargerRequest = new GetChargerRequest();
        Response response = RequestExecutor.execute(getChargerRequest);

        Charger charger = response.as(Charger.class);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.SC_OK);
    }
}
