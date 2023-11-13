package com.otelRezervasyonu.tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class baseTest
{
    RequestSpecification spec;
    @BeforeEach //Junit' in bize sağladığı özellik. Tüm test koşumları öncesinde bir defa koşacaktır.
    public void setup(){
        spec = new RequestSpecBuilder()        //spec değişkenini diğer class larda da kullanacağım için fonsiyonun üstünde tanımlamamız lazım.
                .setBaseUri("https://restful-booker.herokuapp.com/")
                .addFilters(Arrays.asList(new RequestLoggingFilter(),new ResponseLoggingFilter()))         // bize loglama işlemini sağlayacaktır.
                .build();
    }

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
        Response response = given(spec)
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject())
                .post("booking"); //baseuri tanımladığımız için sadece / dan sonrasını yazmamız ilgili yere gitmesini sağlayacaktır.
       // response.prettyPrint();     //Yukarıda oluşturuğumuz filter' lar sayesinde prettyPrint fonksiyonuna da ihtiyacımız kalmadı.

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
        Response response = given(spec)             //given() fonksiyonuna given(spec) yazdığımızda spec değerleri otomatik olarak çağrı içerisinde kullanılır.
                .when()
                .contentType(ContentType.JSON)
                .body(userAndPassword.toString())
                //.log().all() ////Yukarıda oluşturuğumuz filter' lar sayesinde loglama fonksiyonuna da ihtiyacımız kalmadı.
                .post("auth");
        //response.prettyPrint();

        return response.jsonPath().getJsonObject("token");
    }
}
