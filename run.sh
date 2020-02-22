#!/bin/bash
git checkout .
git pull origin master

echo "start process"

mvn clean package -Dmaven.test.skip=true
echo "package done"


imageName=reservoir-`date +%Y%m%d`
echo $imageName

docker build -t $imageName .
echo "build image done"

containerId=`cat ~/tmp/reservoir_id`
docker stop $containerId
echo "stop $containerId"
sleep 5

docker run -d -p 9528:9528 $imageName > ~/tmp/reservoir_id

echo `cat ~/tmp/reservoir_id`
echo "run new instance done"
