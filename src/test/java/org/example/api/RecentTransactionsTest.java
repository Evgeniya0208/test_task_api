package org.example.api;

import io.restassured.RestAssured;
import org.example.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.constants.ApiConstants.*;

public class RecentTransactionsTest {

    @Test
    public void checkRecentTransaction() {
        Specifications.getSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        List<Transaction> transactions = RestAssured
                .given()
                .queryParam("type", "received")
                .when()
                .get("/api/v2/get_transactions/")
                .then().log().all()
                .extract().body().jsonPath().getList("data.txs", Transaction.class);

        List<Transaction> expectedTransactions = transactions.stream().filter(tx -> tx.getAmounts_received().stream()
                .anyMatch(ar -> ar.getAmount().equals(TRANSACTION_AMOUNT) && ar.getRecipient().equals(BTC_ADDRESS))).collect(Collectors.toList());

        Assertions.assertFalse(expectedTransactions.isEmpty(), String.format("No transaction found with '%s' recipient and '%s' amount", BTC_ADDRESS, TRANSACTION_AMOUNT));
    }
}
