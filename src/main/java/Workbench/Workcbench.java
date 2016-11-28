package Workbench;

import EgaisConnector.SendRequest;
import org.jetbrains.annotations.Nullable;
import service.HtmlParser;
import service.InputCorrector;
import service.IsInternetConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLClientInfoException;

/**
 * Created by ilya-kulakov on 19.10.16.
 */
public class Workcbench extends JFrame {
    private JPanel rootPane;
    private JButton extractCodeButton;
    private JButton RequestButton;
    private JLabel alcoLable;
    private JLabel alcoCodeLable;
    private JTextField PDF417codeField;

    public Workcbench(){
        super("Alco Scaner");
        setContentPane(rootPane);
        alcoLable.setVisible(false);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500, 200);
        extractCodeButton.setVisible(true);


        /**Анонимный класс для обработки нажатия на кнопку "Получить код"*/
        extractCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getCode();
            }
        });
        /*
        TODO: 26.10.16  Проверить наличие корректора ввода. Tip: Добовамить обработчика на изменение текста вызывающ\
        его кррректор
        */
        RequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                    IsInternetConnection connection = new IsInternetConnection("xn--80affoam1c.xn--p1ai");
//                    Thread nThread = new Thread(connection);
//                    nThread.start();
//                    sendRequest();
                requesToExternalBase();
            }

        });



        PDF417codeField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String code = getCode();
                    try {
                        SerchInBase.SerchInBase(code);
                        if (SerchInBase.isExist()) {
                            JOptionPane.showMessageDialog(
                                    new JPanel(),
                                    "Количество товара увеличено.",
                                    "Information",
                                    JOptionPane.YES_OPTION);
                        } else {
                            int userChoose = JOptionPane.showConfirmDialog(
                                    new JPanel(),
                                    "Товар не найден.\nХотите запросить информацию из базы ЕГАИС?",
                                    "Warning",
                                    JOptionPane.YES_NO_OPTION);
                            if (userChoose == JOptionPane.OK_OPTION) {
                                requesToExternalBase();
                            }
                        }
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (SQLClientInfoException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    // TODO: 26.10.16 Создавать новое окно в отдельном потоке. Вычисление ключа не должно мешать открывать базу и наоборот
    private void sendRequest(){
        String requestInfo="";
        try {
            SendRequest request = new SendRequest(PDF417codeField.getText());
            requestInfo = request.getInfo();
        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Request Error",
                    "При получении информации возникла ошибка",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }


        HtmlParser parser = new HtmlParser(requestInfo);
        String[] infoFields = parser.parsing();
        InformationTable infoTable = new InformationTable(infoFields);
        infoTable.setLocationRelativeTo(null);
    }


    @Nullable
    private  String getCode(){
        InputCorrector corrector = new InputCorrector(PDF417codeField.getText());
        String str = corrector.getCorrecredCode();
        if(str == null){
            PDF417codeField.setText(null);
            return null;
        }
        PDF417codeField.setText(str);
        PDF417Decoder alcoCode = new PDF417Decoder(PDF417codeField.getText());
        PDF417codeField.setText(null);
        String code = alcoCode.extractCode();
        alcoCodeLable.setText(code);
        alcoLable.setVisible(true);
        return code;
    }

    private void requesToExternalBase(){
        IsInternetConnection connection = new IsInternetConnection("xn--80affoam1c.xn--p1ai");
        Thread nThread = new Thread(connection);
        nThread.start();
        sendRequest();
    }

}
