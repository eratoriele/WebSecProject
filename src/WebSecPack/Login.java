package WebSecPack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(name = "Login")
public class Login extends HttpServlet {

    private String user;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("un", request.getParameter("un"));
        request.setAttribute("pw", request.getParameter("pw"));

        String un = (String)request.getAttribute("un");
        String pw = (String)request.getAttribute("pw");


        try {
            java.sql.Connection con = AppLayer.Connection.Connection();

            Statement select = con.createStatement();
            ResultSet rs = select.executeQuery("SELECT * FROM Users WHERE Username = '" + un + "';");
            rs.next();

            byte[] receivedSalt = rs.getBytes("Salt");
            String hashedGivenStr = AppLayer.HashPassword.getHashedPassword(receivedSalt, pw);

            if (hashedGivenStr.equals(rs.getString("HashedPassword"))){
                this.user = un;
                System.out.println("Logged in " + un);
                userLoggedIn(request, response, con);
            }

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void userLoggedIn(HttpServletRequest request, HttpServletResponse response, java.sql.Connection con) {

        try {
            Statement select = con.createStatement();
            ResultSet rs = select.executeQuery("SELECT * FROM Posts");      // Later add WHERE to specify groups

            ArrayList<String> headers = new ArrayList<String>();
            ArrayList<String> bodies = new ArrayList<String>();

            while (rs.next()) {
                String header = rs.getString("PostHeader");
                String body = rs.getString("PostBody");

                headers.add(header);
                bodies.add(body);
            }
            request.setAttribute("headers", headers);
            request.setAttribute("bodies", bodies);

            request.getRequestDispatcher("/welcome.jsp").forward(request, response);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}