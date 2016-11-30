//package service;
//
//import Workbench.InformationTable;
//
//import java.sql.*;
//
///**
// * Created by ilya-kulakov on 30.11.16.
// */
//public class Updater implements DataBaseWorker_ {
//private  InformationTable table;
//    final String url = "jdbc:mysql://localhost:3306/ProductBase?characterEncoding=UTF8";
//    final String user = "root";
//    final String password = "12345";
//    Connection con;
//
//    public void DataBaseWorker() throws ClassNotFoundException, SQLClientInfoException {
//        Class.forName("com.mysql.jdbc.Driver");
//        try {
//            con = DriverManager.getConnection(url, user, password);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void getInfo(InformationTable table) {
//
//        this.table = table;
//    }
//
//    public void workWithVbase() throws SQLException {
//        String req = "INSERT  INTO ProductBase.products(productName) Values(?)";
//        PreparedStatement stmnt = con.prepareStatement(req);
//        stmnt.setString(1, table.getProductName());
//    }
//
//}
