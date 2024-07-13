package com.herokuapp.restfulbooker;

import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.herokuapp.restfulbooking.BaseTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

public class CreateBookingTest extends BaseTest{

	
		@Test
		public void createBookingTest() {
			Response response = createBooking();
			
			// Verify booking details
			SoftAssert softAssert = new SoftAssert();
			String actualFirstName = response.jsonPath().getString("booking.firstname");
			softAssert.assertEquals(actualFirstName, "Dmitry","First Name is not expected");
			
			String actualLastName = response.jsonPath().getString("booking.lastname");
			softAssert.assertEquals(actualLastName, "Shyshkin","Last Name is not expected");
			
			int actualPrice = response.jsonPath().getInt("booking.totalprice");
			softAssert.assertEquals(actualPrice, 150,"Total price is not expected");
			
			boolean actualDepositPaid = response.jsonPath().getBoolean("booking.depositpaid");
			softAssert.assertFalse(actualDepositPaid, "Deposit Paid is not expected");
			
			String actualCheckin = response.jsonPath().getString("booking.bookingdates.checkin");
			softAssert.assertEquals(actualCheckin, "2020-03-25", "checkin in response is not expected");

			String actualCheckout = response.jsonPath().getString("booking.bookingdates.checkout");
			softAssert.assertEquals(actualCheckout, "2020-03-27", "checkout in response is not expected");

			String actualAdditionalneeds = response.jsonPath().getString("booking.additionalneeds");
			softAssert.assertEquals(actualAdditionalneeds, "Baby crib", "additionalneeds in response is not expected");
			
			softAssert.assertAll();
		}

		
}
