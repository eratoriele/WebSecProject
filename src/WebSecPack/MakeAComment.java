package WebSecPack;

import AppLayer.CheckMalicousInput;

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

        request.setAttribute("comment", request.getParameter("comment"));

        String comment = (String)request.getAttribute("comment");

        try {
            java.sql.Connection con = AppLayer.Connection.Connection();

            if(CheckMalicousInput.checkText(comment)){
                request.setAttribute("BadInput", "Your input is seen as including malicous input." +
                        "\nIf you think this is incorrect, please contact me");
                Login.userLoggedIn(request, response, con);
            }
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
