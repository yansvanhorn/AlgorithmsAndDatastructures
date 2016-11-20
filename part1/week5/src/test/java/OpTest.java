import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-10-21.
 */
public class OpTest {

    @Test
    public void testApply() throws Exception {
        assertEquals(5, Op.ADD.apply(2, 3));
        assertEquals(6, Op.MUL.apply(2, 3));
        assertEquals(1, Op.SUB.apply(3, 2));
    }
}