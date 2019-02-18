package answers;

import com.github.tomakehurst.wiremock.http.Fault;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.client.ClientProtocolException;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class WireMockAnswer3 {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(5432);

    public void setupStubExercise3() {

        /************************************************
         * Create a stub that will response to a GET
         * to /us/99999 with an error of the
         * RANDOM_DATA_THEN_CLOSE type
         ************************************************/

        stubFor(get(urlEqualTo("/us/99999"))
            .willReturn(
                aResponse().
                withFault(Fault.RANDOM_DATA_THEN_CLOSE)
            )
        );
    }

    @Test(expected = ClientProtocolException.class)
    public void testExercise3Java() {

        setupStubExercise3();

        given().
        when().
            get("http://localhost:5432/us/99999");
    }

    @Test(expected = ClientProtocolException.class)
    public void testExercise3Json() {

        given().
        when().
            get("http://localhost:9876/us/99999");
    }
}
