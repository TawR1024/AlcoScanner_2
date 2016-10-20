package Workbench;

import EgaisConnector.SendRequest;
import service.HtmlParser;
import service.InputCorrector;
import service.IsInternetConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/**
 * Created by ilya-kulakov on 19.10.16.
 *
 *
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
                alcoLable.setVisible(true);
                InputCorrector corrector = new InputCorrector(PDF417codeField.getText());
                String str = corrector.getCorrecredCode();
                PDF417codeField.setText(str);
                PDF417Decoder alcoCode = new PDF417Decoder(PDF417codeField.getText());
                alcoCodeLable.setText(alcoCode.extractCode());

            }
        });

        RequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                IsInternetConnection connection = new IsInternetConnection("xn--80affoam1c.xn--p1ai");
                Thread nThread = new Thread(connection);
                nThread.start();
                sendRequest();
            }

        });
    }

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

//    public boolean checkConnection(){
//        IsInternetConnection connection = IsInternetConnection.getInstance();
//    }
}
