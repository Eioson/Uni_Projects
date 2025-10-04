import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ArraysFinal {
    // This list will hold the numbers for our operations.
    private final List<Integer> numbers = new ArrayList<>();
    // The scanner is passed to methods that need it.

    public static void main(String[] sigma) {
        // Create an instance of our class to work with.
        ArraysFinal manager = new ArraysFinal();
        // Use a try-with-resources block for the Scanner.
        try (Scanner sc = new Scanner(System.in)) {
            manager.runMenu(sc);
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Main loop to display the menu and handle user choices.
     */
    public void runMenu(Scanner sc) {
        while (true) {
            System.out.println("\n--- List Operations Menu ---");
            System.out.println("1. Add a number");
            System.out.println("2. Remove a number");
            System.out.println("3. View the list");
            System.out.println("4. Search for a number");
            System.out.println("5. Sort the list (Ascending)");
            System.out.println("6. Count elements in the list");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1 -> add(sc);
                    case 2 -> remove(sc);
                    case 3 -> view(sc);
                    case 4 -> search(sc);
                    case 5 -> sort();
                    case 6 -> count();
                    case 7 -> {
                        System.out.println("\nExiting program. Goodbye!\n");
                        return;
                    }
                    default -> System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                }
            } 
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); // Clear the invalid input from the scanner
            }
        }
    }

    /**
     * Adds a number to the list.
     */
    public void add(Scanner sc) {
        int count;
        while (true) {
            System.out.print("How many numbers do you want to add?"
            + "\n\t> ");
            String line = sc.nextLine();
            try {
                count = Integer.parseInt(line);
                if (count > 0) {
                    break; // Valid number, exit loop
                }
                System.out.println("Please enter a number greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }

        for (int i = 0; i < count; i++) {
            while (true) {
                System.out.printf("Enter number %d of %d: ", i + 1, count);
                String input = sc.nextLine().trim();
                try {
                    numbers.add(Integer.valueOf(input)); 
                    // valueOf is a Integer methods that returns an Integer value of the same value as the string
                    System.out.println("Number added successfully.\n");
                    break; // Exit inner while loop and go to next number
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid whole number.");
                }
            }
        }
        try { // Try-catch to handle potential InterruptedException
            System.out.println("Your list is now: " + numbers);
            Thread.sleep(1750); // Stops for 1.75 seconds so the user can see the updated list
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
        } // Pause for a second to let user see the updated list
    }
                     
    /**
     * Removes a number from the list by its value.
     */
    public void remove(Scanner sc) {
        if (numbers.isEmpty()) {
            System.out.println("The list is empty. Nothing to remove.");
            return;
        }
        System.out.print("Enter the number you want to remove"
        + "\n\t> ");
        try {
            Integer numToRemove = sc.nextInt(); // Read as Integer object
            sc.nextLine(); // Consume newline

            // The .remove(Object) method removes the first occurrence of the value.
            // It returns true if the element was found and removed, false otherwise.
            if (numbers.remove(numToRemove)) {
                System.out.println("\n"+ numToRemove + " has been removed from the list.");
                try { // Try-catch to handle potential InterruptedException
                    System.out.println("\tYour list is now: " + numbers);
                    Thread.sleep(1250); // Stops for 1.75 seconds so the user can see the updated list
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); 
                } // Pause for a second to let user see the updated list
            } else {
                System.out.println(numToRemove + " was not found in the list.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a whole number.");
            sc.nextLine(); // Clear invalid input
        }
    }

    /*
     * Displays the current list of numbers.
     */
    public void view(Scanner sc) {
        if (numbers.isEmpty()) {
            System.out.println("The list is currently empty.");
        } else {
            System.out.println("Current list: " + numbers);
        }

        try { // Try-catch to handle potential InterruptedException
            System.out.println("Your list is now: " + numbers);
            Thread.sleep(750); // Stops for 750 miliseconds so the user can analyze the list
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
        } // Pause for a second to let user see the updated list

        System.out.print("Press Enter to continue");
        sc.nextLine(); // Wait for user to press Enter
    }

    /**
     * Searches for a number in the list.
     */
    public void search(Scanner sc) {
        // Implementation for search would go here
        System.out.println("Search functionality not implemented yet.");
    }

    /**
     * Sorts the list in ascending order.
     */
    public void sort() {
        if (numbers.isEmpty()) {
            System.out.println("The list is empty. Nothing to sort.");
            return;
        }
        Collections.sort(numbers);
        System.out.println("\nThe list has been sorted in ascending order." 
        + "/t"+ numbers);  

        try { // Try-catch to handle potential InterruptedException
            System.out.println("Your list is now: " + numbers);
            Thread.sleep(1750); // Stops for 1.75 seconds so the user can see the updated list
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
        } // Pause for a second to let user see the updated list
    }

    /**
     * Counts and displays the number of elements in the list.
     */
    public void count() {
        if (numbers.isEmpty()) {
            System.out.println("The list is empty. Nothing to count.");
        } else {
            System.out.println("The list is: " + numbers
            + "\nThe list contains " + numbers.size() + " element(s).");
        } 
    }
}
