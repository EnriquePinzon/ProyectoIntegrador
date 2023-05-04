# API REST: Proyecto Integrador, Mensajería Express :fax:

## Descripción del proyecto
La API REST de Mensajería Express es una aplicación basada en Spring Boot que permite a los usuarios enviar y realizar el seguimiento de paquetes. Construida con la versión 2.7.12-SNAPSHOT de Spring Boot y compatible con Java 11, esta API utiliza el protocolo HTTP para permitir la interacción con el sistema mediante endpoints establecidos.
El proyecto utiliza una base de datos MySQL, para la persistencia de datos, empleando JPA e Hibernate para el mapeo objeto-relacional. Además, cuenta con características como la integración continua mediante GitHub Actions y el despliegue en Railway.

Este proyecto de mensajería express se enfoca en brindar un sistema completo de seguimiento y gestión de envíos. Cuenta con módulos separados para Cliente, Empleado, Envío y Paquete.

El proyecto también incluye una documentación clara y concisa en Swagger, que especifica los endpoints disponibles. Para garantizar la calidad del código, se han implementado pruebas unitarias utilizando Mockito y JUnit. De igual forma se implementó una capa de seguridad para nuestra aplicación y servicios REST, con Spring Security, la cuál nos permite autorizar y autenticar usuarios, proteger endpoints y controlar el acceso a los recursos de la aplicación .
## Tecnologías
CONFIGURACIÓN & REQUISITOS:

✔ Java 11 

✔ Spring Boot '2.7.10' 

✔ JPA e Hibernate

✔ MySQL

✔ JUnit (Test de pruebas unitarias)

