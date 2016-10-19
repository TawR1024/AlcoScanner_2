package service;

import javax.swing.*;

/**
 * Created by ilya-kulakov on 19.10.16.
 */
public  class IsInternetConnection implements Runnable {
    private static String host;
    private static  String rootDNSip = "198.41.0.4";
    private final  String egaisError = "Ресурс егаисик.рф в данный момент не доступен.\n Попробуйте позже";
    private final String conectionErr = " Вданный момент отсутсвует интернет соединение.\nРабота может быть затруднена.\n" +
            "Обратитесь к системному администератору";
    private  String errorMessg;

    private boolean connectionAvaible = false;

    private static IsInternetConnection ourInstance = new IsInternetConnection();

    public static IsInternetConnection getInstance() {
        return ourInstance;
    }

    public IsInternetConnection(String ... hostUrl){
        if(hostUrl.length == 0){
            host = rootDNSip;
            errorMessg = conectionErr;
        }else{
            host = hostUrl[0];
            errorMessg = egaisError;
        }

    }

    private static boolean isReachableByPing() {
        try{
            String cmd = "";
            if(System.getProperty("os.name").startsWith("Windows")) {
                // For Windows
                cmd = "ping -n 3 " + host;
            } else {
                // For Linux and OSX
                cmd = "ping -c 3 " + host;
            }
            Process myProcess = Runtime.getRuntime().exec(cmd);
            myProcess.waitFor();

            if(myProcess.exitValue() == 0) {
                return true;
            } else {

                return false;
            }
        } catch( Exception e ) {
            e.printStackTrace();
            return false;
        }
    }

    private void checkConnection(){

        if(isReachableByPing() == false){
            JOptionPane.showMessageDialog(new JFrame(),errorMessg,
                    "No Internet Connection",
                    JOptionPane.WARNING_MESSAGE);
        }
        else{
            connectionAvaible = true;
        }
    }

    @Override
    public void run() {
        checkConnection();
    }

    public boolean getStatus(){
        return connectionAvaible;
    }
}
