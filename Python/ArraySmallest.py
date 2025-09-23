def find_smallest_number():
    """
    Prompts the user to enter numbers, then finds and prints the smallest one.
    """
    numbers = []
    
    print("Enter numbers one at a time.")
    print("Enter 'Done' or press Enter on an empty line when you are finished.")

    while True:
        ui = input("> ")
        
        # Check for termination condition
        if ui.lower() == 'done' or ui == "": 
        # checks if the user input (ui) is 'done' (case insensitive) or an empty string.
            break
        
        # Convert input to an integer and add to the list
        try:
            num = int(ui)
            numbers.append(num)
        except ValueError:
            # Handle cases where the input is not a valid number
            print("Invalid input. Please enter a number, 'Done', or an empty line.")

    # Find and display the smallest number
    if numbers:
        smallest = min(numbers) 
        # min() function returns the smallest item in an iterable or the smallest of two or more arguments.
        
        print(f"\nThe smallest number is: {smallest}")
    else:
        print("\nNo numbers were entered.")

# Run the main function when the script is executed
if __name__ == "__main__":  # This condition checks if the script is being run directly by the Python interpreter.
    find_smallest_number() # If the script is being run directly, it calls the main function to start the program.