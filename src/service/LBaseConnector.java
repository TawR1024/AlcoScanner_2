package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by ilya-kulakov on 07.11.16.
 */
public class LBaseConnector {

    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "12345";

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void LBaseConnector() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String query = "select count(*) from book";
        String query2 = "select * from book";

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);

            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int count = rs.getInt(1);
                System.out.println("Total number of books in the table : " + count);
            }

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }

}
