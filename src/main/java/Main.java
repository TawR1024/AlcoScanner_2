import Workbench.LookAndFeel;
import Workbench.Workbench;
import service.IsInternetConnection;

import java.util.logging.Logger;

/**
 * Created by ilya-kulakov on 19.10.16.
 *
 * @version 2.1.0
 */
class Main {

    public static void main(String[] args) {

        LookAndFeel progStyle = new LookAndFeel();
        progStyle.setLookAndFeel();

        IsInternetConnection connection = new IsInternetConnection();
        Thread nThread = new Thread( connection );
        nThread.start();
        Workbench workplace = new Workbench();
        workplace.setLocationRelativeTo( null );
    }
}
