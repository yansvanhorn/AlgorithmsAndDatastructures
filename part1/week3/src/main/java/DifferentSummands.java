import java.util.*;

public class DifferentSummands {
    public static List<Integer> optimalSummands(int total) {
        List<Integer> summands = new ArrayList<Integer>();
        //write your code here

        int last = 0;
        while(total > 0) {
            ++last;

            if(total < last + last + 1) {
                summands.add(total);
                total = 0;
            } else {
                summands.add(last);
                total -= last;
            }
        }

        return summands;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

