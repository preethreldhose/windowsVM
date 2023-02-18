/*

package e2eTestingPWC_AID.reference;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class BasicAuth {

    @Test
    public void getData() {
       RequestSpecification httpRequest = RestAssured.given().auth().basic("2bd25672e30748acc49bf96e", "2b1e5d9d05374675540463331561bf4cc8cc690282933020c21c90ef65ed3f98818cfe65"); 
       Response res = httpRequest.get("https://a.blazemeter.com/api/v4/tests/11007272/files");
       ResponseBody body = res.body();
       //Converting the response body to string
       String rbdy = body.asString();
       System.out.println("Data from the GET API- "+rbdy);
    }
}

*/