import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* This program would find and display the smallest number in a
list of numbers that the user inputs */

public class ArraySmallest {
    public static void main(String[] sigma) {
        Scanner sc = new Scanner(System.in); 
        List<Integer> numbers = new ArrayList<>();
        int smallest = Integer.MAX_VALUE;
        // Prompt the user to enter numbers and add them to the list
        
        System.out.println("Enter the numbers one at a time."
        + " Enter 'Done' or press Enter on an empty line when you are done entering numbers.");

        while (true) {
            System.out.print("> ");
            // int num = scanner.nextInt();
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("Done") || input.isEmpty()) {
                sc.close();
                break;
            }
            int num = Integer.parseInt(input);
            numbers.add(num);
        }

        // Find the smallest number in the list
        if (!numbers.isEmpty()) {
            for (int num : numbers) {
                if (num < smallest) {
                    smallest = num;
                }   
            }
        } else {
            System.out.println("No numbers were entered.");
            return;
        }

        // Display the smallest number
        System.out.println("\nThe smallest number is: " + smallest);
    }
}
