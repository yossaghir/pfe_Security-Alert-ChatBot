#!/bin/bash
mkdir analyseLin
echo "hp" | sudo -S cat /home/$1/.bash_history > ./analyseLin/$1

