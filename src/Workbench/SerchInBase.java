package Workbench;

import java.sql.*;

/**
 * Created by ilya-kulakov on 26.11.16.
 */
public   class SerchInBase {
    public static String code;

    public static void SerchInBase(String alCode) throws ClassNotFoundException, SQLClientInfoException {
        code = alCode;
    }


   static final String url = "jdbc:mysql://localhost:3306/ProductBase?characterEncoding=UTF8";
   static final String user = "root";
   static final String password = "12345";

    public static boolean isExist(){
        Connection connection;
        //Statement statement;
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
                return false;
            }
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException sqlErr){

        }
        return true;
    }
}
