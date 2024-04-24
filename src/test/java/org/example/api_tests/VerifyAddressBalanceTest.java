package org.example.api_tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.BaseApiTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VerifyAddressBalanceTest extends BaseApiTest {

    @Test
    public void sendCryptoTransactionAndVerifyAddressBalance(){
        sendCryptoTransaction(BITCOIN_ADDRESS, TRANSACTION_AMOUNT);

        Response response = RestAssured
                .given()
                .queryParam("api_key", BITCOIN_API_KEY)
                .queryParam("addresses", BITCOIN_ADDRESS)
                .when()
                .get("https://block.io/api/v2/get_address_balance/")
                .then()
                .extract()
                .response();


//        JsonPath jsonPathEvaluator = response.jsonPath();
//        String address = jsonPathEvaluator.get("data.balances.address");

//        String address = response.getString("data.balances.address");
//        response.jsonPath().get("address");



        System.out.println("Response body: " + response.asString());
//        System.out.println("Response body: " + response);
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode(), "Unexpected status code");
        Assertions.assertEquals(BITCOIN_ADDRESS, response.getBody().jsonPath().getString("data.balances.address").replaceAll("\\[|\\]", ""), "");
        Assertions.assertEquals(TRANSACTION_AMOUNT, response.getBody().jsonPath().getString("data.balances.available_balance").replaceAll("\\[|\\]", ""), "");
    }

    private void sendCryptoTransaction(String address, String amount) {
//        String transaction = "/api/v2/prepare_transaction/?api_key=API KEY&from_addresses=ADDRESS1,ADDRESS2,...&to_addresses=ADDRESS1,ADDRESS2,...&amounts=AMOUNT1,AMOUNT2,...\n";
//        RestAssured.given()
//                .queryParam("api_key", BITCOIN_API_KEY)
//                .queryParam("from_addresses", "2N1tWksma5jWayiV9D6bmoQmQXMpxrt3Foj")
//                .queryParam("to_addresses", address)
//                .queryParam("amounts", amount)
//                .when()
//                .post("https://block.io/api/v2/prepare_transaction/")
//                .andReturn();
    }
}
