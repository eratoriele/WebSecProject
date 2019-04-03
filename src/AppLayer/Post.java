package AppLayer;

public class Post {

    private String header;
    private String body;
    private String posted_by;

    public Post(String header, String body, String posted_by) {

        this.header = header;
        this.body = body;
        this.posted_by = posted_by;

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
