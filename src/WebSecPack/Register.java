package WebSecPack;

import AppLayer.HashPassword;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Statement;

@WebServlet(name = "Register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("un", request.getParameter("un"));
        request.setAttribute("pw", request.getParameter("pw"));
        request.setAttribute("q1", request.getParameter("q1"));
        request.setAttribute("q2", request.getParameter("q2"));
        request.setAttribute("q3", request.getParameter("q3"));
        request.setAttribute("q4", request.getParameter("q4"));

        String un = (String)request.getAttribute("un");
        String pw = (String)request.getAttribute("pw");
        String q1 = (String)request.getAttribute("q1");
        String q2 = (String)request.getAttribute("q2");
        String q3 = (String)request.getAttribute("q3");
        String q4 = (String)request.getAttribute("q4");

        boolean badUnPwflag = CheckUnPw.chechUnPw(un, pw, q1, q2, q3, q4);
        if (badUnPwflag) {
            request.setAttribute("UnPwLength", "Username and Password must be between 8 and 20 characters\n" +
                                                      "Please check if you have chosen an answer for each question");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }

                                                                        // SHA-512, 128 chars long,
        byte[] salt = AppLayer.HashPassword.getSalt();                  // Salt
        String hashedpw = AppLayer.HashPassword.getHashedPassword(salt, pw);


        String SQL = "INSERT INTO Users VALUES('" + un + "', '" + hashedpw + "', '"
                + salt + "', " + q1 + "," + q2 + "," + q3 + "," + q4 + "');";

        try {
            java.sql.Connection con = AppLayer.Connection.Connection();
            PreparedStatement insert = con.prepareStatement("INSERT INTO Users VALUES ('" + un + "', '" + hashedpw + "', ?, '"
                    + q1 + "," + q2 + "," + q3 + "," + q4 + "')");
            insert.setBytes(1, salt);
            insert.executeUpdate();
            insert.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
