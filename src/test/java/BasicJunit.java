import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;



public class BasicJunit {

        @Test
    public void verifyGetProjects(){
        given()
                .auth()
                .preemptive()
                .basic("vayo@gmail.com","sample5")
                .log()
                .all()
        .when()
                .get("http://todo.ly/api/items.json")
       .then()
                .log()
                .all();



    }

}
