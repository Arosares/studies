#!/usr/bin/env python 
import string
import collections
import sets

# XORs two string
def strxor(a, b):     # xor two strings (trims the longer input)
    return "".join([chr(ord(x) ^ ord(y)) for (x, y) in zip(a, b)])

# 10 unknown ciphertexts (in hex format), all encrpyted with the same key
c1 = "9d567b9ae76c8a1c72c9075e28d3537a8325bed6043620216c5d796fa65ef24d46a059\
9b0c337e9f32465dae103cdb852cddc2e49ab525e923bf364e5c7602f31ee40805ef36\
5340e83ef1ee2220fadb2d58ac6e8cf3d0348c34b7a48887a85d8a864bcd5f4ff5b3dc\
0b33ada6b92c069f"
c2 = "9e576ac8dd5ebd5571c1434f618b4e6f8326bfc74c782c207a562d63a75eed4752a018\
861a773cd0344359e21d318f8e3ac5d3b6dcbf2ae22ff02d4c493302e55bbd0e16ef26\
4b0fa63ce0bd2520f6db7e19e46988f38328c43ff8b98a8fed1b95860ece4052a9"
c3 = "96573887f079de0674c15246298b427fc620f7d71f73633b7752787ebc1bf1564ba618\
861a7730952e484ebb0c319280378296a1d2bb65ac4ccb11005f6151c37cde4707a626\
540ff325a5831702bd9e7857e06d8de58334c97ab3a48495fb1b919c0e884047f7a695\
0537aabcb725109f"
c4 = "915e3891f169de1469da4f4f23df4e6ac226b2821579363c3950647abc1bed5647bd0d\
d2087a6498604a1c8f3d06d7cf2cddd3e488b42eac4af12052496305a04af5021eef1f\
7d23a632eaa02535e3cb6e4de56790b6c232c87ab6a59f8ae15582d40ec45a4fa9"
c5 = "9f516e8df03c8c107ddd48442cc94b6c8333a4d1197b333a705c6379f85eeb4a47e53c\
9c1c616980340b48aa192bdba218ed96a793b238f87dea2054597c1fa049fc1450bf20\
5316e33fa5ba3961f3db2d4ae96b8be4c67cce23f8888e8ee45a97914bc9474ea7849d\
0b26acb0b53b11d403"
c6 = "8c507dc8ff6f8d0071de534322c55429c53da582187e266e69416265b25ef04402b61c\
910a617984390b53a45c00958c2bd7c6b0dca823e961bf0e61733310f25bbd0650bc26\
4e0fe836e9b77634ffd8624beb699cfac67ce11b9bea8a8cec1bacba2f856a7ac6ea99\
0835acaca83f0ade4359"
c7 = "8c507d9afb3c9f07798e44433dc3427b833fb8c609656f6e6e5b6469bc5efe5756ad1c\
9c0b7a7391344e1ca31221db8a37cdc4bd8ca86be561bf2c4e553302f45bed4b50b83b\
4808e924f1ee3e20e7d7635eac7c91b6d139c023f8a585c2e955c5950fcc405eeea592\
073afe9899084d"
c8 = "97567dc8f17ade0174cb074924db4f6cd172bacd0873306239446563b716bf4357b111\
9711677993215f59e20b2d92833c8ed3aa9fae32fc7bf62d47107a02a079fc0b1fa621\
1c23e924ebba3333b1f3625de924dee1cb35cf32f8a9848fea528b9118886a7ed5ea9d\
0832fe92900a30f903"
c9 = "9f597487f76fde3673db495e28d90744cc36b2824451000330136479f408fa505be50f\
87137d7582214950a75c3194cf2bcbc6a19da82ee82ff12c4e537602ae1ec40805ef31\
5d0ea634f3ab3861e3db6e56fa6d8cb6d734c97ab9bf9f8aed55919d08c95d43e8a4dc\
0d33a7fb"
c10 = "965d6f8dec3c8d016ecb46476dc84e79cb37a5d1403626607e1d2d449b2cc70e02b00a\
975f723094355b50a70465889f36c0d1a1dcbf24e27ceb3155536718ef50bd131fef37\
5203f428f5ba7620ffda2d58f97c96f3cd28c539b9be8ec2e94fc58003cd0959e6a799\
4622b7b8bd65"
ciphers = [c1, c2, c3, c4, c5, c6, c7, c8, c9, c10]
# The target ciphertext we want to crack
target_cipher = "8c507dc8ce4fb7556bc1554124c54029c420b8d71c36202177547f6ba00bf34356a00a\
d2067c65d026444ee20f2a979930c0d1e488b422ff2ffa3b45427018f35bb34739bb72\
4b01f571e0af2538bd9e7f50eb608aa9"

# To store the final key
final_key = [None]*150
# To store the positions we know are broken
known_key_positions = set()

# For each ciphertext
for current_index, ciphertext in enumerate(ciphers):

	counter = collections.Counter()
	# for each other ciphertext
	for index, ciphertext2 in enumerate(ciphers):
		if current_index != index: # don't xor a ciphertext with itself
			for indexOfChar, char in enumerate(strxor(ciphertext.decode('hex'), ciphertext2.decode('hex'))): # Xor the two ciphertexts
				# If a character in the xored result is a alphanumeric character, it means there was probably a space character in one of the plaintexts (we don't know which one)
				if char in string.printable and char.isalpha(): counter[indexOfChar] += 1 # Increment the counter at this index
	knownSpaceIndexes = []

	# Loop through all positions where a space character was possible in the current_index cipher
	for ind, val in counter.items():
		# If a space was found at least 7 times at this index out of the 9 possible XORS, then the space character was likely from the current_index cipher!
		if val >= 7: knownSpaceIndexes.append(ind)
	#print knownSpaceIndexes # Shows all the positions where we now know the key!

	# Now Xor the current_index with spaces, and at the knownSpaceIndexes positions we get the key back!
	xor_with_spaces = strxor(ciphertext.decode('hex'),' '*150)
	for index in knownSpaceIndexes:
		# Store the key's value at the correct position
		final_key[index] = xor_with_spaces[index].encode('hex')
		# Record that we known the key at this position
		known_key_positions.add(index)

# Construct a hex key from the currently known key, adding in '00' hex chars where we do not know (to make a complete hex string)
final_key_hex = ''.join([val if val is not None else '00' for val in final_key])
# Xor the currently known key with the target cipher
output = strxor(target_cipher.decode('hex'),final_key_hex.decode('hex'))
# Print the output, printing a * if that character is not known yet
print ''.join([char if index in known_key_positions else '*' for index, char in enumerate(output)])

'''
Manual step
'''
# From the output this prints, we can manually complete the target plaintext from:
# The secuet-mes*age*is: Wh** usi|g **str*am cipher, nev***use th* k*y *ore than onc*
# to:
# The secret message is: When using a stream cipher, never use the key more than once

# We then confirm this is correct by producing the key from this, and decrpyting all the other messages to ensure they make grammatical sense
target_plaintext = "The PSI working group congratulates you for solving this exercise. It was easy, right?"
print target_plaintext
key = strxor(target_cipher.decode('hex'),target_plaintext)
for cipher in ciphers:
	print strxor(cipher.decode('hex'),key)