def is_balanced(arr):
    arr_sum = sum(arr)
    print(arr_sum)
    if arr_sum % 2 != 0:
        return False
    
    target = arr_sum / 2
    i = 0
    blc = 0
    while (blc < target):
        blc += arr[i]
        print("Blc:", blc)
        print("index:",i)
        i += 1

    if blc == target:
        return True
    else:
        return False


if __name__=='__main__':
    arr = [1,2,3]
    arr2= [3,5,7]
    arr3 = [1,4,6,7,2,6,8,2,2,5,8,9,3,3]

    #print(is_balanced(arr))
    #print(is_balanced(arr2))
    print(is_balanced(arr3))