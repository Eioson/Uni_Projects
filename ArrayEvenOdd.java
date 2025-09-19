import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArrayEvenOdd {
    public static void main(String[] sigma) {
            try(Scanner sc = new Scanner(System.in)){
            List<Integer> numbers = new ArrayList<>();
            System.out.println("Enter numbers one at a time. Enter 'Done' or press Enter on an empty line when you are done entering numbers.");

            while (true) {
                System.out.print("> ");
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("Done") || input.isEmpty()) {
                    break;
                }
                int num = Integer.parseInt(input);
                numbers.add(num);
            }

            if (numbers.isEmpty()) {
                System.out.println("No numbers were entered.");
                return;
            }
            // Separate even and odd numbers
            List<Integer> evenNumbers = new ArrayList<>();
            List<Integer> oddNumbers = new ArrayList<>();
            for (int num : numbers) {
                if (num % 2 == 0) {
                    evenNumbers.add(num);
                } else {
                    oddNumbers.add(num);
                }
            }

            // Print the results            
            System.out.println("Even numbers: " + evenNumbers);
            System.out.println("Odd numbers: " + oddNumbers);
        }
    }
}
