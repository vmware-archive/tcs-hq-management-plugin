# Edit this file to CATALINA_BASE/bin/setenv.sh to set custom options
# Tomcat accepts two parameters JAVA_OPTS and CATALINA_OPTS
# JAVA_OPTS are used during START/STOP/RUN
# CATALINA_OPTS are used during START/RUN

# JVM memory settings - general
GENERAL_JVM_OPTS="-Xmx512m -Xss192k"

# JVM Sun specific settings
# For a complete list https://blogs.oracle.com/roller-ui/errors/404.jsp
#SUN_JVM_OPTS="-XX:MaxPermSize=192m \
#              -XX:MaxGCPauseMillis=500 \
#              -XX:+HeapDumpOnOutOfMemoryError"

#SUN_JVM_OPTS="-XX:MaxPermSize=192m \
#              -XX:NewSize=128m \
#              -XX:MaxNewSize=256m \
#              -XX:MaxGCPauseMillis=500 \
#              -XX:HeapDumpOnOutOfMemoryError \
#              -XX:+PrintGCApplicationStoppedTime \
#              -XX:+PrintGCTimeStamps \
#              -XX:+PrintGCDetails \
#              -XX:+PrintHeapAtGC \
#              -Xloggc:gc.log"
              
# JVM IBM specific settings
#IBM_JVM_OPTS=""

# Set any custom application options here
#APPLICATION_OPTS=""

# Must contain all JVM Options.  Used by AMS.
JVM_OPTS="$GENERAL_JVM_OPTS $SUN_JVM_OPTS"

CATALINA_OPTS="$JVM_OPTS $APPLICATION_OPTS"

#JAVA_HOME=setme
#JRE_HOME=setme
