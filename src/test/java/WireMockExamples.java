import com.github.tomakehurst.wiremock.http.Fault;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockExamples {

    public void examplePOST() {

        stubFor(post(urlEqualTo("/some/thing"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "text/plain")
                .withBody("Hello world!")));
    }

    public void exampleGETbody() {

        stubFor(get(urlEqualTo("/some/thing"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("\"key\": \"value\", \"another_key\", \"another_value\"")));
    }

    public void exampleGETerror() {

        stubFor(get(urlEqualTo("/fault"))
            .willReturn(aResponse().withFault(Fault.MALFORMED_RESPONSE_CHUNK)));
    }

    public void exampleGETdelay() {

        stubFor(get(urlEqualTo("/delayed")).willReturn(
            aResponse()
                .withStatus(200)
                .withFixedDelay(2000)));
    }
}
