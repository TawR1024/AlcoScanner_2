//package Workbench;
//
//import java.sql.*;
//
///**
// * Created by ilya-kulakov on 27.11.16.
// */
//public class saveInBase {
//    static  String code;
//    public void saveInBase(String alCode){
//        code = alCode;
//    }
//    static final String url = "jdbc:mysql://localhost:3306/ProductBase?characterEncoding=UTF8";
//    static final String user = "root";
//    static final String password = "12345";
//
//    public  static void save(){
//        Connection con;
//            Statement stmt;
//            ResultSet rs;
//            Class.forName("com.mysql.jdbc.Driver");
//            String  qr = "INSERT INTO ProductBase.products(productName,alcoCode,codeClass,strength,volume,manufacture,fsrar,fullname,inn,kpp,adr,importer,impFsrar,impFullName,impInn,impKpp,impAdr)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
//                        PreparedStatement stmnt = con.prepareStatement(qr);
//                        stmnt.setString(1,nameTextField.getText());
//                        stmnt.setString(2,AlcoCodetextField.getText());
//                        stmnt.setString(3,CodeClasstextField.getText());
//                        stmnt.setString(4,StrengthtextField.getText());
//                        stmnt.setString(5,volumeTextField.getText());
//                        stmnt.setString(6,manufacturTextField.getText());
//                        stmnt.setString(7,fsrarTextField.getText());
//                        stmnt.setString(8,fullNameTextField.getText());
//                        stmnt.setString(9,innTextField.getText());
//                        stmnt.setString(10,kppTextField.getText());
//                        stmnt.setString(11,adresTextField.getText());
//                        stmnt.setString(12,importerTextField.getText());
//                        stmnt.setString(13,importFsrarTextField.getText());
//                        stmnt.setString(14,importFullNameTextField.getText());
//                        stmnt.setString(15,importerInn.getText());
//                        stmnt.setString(16,importerKpp.getText());
//                        stmnt.setString(17,importAdresTextField.getText());
//                        stmnt.execute();
//                        JOptionPane.showConfirmDialog(
//                                rootPanel,
//                                "Товар успешно внесён в базу",
//                                "Information",
//                                JOptionPane.YES_OPTION);
//                        }
////                    //15N00001CJJRHTDA8MH1NS9111090190097471551531120421912173024240294724
////                    }//20N00001CGUMZYCB99J1NKN31105001000056NQQMS5VP4HTF5SB46ZSQQJD8BNJP891
//                    con.close();
//                    stmnt.close();
//                } catch (SQLException sqlEx) {
//                    sqlEx.printStackTrace();
//                }
//            }
//        }
//
//    }
