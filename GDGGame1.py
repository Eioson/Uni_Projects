'''Program that let you play rock paper scissors through a GUI
GUI based, with where the user places their answer in a text box
after the user places a choice in the text box, the program will
print three dots then print the computer's choice. If the result
is a draw, it will print "Draw", if the player wins, it will print
"Player wins", and if the computer wins, it will print "Computer wins"'''

'''0.5 second delay between dots to demonstrate the computer is thinking'''

'''Íf the user writes paper, the program will denote that as the number 2, so
rock is equal to 1, scissors is equal to three, and paper is equal to two'''

'''Note: relearn Tkinter, it is used for it's GUI library'''

import random
import time
import tkinter as tk
from tkinter import messagebox


def play_game():
    """Handles the main game logic when the 'Play' button is clicked."""
    player_input = entry.get().lower().strip()

    choice_map = {"rock": 1, "paper": 2, "scissors": 3, "rock": 4, "paper": 5, "scissors": 6}

    if player_input not in choice_map:
        messagebox.showerror("Invalid Input", "Please enter 'rock', 'paper', or 'scissors'.")
        return

    player_choice_num = choice_map[player_input]
    computer_choice_num = random.randint(1, 6)

    # Disable button during animation
    play_button.config(state="disabled")
    entry.config(state="disabled")

    # Start the "thinking" animation
    animate_thinking(1, player_choice_num, computer_choice_num)


def animate_thinking(step, player_choice_num, computer_choice_num):
    """Creates a delay to simulate the computer thinking."""
    if step <= 3:
        result_label.config(text="." * step)
        root.after(500, lambda: animate_thinking(step + 1, player_choice_num, computer_choice_num))
    else:
        # Start the sequential result display
        display_result_sequentially(player_choice_num, computer_choice_num)


def display_result_sequentially(player_num, computer_num, step=1):
    """Determines the winner and shows the result on the screen one line at a time."""
    choice_map = {"rock": 1, "paper": 2, "scissors": 3, "rock": 4, "paper": 5, "scissors": 6}

    # Determine the winner
    if player_num == computer_num:
        result = "Draw"
    elif (player_num == 1 and computer_num == 3) or \
         (player_num == 2 and computer_num == 1) or \
         (player_num == 3 and computer_num == 2) or \
         (player_num == 4 and computer_num == 6) or \
         (player_num == 5 and computer_num == 4) or \
         (player_num == 6 and computer_num == 5): # Done solely to lower odds of a draw
        result = "Player wins"
    else:
        result = "Computer wins"

    player_choice_str = [k for k, v in choice_map.items() if v == player_num][0]
    computer_choice_str = [k for k, v in choice_map.items() if v == computer_num][0]

    line1 = f"You chose: {player_choice_str.capitalize()}"
    line2 = f"Computer chose: {computer_choice_str.capitalize()}"

    if step == 1:
        # Show player's choice
        result_label.config(text=line1)
        root.after(1000, lambda: display_result_sequentially(player_num, computer_num, step=2))
    elif step == 2:
        # Add computer's choice
        result_label.config(text=f"{line1}\n{line2}")
        root.after(1000, lambda: display_result_sequentially(player_num, computer_num, step=3))
    elif step == 3:
        # Add the final result and re-enable widgets
        result_label.config(text=f"{line1}\n{line2}\n{result}")
        play_button.config(state="normal")
        entry.config(state="normal")
        entry.delete(0, tk.END)


# --- GUI Setup ---
root = tk.Tk()
root.title("AWS Learning Club -- NU Cebu Chapter")
root.geometry("800x600") # Start with a standard size instead of fullscreen
root.resizable(True, True)
root.configure(bg="#0d6666")

# Create a central frame to hold all the widgets
main_frame = tk.Frame(root, bg="#0d6666")
main_frame.place(relx=0.5, rely=0.5, anchor="center")

instruction_label = tk.Label(main_frame, text="Enter rock, paper, or scissors:", fg="white", bg="#0d6666", font=("Arial", 14))
instruction_label.pack(pady=(20, 5))

entry = tk.Entry(main_frame, font=("Arial", 14), width=20, justify='center')
entry.pack(pady=5)

play_button = tk.Button(main_frame, text="Play", font=("Arial", 14), command=play_game, width=10)
play_button.pack(pady=10)

result_label = tk.Label(main_frame, text="", fg="white", bg="#0d6666", font=("Arial", 16), justify="center", height=4)
result_label.pack(pady=20)

root.mainloop()
