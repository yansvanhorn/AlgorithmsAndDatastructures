import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-11-20.
 */
public class check_bracketsTest {

    @Test
    public void testCheckBrackets() throws Exception {
        assertEquals(0, check_brackets.checkBrackets("[({})][]"));
        assertEquals(1, check_brackets.checkBrackets("["));
        assertEquals(1, check_brackets.checkBrackets("]"));
        assertEquals(3, check_brackets.checkBrackets("[]("));
    }
}