package examples;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;

public class ReadyTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(5432);

    @Test
    public void ReadyTest() {

        exampleGetBody();

        RestAssured.defaultParser = Parser.JSON;

        String value =

        given().when().get("http://localhost:5432/some/thing").then().extract().path("key");

        Assert.assertEquals(value, "value");
    }

    private void exampleGetBody() {

        stubFor(get(urlEqualTo("/some/thing"))
            .willReturn(aResponse()
                .withHeader("Content-Type", "application/json")
                .withBody("{\"key\": \"value\", \"another_key\": \"another_value\"}")));
    }
}
