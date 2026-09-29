# cmp_student

Aplicación Spring Boot para gestión de estudiantes con WebFlux.

## Descripción

Este proyecto expone un controlador REST para consultar y actualizar estudiantes usando un cliente WebClient hacia un servicio externo. La estructura principal incluye:

- Controlador REST en [src/main/java/com/cmp_student/controller/StudentController.java](src/main/java/com/cmp_student/controller/StudentController.java)
- Servicio con lógica de validación y actualización en [src/main/java/com/cmp_student/service/StudentService.java](src/main/java/com/cmp_student/service/StudentService.java)
- DTO de estudiante en [src/main/java/com/cmp_student/dto/StudentDto.java](src/main/java/com/cmp_student/dto/StudentDto.java)
- Configuración de WebClient en [src/main/java/com/cmp_student/config/WebClientConfig.java](src/main/java/com/cmp_student/config/WebClientConfig.java)

## Requisitos

- Java 17 o superior
- Maven 3.9+

## Ejecución

```bash
./mvnw clean install
./mvnw spring-boot:run
```

La aplicación quedará disponible en:

- http://localhost:9090

## Endpoints principales

- GET /student
- PUT /student/{id}

## Variables de entorno

El valor base del servicio externo se configura en:

- [src/main/resources/application.properties](src/main/resources/application.properties)

```properties
url.base.student=http://localhost:8080/student
```

## Notas

- Se ajustó la dependencia de Lombok para compatibilidad con el JDK en uso.
- Se eliminó una prueba colocada fuera de la estructura estándar de Maven para evitar errores de compilación.
- La app se ejecuta con Spring Boot y WebFlux.
