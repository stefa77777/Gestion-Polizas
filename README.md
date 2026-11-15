Gestión de Pólizas - Prueba Técnica (Java 21 & Spring Boot)

Microservicio desarrollado en Java 21 y Spring Boot para la gestión de pólizas y riesgos, cumpliendo con los estándares de arquitectura por capas, seguridad mediante API Key y reglas de negocio automatizadas.

Requisitos Técnicos
* **Java 21** (JDK 21)
* **Gradle** (Se incluye Gradle Wrapper `./gradlew`)

---

Estructura del Proyecto
El proyecto está organizado bajo una arquitectura limpia por capas orientada a microservicios:

src/
└── main/
    └── java/
        └── com/
            └── polizas/
                └── gestion_polizas/
                    ├── controller/    # Endpoints REST (PolizaController)
                    ├── model/         # Entidades y Enums (Poliza, Riesgo, TipoPoliza)
                    ├── repository/    # Persistencia en memoria (PolizaRepository)
                    └── service/       # Lógica de negocio (PolizaService)
                    
Reglas de Negocio Implementadas
Seguridad Obligatoria: Todos los endpoints requieren la cabecera HTTP x-api-key: 123456. De lo contrario, se retornará un error 401 Unauthorized.

Renovación con IPC: Al renovar una póliza, el canon mensual y el valor de la prima se incrementan automáticamente en un 5%.

Validación de Riesgos: Solo se pueden agregar riesgos adicionales a pólizas cuyo tipo sea COLECTIVA.

Ciclo de Vida: Las pólizas manejan estados como ACTIVA, RENOVADA y CANCELADA.

Endpoints Principales para Pruebas (Postman)
1. Listar Pólizas
URL: GET http://localhost:8080/polizas

Headers: x-api-key: 123456

Parámetros Opcionales (Query Params): tipo (INDIVIDUAL / COLECTIVA), estado (ACTIVA / CANCELADA)

2. Renovar Póliza (Aplica +5% IPC)
URL: POST http://localhost:8080/polizas/{id}/renovar

Headers: x-api-key: 123456

3. Cancelar Póliza
URL: POST http://localhost:8080/polizas/{id}/cancelar

Headers: x-api-key: 123456

4. Agregar Riesgo (Solo Pólizas Colectivas)
URL: POST http://localhost:8080/polizas/{id}/riesgos

Headers:

x-api-key: 123456

Content-Type: application/json

Body (JSON):

JSON
{
    "riesgoId": 102
}
Instrucciones de Ejecución
Clona o abre el proyecto en tu entorno de desarrollo (IntelliJ IDEA / VS Code).

Ejecuta la clase principal GestionPolizasApplication.java.

La aplicación se levantará en el puerto por defecto: http://localhost:8080.
