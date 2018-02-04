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

ips = {}
with open('left.txt', 'r') as file:
    src_lines = file.readlines()

with open('right.txt', 'r') as file:
    dst_lines = file.readlines()


for line in src_lines:
    match_src = re.search(r'(.+)\s.+([?21:59:|22:00:]:\d+)', line)
    if match_src:
        ip = match_src.group(1)
        time = match_src.group(2)
        #timeValid = time_check(time)
        match_dst = re.search(r'[?21:59:|22:00:]:\d+).*"http://')
        if timeValid:
            if ip in ips:
                ips[ip] += 1
            else:
                ips[ip] = 1
        
sorted_ips = sorted(ips.items(), key=operator.itemgetter(1), reverse=True)
print(len(sorted_ips))
print(sorted_ips[:31])


