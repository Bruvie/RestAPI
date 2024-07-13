package com.herokuapp.restfulbooker;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.tools.javac.util.List;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetBookingIdsTest {
		
	@Test
	public void getBookingIdsWithoutFilterTest() {
		// Get Response with booking ID
		RestAssured.baseURI = "https://restful-booker.herokuapp.com/";
		RestAssured.useRelaxedHTTPSValidation();
		Response response = RestAssured.get("booking");
		response.print();
		
		// Verify response as 200
		Assert.assertEquals(response.getStatusCode(), 200, "Status Code should be 200 but its not");
		
		// Verify at least 1 booking
		java.util.List<Object> bookingIds = response.jsonPath().getList("bookingid");
		Assert.assertFalse(bookingIds.isEmpty(),"List of bookingIds is empty");
	}
}
