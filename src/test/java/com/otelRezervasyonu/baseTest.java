package com.otelRezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class baseTest
{
    protected String bookingObject(){

    JSONObject body = new JSONObject();
    body.put("firstname","Sefa");
    body.put("lastname","Demiratlı");
    body.put("totalprice",444);
    body.put("depositpaid",false);

//test test
    JSONObject bookindDates = new JSONObject();
    bookindDates.put("checkin","2023-02-03");
    bookindDates.put("checkout","2023-04-06");

    body.put("bookingdates",bookindDates);
    body.put("additionalneeds","Evcil Hayvan Kabul Edilen Oda");

    return body.toString();
}

    protected Response createBooking(){
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject())
                .post("https://restful-booker.herokuapp.com/booking");
        response.prettyPrint();

        //kontrolleri yaz
        response
                .then()
                .statusCode(200);
        return response;
    }
}
