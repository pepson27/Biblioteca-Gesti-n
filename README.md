# PACM06_CastexFelipe
Aplicación para gestión de Biblioteca

PAC Actividad de desarrollo - Acceso a datos
Alumno: Felipe Castex Mosquera

Lenguaje y entorno de desarrollo:
Proyecto desarrollado con Java utilizando el IDE Eclipse.

Frameworks utilizados: 
Hibernate: Mapeo objeto-relacional (ORM) para Java.
Mockito: Mocking para pruebas unitarias en Java. Estos mocks se utilizan para simular el comportamiento de objetos reales en el entorno de prueba. Concretamente utilizado en la clase PrestamoControllerTest.

En qué consiste el proyecto:
Se trata de una aplicación para gestionar una biblioteca.
El usuario podrá gestionar operaciones CRUD (create, read, update, delete) con los libros, lectores y prestamos de la base de datos. Esto lo hará mediante una interfaz de usuario que le permitirá escoger qué operación desea realizar.
La interfaz será la siguiente:
```
--------------------------------------------------             
                   BIBLIOTECA                   
--------------------------------------------------
1. Insertar Libro
2. Insertar Lector
3. Insertar Préstamo
4. Gestión de libros (actualizar, eliminar)
5. Gestión de lectores (actualizar, eliminar)
6. Gestión de préstamos (actualizar, eliminar)
7. Listado de Libros
8. Listado de Lectores
9. Listado de Préstamos
10. Ver Libro por ID
11. Ver Lector por ID
12. Ver Préstamo por ID
13. Libros actualmente prestados a un lector
14. Libros disponibles para préstamo
15. Historial de préstamos por lector
16. Salir
--------------------------------------------------
```

Explicación de clases:

Carpeta src/main/java:
Tenemos una base de datos MySQL con las tablas Libro, Lector y Prestamo que están asociadas a las clases Libro, Lector y Prestamo de Java mediante el mapeo que realizamos con Hibernate. 
Estas clases las he creado dentro de un paquete que he llamado "models".

Por otro lado tenemos las clases LibroController, LectorController y PrestamoController que se encargan de gestionar todos los métodos de CRUD que involucran a las entidades Libro, Lector y Prestamo.
Estas clases las he creado dentro de un paquete que he llamado "controllers".

Por último tenemos la clase BibliotecaMain, que muestra la interfaz de usuario y hace las llamadas a los métodos de las clases del paquete controllers para poder gestionar lo que el usuario desee.
Esta clase la he creado dentro de un paquete que he llamado "view".


Explicación de las pruebas unitarias:

Carpeta src/test/java:
Paquete controllers: Dentro de esta carpeta he creado un paquete llamado controllers, ya que vamos a testear todos los métodos CRUD que se encuentran en las clases LibroController, LectorController y PrestamoController.
Dentro este paquete he creado las clases LibroControllerTest, LectorControllerTest y PrestamoControllerTest.
Estas clases la he creado de la siguiente forma: New - JUnit Test Case.
Por último he programado los tests unitarios para cada uno de los métodos de LibroController, LectorController y PrestamoController, que son los que posibilitan la interacción entre el usuario y la base de datos.

Conexión a la base de datos:
Nombre: biblioteca_pac
Usuario: root
Contraseña: No tiene contraseña
La conexión a la base de datos la he realizado con el software XAMPP, dandole a Start en el módulo Apache y MySQL. La base de datos le he visualizado buscando localhost/phpmyadmin en google.

En este zip adjuntaré:
1. El proyecto completo de java para ser importado en eclipse
2. El archivo SQL de la base de datos creada para este proyecto.
