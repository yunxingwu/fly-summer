#!/bin/sh
server_name="fly-provider"
CLASSPATH=`find ../lib/ -name "*.jar"|xargs|sed "s/ /:/g"`
mainclass=com.star.fly.impl.Main
echo -e "starting the $server_name..."
java -cp $CLASSPATH com.star.fly.impl.Main $*
echo "OK!"