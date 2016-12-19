package service;

import jdk.internal.dynalink.beans.StaticClass;
import junit.extensions.TestSetup;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ilya-kulakov on 28.11.16.
 */
public class InputCorrectorTest {


    private static InputCorrector inputCorrector;
    @BeforeClass
    public static void Setup(){
        String input = "йцукенгшщзхъфывапролджэячсмить";

        inputCorrector = new InputCorrector(input);
        assertNotNull( "Обьект не созда", inputCorrector);
    }
    @Test
    public void getCorrecredCode() throws Exception {
        String expected = "qwertyuiop[]asdfghjkl;'zxcvbnm";
        assertEquals("Кирилица исправлена неверно", expected,inputCorrector.getCorrecredCode());
    }

}