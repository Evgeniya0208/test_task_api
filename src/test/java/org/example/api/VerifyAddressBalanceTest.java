package org.example.api;

import io.restassured.RestAssured;
import org.example.data.TransactionDataProvider;
import org.example.model.Balance;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.example.constants.ApiConstants.*;
import static org.junit.jupiter.api.Assertions.*;

public class VerifyAddressBalanceTest {

    @Test
    public void sendCryptoTransactionAndVerifyAddressBalance(){
        TransactionDataProvider transactionDataProvider = new TransactionDataProvider();
        transactionDataProvider.createTransaction(BTC_ADDRESS, ADDRESS_SEND_FROM, TRANSACTION_AMOUNT);

        Specifications.getSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        List<Balance> balances = RestAssured
                .given()
                .queryParam("addresses", BTC_ADDRESS)
                .when()
                .get("/api/v2/get_address_balance/")
                .then().log().all()
                .extract().body().jsonPath().getList("data.balances", Balance.class);

        String actualBalance = balances.stream().filter(x -> x.getAddress().equals(BTC_ADDRESS)).collect(Collectors.toList()).get(0).getAvailable_balance();
        assertEquals(TRANSACTION_AMOUNT, actualBalance, String.format("Balance for address %s should be %s.", BTC_ADDRESS, TRANSACTION_AMOUNT));
    }
}
