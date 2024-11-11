package view;

import java.util.List;
import java.util.Scanner;

import controllers.LectorController;
import controllers.LibroController;
import controllers.PrestamoController;
import models.Lector;
import models.Libro;
import models.Prestamo;

public class BibliotecaMain {

	public static void main(String[] args) {
		
		//Variables de Libro
		int idLibro; String titulo; String autor; int añoPublicacion;		
		//Variables de Lector
		int idLector; String nombre; String apellido; String email;			
		//Variables de Prestamo
		int idPrestamo; String fechaPrestamo; String fechaDevolucion;		
	    //Variables generales:
		int opcion; String campo; String valor;
						
		Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("---------------------------------------------------------------");
            System.out.println("                          BIBLIOTECA                           ");
            System.out.println("---------------------------------------------------------------");
            System.out.println("1. Insertar Libro");
            System.out.println("2. Insertar Lector");
            System.out.println("3. Insertar Préstamo");
            System.out.println("4. Gestión de libros (actualizar, eliminar)");
            System.out.println("5. Gestión de lectores (actualizar, eliminar)");
            System.out.println("6. Gestión de préstamos (actualizar, eliminar)");
            System.out.println("7. Listado de Libros");
            System.out.println("8. Listado de Lectores");
            System.out.println("9. Listado de Préstamos");
            System.out.println("10. Ver Libro por ID");
            System.out.println("11. Ver Lector por ID");
            System.out.println("12. Ver Préstamo por ID");
            System.out.println("13. Libros actualmente prestados a un lector");
            System.out.println("14. Libros disponibles para préstamo");
            System.out.println("15. Historial de préstamos por lector");
            System.out.println("16. Salir");
            System.out.println("---------------------------------------------------------------");
            System.out.println("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
            case 1:           	
                System.out.println("Opción elegida: Insertar Libro");
                
                LibroController libroController = new LibroController();                                
                
                System.out.println("Ingrese el título del libro: ");
                titulo = scanner.nextLine();
                System.out.println("Ingrese el autor del libro: ");
                autor = scanner.nextLine();
                System.out.println("Ingrese el año de publicación del libro: ");
                añoPublicacion = scanner.nextInt();    
                
                String libroNuevo = libroController.createLibro(titulo, autor, añoPublicacion);
                System.out.println(libroNuevo);                                             
                
                break;
            case 2:
                System.out.println("Opción elegida: Insertar Lector");
                
                LectorController lectorController = new LectorController();
                
                System.out.println("Ingrese el nombre del lector: ");
                nombre = scanner.nextLine();
                System.out.println("Ingrese el apellido del lector: ");
                apellido = scanner.nextLine();
                System.out.println("Ingrese el email del lector: ");
                email = scanner.nextLine();
                
                String lectorNuevo = lectorController.createLector(nombre, apellido, email);
                System.out.println(lectorNuevo);                                               
                break;
            case 3:
                System.out.println("Opción elegida: Insertar Préstamo");
                
                PrestamoController prestamoController = new PrestamoController();
                
                System.out.println("Ingrese el id del Libro que desea asociar a este préstamo: ");
                idLibro = scanner.nextInt();    
                System.out.println("Ingrese el id del Lector que desea asociar a este préstamo: ");
                idLector = scanner.nextInt();    
                scanner.nextLine();
                System.out.println("Aclaración: Ahora deberás ingresar las fechas de inicio y fin del préstamo. Es importante que el formato que utilices sea YYYY-MM-DD");
                System.out.println("Ingrese la fecha de inicio del préstamo: ");
                fechaPrestamo = scanner.nextLine();
                System.out.println("Ingrese la fecha de devolución del préstamo: ");
                fechaDevolucion = scanner.nextLine();
                
                String prestamoNuevo = prestamoController.createPrestamo(idLibro, idLector, fechaPrestamo, fechaDevolucion);
                System.out.println(prestamoNuevo);
                
                break;
            case 4:
                System.out.println("Opción elegida: Gestión de libros (actualizar, eliminar)");
                
                System.out.println("Opción 1: actualizar, Opción 2: eliminar");
                
                opcion = scanner.nextInt();
                scanner.nextLine();
                
                switch(opcion) {
                
                case 1:
                	System.out.println("Opción elegida: actualizar libro)");
                	libroController = new LibroController();                  	
                	
                	System.out.println("Ingrese el id del libro que desea actualizar: ");
                    idLibro = scanner.nextInt();
                    scanner.nextLine();
                	System.out.println("Ingrese el campo que desea modificar (titulo/autor/añoPublicacion)");
                	campo = scanner.nextLine();
                	System.out.println("Ingrese el nuevo valor que desea asignar al campo seleccionado");
                	valor = scanner.nextLine();
                	
                	String libroUpdate = libroController.updateLibro(idLibro, campo, valor);
                	System.out.println(libroUpdate);
                	break;
                	
                case 2:
                	System.out.println("Opción elegida: eliminar libro)");
                	libroController = new LibroController();                  	
                	
                	System.out.println("Ingrese el id del libro que desea eliminar: ");
                    idLibro = scanner.nextInt();
                    scanner.nextLine();
                	
                	
                	String libroDelete = libroController.deleteLibro(idLibro);
                	System.out.println(libroDelete);
                	break;                                                              
                }                                                                                
                break;
            case 5:
                System.out.println("Opción elegida: Gestión de lectores (actualizar, eliminar)");
                
                System.out.println("Opción 1: actualizar, Opción 2: eliminar");
                
                opcion = scanner.nextInt();
                scanner.nextLine();
                
                switch(opcion) {
                
                case 1:
                	System.out.println("Opción elegida: actualizar lector)");
                	lectorController = new LectorController();                  	
                	
                	System.out.println("Ingrese el id del lector que desea actualizar: ");
                    idLector = scanner.nextInt();
                    scanner.nextLine();
                	System.out.println("Ingrese el campo que desea modificar (nombre/apellido/email)");
                	campo = scanner.nextLine();
                	System.out.println("Ingrese el nuevo valor que desea asignar al campo seleccionado");
                	valor = scanner.nextLine();
                	
                	String lectorUpdate = lectorController.updateLector(idLector, campo, valor);
                	System.out.println(lectorUpdate);
                	break;
                	
                case 2:
                	System.out.println("Opción elegida: eliminar lector)");
                	lectorController = new LectorController();                  	
                	
                	System.out.println("Ingrese el id del lector que desea eliminar: ");
                    idLector = scanner.nextInt();
                    scanner.nextLine();
                	
                	
                	String lectorDelete = lectorController.deleteLector(idLector);
                	System.out.println(lectorDelete);
                	break;                                                              
                }                                               
                break;
            case 6:
                System.out.println("Opción elegida: Gestión de préstamos (actualizar, eliminar)");
                
                System.out.println("Opción 1: actualizar, Opción 2: eliminar");
                
                opcion = scanner.nextInt();
                scanner.nextLine();
                
                switch(opcion) {
                
                case 1:
                	System.out.println("Opción elegida: actualizar préstamo)");
                	prestamoController = new PrestamoController();                  	
                	
                	System.out.println("Ingrese el id del préstamo que desea actualizar: ");
                    idPrestamo = scanner.nextInt();
                    scanner.nextLine();
                	System.out.println("Ingrese el campo que desea modificar (idLibro/idLector/fechaPrestamo/fechaDevolucion)");
                	System.out.println("Aclaración: Si decides actualizar las fechas de inicio y fin del préstamo, es importante que el formato que utilices sea YYYY-MM-DD");
                	campo = scanner.nextLine();
                	System.out.println("Ingrese el nuevo valor que desea asignar al campo seleccionado");
                	valor = scanner.nextLine();
                	
                	String prestamoUpdate = prestamoController.updatePrestamo(idPrestamo, campo, valor);
                	System.out.println(prestamoUpdate);
                	break;
                	
                case 2:
                	System.out.println("Opción elegida: eliminar préstamo)");
                	prestamoController = new PrestamoController();                      	
                	
                	System.out.println("Ingrese el id del préstamo que desea eliminar: ");
                    idPrestamo = scanner.nextInt();
                    scanner.nextLine();
                	
                	
                	String prestamoDelete = prestamoController.deletePrestamo(idPrestamo);
                	System.out.println(prestamoDelete);
                	break;                                                              
                }          
                break;
            case 7:
                System.out.println("Opción elegida: Listado de Libros");
                
                libroController = new LibroController();
                List<Libro> libros = libroController.getAllLibros();
                if (libros.isEmpty()) {
                    System.out.println("No hay libros listados.");
                } else {
                    System.out.println("Listado de Libros:");
                    for (Libro libro : libros) {
                        System.out.println("ID: " + libro.getId());
                        System.out.println("Título: " + libro.getTitulo());
                        System.out.println("Autor: " + libro.getAutor());
                        System.out.println("Año de publicación: " + libro.getAñoPublicacion());
                        System.out.println("Disponible: " + (libro.isDisponible() ? "Sí" : "No"));
                        System.out.println("-----------------------------");
                    }
                }               
                    
                break;
            case 8:
                System.out.println("Opción elegida: Listado de Lectores");
                
                lectorController = new LectorController();
                List<Lector> lectores = lectorController.getAllLectores();
                if (lectores.isEmpty()) {
                    System.out.println("No hay lectores listados.");
                } else {
                    System.out.println("Listado de Lectores:");
                    for (Lector lector : lectores) {
                        System.out.println("ID: " + lector.getId());
                        System.out.println("Nombre: " + lector.getNombre());
                        System.out.println("Apellido: " + lector.getApellido());
                        System.out.println("Email: " + lector.getEmail());                       
                        System.out.println("-----------------------------");
                    }
                } 
                
                break;
            case 9:
                System.out.println("Opción elegida: Listado de Préstamos");
                
                prestamoController = new PrestamoController();
                List<Prestamo> prestamos = prestamoController.getAllPrestamos();
                if (prestamos.isEmpty()) {
                    System.out.println("No hay prestamos listados.");
                } else {
                    System.out.println("Listado de Préstamos:");
                    for (Prestamo prestamo : prestamos) {
                        System.out.println("ID: " + prestamo.getId());
                        System.out.println("ID Libro: " + prestamo.getIdLibro());
                        System.out.println("ID Lector: " + prestamo.getIdLector());
                        System.out.println("Fecha de inicio: " + prestamo.getFechaPrestamo());
                        System.out.println("Fecha de devolución: " + prestamo.getFechaDevolucion());
                        System.out.println("-----------------------------");
                    }
                }                                  
                
                break;
            case 10:
                System.out.println("Opción elegida: Ver Libro por ID");
                libroController = new LibroController();   
                System.out.println("Ingrese el id del libro cuyos datos desea leer: ");
                idLibro = scanner.nextInt();
                scanner.nextLine();
                String libroRead = libroController.readLibro(idLibro);
            	System.out.println(libroRead);
                
                break;
            case 11:
                System.out.println("Opción elegida: Ver Lector por ID");
                lectorController = new LectorController();      
                System.out.println("Ingrese el id del lector cuyos datos desea leer: ");
                idLector = scanner.nextInt();
                scanner.nextLine();
                String lectorRead = lectorController.readLector(idLector);
            	System.out.println(lectorRead);
                
                break;
            case 12:
                System.out.println("Opción elegida: Ver Préstamo por ID");
                prestamoController = new PrestamoController();  
                System.out.println("Ingrese el id del préstamo cuyos datos desea leer: ");
                idPrestamo = scanner.nextInt();
                scanner.nextLine();
                String prestamoRead = prestamoController.readPrestamo(idPrestamo);
            	System.out.println(prestamoRead);                                               
                
                break;
            case 13:
                System.out.println("Opción elegida: Libros actualmente prestados a un lector");
                
                prestamoController = new PrestamoController();
                
                // Pedimos al usuario que ingrese el ID del lector
                System.out.println("Ingrese el ID del lector para ver los libros prestados:");
                idLector = scanner.nextInt();
                scanner.nextLine(); 
                
                // Llamamos al método para obtener los libros prestados al lector con el ID proporcionado
                List<Libro> librosPrestados = prestamoController.getLibrosPrestadosALector(idLector);
                
                // Imprimimos la lista de libros prestados por consola
                System.out.println("Libros actualmente prestados al lector con ID " + idLector + ":");
                for (Libro libro : librosPrestados) {
                    System.out.println(libro);
                }
                break;
            case 14:
                System.out.println("Opción elegida: Libros disponibles para préstamo");
                
                libroController = new LibroController();
                
                List<Libro> librosDisponibles = libroController.getAllLibrosDisponibles();
                
                System.out.println("Libros actualmente disponibles para préstamo:");
                for (Libro libro : librosDisponibles) {
                    System.out.println(libro);
                }
                
                break;
            case 15:
                System.out.println("Opción elegida: Historial de préstamos por lector");
                
                prestamoController = new PrestamoController();                                
                
                // Pedimos al usuario que ingrese el ID del lector
                System.out.println("Ingrese el ID del lector para ver los préstamos asociados:");
                idLector = scanner.nextInt();
                scanner.nextLine(); 
                
                List<Prestamo> prestamosPorLector = prestamoController.getHistorialPrestamosPorLector(idLector);
                
                System.out.println("Prestamos asociados al lector con id " + idLector);
                for (Prestamo prestamo : prestamosPorLector) {
                    System.out.println(prestamo);
                }               
                
                break;
            case 16:
                System.out.println("Saliendo...");
                scanner.close();
                return;
            default:
                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                break;
        }

        System.out.println("Presione Enter para volver al Menú"
        		+ "...");
        scanner.nextLine(); // Esperar a que el usuario presione Enter
    }
  }
		
		
		
	}


