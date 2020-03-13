import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static io.restassured.RestAssured.given;

public class TestCase {

    public String getStringFromFile(String fileName) throws IOException {
        return IOUtils.toString( new FileInputStream(new File("src/test/resources/"+fileName)));
    }

    @Test
    public void checkApiGet() throws IOException {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://reqres.in")
                .setContentType(ContentType.JSON)
                .build();
        given()
                .spec(requestSpecification)
                .basePath("/api/users")
//                .param("page", 2)
                .when()
                .body(getStringFromFile("data.json"))
                .post()
                .then()
                .log().body();
    }

}

