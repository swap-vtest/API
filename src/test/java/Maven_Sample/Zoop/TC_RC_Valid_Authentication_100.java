package Maven_Sample.Zoop;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
//import kong.unirest.Unirest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import io.restassured.path.json.JsonPath;

import io.restassured.response.Response;

import org.json.JSONArray;
import org.json.JSONException;
//import org.json.JSONObject;
import org.json.simple.JSONObject;
import org.json.JSONString;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.runner.Request;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import baseTest.BaseClass;
import files.ReUsableMethods;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;


//import org.json.Assertions;
@Test

public class TC_RC_Valid_Authentication_100 extends BaseClass {

	String p = LoadProperties().getProperty("baseUri_prod");

	String p1 = LoadProperties().getProperty("path_rc_lite");

	String ContentType1 = LoadProperties().getProperty("ContentType");
	String UserAgent1 = LoadProperties().getProperty("User-Agent");

	String Accept1 = LoadProperties().getProperty("Accept");
	String AcceptEncoding1 = LoadProperties().getProperty("Accept-Encoding");
	String Connection1 = LoadProperties().getProperty("Connection");
	String auth1 = LoadProperties().getProperty("auth");

	String K = LoadProperties().getProperty("api-key");
	String A = LoadProperties().getProperty("app-id");

	String mode = LoadProperties().getProperty("mode");
	String vehicle_registration_number = LoadProperties().getProperty("vehicle_registration_number");

	String consent = LoadProperties().getProperty("consent_Y");
	String consent_text = LoadProperties().getProperty("consent_text_RC");

	@Test

	public void APIResponse() throws IOException {

		System.out.println("mode is:" + mode);
		System.out.println("vehicle_registration_number is:" + vehicle_registration_number);
		
		System.out.println("consent:" + consent);
		System.out.println("consent_text is:" + consent_text);

		// List<Map<String,Object>> requestList = new ArrayList<>();

		Map<String, Object> dt = new HashMap<>();
		// dt.put("mode", "sync");
		dt.put("vehicle_registration_number", vehicle_registration_number);
		
		dt.put("consent", consent);
		dt.put("consent_text", consent_text);
		System.out.println("Map is:" + dt);

		// requestList.add(dt);

		Map<String, Object> m1 = new HashMap<>();
		m1.put("mode", mode);
		m1.put("data", dt);



	       
		
	   /*     HttpResponse<String> response = client.send(request,
	                HttpResponse.BodyHandlers.ofString());
		*/
		
		
		String response = RestAssured.given()

				.header("Content-Type", ContentType1)

				// request.header("Content-Length","<calculated when request is sent>");
				// request.header("Host","<calculated when request is sent>");
				.header("User-Agent", UserAgent1).header("Accept", Accept1).header("Accept-Encoding", AcceptEncoding1)
				.header("Connection", Connection1).header("auth", auth1)

				.header("app-id", A).header("api-key", K)

				.baseUri(p).body(m1).when()

				.post(p1).then().assertThat().log().all().extract().response().asString();
	      	System.out.println("MAP :" + dt);

		JsonPath js = ReUsableMethods.rawToJson(response);
		String id = js.get("response_code");

		String name = js.get("result.user_name");
		String msg = js.get("response_message");
		String bill = js.get("metadata.billable");

		// int id = js.get("response_code");

		System.out.println("user_name is :" + name);
		System.out.println("Response Code is :" + id);
		System.out.println("Response Message is :" + msg);
		System.out.println("Billable is :" + bill);

		assertEquals(id, "100");

	}

}
