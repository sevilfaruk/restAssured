package examples;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(DataProviderRunner.class)
public class Chapter3 {


    // bu method data driven için içeri veri basıcak
    @DataProvider
    public static Object[][] zipCodesAndPlaces() {
        return new Object[][]{
                {"us", "90210", "Beverly Hills"}, //countryCode, zipCode, expectedPlaceName
                {"us", "12345", "Schenectady"},
                {"ca", "B2R", "Waverley"}
                //istediğin kadar data ekle
        };

    }


    //yukarıdaki data provider buraya veri basıcak
    @Test
    @UseDataProvider("zipCodesAndPlaces")
    public void requestZipCodesFromCollection(String countryCode, String zipCode, String expectedPlaceName){
        given().
                pathParam("countryCode", countryCode).pathParam("zipCode", zipCode).
                when().
                    get("http://api.zippopotam.us/{countryCode}/{zipCode}").

                then().
                log().body().
                        assertThat().body("places[0]. 'place name'", equalTo(expectedPlaceName));
    }


    //Aşağıdaki metodlar DataProvider'ın içinde olan verileri tek tek assert ediyor. Amaç kod tekrarından kurtulmak
    /*
    @Test
    public void requestUsZipCode90210_checkPlaceNameInResponseBody_expectBeverlyHills() {

        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places[0].'place name'", equalTo("Beverly Hills"));
    }

    @Test
    public void requestUsZipCode12345_checkPlaceNameInResponseBody_expectSchenectady() {

        given().
                when().
                get("http://zippopotam.us/us/12345").
                then().
                assertThat().
                body("places[0].'place name'", equalTo("Schenectady"));
    }

    @Test
    public void requestCaZipCodeB2R_checkPlaceNameInResponseBody_expectWaverley() {

        given().
                when().
                get("http://zippopotam.us/ca/B2R").
                then().
                assertThat().
                body("places[0].'place name'", equalTo("Waverley"));
    }
}*/

}
