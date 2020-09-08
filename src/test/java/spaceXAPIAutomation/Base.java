package spaceXAPIAutomation;

import static io.restassured.RestAssured.given;

import Constants.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Base {
	
	protected Response Get(String resource) {
		RestAssured.baseURI = Constants.baseURI;
		return given().header("Content-Type", "application/json").when().get(resource);
	}

	protected Response Post(String resource,String body) {
		RestAssured.baseURI = Constants.baseURI;
		return given().header("Content-Type", "application/json").body(body).when().post(resource);
	}
}
