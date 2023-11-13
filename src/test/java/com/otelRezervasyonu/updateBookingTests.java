package com.otelRezervasyonu;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

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
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .header("Cookie" , "token= " +token) //Token@ i headerda gönderiyoruz çünkü yukarıda sayfa özelliklerinde o şekilde belirtmiş
                .body(bookingObject())
                .put("https://restful-booker.herokuapp.com/booking/" + bookingID);
        response.prettyPrint();

    }

    @Test
    public String createToken() {
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
