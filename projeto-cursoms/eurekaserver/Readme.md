Para usar o RabbitMQ subir o container:
- docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management

-Criar uma fila no AddQueue com 

type: Classic

nome: emissao-cartoes



---

Para usar o Keycloak :
- docker run -p 8081:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:18.0.0 start-dev

Criar um Realm: Add realm
nome: mscourserealm

Depois criar um Client: Create

ClientID: mscredito

Depois de criar configurar:

Access Type: confidential

Service Accounts Enabled: ON

Authorization Enabled: ON

Valid Redirect URIs: http://localhost:8080

Quando tiver trabalhando com docker
Frontend URL : http://cursoms-keycloak:8080
---

Procurar por "policies" no arquivo que irá exportar para o keycloak e apagar o coteúdo e salvar depois tentar improtar novamente

---

- docker run -p 8081:8080 --network cursoms-network --name cursoms-keycloak -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:18.0.0 start-dev

-  docker run -it --rm --name rabbitmq --network cursoms-network -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management

-  docker run --name cursoms-eureka -p 8761:8761 --network cursoms-network cursoms-eureka

- docker run --name cursosms-avaliadorcredito --network cursoms-network -e RABBITMQ_SERVER=rabbitmq -e EUREKA_SERVER=teste-eureka  cursoms-avaliadorcredito

- docker run --name cursosms-clients --network cursoms-network -e EUREKA_SERVER=teste-eureka  cursoms-clients                                              

- docker run --name cursosms-cards --network cursoms-network -e RABBITMQ_SERVER=rabbitmq -e EUREKA_SERVER=teste-eureka  cursoma-cards

- docker run --name cursoms-gateway -p 8080:8080 --network cursoms-network -e EUREKA_SERVER=cursoms-eureka -e KEYCLOAK_SERVER=cursoms-keycloak -e KEYCLOAK_PORT=8080  cursoms-gateway 