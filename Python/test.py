import random


list = [random.randint(1,51) for _  in range(10)]

list.sort()
print(list)
list.reverse()
print(list)

'''
for i in enumerate(list):
    print(i)
    print(y)

a = 2 
b = 3

print(a^b) # doesnt seem to be exponent...
print(a&b) # whatdapak is the "&" operator
'''
'''
a = "dad is cool"

print(f"Original string: {a}")

c_words = a.split() # Splits each word into a list
print(f"Splitting by words (using .split()): \n{c_words}")

c_letters = list(a) # Splits the individual characters into a list
print(f"Splitting by letters (using list()): \n{c_letters}")# --- Dictionary Challenge: Word Frequency Counter ---
'''
# ----------------------------------------------------------------------------------------------------------------------------
''''
# The goal is to count how many times each word appears in this sentence.
# A dictionary is perfect for this! The word will be the 'key' and the count will be the 'value'.

sentence = "the quick brown fox jumps over the lazy dog and the fox was quick"

# 1. Create an empty dictionary to store the word counts.
word_counts = {}

# 2. Split the sentence into a list of words.
words = sentence.split()
print(f"List of words: {words}\n")

# 3. Loop through the 'words' list. For each word, update your dictionary.
for word in words:
    if word in word_counts:
        # If the word is already a key, increment its value (the count) by 1.
        word_counts[word] += 1
    else:
        # If the word is not yet a key, add it to the dictionary with a value of 1.
        word_counts[word] = 1


# 4. After the loop, print the final dictionary to see the results.
print("--- Word Counts ---")
print(word_counts)
'''

binaries = ('0', '1') # Using strings for more direct comparison

bingus = input() # Grabs input from user.

bingus_list = list(bingus) # splits the variable by characer into a list.

# Unpack the index and value from enumerate directly for clarity
for index, character in enumerate(bingus_list):
    if character == binaries[0]: # if the character is '0'

        bingus_list[index] = binaries[1] # replace it with '1'

    elif character == binaries[1]: # if the character is '1'

        bingus_list[index] = binaries[0] # replace it with '0'

    elif character != binaries[0] or character != binaries[1]: # if the character is neither '0' nor '1'
        
        bingus_list[index] = " " # replace it with a space

bingus = "".join(bingus_list) # join the list into a string

print(bingus)