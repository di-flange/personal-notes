## Install JAVA enviroment ##

    apt-get remove openjdk*
    add-apt-repository ppa:webupd8team/java
    apt-get update
    apt-get install oracle-java8-installer
 
## Change time zone ##
To check current time zone and time:

    date && more /etc/timezone

To change date 

    dpkg-reconfigure tzdata

Or automaticly

    echo "Europe/Warsaw" > /etc/timezone    
    dpkg-reconfigure -f noninteractive tzdata
  
Some zones: Europe/Warsaw, Europe/Tallinn, Europe/Moscow 
  
## CRON daily night backup ##
Backap daily at 03:00

    
## Change locale ##
    
    export LANGUAGE="en_US.UTF-8"
    echo 'LANGUAGE="en_US.UTF-8"' >> /etc/default/locale
    echo 'LC_ALL="en_US.UTF-8"' >> /etc/default/locale
    
    locale-gen en_US.UTF-8
    dpkg-reconfigure locales
