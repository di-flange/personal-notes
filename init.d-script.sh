#!/bin/sh

SERVICE_NAME=ProjectService
RUN_COMMAND="/root/app/run.sh"
PID_STORE=/tmp/project-service.pid

case $1 in
    start)
        echo "Starting $SERVICE_NAME..."
        if [ ! -f $PID_STORE ]; then
            nohup $RUN_COMMAND 2>> /var/log/startup1.log >> /var/log/startup2.log &
                        echo $! > $PID_STORE
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is already running ..."
        fi
    ;;
    stop)
        if [ -f $PID_STORE ]; then
            PID=$(cat $PID_STORE);
            echo "$SERVICE_NAME stoping ..."
            kill $PID;
            echo "$SERVICE_NAME stopped ..."
            rm $PID_STORE
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
    restart)
        if [ -f $PID_STORE ]; then
            PID=$(cat $PID_STORE);
            echo "$SERVICE_NAME stopping ...";
            kill $PID;
            echo "$SERVICE_NAME stopped ...";
            rm $PID_STORE
            echo "$SERVICE_NAME starting ..."
            nohup $RUN_COMMAND 2>> /var/astel/log/startup1.log >> /var/astel/log/startup2.log &
                        echo $! > $PID_STORE
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
esac 
