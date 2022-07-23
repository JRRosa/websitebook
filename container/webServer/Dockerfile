FROM tomcat:10-jdk8-openjdk-slim
EXPOSE 8080
RUN rm -fr /usr/local/tomcat/webapps/
COPY ./volumen/*.war /usr/local/tomcat/webapps/
CMD ["catalina.sh", "run"]

# docker build -f Dockerfile .
# docker rmi 7de72873616a
# docker tag 52ce02190097 tomcat-image

# docker run -it -d --rm -p 8080:8080 --name tomcat-image tomcat-image
# docker system prune -af