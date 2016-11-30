package service;

import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;
import org.jetbrains.annotations.Contract;

import java.util.HashMap;

/**
 * Created by ilya-kulakov on 30.11.16.
 */
public class LogIn {

        private LogIn() {
            fHashMap = new HashMap();
        }
    private HashMap fHashMap;

        private static LogIn SINGLETON;
        static {
            SINGLETON = new LogIn();
        }

    @Contract("_, null -> fail")
    public static void put(String key, Object data) {
        if (data == null) {
            throw new IllegalArgumentException();
        } else {
            SINGLETON.fHashMap.put(key, data);
        }
    }
}
