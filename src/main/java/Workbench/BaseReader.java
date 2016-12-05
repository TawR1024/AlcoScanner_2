package Workbench;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Document;

import java.text.SimpleDateFormat;
import java.util.Date;
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

    String path;

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
                MyFrame frame = new MyFrame();
                BaseFont bf = null;
                BaseFont anchorFnt = null;
                try {
                    bf = BaseFont.createFont( "Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED );
                    Font customFont;
                    //bf = BaseFont.createFont(new FileInputStream(getResourceAsStream("fonts/arial.ttf")), BaseFont.IDENTITY_H, BaseFont.EMBEDDED );
                   anchorFnt = BaseFont.createFont( "arialbd.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED );
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Font simplefont = new Font( bf );
                Font titleFont = new Font( anchorFnt );
                Document document = new Document( PageSize.A4, 50, 50, 50, 50 );
                try {
                    PdfWriter writer = PdfWriter.getInstance( document, new FileOutputStream( path + "/Отчёт по товарам" ) );
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                document.open();
                Anchor anchorTarget = new Anchor( "Информация по товарам", titleFont );
                anchorTarget.setName( "BackToTop" );
                Paragraph paragraph1 = new Paragraph();
                paragraph1.setSpacingBefore( 60 );
                paragraph1.add( anchorTarget );
                paragraph1.setAlignment( Element.ALIGN_CENTER );
                Date date = new Date();
                SimpleDateFormat format1 = new SimpleDateFormat("dd.MM.yyyy hh:mm");
                Paragraph baseText =  new Paragraph( "Отчёт по товарам на "+ format1.format(date) + "\nОтчёт сформирован с помощью AlcoScanner 2",simplefont);
                try {
                    document.add( baseText );
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                document.newPage();

                try {
                    document.add( paragraph1 );
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                ResultSet set;
                try {
                    set = getAllBase();
                    int i =1;
                    while (set.next()) {


                        PdfPTable table = new PdfPTable( 2 );
                        PdfPCell cell1 = new PdfPCell( new Paragraph( "Наименование", simplefont ) );
                        PdfPCell cell2 = new PdfPCell( new Paragraph( set.getString( "productName" ), simplefont ) );

                        PdfPCell cell3 = new PdfPCell( new Paragraph( "Алко код", simplefont ) );
                        PdfPCell cell4 = new PdfPCell( new Paragraph( set.getString( "alcoCode" ), simplefont ) );

                        PdfPCell cell5 = new PdfPCell( new Paragraph("Код вида", simplefont ) );
                        PdfPCell cell6 = new PdfPCell( new Paragraph( set.getString( "codeClass" ), simplefont ) );

                        PdfPCell cell7 = new PdfPCell( new Paragraph("Крепость", simplefont ) );
                        PdfPCell cell8 = new PdfPCell( new Paragraph(set.getString( "strength" ), simplefont ) );

                        PdfPCell cell9 = new PdfPCell( new Paragraph("Объём", simplefont ) );
                        PdfPCell cell10 = new PdfPCell( new Paragraph(set.getString( "volume" ), simplefont ) );

                        PdfPCell cell11 = new PdfPCell( new Paragraph("Производитель", simplefont ) );
                        PdfPCell cell12 = new PdfPCell( new Paragraph(set.getString( "manufacture" ), simplefont ) );

                        PdfPCell cell13 = new PdfPCell( new Paragraph("FSRAR_ID", simplefont ) );
                        PdfPCell cell14 = new PdfPCell( new Paragraph(set.getString( "fsrar" ), simplefont ) );

                        PdfPCell cell15 = new PdfPCell( new Paragraph("Полное наименование", simplefont ) );
                        PdfPCell cell16 = new PdfPCell( new Paragraph(set.getString( "fullname" ), simplefont ) );

                        PdfPCell cell17 = new PdfPCell( new Paragraph( "ИНН", simplefont ) );
                        PdfPCell cell18 = new PdfPCell( new Paragraph(set.getString( "inn" ), simplefont ) );

                        PdfPCell cell19 = new PdfPCell( new Paragraph("КПП", simplefont ) );
                        PdfPCell cell20 = new PdfPCell( new Paragraph(set.getString( "kpp" ), simplefont ) );

                        PdfPCell cell21 = new PdfPCell( new Paragraph("Адрес", simplefont ) );
                        PdfPCell cell22 = new PdfPCell( new Paragraph(set.getString( "adr" ), simplefont ) );

                        PdfPCell cell23 = new PdfPCell( new Paragraph("Импортёр", simplefont ) );
                        PdfPCell cell24 = new PdfPCell( new Paragraph(set.getString( "importer" ), simplefont ) );

                        PdfPCell cell25 = new PdfPCell( new Paragraph("FSRAR_ID", simplefont ) );
                        PdfPCell cell26 = new PdfPCell( new Paragraph(set.getString( "impFsrar" ), simplefont ) );

                        PdfPCell cell27 = new PdfPCell( new Paragraph( "Полное наименование", simplefont ) );
                        PdfPCell cell28 = new PdfPCell( new Paragraph(set.getString( "impFullName" ), simplefont ) );

                        PdfPCell cell29 = new PdfPCell( new Paragraph("ИНН", simplefont ) );
                        PdfPCell cell30 = new PdfPCell( new Paragraph(set.getString( "impInn" ), simplefont ) );

                        PdfPCell cell31 = new PdfPCell( new Paragraph("КПП", simplefont ) );
                        PdfPCell cell32 = new PdfPCell( new Paragraph(set.getString( "impKpp" ), simplefont ) );

                        PdfPCell cell33 = new PdfPCell( new Paragraph("Адрес", simplefont ) );
                        PdfPCell cell34 = new PdfPCell( new Paragraph(set.getString( "impAdr" ), simplefont ) );


                        table.addCell( cell1 );
                        table.addCell( cell2 );
                        table.addCell( cell3 );
                        table.addCell( cell4 );
                        table.addCell( cell5 );
                        table.addCell( cell6 );
                        table.addCell( cell7 );
                        table.addCell( cell8 );
                        table.addCell( cell9 );
                        table.addCell( cell10 );
                        table.addCell( cell11 );
                        table.addCell( cell12);
                        table.addCell( cell13 );
                        table.addCell( cell14);
                        table.addCell( cell15 );
                        table.addCell( cell16 );
                        table.addCell( cell17 );
                        table.addCell( cell18 );
                        table.addCell( cell19 );
                        table.addCell( cell20 );
                        table.addCell( cell21 );
                        table.addCell( cell22 );
                        table.addCell( cell23 );
                        table.addCell( cell24 );
                        table.addCell( cell25 );
                        table.addCell( cell26 );
                        table.addCell( cell27 );
                        table.addCell( cell28 );
                        table.addCell( cell29 );
                        table.addCell( cell30 );
                        table.addCell( cell31 );
                        table.addCell( cell32);
                        table.addCell( cell33);
                        table.addCell( cell34 );

                        Anchor anchorTarget1 = new Anchor( "Товар № "+ i, titleFont );
                        anchorTarget.setName( "Товар" );
                        Paragraph par = new Paragraph();
                        par.setSpacingAfter( 60 );
                        par.add( anchorTarget1 );
                        par.setAlignment( Element.ALIGN_CENTER );


                        document.add(par);
                        document.add( table );
                        document.newPage();
                        i++;
                    }
                } catch (DocumentException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

//                    /*для винды c:/Windows/Fonts/arial.ttf*/
                document.close();
            }
        } );
    }


    public class MyFrame extends JFrame {
        MyFrame() {
//            JFileChooser fileopen = new JFileChooser();
//            fileopen.showSaveDialog( this);
//            setBounds( 0, 0, 600, 500 );
//            JFileChooser dialog = new JFileChooser();
//            dialog.showOpenDialog( this );
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            chooser.showSaveDialog(null);
             path = chooser.getSelectedFile().getAbsolutePath();
           // PrintWriter file = new PrintWriter(new File(path+"EncryptedMessage.txt"));

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
        ArrayList<Integer> ids = new ArrayList();
        while (resultSet.next()) {
            ids.add( resultSet.getInt( "id" ) );
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
        }
        try {
            connection = DriverManager.getConnection( url, user, password );
        } catch (SQLException e) {
            e.printStackTrace();
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
        String selectAll = "SELECT * FROM  ProductBase.products";
        PreparedStatement statment = connection.prepareStatement( selectAll );
        resultSet = statment.executeQuery();
        return resultSet;
    }

}

