import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class BasicSchemaValidation {
    @Test
    public void validationSchemaTest(){

        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
                .setValidationConfiguration(ValidationConfiguration.newBuilder()
                        .setDefaultVersion(SchemaVersion.DRAFTV4).freeze()).freeze();


        JSONObject body = new JSONObject();
        body.put("Content","EynarJSONObject");
        body.put("Icon",4);

        given()
                .auth()
                .preemptive()
                .basic("restassured@jb.com","12345")
                .body(body.toString())
                .log()
                .all()
        .when()
                .post("http://todo.ly/api/projects.json")
        .then()
                .body(matchesJsonSchemaInClasspath("expectedSchema.json").using(jsonSchemaFactory))
                .log()
                .all();

    }


}