import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Utility that gets the prime factors from a prompt with multiple numbers. */
public class PrimeFactorsNotes {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter one or more whole numbers separated by spaces:");
            String inputLine = scanner.nextLine(); // Yoinks the user's input

            // Split the input string by one or more spaces to handle multiple numbers
            String[] numberStrings = inputLine.trim().split("\\s+");
            /* ^ The regex pattern "\\s+" matches one or more whitespace characters 
             * 
             * Splits the user's input and places all the numbers into a new array, removing whitespace
            */


            // If the numberStrings array is empty OR if it has only one element AND it (the first element) is empty, print an error message
            if (numberStrings.length == 0 || (numberStrings.length == 1 && numberStrings[0].isEmpty())) {
                System.out.println("No numbers were entered.");
                return;
            }

            for (String numStr : numberStrings) { // Iterates through each number in the numberStrings array
                try {
                    long number = Long.parseLong(numStr); // Transforms the current number into a Long datatype
                    
                    if (number < 2) { // If the current number is less than 2, print a message
                        System.out.println("Prime factors are not defined for numbers less than 2. Skipping: " + number);
                        continue;
                    }
                    List<Long> factors = findPrimeFactors(number); // Activates the findPrimeFactors method and assigns it to the factors variable
                    
                    System.out.println("Prime factors of " + number + ": " + factors);
                } 
                
                catch (NumberFormatException e) { // Catches non-integer inputs
                    System.out.println("Invalid input '" + numStr + "'. Please enter whole numbers only.");
                }
            }
        }
    }

    /**
     * Calculates the prime factors of a given positive integer using trial division.
     * @param n The number to factorize. Must be greater than 1.
     * @return A list of prime factors of n.
     */
    public static List<Long> findPrimeFactors(long n) {
        List<Long> factors = new ArrayList<>();

        // Handle the factor 2 separately to optimize for even numbers.
        while (n % 2 == 0) {
            factors.add(2L);
            n /= 2;
        }

        // At this point, n must be odd. We can skip even numbers in our loop.
        for (long i = 3; i <= Math.sqrt(n); i += 2) {
            // While i divides n, add i to the list and divide n.
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }

        // This handles the case where the remaining n is a prime number > 2.
        if (n > 2) {
            factors.add(n);
        }

        return factors;
    }
}
