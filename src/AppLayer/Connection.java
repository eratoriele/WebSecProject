package AppLayer;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

    public static java.sql.Connection Connection() throws SQLException, ClassNotFoundException {

        String url = "jdbc:sqlserver://DESKTOP-ICNMN9R\\SQLEXPRESS:1433;DatabaseName=WebSecProject";

        try {

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            java.sql.Connection con = DriverManager.getConnection(url, "era", "123909321a");

            return con;

        }
        // Exit if there are any errors ex. = wrong username etc
        catch (Exception el) {
            el.printStackTrace();
            throw el;
        }
    }

}
