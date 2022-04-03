#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/id_rsa/pet_rsa \
    target/petShop.jar \
    mrenatax@192.168.1.160:/home/mrenatax/

echo 'Restart server...'

ssh -i ~/.ssh/id_rsa/pet_rsa mrenatax@192.168.1.160 << EOF
pgrep java | xargs kill -9
nohup java -jar petShop.jar > log.txt &
EOF

echo 'Bye'