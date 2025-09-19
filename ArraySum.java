import java.util.ArrayList;
import java.util.List;

// I'll use this to make a 2^n array up to n = 10, adding it all together.

public class ArraySum {
    public static void main(String[] sigma) {
        List<Integer> powersOfTwo = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
            powersOfTwo.add((int) Math.pow(2, i));
        }

        int sum = 0;
        for (int num : powersOfTwo) { // for each num in powersOfTwo
            sum += num;
        }

        System.out.println("Powers of two: " + powersOfTwo);
        System.out.println("The sum is: " + sum);
    }
}
