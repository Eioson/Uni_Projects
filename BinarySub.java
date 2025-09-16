import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BinarySub {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("\n--- New Binary Subtraction ---"
                + "Enter the binary numbers to subtract, one per line."
                + "Type 'Calc' or press Enter on an empty line when you are done."
                + "Or, type 'Exit' to quit the program.");  

                List<String> binaryNumbers = new ArrayList<>();

                while (true) {
                    System.out.print("> ");
                    String input = sc.nextLine().trim();

                    if (input.equalsIgnoreCase("exit")) {
                        /*  String.equalIgnoreCase is a method that checks if the input is "exit". Ignoring case sensitivity
                        *     which means I can enter "EXIT", "exit", or "ExIt", etc. and it would still be the same "exit".
                        *       if not, returns a false.
                        */

                        System.out.println("Exiting program... for now :]");
                        return; // Exit the program completely
                    }

                    if (input.isEmpty() || input.equalsIgnoreCase("calc")) {
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

                // Perform binary subtraction using the rules
                String difference = binaryNumbers.get(0);
                for (int i = 1; i < binaryNumbers.size(); i++) {
                    difference = subtractBinary(difference, binaryNumbers.get(i));
                }

                // Print the results
                System.out.println("\n--- Calculation ---");
                for (int i = 0; i < binaryNumbers.size(); i++) {
                    System.out.println((i == 0 ? "  " : "- ") + binaryNumbers.get(i));
                }
                System.out.println("--------------------");
                System.out.println("= " + difference + " (Binary)");

                // For verification, also show the decimal conversion
                try {
                    long decimalDifference = Long.parseLong(binaryNumbers.get(0), 2);
                    for (int i = 1; i < binaryNumbers.size(); i++) {
                        decimalDifference -= Long.parseLong(binaryNumbers.get(i), 2);
                    }
                    System.out.println("= " + decimalDifference + " (Decimal)");
                } catch (NumberFormatException e) {
                    System.out.println("(Decimal value is too large to display as a standard integer)");
                }
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
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
        // To perform b1 - b2, we calculate b1 + (Two's Complement of b2).
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