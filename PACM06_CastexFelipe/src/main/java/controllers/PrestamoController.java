package controllers;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import models.Libro;
import models.Prestamo;

public class PrestamoController {

	public String createPrestamo(int idLibro, int idLector, String fechaPrestamo, String fechaDevolucion){
		
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Prestamo.class).addAnnotatedClass(Libro.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		try {
			
			Libro libro = session.get(Libro.class, idLibro);
			
			if(!libro.isDisponible()) {
				
				return "El libro que has intentado insertar ya pertenece a otro préstamo";
				
			}
			
			Prestamo prestamo = new Prestamo(idLibro, idLector, fechaPrestamo, fechaDevolucion);
									
			session.beginTransaction();
			
			session.persist(prestamo);
			
			session.getTransaction().commit();						
			
			
			
			session.beginTransaction();
			
			libro.setDisponible(false);
			
			session.merge(libro);			
			
			session.getTransaction().commit();
			
			sessionFactory.close();						
			
			return "Prestamo registrado";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Error al registrar prestamo";
		
	}
	
	public String deletePrestamo(int id){
		
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Prestamo.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		try {
									
			session.beginTransaction();
			
			Prestamo prestamo = session.get(Prestamo.class, id);
			
			session.remove(prestamo);
			
			session.getTransaction().commit();
			
			sessionFactory.close();
			
			return "Prestamo eliminado correctamente";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Error al eliminar prestamo";
		
	}
	
	public String updatePrestamo(int id, String campo, String valor){
		
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Prestamo.class).addAnnotatedClass(Libro.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		try {
						
			
			session.beginTransaction();
			
			Prestamo prestamo = session.get(Prestamo.class, id);
			
			int idLibro = prestamo.getIdLibro();
			
			switch (campo) {
			case "idLibro":
				
				Libro libroNuevo = session.get(Libro.class, valor);								
				
				if(!libroNuevo.isDisponible()) {
					
					return "El libro que has intentado insertar ya pertenece a otro prestamo";
					
				}
				
				int nuevoidLibro = Integer.parseInt(valor);
				prestamo.setIdLibro(nuevoidLibro);
				
				Libro libroAnterior = session.get(Libro.class, idLibro);
				libroAnterior.setDisponible(true);
				
				
				libroNuevo.setDisponible(false);
				
				break;
				
			case "idLector":
				
				int nuevoidLector = Integer.parseInt(valor);
				prestamo.setIdLector(nuevoidLector);
				
				break;
				
			case "fechaPrestamo":
				
				prestamo.setFechaPrestamo(valor);
				
				break;		
				
			case "fechaDevolucion":
								
				prestamo.setFechaDevolucion(valor);
								
				
				break;		
								
			}						
			
			session.merge(prestamo);
			
			session.getTransaction().commit();
			
			sessionFactory.close();
			
			return "Prestamo actualizado correctamente";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Error al actualizar préstamo";
		
	}
	
	public String readPrestamo(int id){
		
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Prestamo.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		try {						
			
			session.beginTransaction();
			
			Prestamo prestamo = session.get(Prestamo.class, id);						
			
			session.getTransaction().commit();
			
			sessionFactory.close();
			
			return prestamo.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Error al obtener prestamo";
		
	}

	public List<Prestamo> getAllPrestamos() {

        
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Prestamo.class).buildSessionFactory();

        
        try (Session session = sessionFactory.openSession()) {
            
            session.beginTransaction();

            // Obtener todos los libros disponibles
            List<Prestamo> prestamos = session.createQuery("FROM Prestamo", Prestamo.class).list();
            
            session.getTransaction().commit();

            return prestamos;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al obtener lista de prestamos");
        } finally {
            
            sessionFactory.close();
        }

        return null;
    }

	public List<Libro> getLibrosPrestadosALector(int idLector) {
	    
	    SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Prestamo.class).addAnnotatedClass(Libro.class).buildSessionFactory();

	    // Crear una lista para almacenar los libros prestados al lector
	    List<Libro> librosPrestados = new ArrayList<>();

	    
	    try (Session session = sessionFactory.openSession()) {
	        
	        session.beginTransaction();
	        
	        // Obtener la fecha actual
	        LocalDate fechaActual = LocalDate.now();
	        String fechaActualString = fechaActual.toString();
	        	        

	        // Consulta para obtener los préstamos asociados al ID del lector
	        Query<Prestamo> query = session.createQuery("FROM Prestamo WHERE idLector = :idLector AND fechaPrestamo < :fechaActualString AND fechaDevolucion > :fechaActualString", Prestamo.class);
	        query.setParameter("idLector", idLector);
	        query.setParameter("fechaActualString", fechaActualString);

	        // Obtener la lista de préstamos para el lector dado
	        List<Prestamo> prestamos = query.list();

	        // Iterar sobre cada préstamo y obtener el libro asociado
	        for (Prestamo prestamo : prestamos) {
	            Libro libro = session.get(Libro.class, prestamo.getIdLibro());
	            librosPrestados.add(libro);
	        }
	        
	        session.getTransaction().commit();

	        return librosPrestados;
	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Error al obtener lista de libros prestados al lector");
	    } finally {
	        
	        sessionFactory.close();
	    }

	    return null;
	}

	public List<Prestamo> getHistorialPrestamosPorLector(int idLector) {
        
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Prestamo.class).buildSessionFactory();

        
        try (Session session = sessionFactory.openSession()) {
            
            session.beginTransaction();

            // Consulta para obtener todos los préstamos asociados al lector con el ID proporcionado
            List<Prestamo> historialPrestamos = session.createQuery("FROM Prestamo WHERE idLector = :idLector", Prestamo.class)
                    .setParameter("idLector", idLector)
                    .list();
            
            session.getTransaction().commit();

            return historialPrestamos;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al obtener historial de prestamos por lector");
        } finally {
            
            sessionFactory.close();
        }

        return null;
    }

	public static int getLastId() {
	    try (SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Prestamo.class).buildSessionFactory();
	         Session session = sessionFactory.openSession()) {
	        
	        BigInteger lastId = (BigInteger) session.createNativeQuery("SELECT MAX(id) FROM Prestamo", BigInteger.class).uniqueResult();
	        if (lastId != null) {
	            return lastId.intValue();
	        } else {
	            throw new IllegalStateException("No se pudo obtener el último ID de la tabla Prestamo");
	        }
	    } catch (HibernateException e) {
	        throw new IllegalStateException("Error al obtener el último ID de la tabla Prestamo", e);
	    }
	}

	@SuppressWarnings("unused")
	private SessionFactory sessionFactory;

	public void init(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}