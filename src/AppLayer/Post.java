package AppLayer;

public class Post {

    private String PostID;
    private String header;
    private String body;
    private String posted_by;

    public Post(String PostID, String header, String body, String posted_by) {

        this.PostID = PostID;
        this.header = header;
        this.body = body;
        this.posted_by = posted_by;

    }

    public String getPostID() {
        return PostID;
    }

    public String getHeader() {
        return header;
    }

    public String getBody() {
        return body;
    }

    public String getPosted_by() {
        return posted_by;
    }
}
