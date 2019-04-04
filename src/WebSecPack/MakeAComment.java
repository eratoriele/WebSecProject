package WebSecPack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;

@WebServlet(name = "MakeAComment")
public class MakeAComment extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // TODO Check the length and malicious text

        request.setAttribute("comment", request.getParameter("comment"));

        String comment = (String)request.getAttribute("comment");

        try {
            java.sql.Connection con = AppLayer.Connection.Connection();
            PreparedStatement insert = con.prepareStatement(
                    "INSERT INTO Comments VALUES('" +
                            Comments.getPostToGetCommentsFrom() + "', '" + Login.getUsername() + "', '" + comment + "');");
            insert.executeUpdate();
            insert.close();
            Login.userLoggedIn(request, response, con);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
