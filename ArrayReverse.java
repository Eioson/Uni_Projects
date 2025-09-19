import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ArrayReverse {
    public static void main(String[] sigma) {
        try (Scanner sc = new Scanner(System.in)) {
            List<String> inputs = new ArrayList<>();

            // First: gets the inputs from the user
            System.out.println("Enter items one per line. Type 'done' or press Enter on an empty line to finish.");
            while (true) {
                System.out.print("> ");
                String input = sc.nextLine();
                if (input.isEmpty() || input.equalsIgnoreCase("done")) {
                    break;
                }
                inputs.add(input);
            }

            if (inputs.isEmpty()) {
                System.out.println("No items were entered.");
                return;
            }

            // 2. Print the original list
            System.out.println("\nOriginal list: " + inputs);

            // 3. Reverse the list in-place
            for (int i = 0; i < inputs.size() / 2; i++) {
                // Get the index of the element to swap with from the end of the list
                int fromEnd = inputs.size() - 1 - i;

                // Swap the elements
                String temp = inputs.get(i); // Store the "front" element
                inputs.set(i, inputs.get(fromEnd)); // Set the "front" element to the "back" element
                inputs.set(fromEnd, temp); // Set the "back" element to the stored front element
            }

            // 4. Print the reversed list
            System.out.print("Reversed list: " + inputs
            + "\n");
        }
    }
}
