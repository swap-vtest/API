package Maven_Sample.Zoop;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import baseTest.BaseClass;
import files.ReUsableMethods;
import io.restassured.RestAssured;
//import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;

import org.testng.annotations.Test;



//import org.json.Assertions;
@Test

public class TC_Blank_API_key extends BaseClass {

	String p = LoadProperties().getProperty("baseUri_prod");

	String p1 = LoadProperties().getProperty("path_bav_lite");

	String ContentType1 = LoadProperties().getProperty("ContentType");
	String UserAgent1 = LoadProperties().getProperty("User-Agent");

	String Accept1 = LoadProperties().getProperty("Accept");
	String AcceptEncoding1 = LoadProperties().getProperty("Accept-Encoding");
	String Connection1 = LoadProperties().getProperty("Connection");
	String auth1 = LoadProperties().getProperty("auth");

	String K = LoadProperties().getProperty("api-key_BLNK");
	//String A = LoadProperties().getProperty("app-test-id");
	String A = LoadProperties().getProperty("app-id");


	String mode = LoadProperties().getProperty("mode");
	String account_number = LoadProperties().getProperty("account_number");
	String ifsc = LoadProperties().getProperty("ifsc");
	String consent = LoadProperties().getProperty("consent_Y");
	String consent_text = LoadProperties().getProperty("consent_text_BAV");

	@Test

	public void APIResponse() throws IOException {

		System.out.println("mode is:" + mode);
		System.out.println("account_number is:" + account_number);
		System.out.println("ifsc is:" + ifsc);
		System.out.println("consent:" + consent);
		System.out.println("consent_text is:" + consent_text);

		// List<Map<String,Object>> requestList = new ArrayList<>();

		Map<String, Object> dt = new HashMap<>();
		// dt.put("mode", "sync");
		dt.put("account_number", account_number);
		dt.put("ifsc", ifsc);
		dt.put("consent", consent);
		dt.put("consent_text", consent_text);
		System.out.println("Map is:" + dt);

		// requestList.add(dt);

		Map<String, Object> m1 = new HashMap<>();
		m1.put("mode", mode);
		m1.put("data", dt);

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
		int id = js.get("response_code");

		String name = js.get("result.beneficiary_name");
		String msg = js.get("response_message");
		String bill = js.get("metadata.billable");

		// int id = js.get("response_code");

		System.out.println("beneficiary_name is :" + name);
		System.out.println("Response Code is :" + id);
		System.out.println("Response Message is :" + msg);
		System.out.println("Billable is :" + bill);

		assertEquals(id, 401);

	}

}

