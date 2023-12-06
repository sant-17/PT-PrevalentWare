# Introducción

Hola, soy Santiago García Herrera, desarrollador backend. Este repositorio tiene como propósito justificar mis decisiones de diseño en la prueba técnica, así como dar instrucciones para ejecutar la solución, ofrecer el comando Docker para ejecutar el contenedor y, por último, describir mi propuesta de despliegue.

## Acerca de la arquitectura elegida

Se usó la Arquitectura Hexagonal para la solución de la prueba. Dentro de las razones para usar dicha arquitectura estan:

- La clara separación de responsabilidades entre las capas de la aplicación.
- Desacoplar la lógica del negocio de unas tecnologías en específicas tales como la base de datos.

## Manejo de las respuestas

Como buena práctica se creó una clase estandarizada para las respuestas que pudieran dar nuestros endpoints. Dicha clase fue nombrada [APIResponse](https://github.com/sant-17/PT-PrevalentWare/blob/main/src/main/java/com/prevalentware/prueba_tecnica/infrastructure/utils/APIResponse.java) y fue usada en todas las rutas de la solución.

## Acerca del Manejo de Errores

Para el manejo de errores se usó el [@ControllerAdvice](https://github.com/sant-17/PT-PrevalentWare/blob/main/src/main/java/com/prevalentware/prueba_tecnica/infrastructure/exceptionhandler/ControllerAdvisor.java) ya que permite centralizar el manejo de errores en un solo lugar de la aplicación, además de facilitar el manejo de excepciones personalizadas y la reutilización de código. El ejemplo más claro de esto se encuentra en las clases [CountryUseCase](https://github.com/sant-17/PT-PrevalentWare/blob/main/src/main/java/com/prevalentware/prueba_tecnica/domain/usecase/CountryUseCase.java), [UserMonitoringUseCase](https://github.com/sant-17/PT-PrevalentWare/blob/main/src/main/java/com/prevalentware/prueba_tecnica/domain/usecase/UserMonitoringUseCase.java) y [UserUseCase](https://github.com/sant-17/PT-PrevalentWare/blob/main/src/main/java/com/prevalentware/prueba_tecnica/domain/usecase/UserUseCase.java), donde reutilizamos la excepción [LogNotFoundException](https://github.com/sant-17/PT-PrevalentWare/blob/main/src/main/java/com/prevalentware/prueba_tecnica/domain/exception/LogNotFoundException.java) (la cual fue creada por nosotros y se lanza cuando no se encuentra algún registro solicitado) y definimos su comportamiento en la clase [ControllerAdvisor](https://github.com/sant-17/PT-PrevalentWare/blob/main/src/main/java/com/prevalentware/prueba_tecnica/infrastructure/exceptionhandler/ControllerAdvisor.java) de la siguiente manera:

```java
@ExceptionHandler(LogNotFoundException.class)
public ResponseEntity<APIResponse<String>> handleLogNotFoundException(LogNotFoundException ex) {
    APIResponse<String> apiResponse = APIResponse.notFound(
        null,
        Constant.getLogResponseHashMap(),
        ex.getMessage(),
        Constant.LOG_RESPONSE_CODE_PREFIX.concat("1")
    );

    return new ResponseEntity<>(apiResponse, HttpStatus.valueOf(apiResponse.getHttpStatus()));
}
```
Y ahora cuando se lance dicha excepción obtendremos un mensaje del porqué se ha originado el error. Si tomamos de ejemplo el [endpoint usado para obtener un User por email](https://github.com/sant-17/PT-PrevalentWare/blob/main/src/main/java/com/prevalentware/prueba_tecnica/infrastructure/input/rest/UserRestController.java) (y suponiendo que dicho User no existe en la base de datos) obtendríamos la siguiente respuesta:

```json
{
    "status": "KO",
    "httpStatus": 404,
    "message": "Log not found - No User Log Found By Email",
    "internalCode": "LOG-1",
    "data": null
}
```

Adicionalmente se tuvo en cuenta excepciones ya existentes que se podrían lanzar al momento de usar algún endpoint. Por ejemplo, si en la paginación alguien llegara a colocar una letra en lugar de un número ('number' para el número de página), esto naturalmente lanzaría una excepción del tipo MethodArgumentTypeMismatchException sin controlar, pero nosotros en el [ControllerAdvisor](https://github.com/sant-17/PT-PrevalentWare/blob/main/src/main/java/com/prevalentware/prueba_tecnica/infrastructure/exceptionhandler/ControllerAdvisor.java) ya lo estamos controlando y definiendo su comportamiento.

## Acerca de la paginación
La paginación fue añadida únicamente en los endpoints que manejaban grandes volúmenes de registros y se omitió su uso en endpoints que solo devolvían unos pocos registros (y se tenía certeza de que respondían pocos registros).

Para la paginación se requirió que dichos endpoints que lo usaban, solicitaran el tamaño (size) y número (number) de página en la ruta. Por ejemplo: @GetMapping("all/size/{size}/number/{number}")

Posteriormente, gracias a @PathVariable, las variables 'size' y 'number' serían reutilizados en el adaptador de la capa de infraestructura se crearía un objeto de la clase Pageable con las variables anteriormente mencionadas y de esa forma obtendríamos la respuesta con paginación.

## Acerca de la seguridad y poniendo a prueba los endpoints
A continuación se expondrá un resumen de qué endpoints están disponibles, cómo usarlos y qué restricción de seguridad tienen. Hay que tener en cuenta que para obtener la autorización por parte del servicio, hay que introducir alguno de los sessionTokens que se encuentran en la tabla Session.

Usando Postman para probar los endpoints, pueden introducir el sessionToken Authorization > Type [Bearer Token] e introducir directamente el token.

- "User: Solo puede ver sus propios datos, incluidos sus países (Countries) asociados y su monitoreo
de usuario (UserMonitoring)". Ruta: GET /api/v1/user/ - Roles autorizados: Admin, Manager, User 
- "Puede ver todos los usuarios (users) de todos los países (countries), pero no puede
acceder a UserMonitoring." Ruta: GET /api/v1/user/all/size/{size}/number/{number} - Roles autorizados: Admin, Manager

- "Obtener un usuario por correo electrónico: Un endpoint que devuelva la información de un
usuario específico, identificado por su correo electrónico." Ruta: /api/v1/user/{email} - Roles autorizados: Admin, Manager

- "Obtener todos los países (Countries): Este endpoint solo estará disponible para usuarios Admin o
Manager, y devolverá información de todos los países." Ruta: /api/v1/country/all/size/{size}/number/{number} - Roles autorizados: Admin, Manager

- "Obtener UserMonitoring de un usuario en un rango de tiempo: Este endpoint requerirá el correo
del usuario, la fecha inicial y la fecha final del rango de búsqueda para devolver los datos de
UserMonitoring". Ruta: /api/v1/user-monitoring/ - Roles autorizados: Admin - Recibe el siguiente DTO:
```json
{
    "email": "string",
    "from": "yyyy-MM-dd",
    "to": "yyyy-MM-dd"
}
```
- "Obtener los tres usuarios con más registros en UserMonitoring en un rango de tiempo específico:
Este endpoint, reservado para administradores, requerirá la fecha inicial y la fecha final del rango
de búsqueda". Ruta: /api/v1/user-monitoring/top-users - Roles autorizados: Admin - Recibe el siguiente DTO: 
```json
{
    "from": "yyyy-MM-dd",
    "to": "yyyy-MM-dd"
}
```
## Acerca de la documentación
Se usó la dependencia de OpenApi para la documentación. Se puede acceder a dicha documentación mediante la ruta /swagger-ui/index.html

## Acerca de un endpoint que no se pudo desarrollar
Dentro del documento de la prueba técnica estaba este endpoint en específico: "Obtener los principales usuarios por tipo de uso en un país específico en un rango de tiempo: Este endpoint, también reservado para administradores, requerirá el tipo de monitoreo (signIn, print, share), el ID del país y el rango de fechas para la búsqueda". Dicho endpoint no pudo ser desarrollado debido a su complejidad, ya que presenté problemas relacionados a la tabla intermedia entre User y Country (_CountryToUser), lo cual me generaba errores en el código. 

Sin embargo, en la interfaz [IUserRepository](https://github.com/sant-17/PT-PrevalentWare/blob/main/src/main/java/com/prevalentware/prueba_tecnica/infrastructure/output/jpa/repository/IUserRepository.java) queda el vestigio de cuál era mi idea para el desarrollo de este endpoint:

```java
@Query("SELECT DISTINCT u " +
            "FROM User u " +
            "JOIN _CountryToUser ctu ON u.id = ctu.B " +
            "JOIN UserMonitoring um ON u.id = um.userId.id " +
            "WHERE ctu.A.id = ?1 " +
            "AND um.description = ?2 " +
            "AND um.createdAt BETWEEN ?3 AND ?4")
List<User> findUsersByUsageAndCountryAndTimeRange(String countryId, String description, LocalDateTime from, LocalDateTime to, Pageable pageable);
```

## Construyendo la imágen de Docker a partir del dockerfile
En el correo que enviaré a los email proporcionados adjuntaré el archivo .env y el comando docker run completo para correr la solución.

## Desplegando en ECS

A continuación se presentará un paso a paso de cómo se realizaría el despliegue en AWS ECS. Tener en cuenta que esta es una noción básica de cómo llevarlo a cabo en base a mis conocimientos en el tema:

- Ingresamos a la página de [AWS](https://aws.amazon.com/es/) y nos registramos y/o iniciamos sesión.
- Al iniciar sesión, en la parte superior derecha debemos asegurarnos de estar en EE.UU. Este (Norte de Virginia - us-east-1) o EE.UU. Este (Ohio - us-east-2) (ya que en estas dos regiones se maneja mejores precios que en el resto).
 - En el buscador buscamos y seleccionamos ECS. En la sección de Lanzar la instancia, le damos clic.
- Le damos un nombre.
- Seleccionamos Clusters y damos clic a Create Cluster. Le damos un nombre al cluster, seleccionamos Create VPC, ponemos todo por defecto y damos clic a Create.
- Al finalizar el lanzamiento del cluster, damos clic a View Cluster.
- Hacemos clic en Task Definitions y luego Create new Task Definition. Seleccionamos Fargate. Damos clic a siguiente.
- En Task definition name le colocamos el nombre de la aplicación, en este caso, prueba_tecnica.
- En Task role, damos clic a IAM Console. En el buscador ponemos ECS y seleccionamos AWSServiceRoleForECS y damos clic a Crear Rol.
- En tipo de entidad de confianza seleccionamos Servicio de AWS. En caso de uso buscamos Elastic Container Service y luego Elastic Container Service Task. Damos clic a Siguiente.
- En Políticas de Permisos buscamos ECS y seleccionamos AmazonECSTaskExecutionRolePolicy, le damos a siguiente.
- En Detalles del rol, asignamos un nombre del rol, por ejemplo, ECSTaskExecutionRole y damos clic en Crear rol. En la consola de IAM Management debe aparecer el rol que creamos.
- Volviendo a la ventana de Configure task and container definitions debe aparecer el rol que creamos. Lo seleccionamos.
- En Operating system family seleccionamos Linux.
- En Task size podemos seleccionar la memoria que consideramos suficiente o necesaria.
- En Container definitions damos clic a Add container. A Container name le asignamos el nombre que ya habíamos colocado, es decir, prueba_tecnica.
- En Image colocamos la ruta de la imagen que debemos tener en DockerHub
- En Memory Limit podemos otorgar la cantidad de memoria que creamos necesaria.
- En Por Mappings colocamos 8086, como el puerto del proyecto.
- En Enviroment variables podríamos colocar las variables de entorno del archivo .env que les enviaré al correo.
- En Storage and Logging verificamos que esté seleccionado Log Configuration - Auto-configure CloudWatch Logs.
- Finalizamos dando clic a Agregar.

Estoy seguro que aún faltan más pasos, pero no cuento con conocimientos suficientes para seguir con la definición del proceso de despliegue. 

##

Agradezco el tiempo que se hayan tomado para leer este documento y tenerme en cuenta en el proceso de selección.
