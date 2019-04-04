package WebSecPack;

import AppLayer.Comment;
import AppLayer.Post;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(name = "Comments")
public class Comments extends HttpServlet {

    static private String postToGetCommentsFrom;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("i", request.getParameter("i"));

        int index = Integer.parseInt((String)request.getAttribute("i"));

        postToGetCommentsFrom = Login.getPosts().get(index).getPostID();

        request.setAttribute("post", Login.getPosts().get(index));


        try {
            java.sql.Connection con = AppLayer.Connection.Connection();

            Statement select = con.createStatement();
            ResultSet rs = select.executeQuery("SELECT * FROM Comments WHERE PostID = '" + postToGetCommentsFrom + "';");

            ArrayList<Comment> comments = new ArrayList<>();

            while(rs.next()) {
                String posted_by = rs.getString("Posted_by");
                String comment_body = rs.getString("CommentBody");

                Comment temp = new Comment(posted_by, comment_body);
                comments.add(temp);
            }

            request.setAttribute("comments", comments);

            request.getRequestDispatcher("/comments.jsp").forward(request, response);

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static String getPostToGetCommentsFrom() {
        return postToGetCommentsFrom;
    }
}
