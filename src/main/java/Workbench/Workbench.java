package Workbench;

import EgaisConnector.SendRequest;
import org.apache.log4j.Logger;
import service.HtmlParser;
import service.InputCorrector;
import service.IsInternetConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLClientInfoException;

/**
 * Created by ilya-kulakov on 19.10.16.
 */
public class Workbench extends JFrame {
    private JPanel rootPane;
    private JButton extractCodeButton;
    private JButton RequestButton;
    private JLabel alcoLable;
    private JLabel alcoCodeLable;
    private JTextField PDF417codeField;
    private JButton baseBtn;


    private final Logger logger = Logger.getLogger(Workbench.class);


    public Workbench() {
        super("Сканер марки");
        setContentPane(rootPane);
        alcoLable.setVisible(false);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(500, 300);
        extractCodeButton.setVisible(true);
        setResizable( false );
        baseBtn.setToolTipText( "Открыть локальную базу с товарами");



        /**Анонимный класс для обработки нажатия на кнопку "Получить код"*/
        extractCodeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                getCode();
            }
        });

        RequestButton.addActionListener(new ActionListener() {
           // @Override
            public void actionPerformed(ActionEvent actionEvent) {
                requesToExternalBase();
            }

        });


        PDF417codeField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String code = getCode();
                    SearchInBase.SearchInBase(code);
                    if (SearchInBase.isExist()) {
                        JOptionPane.showMessageDialog(
                                new JPanel(),
                                "Количество товара увеличено.",
                                "Information",
                                JOptionPane.YES_OPTION);
                        logger.info( "Товар добавлен в базу" );
                    } else {
                        int userChoose = JOptionPane.showConfirmDialog(
                                new JPanel(),
                                "Товар не найден.\nХотите запросить информацию из базы ЕГАИС?",
                                "Warning",
                                JOptionPane.YES_NO_OPTION);
                        logger.warn( "Warning: В локальной базе товар НЕБЫЛ найден" );

                        if (userChoose == JOptionPane.OK_OPTION) {
                            requesToExternalBase();
                        }
                    }
                    PDF417codeField.setText( "" );
                    alcoCodeLable.setText( "" );
                }
            }
        });


        baseBtn.addActionListener( new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                BaseReader baseReader = new BaseReader();
                baseReader.setLocationRelativeTo( null );
            }
        } );



       /* подсветка текста*/
        extractCodeButton.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered( mouseEvent );
                extractCodeButton.setForeground( new Color( 12, 17, 223 ) );
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited( mouseEvent );
                extractCodeButton.setForeground( Color.black );
            }
        } );


        RequestButton.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered( mouseEvent );
                RequestButton.setForeground( new Color( 12, 17, 223 ) );
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited( mouseEvent );
               RequestButton.setForeground( Color.black );
            }
        } );


        baseBtn.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered( mouseEvent );
                baseBtn.setForeground( new Color( 12, 17, 223 ) );
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited( mouseEvent );
                baseBtn.setForeground( Color.black );
            }
        } );
    }

    // TODO: 26.10.16 Создавать новое окно в отдельном потоке. Вычисление ключа не должно мешать открывать базу и наоборот
    private void sendRequest() {
        String requestInfo = "";
        try {
            SendRequest request = new SendRequest(PDF417codeField.getText());
            requestInfo = request.getInfo();
        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "Request Error",
                    "При получении информации возникла ошибка",
                    JOptionPane.ERROR_MESSAGE);
            logger.error( "Внешний сервер передал пустой ответ" );
            return;
        }


        HtmlParser parser = new HtmlParser(requestInfo);
        String[] infoFields = parser.parsing();
        InformationTable infoTable = new InformationTable(infoFields);
        infoTable.setLocationRelativeTo(null);
    }


    private String getCode() {
        InputCorrector corrector = new InputCorrector(PDF417codeField.getText());
        String str = corrector.getCorrecredCode();
        if (str == null) {
            return null;
        }
        PDF417codeField.setText(str);
        PDF417Decoder alcoCode = new PDF417Decoder(PDF417codeField.getText());
        String code = alcoCode.extractCode();
        alcoCodeLable.setText(code);
        alcoLable.setVisible(true);
        return code;
    }

    private void requesToExternalBase() {
        IsInternetConnection connection = new IsInternetConnection("xn--80affoam1c.xn--p1ai");
        Thread nThread = new Thread(connection);
        nThread.start();
        sendRequest();
    }
}
