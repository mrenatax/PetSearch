#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/petShop.jar \
    mrenatax@51.250.96.123:/home/mrenatax/

echo 'Restart server...'

ssh -i ~/.ssh/id_rsa mrenatax@51.250.96.123 << EOF
pgrep java | xargs kill -9
nohup java -jar petShop.jar > log.txt &
EOF

echo 'Bye'