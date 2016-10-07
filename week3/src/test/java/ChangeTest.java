import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-09-29.
 */
public class ChangeTest {

    @Test
    public void testGetChange() throws Exception {

        assertEquals(0, Change.getChange(0));
        assertEquals(2, Change.getChange(2));
        assertEquals(2 + 1 + 3, Change.getChange(28));
        assertEquals(100, Change.getChange(1000));
        assertEquals(99 + 1 + 4, Change.getChange(999));

    }
}