def longest_row(arr):
    champ = []
    first = arr[0]
    second = arr[1]
    curr = []

    for num in arr[2:]:
        if second > first:
            curr.append(first)
            curr.append(second)
            if num > second:
                curr.append(num)
                first = second
                second = num
                if len(curr) > len(champ):
                    champ = curr.copy()
                    curr.clear()
        if second < first:
            curr.append(first)
            curr.append(second)
            if num < second:
                curr.append(num)
                first = second
                second = num
                if len(curr) > len(champ):
                    champ = curr.copy()
                    curr.clear()

    return champ

if __name__=="__main__":
    arr = [6,4,1,2,3,4,3,2,5]
    print(longest_row(arr))