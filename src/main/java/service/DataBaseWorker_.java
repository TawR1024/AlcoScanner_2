package service;

import Workbench.InformationTable;

import javax.swing.*;
import java.sql.*;

/**
 * Created by ilya-kulakov on 30.11.16.
 */
public interface DataBaseWorker_  {
     String url = "";
     String user = "";
     String password = "";
    void DataBaseWorker()throws  ClassNotFoundException, SQLClientInfoException;
    void getInfo(InformationTable table);
    void workWithVbase() throws SQLException;
    void getStatement(Statement stmnt);
}
