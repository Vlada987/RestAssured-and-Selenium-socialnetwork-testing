package tests;

import java.awt.AWTException;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import frontendSelenium.PageActions;
import io.restassured.response.Response;
import rest.Context;
import rest.EContentType;
import rest.Methods;
import rest.OAuth;
import util.Keys;
import util.RandomMsg;
import util.TextContent;

public class TestClass {

	Context context = new Context();
	public String url = "xxxxxxxxxxxxxxxxxxxxxxx";
	String message = RandomMsg.createMsg();

	@BeforeMethod(groups = "rest")
	public void before() {

		context.baseURL = url;
		context.auth = new OAuth();
		context.apikey = Keys.apikey;
		context.apiSecret = Keys.apiSecret;
		context.token = Keys.token;
		context.tokenSecret = Keys.tokenSecret;
	}

	@Test(priority = 1, groups = "rest")
	public void test01_getTheUser() {

		context.URI = "/xxxxxxx";

		Response resp = Methods.GET(context);
		Assert.assertEquals(resp.getStatusCode(), 200);
		Assert.assertTrue(resp.jsonPath().get("data.name").equals("xxxx"));
	}

	@Test(priority = 2, groups = "rest")
	public void test02_postTheMessage() throws JSONException {

		context.URI = "/xxxxx";
		TextContent text = new TextContent();
		text.setText(message);
		context.requestBody = new JSONObject(text);
		context.requestContentType = EContentType.JSON;

		Response resp = Methods.POST(context);

		Assert.assertEquals(resp.getStatusCode(), 201);
		Assert.assertTrue(resp.jsonPath().get("data.text").equals(message));

	}

	@Test(priority = 3, groups = "frontend")
	public void test03_frontendVerification() throws InterruptedException, AWTException {

		PageActions pa = new PageActions();
		List<String> messages = pa.getAppData();

		Assert.assertTrue(messages.stream().anyMatch(el -> el.equals(message)));

	}

}
