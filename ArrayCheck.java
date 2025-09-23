import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ArrayCheck {
    public static void main(String[] args) {
        
        // -- Initializing --
        List<Integer> randomNumbers = new ArrayList<>(); // Creates the list
        Random rand = new Random(); // Initiates the random number generator
        try(Scanner sc = new Scanner(System.in)){ // Initiates the scanner

            for (int i = 0; i < 10; i++) { // Repeats 10 times
                randomNumbers.add(rand.nextInt(1, 101)); // Appends the randomly set number to randomNumbers
            }

            while (true) { 
                System.out.print("""
                    \nA list of 10 random numbers (0-100) has been generated.
                    Take a guess at a number from that list.
                    """);
                System.out.print("\t> ");
                String input = sc.nextLine();

                if (input.matches("\\d+")){ 
                    /* Checks if the input is a number using the String.matches() method
                    *  "\\d+" is a regular expression [regex] that matches any digit (0-9)
                    *  \\ - is an escape character that is needed, else it wont work
                    *  d - denotes a digit (0-9)
                    *  + - One or more repetitions of the character inside the brackets, basically if it repeats once or more
                    */

                    int sN = Integer.parseInt(input); //Parses the input into 

                    if (randomNumbers.contains(sN)) { // Checks if randomNumbers contains sN
                        System.out.print("The number " + sN + " is in the list.");
                    } else {
                        System.out.print("The number " + sN + " is not in the list.");
                    }
                    break;

                } else { // Exception case if te user inputs something other than a number
                    System.out.print("Invalid input. Please enter a valid number.\n");
                }
                
            }
        }
    }
}