package com.otelRezervasyonu;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class getAllBookingTests extends baseTest{
    //Çağırıyı oluşturmamız gerekiyor
    //Response kontrolleri
    //curl -i https://restful-booker.herokuapp.com/booking

    @Test
    public void getAllBookingTest(){
                given(spec)
                .when()
                .get("booking")
                .then()
                .log().all()
                .statusCode(200);

    }
}
