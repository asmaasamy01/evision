# evision
1-download kafka from https://kafka.apache.org/downloads
2-install git to use git batch
3- run ZooKeeper (Binary) by the following commad
 bin/windows/zookeeper-server-start.bat config/zookeeper.properties
4-run kafka by the following command
bin/windows/kafka-server-start.bat config/server.properties

5-show result throw  the following command
bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic csv1
bin/windows/kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic csv1

4- create spring boot application using eclipse as an editor
