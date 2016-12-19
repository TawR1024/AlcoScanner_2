package Workbench;

import javax.swing.*;
import java.awt.*;
/**
 * Created by Ilya Kulakov on 08.12.16.
 */

@Deprecated
class SearchByCode extends JFrame {

    public SearchByCode(){
        super("Поиск по коду");
        this.setSize( 600,10 );
        this.setLocationRelativeTo( null );
        Container container = this.getContentPane();
        container.setLayout( new GridLayout( 1,2 ) );
        JLabel codeLable = new JLabel( "Введите код: " );
        container.add( codeLable );
        JTextField codeField = new JTextField();
        container.add( codeField );
        codeField.setSize(400,10);
        setVisible( true );
    }

}
