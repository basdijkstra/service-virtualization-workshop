import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class WireMockExercise2 {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(5432);

    public void setupStubExercise2() {

        /************************************************
         * Create a stub that will response to a GET
         * to /lv/1050 with an HTTP status code 200 and a
         * response body {"city": "Riga"}. Don't forget to
         * escape the quotes in the JSON body using \"
         ************************************************/


    }

    @Test
    public void testExercise2Java() {

        RestAssured.defaultParser = Parser.JSON;

        setupStubExercise2();

        String city =

        given().
        when().
            get("http://localhost:5432/lv/1050").
        then().
            assertThat().
            statusCode(200).
        and().
            extract().
            path("city");

        Assert.assertEquals("Riga", city);
    }

    @Test
    public void testExercise2Json() {

        given().
        when().
            get("http://localhost:9876/lv/1050").
        then().
            assertThat().
            statusCode(200);
    }
}
