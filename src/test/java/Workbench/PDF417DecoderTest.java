package Workbench;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ilya-kulakov on 28.11.16.
 *
 */
public class PDF417DecoderTest {
    @Test
    public void extractCode() throws Exception {
        PDF417Decoder decoder = new PDF417Decoder("22N00002V92CQV2H1TO83A95092901501646012XUJLVNRCNWM3R2W36T07WQUIB5YTT");
        decoder.extractCode();
        assertEquals("0377505000001233276",decoder.extractCode());
    }

}