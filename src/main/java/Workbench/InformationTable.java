package Workbench;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

/**
 * Created by ilya-kulakov on 20.10.16.
 */
class InformationTable extends JFrame {
    private JPanel rootPanel;
    private JLabel nameLable;
    private JLabel alcoCodeLable;
    private JLabel codeClassLable;
    private JLabel strengthLable;
    private JLabel volumeLable;
    private JLabel manufactureLable;
    private JLabel fsrarIDLable;
    private JLabel fullNameLable;
    private JLabel innLable;
    private JLabel kppLable;
    private JLabel adresLable;
    private JLabel importerLable;
    private JLabel importerFsrarIDLable;
    private JLabel importerFullNameLable;
    private JLabel importerInnLable;
    private JLabel importerKppLable;
    private JLabel importerAdresLable;
    private JScrollPane namePanel;
    private JTextField nameTextField;
    private JTextField AlcoCodetextField;
    private JTextField CodeClasstextField;
    private JTextField StrengthtextField;
    private JTextField volumeTextField;
    private JScrollPane ManufacturePanel;
    private JTextField manufacturTextField;
    private JTextField fsrarTextField;
    private JScrollPane fullNamePanel;
    private JTextField fullNameTextField;
    private JTextField innTextField;
    private JTextField kppTextField;
    private JScrollPane adresPanel;
    private JTextField adresTextField;
    private JTextField importerTextField;
    private JTextField importFsrarTextField;
    private JScrollPane importFullName;
    private JTextField importFullNameTextField;
    private JTextField importerInn;
    private JTextField importerKpp;
    private JScrollPane importAdresPanel;
    private JTextField importAdresTextField;
    private JButton redactor;
    private JButton Save;
    private String[] scanedCode;


