package examples;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Chapter4 {

    private static RequestSpecification requestSpec;

    //request url i güzel hale getirdik
    @BeforeClass
    public static void createRequestSpecification(){
        requestSpec = new RequestSpecBuilder().
                setBaseUri("https://api.zippopotam.us/").
                build();
    }
    @Test
    public void requestZipCode(){
        given().
                spec(requestSpec). //url
                    when().
                        get("us/90210"). //path
                then().
                assertThat().
                statusCode(200);
    }

    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createResponseSpecification(){
        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @Test
    public void requestUsZipCode() {
        String placeName =

                String.valueOf(given().
                    spec(requestSpec).
                    when().
                    get("http://zippopotam.us/us/90210").

                    then().
                    spec(responseSpec).
                    and().
                    assertThat().
                    body("places[0].'place name'", equalTo("Beverly Hills")));

        System.out.println(placeName);
    }
}
