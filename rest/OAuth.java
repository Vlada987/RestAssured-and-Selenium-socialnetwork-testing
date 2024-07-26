package rest;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class OAuth implements IAuth {

	@Override
	public RequestSpecification auth(String apikey, String apiSecret, String token, String tokenSecret) {

		RequestSpecification reqSpec = RestAssured.given().auth().oauth(apikey, apiSecret, token, tokenSecret);

		return reqSpec;
	}

}
