#com o as vamos "apelidar" essa primeira parte como build
FROM maven:3.8.5-openjdk-11 as build
WORKDIR /app
#Do jeito abaixo vai copiar todos os arquivos na raiz de onde o Dockerfile está para dentro do container
COPY . .
RUN mvn clean package -DskipTests


#Daqui pra baixo preciso ter o arquivo .jar criado
FROM openjdk:11
#cria um diretorio no containeiner chamado app
WORKDIR /app
#Copia o arquivo jar que está no build acima
COPY --from=build ./app/target/*.jar ./app.jar
#Expoe a porte 8761
EXPOSE 8761
#Executa o arquivo jar
ENTRYPOINT java -jar app.jar