✔ Swagger [Documentación Swagger](https://proyectointegrador-production-8e07.up.railway.app/swagger-ui/index.html#/)

## Patrón DTO (Data Transfer Object)

Se utilizó el patrón DTO, para separar los datos que se transfieren entre las capas de la aplicación. La creación de las clases DTO, dieron su  utilidad y representación de datos, los cuales sirven para transportar los datos desde los controladores a los respectivos servicios.

## Modelo de Entidad-Relación
Diagrama generado con la base de datos en MySQL

![imagen](https://user-images.githubusercontent.com/114439510/234979645-de65ae1e-f14d-453f-8688-9b8ca1c001f3.png)

:small_blue_diamond: **Tabla cliente**: Almacena la información de todos los clientes, tales cómo: cédula, nombre, apellido, edad, correo, dirección.  
 
:small_blue_diamond: **Tabla empleados**: Almacena la información los empleados, tales cómo: cedula, nombre, apellido, celular, ciudad, correo electronico, dirección, antigüedad, tipo de sangre y tipo de empleado.

:small_blue_diamond: **Tabla envios**: Almacena la información de todos los envios, tales cómo: número de guia, ciudad de destino, ciudad origen, dirección destino, estado del envio, hora de entrega, nombre destinario, numero de la persona, valor del envio.

:small_blue_diamond: **Tabla paquetes**: Almacena la información de todos los paquetes, tales cómo: el id(identificador) del paquete, peso, tipo de paquete y valor declarado.


![imagen](https://user-images.githubusercontent.com/114439510/235053025-1b091688-9348-471f-99dd-9066b3cb274a.png)

Para el consumo, consultas, peticiones y visualización de nuestros endpoints en nuestra API REST, se utilizó la herramienta de **Insomnia**, probando todos nuestras verbos HTTP obteniendo respuestas en ejecución y tiempo real. Las cuáles están divididas y ordenadas según necesidad para realizar la petición HTTP (GET,POST, PUT, DELETE).

![imagen](https://user-images.githubusercontent.com/114439510/235062937-61f6fe66-1530-418d-9cce-93eddab307bf.png)


## Microservicios
El proyecto constaba de tres microservicios: Cliente, Empleado, Envío y Paquete. Estos microservicios se comunicaron entre sí utilizando el protocolo HTTP, lo que permitió una comunicación eficiente y escalable entre los servicios. Lo que facilitó la evolución de cada uno de los servicios de manera independiente.
__________
## :dart: Endpoints:
### Endpoint creación del cliente:
***![imagen](https://user-images.githubusercontent.com/114439510/234991150-0966b197-a91f-482d-94be-7b094dab2688.png)***

Crea un nuevo cliente en la base de datos con la información necesaria en el body de la solicitud.

Parámetros de ingreso del cliente en formato JSON:

Una vez ingresados, la API nos devolverá la creación exitosa de un cliente en formato JSON.

![imagen](https://user-images.githubusercontent.com/114439510/234988041-5793c561-ce5a-4df6-97b4-3f8df68aa9dc.png)

### Endpoint obtener informacion del cliente:
***![imagen](https://user-images.githubusercontent.com/114439510/234991271-19efd80a-902d-4cfb-ba54-898b7b92e7f0.png)***

Este endpoint permite obtener/buscar los datos de un cliente mediante la cédula, es una funcionalidad que permite traer los datos de un cliente existente en la base de datos.

![imagen](https://user-images.githubusercontent.com/114439510/234991703-00974633-1671-4bb4-ad29-9fea6358e101.png)

### Endpoint actualizar informacion del cliente:

***![imagen](https://user-images.githubusercontent.com/114439510/234992262-40e30596-00f5-43a0-99b2-3ae157766abc.png)***

Este endpoint permite actualizar los datos de un cliente es una funcionalidad que permite a los usuarios modificar la información filtrando por la cédula de un cliente existente en la base de datos.

Cliente creado anteriormente:

![imagen](https://user-images.githubusercontent.com/114439510/234993021-ad288039-c4e3-4d11-960a-036df8ecec90.png)

Cliente actualizado apartir de la cédula:

***![imagen](https://user-images.githubusercontent.com/114439510/234992857-cd073760-347f-4ee1-ba9e-88305a987b4c.png)***

### Endpoint eliminar un cliente por cédula:
![imagen](https://user-images.githubusercontent.com/114439510/234993365-23e775d9-aecd-4411-b4da-4bb8b617a59d.png)

Este endpoint permite eliminar un cliente existente en la base de datos por su cédula.

En este caso, retonará un mensaje indicando que la petición DELETE fue exitosa:

![imagen](https://user-images.githubusercontent.com/114439510/234993643-3b81ec5d-4305-4a4b-9d2b-ce8f58d477e7.png)
__________

### Endpoint creación del empleado:
***![imagen](https://user-images.githubusercontent.com/114439510/235055145-5a790ccb-60a0-47a2-90a5-e29fd429760c.png)***

Crea un nuevo empleado en la base de datos con la información necesaria en el body de la solicitud.

Parámetros de ingreso del empleado en formato JSON:

Una vez ingresados, la API nos devolverá la creación exitosa de un empleado en formato JSON.

![imagen](https://user-images.githubusercontent.com/114439510/235055275-9c9bbc99-d872-4243-96bc-d8fc5f3bddb4.png)

### Endpoint obtener informacion del empleado:
***![imagen](https://user-images.githubusercontent.com/114439510/235055707-825dbd7d-99fa-42ec-98f4-e938ece9afd6.png)***

Este endpoint permite obtener/buscar los datos de un empleado mediante la cédula, es una funcionalidad que permite traer los datos de un empleado existente en la base de datos.

![imagen](https://user-images.githubusercontent.com/114439510/235056047-03b1c09e-c23d-4718-9b07-391cb96ed28b.png)

### Endpoint actualizar informacion del empleado:

***![imagen](https://user-images.githubusercontent.com/114439510/235056410-cf34364c-6d80-4334-86ff-379db93b083b.png)***

Este endpoint permite actualizar los datos de un cliente es una funcionalidad que permite a los usuarios modificar la información filtrando por la cédula de un cliente existente en la base de datos.

Empleado creado anteriormente:

![imagen](https://user-images.githubusercontent.com/114439510/235055275-9c9bbc99-d872-4243-96bc-d8fc5f3bddb4.png)

Empleado actualizado apartir de la cédula:

***![imagen](https://user-images.githubusercontent.com/114439510/235056601-039ed29f-61e1-4eff-9670-7b81a7a06b10.png)***

### Endpoint eliminar un empleado por cédula:
![imagen](https://user-images.githubusercontent.com/114439510/235056783-f6b45e7b-33b9-4759-b16b-8dd36debd90b.png)

Este endpoint permite eliminar un empleado existente en la base de datos por su cédula.

En este caso, retonará un mensaje indicando que la petición DELETE fue exitosa:
![imagen](https://user-images.githubusercontent.com/114439510/235056811-0fde35aa-978f-40ee-968d-c36d2f10b589.png)
__________

### Endpoint creación del envío:
***![imagen](https://user-images.githubusercontent.com/114439510/235057694-0c10a88e-9265-4e14-84e3-38ac530cbe50.png)***

Una vez que un cliente desee enviar un paquete, nuestro sistema de mensajería express realiza un servicio para la gestión de los envíos, el cuál recibe como parámetros para la creación del envío y paquetes los siguientes datos:

Ejemplo de la petición:
Nota: El cliente debe estar registrado previamente en nuestro sistema para la realización del envío

![imagen](https://user-images.githubusercontent.com/114439510/235059276-6ed8af32-039f-4004-b17c-2bc0458aa33b.png)

Una vez ingresados, la API nos devolverá la creación exitosa de un envío en formato JSON, la cuál nos entregará el número de guía y un estado incial del envío que será "RECIBIDO".

![imagen](https://user-images.githubusercontent.com/114439510/235059388-4f944ef7-9f84-421c-b425-0ac2efcf3e1c.png)

### Endpoint obtener informacion del envío:
***![imagen](https://user-images.githubusercontent.com/114439510/235059610-2ceb50e2-a401-4f74-94f1-c3ca73ee9ac2.png)***

Este endpoint permite obtener/buscar los datos de el envío mediante el número de guía generado por nuestro sistema, es una funcionalidad que permite traer los datos del envío/paquete existente en la base de datos.

![imagen](https://user-images.githubusercontent.com/114439510/235059708-80cc02da-7d58-4b27-8884-270e48e47b90.png)

### Endpoint obtener envios filtrando por el estado del envío y la cédula del empleado.

***![imagen](https://user-images.githubusercontent.com/114439510/235060488-3ba8b642-52a6-4c06-949c-5209981e411d.png)***

Nos permite que el servicio de envios pueda filtrar para mostrar todos los pedidos que se encuentren en determinado estado.

![imagen](https://user-images.githubusercontent.com/114439510/235060659-b6d86690-3833-4518-a8e9-48dba72ead3e.png)


### Endpoint para actualizar el estado del paquete:

![imagen](https://user-images.githubusercontent.com/114439510/235061081-85cbb631-4857-4126-b751-8724e7d65ae8.png)

Ejemplo de la petición:

![imagen](https://user-images.githubusercontent.com/114439510/235061503-14cd4e51-e115-4f9e-a7dd-a98da45a97bd.png)

Este endpoint permite actualizar el estado de un paquete:  "RECIBDIDO" -> "EN RUTA", -> "ENTREGADO".

En este caso, actualizamos el estado del envío a "EN RUTA" y la API nos retornará cambio de estado:

![imagen](https://user-images.githubusercontent.com/114439510/235061582-b0c049ce-d094-4b4c-830f-81ced636e411.png)


## Test Unitarios:
Dentro del proyecto se implementó Mockito & JUnit en el cual se pudo verificar el correcto funcionamiento de los componetes, métodos y sistemas empleados. 

## :pushpin: CI-Integración Continua:
En el proyecto utilicé la integración continua (CI) para automatizar el proceso de construcción, prueba y despliegue de mi aplicación. Utilicé GitHub Actions para configurar y ejecutar mis flujos de trabajo de CI, lo que me permitió detectar y solucionar problemas de manera más rápida y eficiente, así como liberar nuevas versiones de mi aplicación con mayor frecuencia y confianza.
Cada vez que se realizaba una solicitud o se realizaban cambios en el código, se ejecutaba automáticamente una serie de pruebas y verificaciones de calidad, lo que me permitió identificar y solucionar errores en una etapa temprana del proceso de desarrollo.

## :pushpin: CD-Despliegue Continuo:
En mi proyecto utilicé la entrega continua (CD) para automatizar el proceso de despliegue de mi aplicación en entornos de producción. Utilicé Railway para implementar y gestionar mi infraestructura de manera sencilla y escalable.
Configuré mi flujo de trabajo de CD en GitHub Actions para que, después de que las pruebas de integración y calidad fueran exitosas, se desplegara automáticamente la aplicación en Railway.






