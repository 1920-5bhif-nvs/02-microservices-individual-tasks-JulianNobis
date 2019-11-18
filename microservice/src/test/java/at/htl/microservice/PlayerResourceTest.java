package at.htl.microservice;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PlayerResourceTest {

    @Test
    public void testNumberOfPlayers() {
        given()
                .when()
                .get("/api/player/count")
                .then()
                .statusCode(200)
                .body(is("7"));
    }

    @Test
    public void testAverageItn() {
        given()
                .when()
                .get("/api/player/avg/itn")
                .then()
                .statusCode(200)
                .body(is("5.942857142857143"));
    }

    @Test
    public void testAverageAge() {
        given()
                .when()
                .get("/api/player/avg/age")
                .then()
                .statusCode(200)
                .body(is("24.28571428571429")); // gerundet
    }
}