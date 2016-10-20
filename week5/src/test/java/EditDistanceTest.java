import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Marcin_Bazarnik on 2016-10-20.
 */
public class EditDistanceTest {

    @Test
    public void testEditDistance() throws Exception {

        assertEquals(5, EditDistance.editDistance("editing", "distance"));
    }

    @Test
    public void testAlignment() throws Exception {
        String[] alignment = EditDistance.alignment("editing", "distance");
        for(String s : alignment) {
            System.out.println(s);
        }
    }
}