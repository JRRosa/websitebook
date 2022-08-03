# websitebook

In this project, create a repository for book

#### Settings 
* RUN mvn clean package war:war
* RUN docker build -t tomcat10/server -f container/webServer/Dockerfile .
* RUN docker run -it -d --rm -p 8080:8080 --name tomcat-image tomcat10/server
* and finally enjoy it at http://localhost:8080/websitebook
* Create a class connection ConnectionSql

#### Extra
in case you are looking for check if the project is running you can perform the next command.

````
$ curl http://localhost:8080/websitebook/api/Healthcheck
````
And as response you will receive 
````
Working and Running
````
