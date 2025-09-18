import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinaryAdd {
    public static void main(String[] sigma) { 
        try (Scanner sc = new Scanner(System.in)) { // Initializes the Scanner object
            while (true) {
                System.out.println("\n--- New Calculation ---");
                System.out.println("Enter the binary numbers to add, one per line.");
                System.out.println("Type 'Calculate' or press Enter on an empty line when you are done.");
                System.out.println("Or, type 'Exit' to quit the program.");

                List<String> binaryNumbers = new ArrayList<>();

                while (true) { // Start of input loop
                    System.out.print("> "); // Directs user input
                    String input = sc.nextLine().trim();

                /*   .nextLine() is a String method that reads the next line of input from the user. 
                 *   .trim() is another String method that removes any leading or trailing whitespace (basically spaces) from the input.
                 */

                    if (input.equalsIgnoreCase("exit")) {
                        /*  String.equalIgnoreCase is a method that checks if the input is "exit". Ignoring case sensitivity
                        *     which means I can enter "EXIT", "exit", or "ExIt", etc. and it would still be the same "exit".
                        *       if not, returns a false.
                        *   The purpose of this is to detect if the user wants to exit the program.
                        */

                        System.out.println("Exiting program... for now :]");
                        return; // Exit the program completely
                    }

                    if (input.isEmpty() || input.equalsIgnoreCase("calculate")) {
                        if (binaryNumbers.size() < 2) { // Checks if the amount of numbers inputted is less than two)
                            System.out.println("Tsk. Enter at least two numbers to perform a calculation. Again.");
                        }
                        break; // Exits input loop to perform calculation

                    } // This if statement checks if the input is empty or "calculate" and exits the loop to start the calculation.

                    // Input validation
                    if (!input.matches("[01]+")) {
                        /* Regex in detail:
                        *   [] - Characters inside the brackets to be compared with String.matches.
                        *   [01] - Characters inside the brackets that can only be 0 or 1. tldr: only allow 0 or 1
                        *   + - One or more repetitions of the character inside the brackets, basically if it repeats once or more
                        *
                        *   If all of these conditions are met, then the input is a valid binary number, thus returning a True boolean value 
                        * that can be modified with NOT.
                        */

                        System.out.println("Invalid input. Please enter a valid binary number (0s and 1s only).");
                        continue; // Asks for a new number
                    }
                    binaryNumbers.add(input); // Finally adds/appends the input to the list
                }

                if (binaryNumbers.size() < 2) {
                    continue; // Not enough numbers, start a new calculation session
                }

                // Perform binary addition using the rules
                String totalSum = binaryNumbers.get(0);                 
                /* Initializes totalSum as the "first" binary number, which is the first binary number entered.
                *  Eventually this becomes the current total sum in the iteration.
                */
                for (int i = 1; i < binaryNumbers.size(); i++) { 
                    // Starts at index 1 (the second number of the list) and runs until the last entered number
                    
                    totalSum = addBinary(totalSum, binaryNumbers.get(i)); // Calls the addBinary method
                }

                /* basically the program calculates it two at a time. Say the user inputs 3 binary numbers, the program will calculate the first two inputted
                 * binaries first, THEN the third number, displaying the final total sum once its finished.
                */

                // Print the results
                System.out.println("\n--- Calculation ---"); 
                for (int i = 0; i < binaryNumbers.size(); i++) { // Loops over every number inputted on the binaryNumbers List
                    System.out.println((i == 0 ? "  " : "+ ") + binaryNumbers.get(i)); 
                    // Displays the current number, then a "+" if it is not the first number
                }

                System.out.println("--------------------");  // Lines to separate the input and output
                System.out.println("= " + totalSum + " (Binary)"); // Finally displays the FINAL totalSum

                // For verification, also shows the decimal version of the sum
                try {
                    long decimalSum = 0; // Initializes the decimal sum variable.
                    for (String binaryNum : binaryNumbers) { // Loops over each number again, this time converting it to decimal
                        decimalSum += Long.parseLong(binaryNum, 2); // Using the method pareseLong to convert binary to decimal, additing to decimalSum
                    }
                    // The above calculations are hidden from the user and done in the background.

                    System.out.println("= " + decimalSum + " (Decimal)"); // Finally it displays the decimal sum
  
                } catch (NumberFormatException e) { // if the number or it's format is different....
                    System.out.println("(Decimal value is too large to display as a standard integer)"); // Error message
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    } // End of main fxn

    // Seperate Method to add binaries.
    private static String addBinary(String binary1, String binary2) {
        StringBuilder result = new StringBuilder(); 
        /* Makes the string modifiable without repeatedly creating a new string variable, thus saving space in memory. 
        *  Since we are building it bit by bit (one character at a time), this is quite useful.
        */

        int carry = 0; // Handles the carry bit
        int i = binary1.length() - 1; // Indexes from right to left, leveraging the length of the first binary input
        int j = binary2.length() - 1; // Indexes from right to left, leveraging the length of the second binary input


        // Loop from right to left, adding bits and handling carry
        while (i >= 0 || j >= 0 || carry > 0) {
            /*i is the index for binary1 (the first number), j is the index for binary2 (the second number)
             * carry is the carry bit 
            */

            int sum = carry; // Starts with assigning sum to carry (starting with the carry bit)

            if (i >= 0) { // If there are more bits in binary1
                sum += binary1.charAt(i--) - '0'; // Convert char '1' or '0' to int 1 or 0 at index i, adding it to sum
        }
            if (j >= 0) { // if there are more bits in binary2
                sum += binary2.charAt(j--) - '0'; // Convert char '1' or '0' to int 1 or 0 at index i, adding it to sum
            }
            result.insert(0, sum % 2); // Insert the resulting bit (0 or 1) at the beginning

            carry = sum / 2; // Calculates the new carry (0 or 1) and assigns it for the new iteration
        }

        return result.toString(); // Assigns the result to a string and returns said string
    }
}

// end result: 49/50