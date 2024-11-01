<div id="user-content-toc">
  <ul align="center">
    <summary><h1 style="display: inline-block">Creación de API REST para gestionar Polizas de Seguros --- Bootcamp BBVA</h1></summary>
  </ul>
</div>

----

**Link a guia del proyecto:** https://docs.google.com/document/d/1jmUs2T4E5qZndNxFvFz_kWWFQN-aOz5it5NREUWYqZ0/edit?usp=sharing 

**Consigna:** 

La aplicación deberá permitir las siguientes operaciones:

----

**1. Crear nueva póliza de seguro:**

  ➜ 📍 Método HTTP: POST

  ➜ Ruta de la API: /polizas

  ➜ Descripción: Este endpoint permitirá a los usuarios crear una nueva póliza de seguro proporcionando los siguientes datos en el cuerpo de la solicitud:
    
  ↪ Tipo de seguro (auto, inmueble, celular)
    
  ↪ Fecha de inicio y vencimiento de la póliza

  ↪ Monto asegurado

  ↪ Detalles adicionales según el tipo de seguro (marca y modelo del auto, dirección del inmueble, modelo del celular, etc.)

**2. Consultar pólizas de seguro:**

  ➜ 📍 Método HTTP: GET

  ➜ Ruta de la API: /polizas

  ➜ Descripción: Este endpoint devolverá una lista de pólizas de seguro existentes, con información detallada sobre cada una, incluyendo tipo de seguro, fecha de inicio y vencimiento, monto asegurado, etc.

**3. Actualizar póliza de seguro:**

  ➜ 📍 Método HTTP: PUT
  
  ➜ Ruta de la API: /polizas/{id}
  
  ➜ Descripción: Este endpoint permitirá a los usuarios actualizar la información de una póliza de seguro existente identificada por su ID.Se deberán enviar en el cuerpo de la solicitud los datos actualizados,
  como la fecha de inicio y vencimiento, el monto asegurado, etc.

**4. Eliminar póliza de seguro**

  ➜ 📍 Método HTTP: DELETE
  
  ➜ Ruta de la API: /polizas/{id}

  ➜ Descripción: Este endpoint permitirá a los usuarios eliminar una póliza de seguro existente identificada por su ID.
 
  ----
  
**⚙️Requerimientos técnicos:**
  
  ✓ Lenguaje de Programación: Utilizar Java como lenguaje de programación principal para el desarrollo del backend.
  
  ✓ Emplear Spring Framework para construir la aplicación backend.

  ✓ Aplicar la arquitectura REST y utilizar estereotipos de Spring como @RestController, @Service, @Repository.

  ✓ Implementar inyección de dependencias para gestionar las instancias de los componentes.

  ✓ Dividir en capas la solución: Dividir la aplicación en capas para una mejor organización y mantenimiento del código. Las posibles capas podrían
  incluir:
  
  ↪ API RESTful
  
  ↪ Servicios

  ↪ Persistencia
  
  ↪ Repositorios (Repositories)

  ↪ Modelos (Entities/Models)

  ↪ Seguridad de la API

  ↪ DTOs (Objetos de Transferencia de Datos)
  
  ↪ Manejo de Excepciones
  
  ↪ Pruebas Unitarias
  
  ✓ Aplicar los principios SOLID y patrones de diseño para asegurar un código limpio, mantenible y escalable.
  
  ✓ Seguir la arquitectura MVC (Modelo-Vista-Controlador) como guía para estructurar el backend.

  ✓ Manejo de Errores: Implementar un manejo adecuado de errores y excepciones en la aplicación backend para proporcionar respuestas claras y significativas en caso de errores durante las operaciones.

  ✓ Pruebas Unitarias: Escribir pruebas unitarias para validar el comportamiento de los controladores, servicios y repositorios utilizando JUnit y Mockito u otra biblioteca de pruebas unitarias para Java.

**Interacción con Base de Datos:**
  
  ✓ Utilizar JDBC para conexiones directas a la base de datos cuando sea necesario.
  
  ✓ Emplear JPA (Java Persistence API) y Hibernate ORM para la persistencia y manipulación de datos.
  
  ✓ Configurar entidades y relaciones utilizando anotaciones como @Entity, @Table, @Id, @OneToMany, @ManyToOne, etc.

----

**🎁Bonus:**
**Envío de Correos Electrónicos:**
  
  ● Implementar un sistema para enviar notificaciones por correo electrónico al cliente y al equipo interno cuando se cree, actualice o elimine una póliza.

  ● Utilizar JavaMailSender u otra biblioteca para el envío de correos. Integración con Bases de Datos Populares: Utilizar MySQL, PostgreSQL u otro sistema de gestión de bases de datos relacional.

  ● Configurar el DataSource y las propiedades de conexión en el archivo application.properties o application.yml.

**Optimización y Buenas Prácticas:**
  
  ● Implementar caché para mejorar el rendimiento en consultas frecuentes.
  
  ● Utilizar logs para el seguimiento y depuración de la aplicación mediante SLF4J y Logback.
  
  ● Aplicar métricas y monitoreo utilizando herramientas como Spring Actuator.
  ----
