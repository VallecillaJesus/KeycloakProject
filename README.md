## Guia de ejecucion

Al no contar con la implementacion con Docker la forma de ejecutar el proyecto es manual

El orden de ejecicion de los microservicios es:

1. Levantar el server de `Keycloak` e importar el archivo `realm-export.json` que contiene las configuraciones del reino

`docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:20.0.1 start-dev`

2. Levantar el microservicio `eureka-service`

3. Levantar el microservicio `users-service`
4. Levantar el microservicio `movies-api`
5. Levantar el microservicio `bills-api`

6. Por ultimo, levantar el microservicio `gateway-api`


### Credenciales

Los usuarios creados son:
    
    - username: admin
    - password: 123

    - username: client
    - password: 123

    - username: provider
    - password: 123

#### Puertos usados
- `users-service`: 8088
- `bills-api`: 8086
- `movies-api`: 8085
- `keycloak`: 8082
- `gateway-api`: 8080
- `eureka-service`: 8167
