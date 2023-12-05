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
