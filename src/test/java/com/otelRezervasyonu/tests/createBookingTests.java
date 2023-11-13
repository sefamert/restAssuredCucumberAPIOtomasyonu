package com.otelRezervasyonu.tests;

import com.otelRezervasyonu.models.Booking;
import com.otelRezervasyonu.models.BookingDates;
import io.restassured.http.ContentType;
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
@Test
    public void createBookingWithPojo(){
        BookingDates bookingDates = new BookingDates("2023-03-01","2023-05-05");
        Booking booking = new Booking("udemy","kurs",500,true,bookingDates,"pass");

        Response response = given(spec)
                .contentType(ContentType.JSON)
                .body(booking)
                .when()
                .post("booking");

    response.then().statusCode(200);
    }
}
