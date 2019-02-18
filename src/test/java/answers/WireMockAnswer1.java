package answers;

import org.junit.Test;
import org.junit.Rule;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class WireMockAnswer1 {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(5432);

    public void setupStubExercise1() {

        /************************************************
         * Create a stub that will response to a POST
         * to /nl/3825 with an HTTP status code 200
         ************************************************/

        stubFor(post(urlEqualTo("/nl/3825"))
            .willReturn(
                aResponse().
                withStatus(200)
            )
        );
    }

    @Test
    public void testExercise1Java() {

        setupStubExercise1();

        given().
        when().
            post("http://localhost:5432/nl/3825").
        then().
            assertThat().
            statusCode(200);
    }

    @Test
    public void testExercise1Json() {

        given().
        when().
            post("http://localhost:9876/nl/3825").
        then().
            assertThat().
            statusCode(200);
    }
}
