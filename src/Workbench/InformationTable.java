package Workbench;

import service.SetEncoding;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ilya-kulakov on 20.10.16.
 */
public class InformationTable extends JFrame{
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
    private JTextField textField3;
    private JTextField textField4;
    private JScrollPane importAdresPanel;
    private JTextField importAdresTextField;
    private JButton redactor;
    private String[] scanedCode;

    InformationTable(String[] code){
        super("Show Info");
        setContentPane(rootPanel);
        pack();
        setVisible(true);
        setResizable(false);

        setScanedCode(code);
        setInformation();

        redactor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int userChoose = JOptionPane.showConfirmDialog(
                        rootPanel,
                        "Действительно хотите выполнить это действие?",
                        "Warning",
                        JOptionPane.YES_NO_OPTION);
                if(userChoose == JOptionPane.OK_OPTION ){
                    setEditable();
                }
            }
        });
    }

    private void setScanedCode(String[] code){
        scanedCode = code;
    }
        //@// TODO: 20.10.16 Возможно стоит изменить на массив, для реализации красивого цикла заполнения
    private void setEditable(){
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

        textField3.setEditable(true);

        textField4.setEditable(true);

        importAdresTextField.setEditable(true);

    }

    private void setInformation(){
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
        textField3.setText(scanedCode[14]);
        textField3.setEditable(false);

       // str = SetEncoding.encodeString(scanedCode[15]);
        textField4.setText(scanedCode[15]);
        textField4.setEditable(false);

       // str = SetEncoding.encodeString(scanedCode[16]);
        importAdresTextField.setText(scanedCode[16]);
        importAdresTextField.setEditable(false);


    }

}
