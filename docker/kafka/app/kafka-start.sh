#!/bin/bash

/home/app/bin/zookeeper-server-start.sh /home/app/config/zookeeper.properties &
/home/app/bin/kafka-server-start.sh /home/app/config/server.properties