# API REST: Proyecto Integrador, Mensajería Express :postbox::fax:

## Descripción del proyecto
La API REST de Mensajería Express es una aplicación basada en Spring Boot que permite a los usuarios enviar y realizar el seguimiento de paquetes. Construida con la versión 2.7.12-SNAPSHOT de Spring Boot y compatible con Java 11, esta API utiliza el protocolo HTTP para permitir la interacción con el sistema mediante endpoints establecidos.
El proyecto utiliza una base de datos MySQL, para la persistencia de datos, empleando JPA e Hibernate para el mapeo objeto-relacional. Además, cuenta con características como la integración continua mediante GitHub Actions y el despliegue en Railway.

Este proyecto de mensajería express se enfoca en brindar un sistema completo de seguimiento y gestión de envíos. Cuenta con módulos separados para Cliente, Empleado, Envío y Paquete.

El proyecto también incluye una documentación clara y concisa en Swagger, que especifica los endpoints disponibles. Para garantizar la calidad del código, se han implementado pruebas unitarias utilizando Mockito y JUnit.
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

## Microservicios
El proyecto constaba de tres microservicios: Cliente, Empleado, Envío y Paquete. Estos microservicios se comunicaron entre sí utilizando el protocolo HTTP, lo que permitió una comunicación eficiente y escalable entre los servicios. Lo que facilitó la evolución de cada uno de los servicios de manera independiente.

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



