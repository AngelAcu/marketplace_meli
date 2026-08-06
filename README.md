## DESCRIPCION
Aplicación backend para la gestión de productos en un marketplace, desarrollada con Spring Boot e integrando conceptos como JPA/Hibernate, DTOs, manejo de excepciones y documentación de endpoints.

## CORRER SERVIDOR
Asegurate de tener Docker instalado. Luego ejecuta el siguiente comando en la raíz del proyecto: **docker-compose up**

## DOCUMENTACION DE ENDPOINTS
Una vez levantado el servidor, accede a la documentacion en: **http://localhost:8080/swagger-ui/index.html**

## ARQUITECTURA
El proyecto sigue una arquitectura en capas:

- **models** -> Definición de las entidades
- **repositories** -> Acceso a la base de datos
- **services** -> Logica de negocio
- **dto** -> Contrato que indica los datos mostrar al cliente
- **controllers** -> Recepción y manejo de las peticiones HTTP
- **exceptions** -> Manejo global de errores y excepciones