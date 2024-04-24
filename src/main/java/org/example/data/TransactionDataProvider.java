package org.example.data;

import io.restassured.RestAssured;
import org.example.api.Specifications;

public class TransactionDataProvider {
    public void prepareCryptoTransaction(String addressTo, String addressFrom, String amount) {
        Specifications.getSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        RestAssured.given()
                .queryParam("from_addresses", addressFrom)
                .queryParam("to_addresses", addressTo)
                .queryParam("amounts", amount)
                .when()
                .post("/api/v2/prepare_transaction/")
                .andReturn();
    }

    public void signTransaction() {
    }

    public void submitTransaction() {
    }

    public void createTransaction(String addressTo, String addressFrom, String amount) {
        prepareCryptoTransaction(addressTo, addressFrom, amount);
        signTransaction();
        submitTransaction();
    }
}
