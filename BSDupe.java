import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BSDupe {
        public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n--- New Binary Subtraction ---");
                System.out.println("Enter two binary numbers to subtract, one per line.");
                System.out.println("Type 'Calculate' or press Enter on an empty line when you are done.");
                System.out.println("Or, type 'Exit' to quit the program.");

                List<String> binaryNumbers = new ArrayList<>();

                while (binaryNumbers.size() < 2) {
                    System.out.print("> ");
                    String input = sc.nextLine().trim();
                    /*
                     *   .nextLine() is a String method that reads the next line of input from the user. 
                     *   .trim() is another String method that removes any leading or trailing whitespace (basically spaces) from the input.
                     */

                    if (input.equalsIgnoreCase("exit")) {
                        /*  String.equalIgnoreCase is a method that checks if the input is "exit". Ignoring case sensitivity
                        *     which means I can enter "EXIT", "exit", or "ExIt", etc. and it would still be the same "exit".
                        *       if not, returns a false.
                        */
                        System.out.println("Exiting program... for now :]");
                        return;
                    }

                    if (input.isEmpty() || input.equalsIgnoreCase("calculate")) {
                        break; // Exit input loop to perform calculation
                    }

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
                        continue;
                    }
                    binaryNumbers.add(input);
                }

                if (binaryNumbers.size() < 2) {
                    System.out.println("Tsk. Please enter exactly two numbers to perform a subtraction. Again.");
                    continue;
                }

                String binary1 = binaryNumbers.get(0);
                String binary2 = binaryNumbers.get(1);

                // Perform binary subtraction
                String difference = subtractBinary(binary1, binary2);

            }
        }
    }

    private static String subtractBinary(String binary1, String binary2) {
        return "0"; // Placeholder for now
    }
}
