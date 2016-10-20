import Workbench.LookAndFeel;
import Workbench.Workcbench;
import service.IsInternetConnection;
import service.SetEncoding;

/**
 * Created by ilya-kulakov on 19.10.16.
 */
public class Main {
    public static void main(String[] args) {
        LookAndFeel progStyle = new LookAndFeel();
        progStyle.setLookAndFeel();

        IsInternetConnection connection = new IsInternetConnection();
        Thread nThread = new Thread(connection);
        nThread.start();

        Workcbench workplace = new Workcbench();
        workplace.setLocationRelativeTo(null);
    }

}
