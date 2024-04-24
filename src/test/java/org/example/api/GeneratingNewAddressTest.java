package org.example.api;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.example.model.AddressData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GeneratingNewAddressTest {

    @Test
    public void newAddressGenerationWithGivenLabel(){
        Specifications.getSpecifications(Specifications.requestSpec(), Specifications.responseSpec200());
        String label = new Faker().bothify("???????");

        AddressData addressData = RestAssured.given()
                .queryParam("label", label)
                .when()
                .get("/api/v2/get_new_address/")
                .then().log().all()
                .extract().body().jsonPath().getList("data", AddressData.class).get(0);

        assertAll(
                () -> assertEquals(addressData.getLabel(), label, String.format("New address label should be '%s'", label)),
                () -> assertNotNull(addressData.getAddress(), "Address field should not be empty")
        );
    }
}
