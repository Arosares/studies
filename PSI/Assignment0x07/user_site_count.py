import re
import operator

def time_check(time):
    hr, m, s = time.split(":")
    hr, m, s = int(hr), int(m), int(s)
    
    if m == 14 and s >= 30:
        return True
    elif m == 15 and s <= 30:
        return True
    else:
        return False

#regex = re.compile(r'http://(w+)"')
ips = {}
with open('left.txt', 'r') as file:
    lines = file.readlines()
    for line in lines:
        match = re.search(r'(.+)\s.+(20:1[4-5]:\d+)', line)
        if match:
            ip = match.group(1)
            time = match.group(2)
            timeValid = time_check(time)
            if timeValid:
                if ip in ips:
                    ips[ip] += 1
                else:
                    ips[ip] = 1
        
sorted_ips = sorted(ips.items(), key=operator.itemgetter(1), reverse=True)
print(len(sorted_ips))
print(sorted_ips[:31])


