package com.otelRezervasyonu;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    public void getBookings_with_firstname_filter_test(){
        //Yeni bir rezarvasyon
        int bookingID = getBookingID();
        //Çağrıya query parametresi
        spec.queryParam("firstname", "Sefa");
        spec.queryParam("lastname", "Demiratlı");
        //Çağrıyı gerçekleştir.
        Response response = given(spec)
                .when()
                .get("booking");
        //Aseertion ekle
        response.then().statusCode(200);

        List<Integer> list = response.jsonPath().getList("bookingid");
        System.out.println(list);
        Assertions.assertTrue(list.contains(bookingID));
    }
}
