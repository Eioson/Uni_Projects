import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class ArrayCheck {
    public static void main(String[] sigma) {
        List<Integer> randomNumbers = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < 10; i++) {
            randomNumbers.add(rand.nextInt(100));
        }

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("A list of 10 random numbers (0-99) has been generated.");
            System.out.print("Enter a number to search for: ");
            int searchNum = sc.nextInt();

            boolean found = false;
            int foundIndex = -1; // Use -1 is used to indicate not found

            for (int i = 0; i < randomNumbers.size(); i++) {
                if (randomNumbers.get(i) == searchNum) {
                    found = true;
                    foundIndex = i;
                    break; // Number found, exit the loop immediately
                }
            }

            if (found) {
                System.out.println("Success! The number " + searchNum + " was found at index " + foundIndex + ".");
            } else {
                System.out.println("The number " + searchNum + " was not found in the list.");
            }

            System.out.println("The list was: " + randomNumbers);
        }
    }
}
