names = ["rei", "valles", "rumar", "less", "harl", "drey"]

names.pop() # Removes item at highest index number
print(names)
names.pop(1) 
''' v
    notation: .pop(i) 
    removes the thing in index i
'''

print(names)

names.reverse() # reverses the list
print(names)
names.reverse() # turns it back to it's original form
print(names)

names.extend("bingus") 
'''     v
    appends the items inside the parenthesis in a way similar to list() at the end
'''
print(names)

names.index()