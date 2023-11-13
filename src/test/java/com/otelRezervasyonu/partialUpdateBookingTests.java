package com.otelRezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class partialUpdateBookingTests extends baseTest{
    @Test
    public void partialUpdateBookingTest(){
        String token = createToken();
        int changeBookingId = getBookingID();
        JSONObject changeBookingPartial = new JSONObject();
        changeBookingPartial.put("firstname" , "Cafer");
        changeBookingPartial.put("lastname" , "hızlı");

        Response response = given(spec)
                            .when()
                            .contentType(ContentType.JSON)
                            .header("Cookie" , "token=" + token)
                            .body(changeBookingPartial.toString())
                            .patch("booking/" + changeBookingId);

        //response.prettyPrint();
        response
                .then()
                .statusCode(200);
    }
}
