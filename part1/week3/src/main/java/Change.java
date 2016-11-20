import java.util.Scanner;

public class Change {
    public static final int[] coins = new int[] { 10, 5, 1 };

    public static int getChange(int money) {
        //write your code here

        int coinidx = 0;
        int coinCount = 0;

        while(money > 0) {
            int coin = coins[coinidx++];

            coinCount += money / coin;
            money %= coin;
        }

        return coinCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));
    }
}