    InformationTable(String[] code) {
        super("Show Info");
        setContentPane(rootPanel);
        pack();
        setVisible(true);
        setResizable(false);
        setScanedCode(code);
        setInformation();
        Save.setVisible(true);


        redactor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                int userChoose = JOptionPane.showConfirmDialog(
                        rootPanel,
                        "Действительно хотите выполнить это действие?",
                        "Warning",
                        JOptionPane.YES_NO_OPTION);
                if (userChoose == JOptionPane.OK_OPTION) {
                    setEditable();
                }
            }
        });

        Save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                DataBaseWorker adder = new DataBaseWorker();
                try {
                    adder.addNewInfo();
                }catch (ClassNotFoundException cnfe) {
                    cnfe.printStackTrace();
                }
            }
        });

        Save.addMouseListener( new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered( mouseEvent );
                Save.setForeground( new Color( 12, 17, 223  ) );
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited( mouseEvent );
                Save.setForeground( Color.black );

            }
        } );
        redactor.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered( mouseEvent );
                redactor.setForeground( new Color( 12,17,223 ) );
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited( mouseEvent );
                redactor.setForeground( Color.black );
            }
        } );
    }


    private void setScanedCode(String[] code) {
        scanedCode = code;
    }

    // TODO: 20.10.16 Возможно стоит изменить на массив, для реализации красивого цикла заполнения
    private void setEditable() {
        nameTextField.setEditable(true);

        AlcoCodetextField.setEditable(true);

        CodeClasstextField.setEditable(true);

        StrengthtextField.setEditable(true);

        volumeTextField.setEditable(true);

        manufacturTextField.setEditable(true);

        fsrarTextField.setEditable(true);

        fullNameTextField.setEditable(true);

        innTextField.setEditable(true);

        kppTextField.setEditable(true);

        adresTextField.setEditable(true);

        importerTextField.setEditable(true);

        importFsrarTextField.setEditable(true);

        importFullNameTextField.setEditable(true);

        importerInn.setEditable(true);

        importerKpp.setEditable(true);

        importAdresTextField.setEditable(true);

    }

    private void setInformation() {
//        String str;
//        str = SetEncoding.encodeString(scanedCode[0]);
        nameTextField.setText(scanedCode[0]);
        nameTextField.setEditable(false);

        // str = SetEncoding.encodeString(scanedCode[1]);
        AlcoCodetextField.setText(scanedCode[1]);
        AlcoCodetextField.setEditable(false);

        //   str = SetEncoding.encodeString(scanedCode[2]);
        CodeClasstextField.setText(scanedCode[2]);
        CodeClasstextField.setEditable(false);

        //  str = SetEncoding.encodeString(scanedCode[3]);
        StrengthtextField.setText(scanedCode[3]);
        StrengthtextField.setEditable(false);

        //   str = SetEncoding.encodeString(scanedCode[4]);
        volumeTextField.setText(scanedCode[4]);
        volumeTextField.setEditable(false);

        //   str = SetEncoding.encodeString(scanedCode[5]);
        manufacturTextField.setText(scanedCode[5]);
        manufacturTextField.setEditable(false);

        //   str = SetEncoding.encodeString(scanedCode[6]);
        fsrarTextField.setText(scanedCode[6]);
        fsrarTextField.setEditable(false);

        //    str = SetEncoding.encodeString(scanedCode[7]);
        fullNameTextField.setText(scanedCode[7]);
        fullNameTextField.setEditable(false);

        //   str = SetEncoding.encodeString(scanedCode[8]);
        innTextField.setText(scanedCode[8]);
        innTextField.setEditable(false);

        //  str = SetEncoding.encodeString(scanedCode[9]);
        kppTextField.setText(scanedCode[9]);
        kppTextField.setEditable(false);

        //  str = SetEncoding.encodeString(scanedCode[10]);
        adresTextField.setText(scanedCode[10]);
        adresTextField.setEditable(false);

        // str = SetEncoding.encodeString(scanedCode[11]);
        importerTextField.setText(scanedCode[11]);
        importerTextField.setEditable(false);

        //  str = SetEncoding.encodeString(scanedCode[12]);
        importFsrarTextField.setText(scanedCode[12]);
        importFsrarTextField.setEditable(false);

        //   str = SetEncoding.encodeString(scanedCode[13]);
        importFullNameTextField.setText(scanedCode[13]);
        importFullNameTextField.setEditable(false);

        //  str = SetEncoding.encodeString(scanedCode[14]);
        importerInn.setText(scanedCode[14]);
        importerInn.setEditable(false);

        // str = SetEncoding.encodeString(scanedCode[15]);
        importerKpp.setText(scanedCode[15]);
        importerKpp.setEditable(false);

        // str = SetEncoding.encodeString(scanedCode[16]);
        importAdresTextField.setText(scanedCode[16]);
        importAdresTextField.setEditable(false);

    }

    public class DataBaseWorker {
        public  void addNewInfo() throws ClassNotFoundException {
            final String url = "jdbc:mysql://localhost:3306/ProductBase?characterEncoding=UTF8";
            final String user = "root";
            final String password = "12345";

            Connection con;
            Class.forName("com.mysql.jdbc.Driver");
            String qr = "INSERT INTO ProductBase.products(productName,alcoCode,codeClass,strength,volume,manufacture,fsrar,fullname,inn,kpp,adr,importer,impFsrar,impFullName,impInn,impKpp,impAdr)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            try {
                con = DriverManager.getConnection(url, user, password);

                PreparedStatement stmnt = con.prepareStatement(qr);
                stmnt.setString(1, nameTextField.getText());
                stmnt.setString(2, AlcoCodetextField.getText());
                stmnt.setString(3, CodeClasstextField.getText());
                stmnt.setString(4, StrengthtextField.getText());
                stmnt.setString(5, volumeTextField.getText());
                stmnt.setString(6, manufacturTextField.getText());
                stmnt.setString(7, fsrarTextField.getText());
                stmnt.setString(8, fullNameTextField.getText());
                stmnt.setString(9, innTextField.getText());
                stmnt.setString(10, kppTextField.getText());
                stmnt.setString(11, adresTextField.getText());
                stmnt.setString(12, importerTextField.getText());
                stmnt.setString(13, importFsrarTextField.getText());
                stmnt.setString(14, importFullNameTextField.getText());
                stmnt.setString(15, importerInn.getText());
                stmnt.setString(16, importerKpp.getText());
                stmnt.setString(17, importAdresTextField.getText());
                stmnt.execute();
                try {

                    // TODO: 07.12.16 Добавить иконку для JOpane
                    JOptionPane.showMessageDialog(rootPanel,
                            "Товар:\n"+nameTextField.getText()+" сохранён в базу.",
                            "Товар добавлен", JOptionPane.INFORMATION_MESSAGE);
                }catch (NullPointerException e) {
                    System.out.print( "\nнет изображения" );
                }
//                    //15N00001CJJRHTDA8MH1NS9111090190097471551531120421912173024240294724
//                    }//20N00001CGUMZYCB99J1NKN31105001000056NQQMS5VP4HTF5SB46ZSQQJD8BNJP891
                con.close();
                stmnt.close();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
    }

    public  String  getProductName(){
        return  nameTextField.getText();
    }

    public String getAlcoCode(){
        return  AlcoCodetextField.getText();
    }
}

