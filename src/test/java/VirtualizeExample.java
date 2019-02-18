import org.junit.Test;
import com.tngtech.java.junit.dataprovider.*;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@RunWith(DataProviderRunner.class)
public class VirtualizeExample {

    @DataProvider
    public static Object[][] latvianZipCodes() {
        return new Object[][] {
            { "2163", "Carnikava" },
            { "1050", "Riga" },
            { "2121", "Tilderi"}
        };
    }

    @Test
    @UseDataProvider("latvianZipCodes")
    public void VirtualizeExampleTest(String zipCode, String expectedPlace) {

        given().
            pathParam("zipCode", zipCode).
        when().
            get("http://localhost:9080/latvia/lv/{zipCode}").
        then().
            assertThat().
            body("places[0].'place name'", equalTo(expectedPlace));
    }
}
