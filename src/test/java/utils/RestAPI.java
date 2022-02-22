package utils;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static io.restassured.config.EncoderConfig.encoderConfig;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.format;

public class RestAPI {

    private static final String URL = "http://jsonplaceholder.typicode.com";

    private final RestAssuredConfig assuredConfig = config()
            .encoderConfig(encoderConfig()
            .defaultContentCharset("UTF-8"));

    public ValidatableResponse getPosts() {
        return get(format("%s/posts", URL)).then();
    }

    public ValidatableResponse getPost(int postId) {
        return get(format("%s/posts/%s", URL, Integer.toString(postId))).then();
    }

    public ValidatableResponse getComments() {
        return get(format("%s/comments", URL)).then();
    }

    public ValidatableResponse getCommentsForPost(int postId) {
        return get(format("%s/posts/%s/comments", URL, Integer.toString(postId))).then();

        // this would return the same data:
        // return get(format("%s/comments?postId=%s", URL, Integer.toString(postId))).then();
    }

    public ValidatableResponse getComment(int commentId) {
        return get(format("%s/comments/%s", URL, Integer.toString(commentId))).then();
    }

    public ValidatableResponse postNewPost(Post post) {
        return given()
                .config(assuredConfig)
                .contentType(JSON)
                .body(post)

                .when()
                    .post(format("%s/posts", URL)).then();
    }

    public ValidatableResponse putUpdateToPost(Post post, int postId) {
        return given()
                .config(assuredConfig)
                .contentType(JSON)
                .body(post)

                .when()
                    .put(format("%s/posts/%s", URL, Integer.toString(postId))).then();
    }

    public ValidatableResponse patchPost(Post post, int postId) {
        return given()
                .config(assuredConfig)
                .contentType(JSON)
                .body(post)

                .when()
                    .patch(format("%s/posts/%s", URL, Integer.toString(postId))).then();
    }

    public Response deletePost(int postId) {
        return given()
                .config(assuredConfig)
                .contentType(JSON)

                .when()
                    .delete(format("%s/posts/%s", URL, Integer.toString(postId)));
    }

}
