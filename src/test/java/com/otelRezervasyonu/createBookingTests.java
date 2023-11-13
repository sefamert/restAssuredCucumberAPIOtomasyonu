package com.otelRezervasyonu;

import io.restassured.http.ContentType;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class createBookingTests extends baseTest{

    @Test
    public void createBookingTest(){

        Response response = createBooking();

        Assertions.assertEquals("Sefa",response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Demiratlı",response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(444,(Integer) (response.jsonPath().getJsonObject("booking.totalprice")));
        Assertions.assertEquals("2023-02-03", response.jsonPath().getJsonObject("booking.bookingdates.checkin"));


    }
}
