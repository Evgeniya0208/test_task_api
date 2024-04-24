package org.example.api_tests;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.BaseApiTest;
import org.json.JSONObject;
import org.json.JSONString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RecentTransactionsTest extends BaseApiTest {
    private final static String TYPE = "received";
//    private final String TRANSACTION = """
//       "recipient": "2Mubz7KvY9srJU4fPsGkdta6askNymTm4H2",[^"]+"amount": "0.00001000"
//            """;

    @Test
    public void checkRecentTransaction() {

        Response response = RestAssured
                .given()
                .queryParam("api_key", BITCOIN_API_KEY)
                .queryParam("type", TYPE)
                .when()
                .get("https://block.io/api/v2/get_transactions/")
                .then()
                .extract()
                .response();


//        JsonPath jsonPathEvaluator = response.jsonPath();
//        String address = jsonPathEvaluator.get("data.balances.address");

//        String address = response.getString("data.balances.address");
//        response.jsonPath().get("address");

        System.out.println("Response body: " + response.asString());
//        System.out.println("Response body: " + response);
//        .body("data.txs[0].amounts_received[0]", equalTo("{ \"recipient\": \"2Mubz7KvY9srJU4fPsGkdta6askNymTm4H2\", \"amount\": \"0.00001000\" }"));
        Assertions.assertEquals(HttpStatus.SC_OK, response.statusCode(), "Unexpected status code");
//        Assertions.assertEquals(BITCOIN_ADDRESS, response.getBody().jsonPath().getString("data.txs.amounts_received.recipient").replaceAll("\\[|\\]", ""), "");
//        Assertions.assertEquals(transactionAmout, response.getBody().jsonPath().getString("data.txs.amounts_received.recipient.amount").replaceAll("\\[|\\]", ""), "");
//        Assertions.assertTrue(response.asString().contains("{ \"recipient\": \"2Mubz7KvY9srJU4fPsGkdta6askNymTm4H2\", \"amount\": \"0.00001000\" }"), "");
//        Assertions.assertTrue(response.asString().contains("\"recipient\": \"2Mubz7KvY9srJU4fPsGkdta6askNymTm4H2\",\n\"amount\": \"0.00001000\""), "");
        JSONObject jsonObject = new JSONObject(response.asString());

        List<Object> txs = jsonObject.getJSONObject("data").getJSONArray("txs").toList();
        List<Map<String, Object>> amountsReceivedFilteredByRecipient = txs.stream()
                .map(tx -> (Map<String, Object>) tx)
                .flatMap(tx -> ((List<Map<String, Object>>) tx.get("amounts_received")).stream())
                .filter(amount -> BITCOIN_ADDRESS.equals(amount.get("recipient")))
                .collect(Collectors.toList());







    }
}
