package answers;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class WireMockAnswer4 {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(5432);

    public void setupStubExercise4() {

        /************************************************
         * Create a stub that will response to a GET
         * to /nz/9814 with an HTTP status code 200,
         * but only after a fixed delay of 2000 milliseconds.
         ************************************************/

        stubFor(get(urlEqualTo("/nz/9814"))
            .willReturn(
                aResponse().
                withStatus(200).
                withFixedDelay(2000)
            )
        );
    }

    @Test
    public void testExercise4Java() {

        setupStubExercise4();

        long responseTime =

        given().
        when().
            get("http://localhost:5432/nz/9814").
            timeIn(TimeUnit.MILLISECONDS);

        Assert.assertTrue(responseTime > 2000);
    }

    @Test
    public void testExercise4Json() {

        long responseTime =

        given().
        when().
            get("http://localhost:9876/nz/9814").
            timeIn(TimeUnit.MILLISECONDS);

        Assert.assertTrue(responseTime > 2000);
    }
}
