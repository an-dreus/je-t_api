import utils.RestAPI;
import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.apache.http.HttpStatus.SC_NOT_FOUND; // 404

public class GetTest {

    private final RestAPI restAPI = new RestAPI();

    @Test
    public void allPostsQuantity() {
        restAPI.getPosts()

                .assertThat()
                .body("size()", greaterThan(0));
    }

    @Test
    public void allPostsResponseTime() {
        restAPI.getPosts()

                .assertThat()
                .time(lessThan(1000L)); // milliseconds (1000L == 1 second)
    }

    @Test
    public void checkPostUserId() {
        restAPI.getPost(99)

                .assertThat()
                .body("userId", equalTo(10));
    }

    @Test
    public void checkPostTitle() {
        restAPI.getPost(2)

                .assertThat()
                .body("title", equalTo("qui est esse"));
    }

    @Test
    public void checkPostBody() {
        restAPI.getPost(37)

                .assertThat()
                .body("body", containsString("debitis et eaque non officia sed"));
    }

    @Test
    public void postNotFound() {
        restAPI.getPost(999)

                .assertThat()
                .body(is("{}"))
                .and()
                .statusCode(SC_NOT_FOUND);
    }

    @Test
    public void allCommentsBelongToPostId() {
        restAPI.getCommentsForPost(3)

                .assertThat()
                .body("postId", everyItem(is(3)));
    }

    @Test
    public void checkEmailInComment() {
        restAPI.getComment(7)

                .assertThat()
                .body("email", equalTo("Dallas@ole.me"));
    }

    @Test
    public void findInComments() {
        restAPI.getComments()

                .assertThat()
                .body("find {it.id == 24}.name", is("in tempore eos beatae est"))
                .body("findAll {it.id >= 1}.postId", hasItems(1,2,3,4,5));
    }

}