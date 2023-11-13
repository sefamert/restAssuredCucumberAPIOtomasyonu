package com.otelRezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class baseTest
{
    protected  int getBookingID(){
        Response createBookingJsonObject = createBooking();
        int bookingID = createBookingJsonObject.jsonPath().getJsonObject("bookingid");
        return bookingID;
    }
    protected String bookingObject(){

    JSONObject body = new JSONObject();
    body.put("firstname","Sefa");
    body.put("lastname","Demiratlı");
    body.put("totalprice",444);
    body.put("depositpaid",false);

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
    protected String createToken() {
        JSONObject userAndPassword = new JSONObject();
        userAndPassword.put("username", "admin");
        userAndPassword.put("password", "password123");
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(userAndPassword.toString())
                .post("https://restful-booker.herokuapp.com/auth");
        response.prettyPrint();

        return response.jsonPath().getJsonObject("token");
    }
}
