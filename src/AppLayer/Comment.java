package AppLayer;

public class Comment {

    private String posted_by;
    private String comment_body;

    public Comment(String posted_by, String comment_body) {

        this.posted_by = posted_by;
        this.comment_body = comment_body;

    }

    public String getPosted_by() {
        return posted_by;
    }

    public String getComment_body() {
        return comment_body;
    }
}
