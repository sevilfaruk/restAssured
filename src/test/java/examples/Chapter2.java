package examples;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

public class Chapter2 {

    //This chapter is about hamcrest matchers.
    @Test
    public void requestZipCodeWithStatusCode200() {
        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                statusCode(200);
    }
    @Test
    public void requestZipCodeWithContentType() {
        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                contentType(ContentType.JSON); //contentType("application/json");

    }
    @Test
    public void requestZipCodeWithLogAllResponse() {
        given().
                log().all().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                log().body();

    }
    @Test
    public void requestZipCodeWithEqualToMethodHamcrest(){
        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places[0].state", equalTo("California"));
    }

    @Test
    public void requestZipCodeWithHasItemMethodHamcrest(){
        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places.'place name'", hasItem("Beverly Hills")); // eğer JSON içinde ki elementte boşluk
                                                                            // olursa buradaki gibi 'place name' şeklinde yazılır
    }

    @Test
    public void requestZipCodeWithHasSizeMethodHamcrest(){
        given().
                when().
                get("http://zippopotam.us/us/90210").
                then().
                assertThat().
                body("places.'place name'", hasSize(1));
    }
}
