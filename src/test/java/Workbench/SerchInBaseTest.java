package Workbench;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ilya-kulakov on 28.11.16.
 */
public class SerchInBaseTest {


    private static  SearchInBase serchInBase;
    private static final String alCode = "0177481000001526569";

    @BeforeClass
    public static void Setup(){
        serchInBase = new SearchInBase();
        SearchInBase.code = alCode;
    }

    @Test
    public void searchInBase() throws Exception {
        SearchInBase.code = alCode;
    }

    @Test
    public void isExist() throws Exception {
        boolean res = SearchInBase.isExist();
        assertTrue("запись не найдена",res);
    }
}