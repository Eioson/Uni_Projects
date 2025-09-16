import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinaryAdd {
    public static void main(String[] sigma) {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n--- New Calculation ---");
                System.out.println("Enter the binary numbers to add, one per line.");
                System.out.println("Type 'Calculate' or press Enter on an empty line when you are done.");
                System.out.println("Or, type 'Exit' to quit the program.");

                List<String> binaryNumbers = new ArrayList<>();

                while (true) {
                    System.out.print("> "); // To direct user input
                    String input = sc.nextLine().trim();

                /*   .nextLine() is a String method that reads the next line of input from the user. 
                 *   .trim() is another String method that removes any leading or trailing whitespace (basically spaces) from the input.
                 */

                    if (input.equalsIgnoreCase("exit")) {
                        /*  String.equalIgnoreCase is a method that checks if the input is "exit". Ignoring case sensitivity
                        *     which means I can enter "EXIT", "exit", or "ExIt", etc. and it would still be the same "exit".
                        *       if not, returns a false.
                        */

                        System.out.println("Exiting program... for now :]");
                        return; // Exit the program completely
                    }

                    if (input.isEmpty() || input.equalsIgnoreCase("calculate")) {
                        if (binaryNumbers.size() < 2) {
                            System.out.println("Tsk. Enter at least two numbers to perform a calculation. Again.");
                        }
                        break; // Exits input loop to perform calculation
                    }

                    // Input validation
                    if (!input.matches("[01]+")) {
                        System.out.println("Invalid input. Please enter a valid binary number (0s and 1s only).");
                        continue; // Asks for a new number
                    }
                    binaryNumbers.add(input);
                }

                if (binaryNumbers.size() < 2) {
                    continue; // Not enough numbers, start a new calculation session
                }

                // Perform binary addition using the rules
                String totalSum = binaryNumbers.get(0);
                for (int i = 1; i < binaryNumbers.size(); i++) {
                    totalSum = addBinary(totalSum, binaryNumbers.get(i));
                }

                // Print the results
                System.out.println("\n--- Calculation ---"); 
                for (int i = 0; i < binaryNumbers.size(); i++) {
                    System.out.println((i == 0 ? "  " : "+ ") + binaryNumbers.get(i));
                }

                System.out.println("--------------------");  // Lines  
                System.out.println("= " + totalSum + " (Binary)");

                // For verification, also show the decimal conversion
                try {
                    long decimalSum = 0;
                    for (String binaryNum : binaryNumbers) {
                        decimalSum += Long.parseLong(binaryNum, 2);
                    }
                    System.out.println("= " + decimalSum + " (Decimal)");
                } catch (NumberFormatException e) {
                    System.out.println("(Decimal value is too large to display as a standard integer)");
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    // Seperate Method to add binaries.
    private static String addBinary(String binary1, String binary2) {
        StringBuilder result = new StringBuilder();
        int carry = 0; // Handles the carry bit
        int i = binary1.length() - 1; // Indexes from right to left
        int j = binary2.length() - 1;

        // Loop from right to left, adding bits and handling carry
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) {
                sum += binary1.charAt(i--) - '0'; // Convert char '1' to int 1
        }
            if (j >= 0) {
                sum += binary2.charAt(j--) - '0';
            }
            result.insert(0, sum % 2); // Insert the resulting bit (0 or 1) at the beginning
            carry = sum / 2; // Calculates the new carry (0 or 1)
        }

        return result.toString(); // Assigns the result to a stringn
    }
}