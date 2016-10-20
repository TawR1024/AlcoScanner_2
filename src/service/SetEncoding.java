package service;


/**
 * Created by ilya-kulakov on 20.10.16.
 */
public class SetEncoding {// TODO: 20.10.16 выполнять проверку в другом потоке. implements Runnable
    static String currentEncoding;
   static boolean encofingAnable=false;

    static private void setSystem(){
        if(System.getProperty("os.name").startsWith("Windows")) {
            encofingAnable = true;
            currentEncoding = "windows-1251";
        }
        else{
                encofingAnable = false;
        }
    }
    public String getCurrentEncoding(){
        return currentEncoding;
    }
    public boolean getEncodigStatus(){
        return encofingAnable;
    }
}
