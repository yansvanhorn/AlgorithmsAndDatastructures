import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-10-21.
 */
public class PlacingParenthesesTest {

    @Test
    public void testParseOps() throws Exception {
        assertArrayEquals(new Op[]{Op.ADD, Op.MUL, Op.SUB}, PlacingParentheses.parseOps("1+2*3-4"));
        assertArrayEquals(new Op[]{Op.ADD, Op.MUL, Op.SUB, Op.MUL}, PlacingParentheses.parseOps("1+2*3-4*5"));
    }

    @Test
    public void testParseValues() throws Exception {
        assertArrayEquals(new long[]{1, 2, 3, 4}, PlacingParentheses.parseValues("1+2*3-4"));
        assertArrayEquals(new long[]{1, 2, 3, 4, 5}, PlacingParentheses.parseValues("1+2*3-4*5"));
    }

    @Test
    public void testGetMaximValue() throws Exception {
        assertEquals(200, PlacingParentheses.getMaximValue("5-8+7*4-8+9"));
    }
}