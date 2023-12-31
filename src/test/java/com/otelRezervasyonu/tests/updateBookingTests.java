package com.otelRezervasyonu.tests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

/*curl -X PUT \
  https://restful-booker.herokuapp.com/booking/1 \
  -H 'Content-Type: application/json' \
  -H 'Accept: application/json' \
  -H 'Cookie: token=abc123' \
  -d '{
    "firstname" : "James",
    "lastname" : "Brown",
    "totalprice" : 111,
    "depositpaid" : true,
    "bookingdates" : {
        "checkin" : "2018-01-01",
        "checkout" : "2019-01-01"
    },
    "additionalneeds" : "Breakfast"
}'*/
public class updateBookingTests extends baseTest {
    @Test
    public void updadeBookingTest() {
        //1. Create token
        String token = createToken();

        //2.Rezervasyon oluştur.
        Response createBookingJsonObject = createBooking();
        int bookingID = createBookingJsonObject.jsonPath().getJsonObject("bookingid");
        System.out.println("nihat");
        //3.Request yap
        Response response = given(spec)
                .when()
                .contentType(ContentType.JSON)
                .header("Cookie", "token= " + token) //Token@ i headerda gönderiyoruz çünkü yukarıda sayfa özelliklerinde o şekilde belirtmiş
                .body(bookingObject())
                .put("booking/" + bookingID);
        //response.prettyPrint();

    }
}
