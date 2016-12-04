package Workbench;

import com.itextpdf.text.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Document;

import java.io.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;


/**
 * Created by ilya-kulakov on 30.11.16.
 */
public class BaseReader extends JFrame {
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
    private JButton nextBtn;
    private JButton prevBtn;
    private JButton Save;
    private JButton searchByCodeBtn;
    private JButton SaveReportBtn;

    final String url = "jdbc:mysql://localhost:3306/ProductBase?characterEncoding=UTF8";
    final String user = "root";
    final String password = "12345";
    Connection connection;
    int currentId = 2;
    Integer[] idArray = {};

    BaseReader() {
        super( "Просмотр базы" );
        setContentPane( rootPanel );
        pack();
        setVisible( true );
        setResizable( false );
        try {
            init();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print( "1" );
        }


        nextBtn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                getConnection();
                try {
                    layoutManager( executeNext() );
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.print( "2" );
                }
            }

        } );


        prevBtn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                getConnection();
                try {
                    layoutManager( executePrevious() );
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.print( "2" );
                }
            }
        } );


        SaveReportBtn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                //MyFrame frame = new MyFrame();
                com.itextpdf.text.Document document = new com.itextpdf.text.Document( PageSize.A4, 50, 50, 50, 50 );
                try {
                    PdfWriter writer = PdfWriter.getInstance( document, new FileOutputStream( "/home/ilya-kulakov/Рабочий стол/mydoc" ) );
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                document.open();
                Anchor anchorTarget = new Anchor( "Информация по товарам" );
                anchorTarget.setName( "BackToTop" );
                Paragraph paragraph1 = new Paragraph();
                paragraph1.setSpacingBefore( 50 );
                paragraph1.add( anchorTarget );
                try {
                    document.add( paragraph1 );
                } catch (DocumentException e) {
                    e.printStackTrace();
                }

                try {
                    /*для винды c:/Windows/Fonts/arial.ttf*/
                    ResultSet set = getAllBase();
                    final BaseFont bf = BaseFont.createFont( "/usr/share/fonts/truetype/msttcorefonts/arialbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED );
                    Font font1 = new Font( bf );
                    //Font font1 = new Font(F, 14, Font.BOLD,new CMYKColor(0, 255, 0, 0));
                    // document.add(new Paragraph("ПИЗДА.", FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD,	new CMYKColor(0, 255, 0, 0))));
//                    document.add(new Paragraph("Аффтар, зохавай исчё тех аццких олбанских креведок, да выпей йаду.", font1));]
                    System.out.print( "Come" );
                    String str = "";
                    if (set.next()) {
                        document.add( new Paragraph( set.getString( "productName" ), font1 ) );
                        document.add( new Paragraph( set.getString( "alcoCode" ) , font1) );
                        document.add( new Paragraph( set.getString( "codeClass" ), font1 ) );
                        document.add( new Paragraph( set.getString( "strength" ), font1 ) );
                        document.add( new Paragraph( set.getString( "volume" ), font1 ) );
                        document.add( new Paragraph( set.getString( "manufacture" ), font1 ) );
                        document.add( new Paragraph( set.getString( "fsrar" ), font1 ) );
                        document.add( new Paragraph( set.getString( "fullname" ), font1 ) );
                        document.add( new Paragraph( set.getString( "adr" ), font1 ) );
                    }
                    //String str = set.getString( "productName" );
                    document.add( new Paragraph( str, font1 ) );
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Paragraph title1 = new Paragraph( "Chapter 1", FontFactory.getFont( FontFactory.HELVETICA, 18, Font.BOLDITALIC, new CMYKColor( 0, 255, 255, 17 ) ) );
                Chapter chapter1 = new Chapter( title1, 1 );
                chapter1.setNumberDepth( 0 );
                Paragraph title11 = new Paragraph( "This is Section 1 in Chapter 1", FontFactory.getFont( FontFactory.HELVETICA, 16, Font.BOLD, new CMYKColor( 0, 255, 255, 17 ) ) );
                Section section1 = chapter1.addSection( title11 );
                Paragraph someSectionText = new Paragraph( "This text comes as part of section 1 of chapter 1." );
                section1.add( someSectionText );
                someSectionText = new Paragraph( "Following is a 3 X 2 table." );
                section1.add( someSectionText );
                try {
                    document.add( chapter1 );
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                document.close();
            }
        } );
    }


    public class MyFrame extends JFrame {
        MyFrame() {
            setBounds( 0, 0, 600, 500 );
            JFileChooser dialog = new JFileChooser();
            dialog.showOpenDialog( this );
            File file = dialog.getSelectedFile();
            setVisible( true );
        }
    }

    private void init() throws SQLException {
        getConnection();
        ResultSet resultSet;
        String qr2 = "SELECT * FROM  ProductBase.products WHERE id =?";
        String id = "SELECT id FROM ProductBase.products";
        PreparedStatement statement = connection.prepareStatement( qr2 );
        PreparedStatement idStatement = connection.prepareStatement( id );
        statement.setInt( 1, 1 );
        resultSet = idStatement.executeQuery();
        int i = 0;
        ArrayList<Integer> ids = new ArrayList();
        while (resultSet.next()) {
            ids.add( resultSet.getInt( "id" ) );
            System.out.print( resultSet.getInt( "id" ) );
        }
        idArray = ids.toArray( new Integer[ids.size()] );
        resultSet = statement.executeQuery();
        layoutManager( resultSet );
    }

    private void getConnection() {
        try {
            Class.forName( "com.mysql.jdbc.Driver" );
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.print( "3" );
        }
        try {
            connection = DriverManager.getConnection( url, user, password );
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print( "4" );
        }
    }

    private void layoutManager(ResultSet set) throws SQLException {
        if (set.next()) {
            nameTextField.setText( set.getString( "productName" ) );
            AlcoCodetextField.setText( set.getString( "alcoCode" ) );
            CodeClasstextField.setText( set.getString( "codeClass" ) );
            StrengthtextField.setText( set.getString( "strength" ) );
            volumeTextField.setText( set.getString( "volume" ) );
            manufacturTextField.setText( set.getString( "manufacture" ) );
            fsrarTextField.setText( set.getString( "fsrar" ) );
            fsrarTextField.setText( set.getString( "fullname" ) );
            innTextField.setText( set.getString( "inn" ) );
            kppTextField.setText( set.getString( "kpp" ) );
            adresTextField.setText( set.getString( "adr" ) );
            importerTextField.setText( set.getString( "importer" ) );
            importFsrarTextField.setText( set.getString( "impFsrar" ) );
            importFullNameTextField.setText( set.getString( "impFullName" ) );
            importerInn.setText( set.getString( "impInn" ) );
            importerKpp.setText( set.getString( "impKpp" ) );
            importAdresTextField.setText( set.getString( "impAdr" ) );
        }

    }

    private ResultSet executeNext() throws SQLException {
        ResultSet resultSet;
        String qr2 = "SELECT * FROM  ProductBase.products WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement( qr2 );
        statement.setInt( 1, idArray[currentId++] );
        resultSet = statement.executeQuery();
        return resultSet;
    }

    private ResultSet executePrevious() throws SQLException {
        ResultSet resultSet;
        String qr2 = "SELECT * FROM  ProductBase.products WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement( qr2 );
        statement.setInt( 1, idArray[currentId--] );
        resultSet = statement.executeQuery();
        return resultSet;
    }

    private ResultSet getAllBase() throws SQLException {
        getConnection();
        ResultSet resultSet;
        String selectAll = "SELECT * FROM  ProductBase.products WHERE id = 2";
        PreparedStatement statment = connection.prepareStatement( selectAll );
        resultSet = statment.executeQuery();
        return resultSet;
    }

}


