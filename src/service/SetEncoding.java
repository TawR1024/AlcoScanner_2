package service;

import java.io.UnsupportedEncodingException;

/**
 * Created by ilya-kulakov on 20.10.16.
 * @deprecated since 2.0.1
 */
public class SetEncoding {// TODO: 20.10.16 выполнять проверку в другом потоке. implements Runnable

    static boolean encodingAnable = false;

    public  static void setSystem() {
        if (true) {//System.getProperty("os.name").startsWith("Windows")
            encodingAnable = true;
        } else {
            encodingAnable = false;
        }
    }

    public static boolean getEncodigStatus() {
        return encodingAnable;
    }

    public static String encodeString(String oldStritng) {
        String newString = null;
        try {
            newString = new String(oldStritng.getBytes(), "UTF-8");
            newString  = newString.replace("И","И");
            return newString;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            return newString;
            }
        }
    }
