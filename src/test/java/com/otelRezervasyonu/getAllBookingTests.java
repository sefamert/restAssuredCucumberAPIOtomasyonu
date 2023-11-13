package com.otelRezervasyonu;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class getAllBookingTests {
    //Çağırıyı oluşturmamız gerekiyor
    //Response kontrolleri
    //curl -i https://restful-booker.herokuapp.com/booking

    @Test
    public void getAllBookingTest(){
        given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .log().all()
                .statusCode(200);

    }
}
