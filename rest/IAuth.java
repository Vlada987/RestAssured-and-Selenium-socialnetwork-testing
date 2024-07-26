package rest;

import io.restassured.specification.RequestSpecification;

public interface IAuth {

	RequestSpecification auth(String apikey, String apiSecret, String token, String tokenSecret);

}
