#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/new_ssh \
    target/petShop.jar \
    mrenatax@51.250.102.166:/home/mrenatax/

echo 'Restart server...'

ssh -i ~/.ssh/new_ssh mrenatax@51.250.102.166 << EOF
pgrep java | xargs kill -9
nohup java -jar petShop.jar > log.txt &
EOF

echo 'Bye'