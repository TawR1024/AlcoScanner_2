package service;


import java.io.UnsupportedEncodingException;

/**
 * Created by ilya-kulakov on 20.10.16.
 */
public class SetEncoding {// TODO: 20.10.16 выполнять проверку в другом потоке. implements Runnable

    static boolean encofingAnable = false;

    static private void setSystem() {
        if (System.getProperty("os.name").startsWith("Windows")) {
            encofingAnable = true;
        } else {
            encofingAnable = false;
        }
    }

    public boolean getEncodigStatus() {
        return encofingAnable;
    }

    public String encodeString(String oldStritng) {
        String newString = null;
        try {
            newString = new String(oldStritng.getBytes(), "Cp1251");
            return newString;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            return newString;
            }
        }
    }
