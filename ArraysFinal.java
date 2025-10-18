import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ArraysFinal {
    // This list will hold the numbers for our operations.
    private final List<Integer> numbers = new ArrayList<>();
    // The maximum capacity for the list. It will be set by the user.
    private int maxCapacity = 0;
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
                     case 6 -> count(sc);
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


    
    /*
     * Helper method to help pause execution for a specified duration.
     */
    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Pause was interrupted.");
        }
    }

    /**
     * Adds a number to the list.
     */
    public void add(Scanner sc) {
        // If capacity is not set yet, prompt the user to set it.
        if (maxCapacity == 0) {
            while (true) { // Loop until a valid capacity is entered
                System.out.print("Set the maximum size for the list (positive whole number)"
                + "\n\t> ");
                try {
                    int capacityInput = Integer.parseInt(sc.nextLine());
                    if (capacityInput > 0) {
                        maxCapacity = capacityInput;
                        System.out.printf("Maximum capacity set to %d.\n", maxCapacity);
                        break;
                    }
                    System.out.println("Capacity must be a positive number.");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a whole number.");
                }
            }
        }

        if (numbers.size() >= maxCapacity) {
            System.out.printf("The list is full (max capacity: %d). Cannot add more numbers.\n", maxCapacity);
            return;
        }

        int remainingCapacity = maxCapacity - numbers.size();

        int count;
        while (true) {
            System.out.print("How many numbers do you want to add?"
            + "\n\t> ");

            String line = sc.nextLine();

            try {
                count = Integer.parseInt(line);
                if (count > 0) { // Check if the count is positive
                        if (count <= remainingCapacity) { // Check if the count is within capacity
                            break; // If it is a valid count value and within capacity, exit loop
                        }

                        System.out.printf("You can only add up to %d more numbers.\n", remainingCapacity);
                        try {
                            Thread.sleep(200); // delays for 0.2 seconds before re-prompting
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }

                } else{
                    System.out.printf("Please enter a number equal to or less than %d.\n\n", remainingCapacity);
                    try {
                            Thread.sleep(300);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                }
            } 
            
            catch (NumberFormatException e) {
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

        System.out.print("Enter the number you want to remove\n\t> ");
        try {
            Integer numToRemove = sc.nextInt(); // Read as Integer object
            sc.nextLine(); // Consume newline

            // Find all indices where the number appears.
            List<Integer> indices = new ArrayList<>();
            for (int i = 0; i < numbers.size(); i++) {
                if (numbers.get(i).equals(numToRemove)) {
                    indices.add(i);
                }
            }

            // Case 1: The number was not found.
            if (indices.isEmpty()) {
                System.out.println("\n" + numToRemove + " was not found in the list.");
            // Case 2: Exactly one occurrence was found.
            } else if (indices.size() == 1) {
                numbers.remove(numToRemove); // Simple removal
                System.out.println("\n" + numToRemove + " has been removed from the list.");
            // Case 3: Duplicates were found.
            } else {
                System.out.printf("\nFound multiple occurrences of %d at indices: %d\n", numToRemove,indices);
                System.out.println("What would you like to do?"
                + "  1. Remove a specific one by index."
                + "  2. Remove ALL occurrences of this number."
                + "  3. Cancel.");
                System.out.print("\t> ");

                String choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        System.out.print("Enter the index you want to remove: ");
                        try {
                            int indexToRemove = Integer.parseInt(sc.nextLine());
                            if (indices.contains(indexToRemove)) {
                                numbers.remove(indexToRemove);
                                System.out.println("\nRemoved " + numToRemove + " at index " + indexToRemove + ".");
                            } else {
                                System.out.println("\nInvalid index. That index does not contain the number " + numToRemove + ".");
                            }
                        } catch (NumberFormatException | IndexOutOfBoundsException e) {
                            System.out.println("\nInvalid index provided.");
                        }
                        break;
                    case "2":
                        // Use removeAll with a list containing just the number to remove.
                        numbers.removeAll(List.of(numToRemove));
                        System.out.println("\nAll occurrences of " + numToRemove + " have been removed.");
                        break;
                    case "3":
                        System.out.println("\nRemoval cancelled.");
                        return; // Exit without showing the list again
                    default:
                        System.out.println("\nInvalid choice. No action taken.");
                        return;
                }
            }

            // Show the updated list after a successful removal.
            System.out.println("\tYour list is now: " + numbers);
            pause(1250);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a whole number.");
            sc.nextLine(); // Clear invalid input
        }
    }

    /**
     * Displays the current list of numbers.
     */
    public void view(Scanner sc) {
        if (numbers.isEmpty()) {
            System.out.println("The list is currently empty.");
        } else {
            System.out.println("Current list: " + numbers);
        }

        pause(750); // Pause so the user can see the list
        System.out.print("Press Enter to continue.. ");
        sc.nextLine(); // Wait for user to press Enter
    }

    /**
     * Searches for a number in the list.
     */
    public void search(Scanner sc) {
        if (numbers.isEmpty()) {
            System.out.println("The list is empty. Nothing to search.");
            return;
        }
        System.out.print("Enter the number you want to search for"
        + "\n\t> ");

        try {
            int numToSearch = sc.nextInt();
            sc.nextLine(); // Consume newline

            if (numbers.contains(numToSearch)) {
                int index = numbers.indexOf(numToSearch); // Use indexOf to find the index of the value.
                System.out.printf("\n%d is found in the list at index %d.\n", numToSearch, index);
            } else {
                System.out.printf("\n%d was not found in the list.\n", numToSearch);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a whole number.");
            sc.nextLine(); // Clear invalid input
        }
    }

    /**
     * Sorts the list in ascending order.
     */
    public void sort() {
        if (numbers.isEmpty()) {
            System.out.println("The list is empty. Nothing to sort.");
            return;
        }

        // --- Bubble Sort Implementation ---
        int n = numbers.size(); // Size of the list
        boolean swapped;    // Flag to track if a swap occurred in a pass

        // Outer loop for each pass, i represents the number of passes
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            // Inner loop for comparing and swapping adjacent elements, j represents the current index
            for (int j = 0; j < n - 1 - i; j++) {
                // Compare adjacent elements
                if (numbers.get(j) > numbers.get(j + 1)) {
                    // Swap them if they are in the wrong order
                    int temp = numbers.get(j);
                    // Temporary variable to hold one of the values during the swap

                    numbers.set(j, numbers.get(j + 1));
                    // Swap the values
                    numbers.set(j + 1, temp);
                    // Replacesa the other value with the temp variable

                    swapped = true; // Indicates a swap occurred in this pass
                }
            }
            // OPTIMIZATION: If no elements were swapped in a pass, the list is already sorted.
            if (!swapped) {
                break;
            }
        }
        // --- End of Bubble Sort ---

        System.out.println("\nThe list has been sorted in ascending order using Bubble Sort.");
        System.out.println("Sorted list: " + numbers);

        pause(750);
    }

    /**
     * Counts and displays the number of elements in the list.
     */
    public void count(Scanner sc) {
        
        System.out.print("\nWould you like to: "
        + "\n  1. Count all elements in the list."
        + "\n  2. Count occurrences of a specific number.");
        System.out.print("\n\t> ");

        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                if (numbers.isEmpty()) {
                    System.out.println("The list is empty. Nothing to count.");
                } else {
                    System.out.printf("\nThe list is: " + numbers
                    + "\nThe list contains %d element(s).\n", numbers.size());
                }
                break;
            case "2":
                    System.out.print("Enter the number you want to count: ");
                    try{ 
                        Integer CountNum = sc.nextInt();
                        sc.nextLine();

                        // Find all indices where the number appears.
                        List<Integer> indices = new ArrayList<>();
                        for (int i = 0; i < numbers.size(); i++) {
                            if (numbers.get(i).equals(CountNum)) {
                                indices.add(i);
                            }
                        }

                        // Case 1: The number was not found.
                        if (indices.isEmpty()) {
                            System.out.println("\n" + CountNum + " was not found in the list.");
                        // Case 2: Exactly one occurrence was found.
                        } else if (indices.size() == 1) {
                            System.out.printf("\nFound one occurrence of %d at index: [%d]\n", CountNum, indices.get(0));
                        // Case 3: Duplicates were found.
                        } else {
                            System.out.printf("\nFound %d occurrences of %d at indices: %s\n", indices.size(), CountNum, indices);
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a whole number.");
                        sc.nextLine(); // Clear invalid input
                    }
                    break;
                    
            default:
                System.out.println("Invalid choice. No action taken.");
        }

        pause(1750);
    }
}
