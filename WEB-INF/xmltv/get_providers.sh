#!/bin/bash

#awkpat='BEGIN              {numfound = 0; blankfound = 0}'
#       '/^[ ]*[0-9]+/      {numfound = 1}'
#       '/^$/ && found == 1 {blankfound = 1}'
#       'END                {if (blankfound == 1) exit 1; else exit 0}'

./mc2xml -c us -g $1 < /dev/null | \
	stdbuf -o0 sed -e 's/.\o10//g' | \
	stdbuf -o0 sed -e '/^\[mc2xml\]/d' > mc2xml_out.txt &

while true; do
	if [ "`cat mc2xml_out.txt | awk '/^[ ]*[0-9]+/{a=1}/^$/&&a==1{b=1; print NR}END{if (b == 1) print "matched"}'`" != "" ]
	then
		killall mc2xml
		break
	fi
done
