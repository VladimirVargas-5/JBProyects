import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CRUDProjecto {
    @Test
    public void verifyCRUDProject(){

        JSONObject body = new JSONObject();
        body.put("Content","ItemsTest");
        body.put("Checked",false);

        // creacion
        Response response = given()
                .auth()
                .preemptive()
                .basic("vayo@gmail.com","sample5")
                .body(body.toString())
                .log()
                .all()
                .when()
                .post("http://todo.ly/api/items.json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("ItemsTest"))
                .body("Checked",equalTo(false))

                .log()
                .all();

        int idItem = response.then().extract().path("Id");

        // actualizacion

        body.put("Content","ItemsTest");
        body.put("Checked",true);

        given()
                .auth()
                .preemptive()
                .basic("vayo@gmail.com","sample5")
                .body(body.toString())
                .log()
                .all()
        .when()
                .put("http://todo.ly/api/items/"+idItem+".json")
        .then()
                .statusCode(200)
                .body("Content", equalTo("ItemsTest"))
                .body("Checked",equalTo(true))
                .log()
                .all();

        // borrado

        given()
                .auth()
                .preemptive()
                .basic("vayo@gmail.com","sample5")
                .log()
                .all()
        .when()
                .delete("http://todo.ly/api/items/"+idItem+".json")
        .then()
                .statusCode(200)
                .body("Content", equalTo("ItemsTest"))
                .body("Checked",equalTo(true))
                .body("Deleted",equalTo(true))
                .log()
                .all();

    }

}
