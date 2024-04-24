package org.example.api_tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.BaseApiTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.notNullValue;

public class GeneratingNewAddressTest extends BaseApiTest {
    private final static String ADDRESS_LABEL = "ngagi44";

    @Test
    public void newAddressGenerationWithGivenLabel(){

//        Map<String, String> addressData = new HashMap<>();
//        addressData.put("api_key", BITCOIN_API_KEY);
//        addressData.put("label", ADDRESS_LABEL);
//        Response addresses = RestAssured.given().queryParams(addressData)
//        RestAssured.given()
//                .queryParams(addressData)
//                .queryParam("api_key", BITCOIN_API_KEY)
//                .queryParam("label", ADDRESS_LABEL)
//                .when()
//                .get("https://block.io/api/v2/get_new_address/")
//                .then()
//                .assertThat()
//                .statusCode(HttpStatus.SC_OK)
//                .body("address", notNullValue())
//                .body("user_id", equalTo(6))
//                .body("label", equalTo(ADDRESS_LABEL));
        Response response = RestAssured
                .given()
                .queryParam("api_key", BITCOIN_API_KEY)
                .queryParam("label", ADDRESS_LABEL)
                .when()
                .get("https://block.io/api/v2/get_new_address/")
                .then()
//                .assertThat()
//                .statusCode(HttpStatus.SC_OK)
                .extract()
                .response();

        System.out.println("Response body: " + response.asString());
//        Assertions.assertTrue(response.getS);
    }
}
