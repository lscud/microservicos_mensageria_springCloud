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
