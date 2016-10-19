package Workbench;

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
                PDF417Decoder alcoCode = new PDF417Decoder(PDF417codeField.getText());
                alcoCodeLable.setText(alcoCode.extractCode());
            }
        });

        RequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                IsInternetConnection connection = new IsInternetConnection("");
                connection.run();
                if ((connection.getStatus()) == false) {
                    JOptionPane.showMessageDialog(rootPane,
                            "No Internet Connection.",
                            "Connection error",
                            JOptionPane.ERROR_MESSAGE);
                }
                try {
//                    String requestInfo;
//                    SendRequest request = new SendRequest(PDF417codeField.getText());
//                    requestInfo = request.getInfo();
//                    HtmlParser parser = new HtmlParser(requestInfo);
//                    String[] infoFields = parser.parsing();
//                    System.out.print(infoFields);
//                    InformationTable infoTable = new InformationTable(infoFields);
//                    infoTable.setLocationRelativeTo(null);
//                    infoTable.ShowInformation();
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(rootPane, "Request Error",
                            "При получении информации возникла ошибка",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

        });
    }

//    public boolean checkConnection(){
//        IsInternetConnection connection = IsInternetConnection.getInstance();
//    }
}
