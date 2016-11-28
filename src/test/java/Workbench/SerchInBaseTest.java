package Workbench;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ilya-kulakov on 28.11.16.
 */
public class SerchInBaseTest {


    static  SearchInBase serchInBase;
    static  String alCode = "0177481000001526569";

    @BeforeClass
    public static void Setup(){
        serchInBase = new SearchInBase();
        serchInBase.code = alCode;
    }

    @Test
    public void searchInBase() throws Exception {
        serchInBase.code  = alCode;
    }

    @Test
    public void isExist() throws Exception {
        boolean res = serchInBase.isExist();
        assertTrue("запись не найдена",res);
    }

}