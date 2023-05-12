FROM openjdk:11
#cria um diretorio no containeiner chamado app
WORKDIR /app
#Copia o arquivo jar que está na pasta target para a pasta app do container renomeando o arquivo jar para app.jar
COPY ./target/eurekaserver-0.0.1-SNAPSHOT.jar app.jar
#Expoe a porte 8761
EXPOSE 8761
#Executa o arquivo jar
ENTRYPOINT java -jar app.jar