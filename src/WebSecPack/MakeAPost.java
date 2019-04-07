package WebSecPack;

import AppLayer.CheckMalicousInput;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;

@WebServlet(name = "MakeAPost")
public class MakeAPost extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // TODO Check the length and malicious text

        request.setAttribute("header", request.getParameter("header"));
        request.setAttribute("body", request.getParameter("body"));

        String header = (String)request.getAttribute("header");
        String body = (String)request.getAttribute("body");

        try {
            java.sql.Connection con = AppLayer.Connection.Connection();
            if(CheckMalicousInput.checkText(header) || CheckMalicousInput.checkText(body)){
                request.setAttribute("BadInput", "Your input is seen as including malicous input." +
                        "\nIf you think this is incorrect, please contact me");
                Login.userLoggedIn(request, response, con);
            }
            PreparedStatement insert = con.prepareStatement(
                    "INSERT INTO Posts VALUES('" +
                            Login.getUsername() + "', '" + Login.getGroups() + "', '" +
                            header + "', '" + body + "', '', '');");
            insert.executeUpdate();
            insert.close();
            Login.userLoggedIn(request, response, con);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
