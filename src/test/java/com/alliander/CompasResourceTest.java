package com.alliander;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CompasResourceTest {

    @Test
    public void testDatabaseEndpoint() {
        given()
          .when().get("/database")
          .then()
             .statusCode(200);
    }

}