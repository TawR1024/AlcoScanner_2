package service;

import java.sql.*;


/**
 * Created by ilya-kulakov on 07.11.16.
 */

class LBaseConnector {

    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "12345";

    private static Connection con;
    private static Statement stmt;
    private static  ResultSet rs;

    public static void LBaseConnector() throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        try {
            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }
}
