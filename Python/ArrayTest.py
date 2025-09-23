n = 11 # seperated by newline instead of emicolons.
m = 11

arr = [[0 for _ in range(m)] for _ in range(n)] 
### Initialize the first row and first column
# [0 for _ in range(m)] generates a list of m zeros: [0, 0, 0, ..., 0]
# while the outer list comprehension creates n such lists to form a 2D array
# using the same principles as the inner list but using the entire list instead of '0'.

for i in range(1, n):
    for j in range(1, m):
        arr[i][j] = i * j

for i in range(1, n):
    for j in range(1, m):
        print(f"{arr[i][j]:3d}", end="")
        # F-String is used for formatted string literals.
        # f"{value:format_spec}"
        # Evalulates everything in {} as a Python expression, in this case the value in
        # arr[i][j].
        #
        # value - The expression to be formatted.
        # format_spec - A string specifying how the value should be formatted.
        #
        # The :3d in the formatted string ensures that 
        # each number takes up at least 3 spaces, aligning the output neatly. 
        # : - seperator, 3 - width, d - decimal
        
        # end="" - prevents moving to a new line after each print. 

    print()
