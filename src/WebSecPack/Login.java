package WebSecPack;

import AppLayer.Post;

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

    private static String username;
    private static String groups;

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
                username = un;
                groups = rs.getString("Groups");
                System.out.println("Logged in " + un);
                userLoggedIn(request, response, con);
            }

        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }

    }

    static public void userLoggedIn(HttpServletRequest request, HttpServletResponse response, java.sql.Connection con) {

        try {
            Statement select = con.createStatement();
            ResultSet rs = select.executeQuery("SELECT * FROM Posts");      // TODO add WHERE to specify groups

            ArrayList<Post> al = new ArrayList<Post>();

            while (rs.next()) {
                String header = rs.getString("PostHeader");
                String body = rs.getString("PostBody");
                String posted_by = rs.getString("Posted_by");

                Post temp = new Post(header, body, posted_by);
                al.add(temp);
            }
            request.setAttribute("posts", al);

            request.getRequestDispatcher("/welcome.jsp").forward(request, response);

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    static public String getUsername() {
        return username;

    }

    static public String getGroups() {
        return groups;
    }
}
