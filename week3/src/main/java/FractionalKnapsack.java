import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class FractionalKnapsack {
    public static double getOptimalValue(int capacity, int[] values, int[] weights) {
        if(values.length == 0) {
            return 0;
        }

        double value = 0;

        PriorityQueue<Item> items = new PriorityQueue<>(values.length, Comparator.comparingLong(Item::getRatio).reversed());
        for (int i = 0; i < values.length; i++) {
            items.add(new Item(values[i], weights[i]));
        }

        while (items.peek() != null && capacity > 0) {
            Item item = items.poll();

            if(item.getWeight() <= capacity) {
                value += item.getValue();
                capacity -= item.getWeight();
            } else {
                value += (double)item.getValue() * (double)capacity / (double)item.getWeight();
                break;
            }
        }

        return value;
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.printf("%.4f\n", getOptimalValue(capacity, values, weights));
    }
} 

class Item
{
    private final int value, weight;
    private final long ratio;

    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
        this.ratio = (long)value * Integer.MAX_VALUE / (long)weight;
    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public long getRatio() {
        return ratio;
    }
}