import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* Utility that gets the prime factors from a prompt with multiple numbers. */
public class PrimeFactors {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter one or more whole numbers separated by spaces:");
            String inputLine = scanner.nextLine();

            String[] numberStrings = inputLine.trim().split("\\s+");

            if (numberStrings.length == 0 || (numberStrings.length == 1 && numberStrings[0].isEmpty())) {
                System.out.println("No numbers were entered.");
                return;
            }

            for (String numStr : numberStrings) {
                try {
                    long number = Long.parseLong(numStr);
                    
                    if (number < 2) {
                        System.out.println("Prime factors are not defined for numbers less than 2. Skipping: " + number);
                        continue;
                    }
                    List<Long> factors = findPrimeFactors(number);
                    
                    System.out.println("Prime factors of " + number + ": " + factors);
                } 
                
                catch (NumberFormatException e) {
                    System.out.println("Invalid input '" + numStr + "'. Please enter whole numbers only.");
                }
            }
        }
    }

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
