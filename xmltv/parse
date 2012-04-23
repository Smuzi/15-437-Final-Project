#!/bin/bash

#awkpat='BEGIN              {numfound = 0; blankfound = 0}'
#       '/^[ ]*[0-9]+/      {numfound = 1}'
#       '/^$/ && found == 1 {blankfound = 1}'
#       'END                {if (blankfound == 1) exit 1; else exit 0}'

./mc2xml -c us -g 15213 < /dev/null | \
	stdbuf -o0 sed -e 's/.\o10//g' | \
	stdbuf -o0 sed -e '/^\[mc2xml\]/d' > foo &

while true; do
	if [ "`cat foo | awk '/^[ ]*[0-9]+/{a=1}/^$/&&a==1{b=1; print NR}END{}END{if (b == 1) print "matched"}'`" != "" ]
	then
		cat foo
		killall mc2xml
		break
	fi
done
