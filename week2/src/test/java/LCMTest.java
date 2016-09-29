import org.junit.Test;

/**
 * Created by Marcin_Bazarnik on 2016-09-28.
 */
public class LCMTest {

    @Test
    public void testStress() throws Exception {

        for (int b = 2; b < 10; b++) {
            for (int a = 2; a < 10; a++) {
                assertEquals(LCM.lcm(a,b), LCM.lcm_naive(a, b));
//                System.out.printf("a*b=%-10d gcd=%-5d lcm_naive=%-5d lcm=%-10d lcm*gcd=%-10d\n", a * b, LCM.gcd(a, b), LCM.lcm_naive(a, b), LCM.lcm(a, b), LCM.gcd(a, b) * LCM.lcm_naive(a, b));
            }
        }
    }

    @Test
    public void test423_1477() throws Exception {
        int a = 423;
        int b = 1477;

        assertEquals(LCM.lcm(a,b), LCM.lcm_naive(a, b));
    }
}