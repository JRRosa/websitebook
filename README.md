# websitebook

In this project, create a repository for book

#### Settings 
* RUN mvn clean package war:war
* RUN docker build -t tomcat10/server -f container/webServer/Dockerfile .
* RUN docker run -it -d --rm -p 8080:8080 --name tomcat-image tomcat10/server
* and finally enjoy it at http://localhost:8080/websitebook
* Create a class connection ConnectionSql

#### Method

In case that you want create user you can create with the next command.

````
$ curl -v -X PUT -H "Content-Type: application/json"^ 
More? -d "{"\"email\":\"mingo@gmail.com\","\"password\":\"xxxx\","\"firstName\":\"mingo\","\"lastName\":\"reyes\","\"gender\":\"1\","\"userTypeId\":\"1\", 
More? http://localhost:8080/websitebook/api/user/create 
````

#### Extra
in case you are looking for check if the project is running you can perform the next command.

````
$ curl http://localhost:8080/websitebook/api/Healthcheck
````
And as response you will receive 
````
Working and Running
````
