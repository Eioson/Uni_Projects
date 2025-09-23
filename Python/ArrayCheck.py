import random

def check_array():
    """
    Prompts the user to enter inputs, then checks if it is in the premade list.
    """
    number_list = [1,2,3,4,5,6,7,8,9,0]
   ## inputs = [(random.randint(1, 51)) for _ in range(10)]
    
    print("A list of 10 random numbers (0-10) has been generated.")
    print("Guess a number from that list.")

    while True:
        guess = input("> ")
        try:
            guess = int(guess)
        except ValueError:
            print("Invalid input. Please enter a number.")
            continue
        

        
        if int(guess) in number_list:
            print(f"Correct! The number was {guess} at index {number_list.index(int(guess))}.")
            print(f"The list was: \n\t{number_list}")
            break
        
        else:
            try:
                int(guess)
            except ValueError:
                print("Invalid input. Please enter a number.")
            else:
                print(f"The number {guess} was not fouund in the list. Try again.")

    return number_list


if __name__ == "__main__":
    check_array()