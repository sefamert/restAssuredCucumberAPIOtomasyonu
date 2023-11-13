package com.otelRezervasyonu.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class deleteBookingTests extends baseTest{
    @Test
    public void deleteBookingTest(){
        String token = createToken();
        int bookingIDforDelete = getBookingID();

        Response response1 = given(spec)
                            .when()
                            .contentType(ContentType.JSON)
                            .header("Cookie" , "token=" + token)
                            .delete("booking/"+ bookingIDforDelete);

        response1
                .then()
                .statusCode(201);

        response1.prettyPrint();
    }
}
