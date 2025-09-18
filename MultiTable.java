/* Create a program that prints a multiplication table with the correct alignment
from 1 to 10. */
public class MultiTable {
  
    public static void main(String[] args) {
        // The size of the table. You can easily change this value to generate a different sized table.
        final int TABLE_SIZE = 10;

        System.out.println("--- Multiplication Table (1 to " + TABLE_SIZE + ") ---");
        printMultiplicationTable(TABLE_SIZE);
    }

    /**
     * Prints a multiplication table of a given size with proper alignment.
     * This method is designed to be flexible and can be easily modified.
     *
     * @param size The number of rows and columns for the table (e.g., 10 for a 10x10 table).
     */
    public static void printMultiplicationTable(int size) {
        if (size < 1) {
            System.out.println("Table size must be at least 1.");
            return;
        }

        // --- 1. Print Header Row ---
        // Print an empty corner, then the column headers (1, 2, 3, ...).
        // "%-5s" formats a string to be 5 characters wide and left-aligned.
        System.out.printf("%-5s", "x");
        for (int i = 1; i <= size; i++) {
            // "%5d" formats an integer to be 5 characters wide and right-aligned.
            System.out.printf("%5d", i);
        }
        System.out.println(); // New line after header

        // --- 2. Print Separator Line ---
        System.out.print("-----");
        for (int i = 1; i <= size; i++) {
            System.out.print("-----");
        }
        System.out.println();

        // --- 3. Print Table Body ---
        for (int i = 1; i <= size; i++) { // Outer loop for rows
            // Print the row header (1, 2, 3, ...)
            System.out.printf("%-5d", i);

            for (int j = 1; j <= size; j++) { // Inner loop for columns
                // Calculate and print the product with correct alignment
                System.out.printf("%5d", i * j);
            }
            System.out.println(); // Move to the next line after each row is complete
        }
    }
}
