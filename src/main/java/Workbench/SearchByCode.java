package Workbench;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
/**
 * Created by Ilya Kulakov on 08.12.16.
 */

@Deprecated
public class SearchByCode extends JFrame {

    private JLabel codeLable = new JLabel( "Введите код: " );
    private JTextField codeField = new JTextField(  );

    public SearchByCode(){
        super("Поиск по коду");
        this.setSize( 600,10 );
        this.setLocationRelativeTo( null );
        Container container = this.getContentPane();
        container.setLayout( new GridLayout( 1,2 ) );
        container.add( codeLable );
        container.add( codeField );
        codeField.setSize(400,10);
        setVisible( true );
    }

}
