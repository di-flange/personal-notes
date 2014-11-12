#!/bin/sh

SERVICE_NAME="Project name"
SERVICE_ALIAS="project_name"
LOCATION="/usr/lib/$SERVICE_ALIAS"
LOG_LOCATION="/var/log/$SERVICE_ALIAS"
RUN_COMMAND="run.command"
PID_STORE="/tmp/$SERVICE_ALIAS.pid"

function run_app {
    echo "$SERVICE_NAME starting ..."

    cd $LOCATION
    
    nohup $RUN_COMMAND 2>> "$LOG_LOCATION/startup1.log" >> "$LOG_LOCATION/startup2.log" &
                echo $! > $PID_STORE 
                
    echo "... started"
}

function stop_app {
    echo "$SERVICE_NAME stopping ...";
    
    kill $1;
    rm $PID_STORE
    
    echo "... stopped";  
}

case $1 in
    start)
        if [ ! -f $PID_STORE ] || [ ! $(kill -s 0 "$(cat $PID_STORE)") ]; then
            run_app
        else
            echo "$SERVICE_NAME is already running."
        fi
    ;;
    status)
        if [ -f $PID_STORE ]; then
            PID=$(cat $PID_STORE);
            
            if [ ! -z "$PID" ]; then
                STATE=$(ps -p $PID | grep $PID) > /dev/null
            else 
                STATE=''
            fi

            if [ ! -z "$STATE" ]; then
                echo "$SERVICE_NAME is running (pid $PID)."
            else
                echo "$SERVICE_NAME is NOT running. (pid $PID - DIED UNEXPECTEDLY)"
            fi
        else
            echo "$SERVICE_NAME is NOT running."
        fi
    ;;
    stop)
        if [ -f $PID_STORE ]; then
            PID=$(cat $PID_STORE)
            stop_app $PID
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
    restart)
        if [ -f $PID_STORE ]; then
            PID=$(cat $PID_STORE)
            
            stop_app $PID
            run_app
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
esac
