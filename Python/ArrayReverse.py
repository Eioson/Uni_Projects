def reverse_array():
    """
    Prompts the user to enter , then reverses and prints the array.
    """
    inputs = []
    
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
            inputs.append(ui)
        except ValueError:
            # Handle cases where the input is not a valid number
            print("Invalid input. Input a word, 'Done', or an empty line.")

    # Reverse and display the array
    if inputs.__len__() > 0:
        inputs.reverse()
        # The reverse() method reverses the order of the elements in the list.
        
        print(f"\nThe reversed array is: {inputs}")
    else:
        print("\nNothing was entered.")

if __name__ == "__main__":
    reverse_array()