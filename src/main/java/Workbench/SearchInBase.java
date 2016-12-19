package Workbench;

import java.sql.*;

/**
 * Created by ilya-kulakov on 26.11.16.
 */
class SearchInBase{
    public static String code;

    /**
     * @param alCode
     * @throws ClassNotFoundException
     * @throws SQLClientInfoException
     */
    public static void SearchInBase(String alCode) {
        code = alCode;
    }


   private static final String url = "jdbc:mysql://localhost:3306/ProductBase?characterEncoding=UTF8";
   private static final String user = "root";
   private static final String password = "12345";

    public static boolean isExist(){
        Connection connection;
        ResultSet resultSet;
        String qr2 = "UPDATE ProductBase.products SET  counter = counter + 1 WHERE alcoCode="+code;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String qr1 = "SELECT * FROM ProductBase.products WHERE alcoCode=" + code;
            connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(qr1);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                PreparedStatement stmnt2 = connection.prepareStatement(qr2);
               stmnt2.execute();
            }else{

                connection.close();
                return false;
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println( "SUCK" );
        }catch (SQLException sqlErr){

        }
        return true;
    }
}
