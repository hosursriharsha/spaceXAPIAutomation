package spaceXAPIAutomation;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import Constants.Constants;
import spaceXAPIAutomation.payLoads;

public class spaceXAPIScenarios extends Base {
	String response;
	JsonPath jsonpath;
	public static String firstCrewId;

	@Test
	public void verifyCrewMembersExists() {
		response = Get("/crew").then().extract().response().asString();
		jsonpath = new JsonPath(response);
		assertTrue(!(jsonpath.getString("name").isEmpty()), "Crew members are not displayed");
		firstCrewId = (jsonpath.getString("id[0]"));
	}

	@Test
	public void verifyFirstCrewMemberName() {
		response = Get("/crew/" + firstCrewId).then().extract().response().asString();
		jsonpath = new JsonPath(response);
		String name = jsonpath.getString("name");
		assertTrue(name.equals(Constants.firstCrewName), "Expected " + Constants.firstCrewName + " but found " + name);

	}
	
	@Test
	public void verifyPayLoads() {
		response=Post("/payloads/query",payLoads.getBodyForQueryingPayloads()).then()
				.extract().response().asString();
		assertTrue(!response.isEmpty(),"Response is empty");
	}
}
