# Real time Status Monitor

Real Time Status  Monitor is a Web project helps to monitor the status of the application job in real time. This is sample project to demostrate the usage of WEBSOCKET. This application is designed to mointor the content of a file available on the server and publish the file content to the client for every 1 minute. 
This application can be accessed from multiple client stations concurrently. 
Also it provides extension to receive command (json based) through browser client.

## Getting Started
Two options available for the usage
*Build and Use 
     Download this web project and import into eclipse workspace as dynamic web project.Execute build.xml to compile and create web application binary.
* Download Binary and Use
    Download StatusCheckWeb.war from this project and follow the instructions provided in "TOMCAT DEPLOYMENT" section
...

### Prerequisites

*JDK 1.8 
*Web server 

```

### Installing

 
###  INSTALLATION Tomcat Deployment (Tomcat 8.x or 9.x)
* [Web Application] Download StatusCheckWeb.war from the project
* [Deploy] Place StatusCheckWeb.war in the webapps folder
* [Environment Setting] Update Catalina.sh with following System configuration 
 		 -DAPP_DOC_ROOT=<directory path>
 		 -DSTATUS_FILE=<status file>
*[Example]  -DAPP_DOC_ROOT=C:\\status -DSTATUS_FILE=status.txt
*[Restart] Restart the Web application 

```
### Manual 

* Use URL http://<HOSTNAME>:<port>/StatusCheckWeb/ to load Status Monitor Page
    Example: http://localhost:8080/StatusCheckWeb/
* On loading of above "Status Monitor" page, websocket connection get initialzed 
* Click "Start Monitoring" button to initiate the Status File Monitoring 
* Click "Stop Monitoring" to stop the status File Monitoring. 









## Authors

* **SARAVANA KUMAR** - *Initial work* - [Saravana](https://github.com/saravananethaji)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.




