#!/bin/bash

sed -e "/^[ ]*[0-9]\+: $1$/!d" mc2xml_out.txt | \
    sed -e "s/^[ ]*\([0-9]\+\):.*$/\1/g" > input
