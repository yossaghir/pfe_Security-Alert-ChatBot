#!/bin/bash

cat /etc/passwd | grep "/home" | awk -F':' '{ print $1}'| tail -n+3 >users.txt

