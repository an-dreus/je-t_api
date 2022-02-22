import utils.RestAPI;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaTest {

    private RestAPI restAPI = new RestAPI();

    @Test
    public void checkPostSchema() {
        restAPI.getPost(1)

                .assertThat()
                .body(matchesJsonSchemaInClasspath("post-schema.json"));
    }

    @Test
    public void checkCommentSchema() {
        restAPI.getComment(3)

                .assertThat()
                .body(matchesJsonSchemaInClasspath("comment-schema.json"));
    }

}