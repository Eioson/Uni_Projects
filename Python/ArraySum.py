powersOfTwo = [2**x for x in range(11)]
print(powersOfTwo)


for x in powersOfTwo:
    current_sum = sum(powersOfTwo[:x+1])

print(f"The sum of the first {len(powersOfTwo)-1} powers of two is {current_sum}")
# len(powersOfTwo) grabs the length of the list (Which returns an integer), 
# minus 1 to account for the 0 index.
# encased in {} to evaluate as a Python expression within the f-string.
