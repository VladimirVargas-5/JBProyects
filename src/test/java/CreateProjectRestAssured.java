import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CreateProjectRestAssured {

    @Test
    public void verifyProjectUsingJsonString(){
        given()
                .auth()
                .preemptive()
                .basic("vayo@gmail.com","sample5")
                .body("{" +
                        "  \"Content\":\"RestAssuredVladimir\"," +
                        "}")
                .log()
                .all()
        .when()
                .post("http://todo.ly/api/items.json")
        .then();


    }
    @Test
    public void verifyProjectUsingExternalFile(){
        given()
                .auth()
                .preemptive()
                .basic("vayo@gmail.com","sample5")
                .body(new File("E:\\Certifcado Mobile\\JBAPI\\src\\test\\resources\\projectBody.json"))
                .log()
                .all()
                .when()
                .post("http://todo.ly/api/items.json")
                .then()
                        .log()
                        .all();


    }
    @Test
    public void verifyProjectUsingJsonObject(){
        JSONObject body = new JSONObject();
        body.put("Content","VladimirJson");
        body.put("Icon","4");

        Response response = given()
                .auth()
                .preemptive()
                .basic("vayo@gmail.com","sample5")
                .body(body.toString())
                .log()
                .all()
        .when()
                .post("http://todo.ly/api/projects.json");

        response.then()
                .log()
                .all();
        int idItem = response.then().extract().path("Id");

    }


}
