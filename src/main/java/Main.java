import Workbench.LookAndFeel;
import Workbench.Workcbench;
import service.IsInternetConnection;
import service.LBaseConnector;

/**
 * Created by ilya-kulakov on 19.10.16.
 * @version 2.0.1
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
        Thread nThread = new Thread(connection);
        nThread.start();

        Workcbench workplace = new Workcbench();
        workplace.setLocationRelativeTo(null);
    }

}
