for i in range(1, 100000):
    # j = 100000 - i
    m = 2

    
    c = (m ** i) % 4
    
    if c == m:
        print("i: " + str(i) + " m: " + str(m) +" c: " + str(c))
        # m = m + 300
        # c = (m ** i) % 391
        # if c == m:
        #     print("FOUND IT: " + "i: " + str(i) + " m: " + str(m) +" c: " + str(c) )
        