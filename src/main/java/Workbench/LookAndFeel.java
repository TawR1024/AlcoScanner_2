/**
 * Created by ilya-kulakov on 19.10.16.
 * @author ilya-kulakov
 * @vercion 2.0
 * Set system look and feel class.
 * on MAC look like standart MAC UI
 * on Windows looks like standart Win ui
 * on linux platform looks loke system X server
 */
package Workbench;

import javax.swing.*;

public class LookAndFeel {

    public  void setLookAndFeel(){
        try {
            // Set System L&F
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        }
        catch (UnsupportedLookAndFeelException e) {
            // handle exception
        }
        catch (ClassNotFoundException e) {
            // handle exception
        }
        catch (InstantiationException e) {
            // handle exception
        }
        catch (IllegalAccessException e) {
            // handle exception
        }
    }
}
