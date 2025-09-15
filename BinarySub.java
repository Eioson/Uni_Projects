import java.util.Scanner;

public class BinarySub {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            boolean play = true;
            while (play) {
                System.out.println("\n--- New Binary Subtraction ---"
                + "\n Enter two binary numbers to subtract."
                + "\n Type 'Calculate' or press Enter on an empty line to proceed."
                + "\n Or, type 'Exit' to quit the program.");

                // Get the first number (minuend)
                System.out.print("Enter the first binary number: ");
                String binary1 = sc.nextLine().trim();

                /*
                 *   .nextLine() is a String method that reads the next line of input from the user. 
                 *   .trim() is another String method that removes any leading or trailing whitespace (basically spaces) from the input.
                 */

                if (binary1.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting program... for now :]");
                    return;
                }
                /*  String.equalIgnoreCase is a method that checks if the input is "exit". Ignoring case sensitivity
                *     which means I can enter "EXIT", "exit", or "ExIt", etc. and it would still be the same "exit".
                *       if not, returns a false.
                */


                if (!binary1.matches("[01]+")) {
                    System.out.println("Invalid input. Please enter a valid binary number (0s and 1s only).");
                    continue;
                }
                /* Regex in detail:
                *   [] - Characters inside the brackets to be compared with String.matches.
                *   [01] - Characters inside the brackets that can only be 0 or 1. tldr: only allow 0 or 1
                *   + - One or more repetitions of the character inside the brackets, basically if it repeats once or more
                *
                *   If all of these conditions are met, then the input is a valid binary number, thus returning a True boolean value 
                * that can be modified with NOT.
                 */


                // Geting the second number (subtrahend)
                System.out.print("Enter the second binary number: ");
                String binary2 = sc.nextLine().trim();

                /* Again,
                 *   .nextLine() is a String method that reads the next line of input from the user. 
                 *   .trim() is another String method that removes any leading or trailing whitespace (basically spaces) from the input.
                 */

                if (binary2.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting program... for now :]");
                    return;
                }

                if (!binary2.matches("[01]+")) { // Checks if the input is a valid binary number using the regex
                    System.out.println("Invalid input. Please enter a valid binary number (0s and 1s only).");
                    continue;
                }

                /* Regex in detail:
                *   [] - Characters inside the brackets to be compared with String.matches.
                *   [01] - Characters inside the brackets that can only be 0 or 1. tldr: only allow 0 or 1
                *   + - One or more repetitions of the character inside the brackets, basically if it repeats once or more
                *
                *   If all of these conditions are met, then the input is a valid binary number, thus returning a True boolean value 
                * that can be modified with NOT.
                 */

                // Perform binary subtraction
                String difference = subtractBinary(binary1, binary2);

                // Print the results
                System.out.println("\n--- Calculation ---");
                System.out.println("  " + binary1);
                System.out.println("- " + binary2);
                System.out.println("--------------------");
                System.out.println("= " + difference + " (Binary)");

                // For verification, also show the decimal conversion
                try {
                    long decimal1 = Long.parseLong(binary1, 2);
                    long decimal2 = Long.parseLong(binary2, 2);
                    System.out.println("= " + (decimal1 - decimal2) + " (Decimal)");
                } catch (NumberFormatException e) {
                    System.out.println("(Decimal value is too large to display as a standard integer)");
                }

                System.out.println("\nType 'Calculate' or press Enter on an empty line to proceed.");
                System.out.println("Or, type 'Exit' to quit the program.");

                // Wait for the user to press Enter
                sc.nextLine();
                String exitChoice = sc.nextLine().trim();
                if (exitChoice.equalsIgnoreCase("exit")) {
                    System.out.println("Exiting program... for now :]");
                    play = false;
                }
                // If the user types anything else, the loop continues.
                // No need for an else block here, as the loop naturally continues if 'play' is still true.

            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Subtracts one binary number from another (b1 - b2).
     *
     * @param b1 The minuend (the number to be subtracted from).
     * @param b2 The subtrahend (the number to subtract).
     * @return The difference as a binary string. Can be negative.
     */
    public static String subtractBinary(String b1, String b2) {
        // To perform A - B, we calculate A + (Two's Complement of B).
        // The number of bits should be one more than the max length to handle the sign.
        int len = Math.max(b1.length(), b2.length()) + 1;

        // Pad the first number to the target length
        String paddedB1 = String.format("%" + len + "s", b1).replace(' ', '0');

        // Calculate the two's complement of the second number
        String twosComplementB2 = twosComplement(b2, len);

        // Add the first number and the two's complement of the second
        String sum = addBinary(paddedB1, twosComplementB2);

        // If the result is longer than our target length, it's a positive result.
        // We discard the leading carry bit.
        if (sum.length() > len) {
            String positiveResult = sum.substring(1);
            // Remove leading zeros for a clean output
            return positiveResult.replaceFirst("^0+(?!$)", "");
        } else {
            // If the result is not longer, it's a negative number in two's complement form.
            // We can convert it back to a negative decimal and then to a standard binary string with a '-' sign.
            long decimalValue = 0;
            // The first bit is the sign bit with a negative weight
            decimalValue -= (sum.charAt(0) - '0') * Math.pow(2, len - 1);
            for (int i = 1; i < len; i++) {
                if (sum.charAt(i) == '1') {
                    decimalValue += Math.pow(2, len - 1 - i);
                }
            }
            if (decimalValue == 0) return "0";
            // Convert the negative decimal value to a binary string and prepend the sign
            return "-" + Long.toBinaryString(Math.abs(decimalValue));
        }
    }

    /**
     * Calculates the two's complement of a binary string for a given bit length.
     */
    private static String twosComplement(String binary, int bits) {
        String paddedBinary = String.format("%" + bits + "s", binary).replace(' ', '0');

        // 1. Invert the bits (One's Complement)
        StringBuilder onesComplement = new StringBuilder();
        for (char c : paddedBinary.toCharArray()) {
            onesComplement.append(c == '0' ? '1' : '0');
        }

        // 2. Add 1
        return addBinary(onesComplement.toString(), "1");
    }

    /**
     * Adds two binary strings.
     */
    private static String addBinary(String b1, String b2) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = b1.length() - 1;
        int j = b2.length() - 1;

        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) sum += b1.charAt(i--) - '0';
            if (j >= 0) sum += b2.charAt(j--) - '0';

            result.insert(0, sum % 2);
            carry = sum / 2;
        }

        return result.toString();
    }
}