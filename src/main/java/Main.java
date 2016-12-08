import Workbench.LookAndFeel;
import Workbench.Workbench;
import service.LBaseConnector;
import service.IsInternetConnection;

/**
 * Created by ilya-kulakov on 19.10.16.
 *
 * @version 2.1.0
 */
public class Main {

    public static void main(String[] args) {
        try {
            LBaseConnector.LBaseConnector();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        LookAndFeel progStyle = new LookAndFeel();
        progStyle.setLookAndFeel();

        IsInternetConnection connection = new IsInternetConnection();
        Thread nThread = new Thread( connection );
        nThread.start();

        Workbench workplace = new Workbench();
        workplace.setLocationRelativeTo( null );
    }

}
