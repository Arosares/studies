import re
import operator

regex = re.compile(r'http://(.+)"')
web_servers = {}
with open('right.txt', 'r') as file:
    lines = file.readlines()
    for line in lines:
        match = re.search(regex, line)
        if match:
            web_site = match.group(1)
            if web_site in web_servers:
                web_servers[web_site] += 1
            else:
                web_servers[web_site] = 1
        
sorted_servers = sorted(web_servers.items(), key=operator.itemgetter(1),
                         reverse=True)
print(len(sorted_servers))
print(sorted_servers[:5])
