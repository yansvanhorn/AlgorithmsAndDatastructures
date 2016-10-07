import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-09-29.
 */
public class DotProductTest {

    @Test
    public void testMaxDotProduct() throws Exception {
        assertEquals(897, DotProduct.maxDotProduct(new int[] {23}, new int[] {39}).longValue());

        assertEquals(23,
                DotProduct.maxDotProduct(
                        new int[] {1, 3, -5},
                        new int[] {-2, 4, 1}).longValue());
    }
}