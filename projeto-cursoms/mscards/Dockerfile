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
#definindo as variaveis de ambiente
ARG RABBITMQ_SERVER=rabbitmq
ARG EUREKA_SERVER=teste-eureka

#Executa o arquivo jar e neste caso esta referenciando um profile application diferente do padrão que seria o application.yml
ENTRYPOINT java -jar -Dspring.profiles.active=production app.jar