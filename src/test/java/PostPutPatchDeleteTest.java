import utils.RestAPI;
import utils.Post;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static org.apache.http.HttpStatus.SC_CREATED; // 201
import static org.apache.http.HttpStatus.SC_OK; // 200

public class PostPutPatchDeleteTest {

    private final RestAPI restAPI = new RestAPI();

    @Test
    public void postPost() {
        String postTitle = "Post title";
        String postBody = "Post body";
        int userId = 25;
        Post post = new Post(postTitle, postBody, userId);

        restAPI.postNewPost(post)

                .assertThat()
                .statusCode(SC_CREATED)
                .body("id", greaterThan(100))
                .body("title", equalTo(postTitle))
                .body("body", equalTo(postBody))
                .body("userId", equalTo(userId));
    }

    @Test
    public void putPost() {
        String postTitle = "New post title";
        String postBody = "New post body";
        int postId = 10;
        int userId = 25;
        Post post = new Post(postId, postTitle, postBody, userId);

        restAPI.putUpdateToPost(post, postId)

                .assertThat()
                .statusCode(SC_OK)
                .body("id", equalTo(postId))
                .body("title", equalTo(postTitle))
                .body("body", equalTo(postBody))
                .body("userId", equalTo(userId));
    }

    @Test
    public void patchPost() {
        String postBody = "Patched body";
        int postId = 8;
        Post post = new Post(postId, postBody);

        restAPI.patchPost(post, postId)

                .assertThat()
                .statusCode(SC_OK)
                .body("body", equalTo(postBody));
    }

    @Test
    public void deletePost() {
        restAPI.deletePost(15)
                .then()

                .assertThat()
                .statusCode(SC_OK);
    }

}