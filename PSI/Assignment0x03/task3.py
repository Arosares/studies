import requests

link = "https://paddingoracle.psi.h4q.it/oracle?ciphertext="
ciphertext = "\
bd4b7c0a62018807bec676f83c0685cc-\
92b2e23fbc24d12a8f726becc58ee7fb-\
307de7ccb0127d7d772e7b4f38edd348-\
14eeb43229dd60c1a4168862ba42a652-\
56565e8b92fdc36c962ca4ba1d977d8e"

hexChars = []

##all readable chars in hex: 32 - 126
for i in range(1, 127):
    hexChars.append(i)

s = ""
for hexChar in hexChars:
    hexString = hex(hexChar)

    if len(hexString) == 3:
        #add 0 to small hexStrings
        hexString = '0' + hexString
    s += hexString

s = s.replace("0x", "")
print(s)

amountPaddingBits = 1
for i in range(1, len(hexChars)):
    ctext = ciphertext
    #remove last i paddings bit
    woPadding = ctext[:-(i*2)]
    # print(s[0:2])
    wPadding = woPadding + (i) * s[0:2]
    s = s[2:]
    print(wPadding)
    page = requests.get(link+wPadding)
    print(page.content)
    # paddingBits = s[0:]
    # woPadding = s[:-2 * amountPaddingBits]
    # woPadding += s[2 * amountPaddingBits:]

# print(ciphertext[:-2])
page = requests.get(link+ciphertext)
print(page.content)