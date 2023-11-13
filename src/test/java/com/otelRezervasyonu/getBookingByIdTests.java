package com.otelRezervasyonu;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class getBookingByIdTests extends baseTest {

    @Test
    public void getBookingById() {
        //Çağırıyı oluşturmamız gerekiyor
        //Response kontrolleri
        //curl -i https://restful-booker.herokuapp.com/booking/1

        /*given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/213")
                .then()
                .log().all()
                .statusCode(200);*/
        Response newBoking = createBooking();
        int reservationID = newBoking.jsonPath().getJsonObject("bookingid");
        Response response = given(spec)
                .when()
                .get("booking/" + reservationID);
        response
                .then()
                .statusCode(200);

        //response.prettyPrint();

        String firstName = response.jsonPath().getJsonObject("firstname");
        Assertions.assertEquals("Sefa", firstName);

        int totalPrice = response.jsonPath().getJsonObject("totalprice");
        Assertions.assertEquals(444, totalPrice);

    }
}
