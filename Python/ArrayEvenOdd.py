def even_odd_array():
    """
    Prompts the user to enter numbers, then separates and prints even and odd numbers.
    """
    inputs = []
    even_numbers = []
    odd_numbers = []
    
    print("Enter numbers one at a time.")
    print("Enter 'Done' or press Enter on an empty line when you are finished.")

    while True:
        ui = input("> ")
        
        # Check for termination condition
        if ui.lower() == 'done' or ui == "": 
            break
        
        try:
            num = int(ui)
            if num % 2 == 0:
                even_numbers.append(num)
            else:
                odd_numbers.append(num)
        except ValueError:
            # Handle cases where the input is not a valid number
            print("Invalid input. Please enter a number, 'Done', or an empty line.")

    if len(even_numbers) > 0 or len(odd_numbers) > 0:
        print(f"\nEven numbers: {even_numbers}")
        print(f"Odd numbers: {odd_numbers}")
    elif len(even_numbers) > 0 and len(odd_numbers) == 0:
        print(f"\nEven numbers: {even_numbers}")
    elif len(odd_numbers) > 0 and len(even_numbers) == 0:
        print(f"\nOdd numbers: {odd_numbers}")
    else:
        print("\nNothing was entered.")

if __name__ == "__main__":
    even_odd_array()