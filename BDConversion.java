import java.util.Scanner;

public class BDConversion {

    public static void main(String[] sigma) { // Main method, giving the user choices for the conversion.
        try (Scanner sc = new Scanner(System.in)) {
             while (true) { // Loop to allow multiple conversions
                System.out.print("\nChoose conversion type:"
                + "\n\t1: Binary to Decimal"
                + "\n\t2: Decimal to Binary"
                + "\n\t3: Exit"
                + "\nEnter your choice (1, 2, or 3): ");

                String choice = sc.nextLine();

                
                switch (choice) {   // Switch cases for the two conversion types, activating the respective methods.
                    case "1":
                        convertBinaryToDecimal(sc);
                        break;
                    case "2":
                        convertDecimalToBinary(sc);
                        break;
                    case "3":
                        System.out.println("Exiting program. Goodbye!");
                        return; // Exit the main method, terminating the program
                    default: // In case the user doesnt enter 1, 2, or 3
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    private static void convertBinaryToDecimal(Scanner scanner) { /* Private method to convert binary to decimal
        (Scanner scanner) is used to initialize the Scanner object as it is called via a method parameter.*/

        System.out.print("\nEnter a binary number: ");
        String binaryInput = scanner.nextLine();


        if (binaryInput.matches("[01]+")) {
            try {
                int decimalValue = Integer.parseInt(binaryInput, 2); /*  Convert binary string to decimal integer, radix denotes 
                the input's base */
                System.out.println("Decimal value: " + decimalValue);
            } catch (NumberFormatException e) {
                // Handle the case where the binary number is too large and the conversion tweaks out
                System.out.println("Error converting binary number. It might be too large.");
            }
        } else {
            System.out.println("Invalid input. A binary number can only contain 0s and 1s.");
        }
    }

    private static void convertDecimalToBinary(Scanner scanner) { /* Private method to convert decimal to binary
        (Scanner scanner) is used to initialize the Scanner object as it is called via a method parameter.*/

        System.out.print("\nEnter a whole decimal number: ");
        String decimalInput = scanner.nextLine();

        try {
            int decimalValue = Integer.parseInt(decimalInput); /* Converts the input into a proper decimal integer, 
            radix denotes the input's base */
            if (decimalValue < 0) { // Check for negative input
                System.out.println("This converter does not support negative numbers.");
                return;
            }
            String binaryValue = Integer.toBinaryString(decimalValue); // Convert decimal integer to binary string using a built-in method
            System.out.println("\nBinary value: " + binaryValue);
        } catch (NumberFormatException e) { // Catches non-integer inputs and overflows
            System.out.println("Invalid input. Please enter a valid whole decimal number.");
        }
    }
}
