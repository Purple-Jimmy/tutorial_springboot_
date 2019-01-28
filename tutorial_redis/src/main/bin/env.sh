#!/bin/bash
    
UEAP_HOME=$BASE_DIR  
SERVER_NAME="vpc"  
STARTUP_CLASS="com.redis.RedisStart"
MAXMEM="1g"
MINMEM="256m"
MAXPERM="512m"

export CLASSPATH=$BASE_DIR/config:$(ls $BASE_DIR/lib/*.jar | tr '\n' :)