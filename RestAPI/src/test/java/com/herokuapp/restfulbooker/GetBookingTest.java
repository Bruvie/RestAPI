package com.herokuapp.restfulbooker;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.sun.tools.javac.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetBookingTest {

	@Test
	public void getBookingIdsWithoutFilterTest() {
		// Get Response with booking
		RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
		RestAssured.useRelaxedHTTPSValidation();
		Response response = RestAssured.get("booking/567");
		response.print();

		// Verify response as 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status Code should be 200 but its not");

		// Verify booking details
		SoftAssert softAssert = new SoftAssert();
		String actualFirstName = response.jsonPath().getString("firstname");
		softAssert.assertEquals(actualFirstName, "Josh","First Name is not expected");
		
		String actualLastName = response.jsonPath().getString("lastname");
		softAssert.assertEquals(actualLastName, "Allen","Last Name is not expected");
		
		int actualPrice = response.jsonPath().getInt("totalprice");
		softAssert.assertEquals(actualPrice, 111,"Total price is not expected");
		
		boolean actualDepositPaid = response.jsonPath().getBoolean("depositpaid");
		softAssert.assertTrue(actualDepositPaid, "Deposit Paid is not expected");
		
		String actualCheckin = response.jsonPath().getString("bookingdates.checkin");
		softAssert.assertEquals(actualCheckin, "2018-01-01", "checkin in response is not expected");

		String actualCheckout = response.jsonPath().getString("bookingdates.checkout");
		softAssert.assertEquals(actualCheckout, "2019-01-01", "checkout in response is not expected");

		String actualAdditionalneeds = response.jsonPath().getString("additionalneeds");
		softAssert.assertEquals(actualAdditionalneeds, "super bowls", "additionalneeds in response is not expected");
		
		softAssert.assertAll();
	}
}
