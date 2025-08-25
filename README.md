# Microservicios - Sistema de Medicamentos

## 🏗️ Arquitectura Backend
- **Inventario Service** (puerto 8081): Gestión de medicamentos
- **Ventas Service** (puerto 8082): Gestión de ventas
- **Base de Datos**: H2 (en memoria)
- **Comunicación**: RestTemplate

## ⚡ Inicio Rápido

### Prerequisitos
- Java 17+
- Maven 3.6+

### 🚀 Ejecutar Microservicios
```
Terminal 1 - Inventario Service
cd inventario-service
mvn spring-boot:run

Terminal 2 - Ventas Service
cd ventas-service
mvn spring-boot:run
```
## 🌐 API Endpoints

### Inventario Service - Puerto 8081

#### Gestión de Medicamentos

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/api/medicine` | Listar medicamentos (con filtros y paginación) |
| `GET` | `/api/medicine/{id}` | Obtener medicamento por ID |
| `POST` | `/api/medicine` | Crear nuevo medicamento |
| `PATCH` | `/api/medicine/{id}` | Actualizar medicamento parcialmente |
| `DELETE` | `/api/medicine/{id}` | Eliminar medicamento |
| `PUT` | `/api/medicine/{id}` | Reducir stock de medicamento |

#### Ejemplos Detallados

##### Listar Medicamentos (con filtros)
```
GET http://localhost:8081/api/medicine

Parámetros opcionales:
?name=aspirina&laboratory=bayer&page=0&size=10&sort=name,asc
```

##### Obtener Medicamento por ID
```
GET http://localhost:8081/api/medicine/123e4567-e89b-12d3-a456-426614174000
```

##### Crear Medicamento

````
POST http://localhost:8081/api/medicine
Content-Type: application/json

{
"name": "Aspirina",
"laboratory": "Bayer",
"manufacturingDate": "2024-01-01",
"expirationDate": "2027-01-01",
"stock": 100,
"unitPrice": 5.50
}
````

##### Actualizar Medicamento
````
PATCH http://localhost:8081/api/medicine/123e4567-e89b-12d3-a456-426614174000
Content-Type: application/json

{
"name": "Aspirina 500mg",
"unitPrice": 6.00
}
````

##### Reducir Stock (para ventas)
````
PUT http://localhost:8081/api/medicine/123e4567-e89b-12d3-a456-426614174000
Content-Type: application/json

{
"quantity": 5
}
````

##### Eliminar Medicamento
````
DELETE http://localhost:8081/api/medicine/123e4567-e89b-12d3-a456-426614174000
````

#### Respuesta de Medicamento
````
{
"id": "123e4567-e89b-12d3-a456-426614174000",
"name": "Aspirina",
"laboratory": "Bayer",
"manufacturingDate": "2024-01-01",
"expirationDate": "2027-01-01",
"stock": 95,
"unitPrice": 5.50
}
````
#### Respuesta Paginada
````
{
"content": [
{
"id": "123e4567-e89b-12d3-a456-426614174000",
"name": "Aspirina",
"laboratory": "Bayer",
"stock": 95,
"unitPrice": 5.50
}
],
"totalElements": 1,
"totalPages": 1,
"size": 10,
"number": 0
}
````


### Ventas Service - Puerto 8082

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/sales` | Listar ventas (con filtros) |
| `GET` | `/sales/medicine/{id}` | Obtener información de medicamento |
| `POST` | `/sales/{id}` | Realizar venta de medicamento |

#### Ejemplos Ventas Service

##### Realizar Venta
````
POST http://localhost:8082/sales/123e4567-e89b-12d3-a456-426614174000
Content-Type: application/json

{
"quantity": 10
}
````


##### Listar Ventas
````
GET http://localhost:8082/sales

Parámetros opcionales: ?medicineId=...&dateFrom=...&dateTo=...
````
## 🔧 Tecnologías
- Spring Boot 3.2
- Spring Data JPA
- H2 Database
- Maven
- Lombok

## 🔧 Características Técnicas
- **Paginación**: Soporte completo con parámetros page, size, sort
- **Filtros**: Búsqueda por nombre, laboratorio, fechas
- **Comunicación**: RestTemplate para consultar inventario desde ventas
- **Validaciones**: Stock automático, fechas de vencimiento
- **UUIDs**: Identificadores únicos para todas las entidades
