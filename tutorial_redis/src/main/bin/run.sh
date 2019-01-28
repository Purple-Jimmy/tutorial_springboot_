#!/bin/bash  
  
if [ -z "$BASE_DIR" ] ; then  
  PRG="$0"  
  
  while [ -h "$PRG" ] ; do  
    ls=`ls -ld "$PRG"`  
    link=`expr "$ls" : '.*-> \(.*\)$'`  
    if expr "$link" : '/.*' > /dev/null; then  
      PRG="$link"  
    else  
      PRG="`dirname "$PRG"`/$link"  
    fi  
  done  
  BASE_DIR=`dirname "$PRG"`/..  
  
  BASE_DIR=`cd "$BASE_DIR" && pwd`
fi  
  
source $BASE_DIR/bin/env.sh  

LOG_DIR="$BASE_DIR/logs"  
LOG_FILE="$LOG_DIR/ccs.log"  
PID_DIR="$BASE_DIR/bin"  
PID_FILE="$PID_DIR/.run.pid"
  
function running(){  
    if [ -f "$PID_FILE" ]; then  
        pid=$(cat "$PID_FILE")  
        process=`ps aux | grep " $pid " | grep -v grep`;  
        if [ "$process" == "" ]; then  
            return 1;  
        else  
            return 0;  
        fi  
    else  
        return 1  
    fi    
}  
  
if running; then
	echo "$SERVER_NAME is running."  
	exit 1  
fi

jdk=`cat /etc/profile |grep -i java_home=|cut -d= -f2-|sed -e 's/ //g'`/bin/java
$jdk -version >/dev/null 2>&1

if [ $? -ne 0 ]
then
	echo "the os did not install java!"
	exit 1
fi 
  
mkdir -p $PID_DIR  
#touch $LOG_FILE  
mkdir -p $LOG_DIR  
      
sleep 1  
nohup $jdk -Xmx$MAXMEM -Xms$MINMEM -XX:PermSize=$MAXPERM -XX:MaxPermSize=$MAXPERM $STARTUP_CLASS >>/dev/null 2>&1 &   
echo $! > $PID_FILE  
chmod 755 $PID_FILE  

echo "start $SERVER_NAME service:"
PID=$!
sleep 1
echo "start master finished, PID=$PID"
echo "checking if $PID is running..."
sleep 1
kill -0 $PID > /dev/null 2>&1
if [ $? -eq 0 ]
then
	echo "$PID is running, start $SERVER_NAME server."
	echo "$SERVER_NAME run successful."
else
	echo "start $SERVER_NAME failed."
fi   
#tail -F $LOG_FILE 