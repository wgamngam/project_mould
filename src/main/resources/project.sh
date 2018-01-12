#!/usr/bin/env bash
usage() {
    echo "Usage: ${0##*/} (start|stop|restart|check|log) (project)"
    exit 1
}

[ $# -gt 1 ] || usage

ACTION=$1
SERVER=$2
ROOT_PATH=/home/hadoop

#CONFIG_ARGS配置项目所需要启动参数（数据库、缓存、分布式文件系统等配置信息）
#FILE_COUNT=`ls -l /lt/scripts/*.vmoptions | grep -c ''`
#if [ "$FILE_COUNT" -ne 1 ]; then
#    echo "Could not locate the need '.vmoptions' file.(None or too many)"
#    exit 1
#fi

#for LINE in `ls /lt/scripts/*.vmoptions | xargs cat | grep '^[^#].'|grep "="|grep '^\-D'`
#do
#    CONFIG_ARGS="$CONFIG_ARGS $LINE"
#done

CONFIG_ARGS=""

#JAVA_OPTION配置JAVA虚拟机参数信息 （内存占用等）
case "$SERVER" in
	project)
        JAVA_OPTIONS="-Xmx2048m -XX:MaxPermSize=256m"
        DEBUG_PORT=5036
    ;;
    *)
        usage
    ;;
esac

CATALINA_HOME=$ROOT_PATH/apache-tomcat-$SERVER
CATALINA_SCRIPT=$CATALINA_HOME/bin/catalina.sh
CATALINA_OUT=$CATALINA_HOME/logs/$SERVER/`date +%Y_%m_%d`.stderrout.log
CATALINA_PID=$CATALINA_HOME/tomcat-$SERVER.pid

del_worker_dir(){
    rm -rf $CATALINA_HOME/work/Catalina
}
ensure_killed(){
    if [ -f "$CATALINA_PID" ]; then
        kill -9 `cat $CATALINA_PID`
        rm -rf $CATALINA_PID
    fi
}
cat_start(){
    export JAVA_OPTS="-server $JAVA_OPTIONS $CONFIG_ARGS -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=$DEBUG_PORT"
    export CATALINA_OUT
    export CATALINA_PID
    echo "JAVA_OPTS=$JAVA_OPTS"
    del_worker_dir
    $CATALINA_SCRIPT start #> /dev/null 2>&1
    echo "Tomcat instance:$SERVER is running now.PID:`cat $CATALINA_PID`"
}
cat_stop(){
    $CATALINA_SCRIPT stop > /dev/null 2>&1
    ensure_killed
    echo "Tomcat instance:$SERVER stopped!"
}
cat_restart(){
    echo "Restarting tomcat..."
    cat_stop && cat_start
    echo "Tomcat instance:$SERVER restarted!"
}

case "$ACTION" in
    start)
        cat_start
    ;;
    stop)
        cat_stop
    ;;
    restart)
        cat_restart
    ;;
    check)
        ps -ef | grep "/tomcat-$SERVER/" | grep -v "grep"
    ;;
    log)
        tail -f $CATALINA_OUT
    ;;
    *)
        usage
    ;;
esac

