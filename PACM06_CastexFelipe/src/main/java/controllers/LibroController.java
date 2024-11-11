package controllers;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import models.Libro;

public class LibroController {

	public String createLibro(String titulo, String autor, int añoPublicacion){
		
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Libro.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		try {
			
			Libro libro = new Libro(titulo, autor, añoPublicacion);
			
			session.beginTransaction();
			
			session.persist(libro);
			
			session.getTransaction().commit();
			
			sessionFactory.close();
			
			return "Libro registrado";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Error al registrar libro";
		
	}
	
	public String deleteLibro(int id){
		
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Libro.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		try {
						
			
			session.beginTransaction();
			
			Libro libro = session.get(Libro.class, id);
			
			session.remove(libro);
			
			session.getTransaction().commit();
			
			sessionFactory.close();
			
			return "Libro eliminado correctamente";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Error al eliminar libro";
		
	}
	
	public String updateLibro(int id, String campo, String valor){
		
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Libro.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		try {
						
			
			session.beginTransaction();
			
			Libro libro = session.get(Libro.class, id);
			
			switch (campo) {
			case "titulo":
				
				libro.setTitulo(valor);
				
				break;
				
			case "autor":
				
				libro.setAutor(valor);
				
				break;		
				
			case "añoPublicacion":
				
				int nuevoAño = Integer.parseInt(valor);
				libro.setAñoPublicacion(nuevoAño);
				
				break;		
				
			case "disponible":
				
				boolean disponible = Boolean.parseBoolean(valor);
				libro.setDisponible(disponible);
				
				break;		
			}						
			
			session.merge(libro);
			
			session.getTransaction().commit();
			
			sessionFactory.close();
			
			return "Libro actualizado correctamente";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Error al actualizar libro";
		
	}
	
	public String readLibro(int id){
		
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Libro.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		try {						
			
			session.beginTransaction();
			
			Libro libro = session.get(Libro.class, id);						
			
			session.getTransaction().commit();
			
			sessionFactory.close();
			
			return libro.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Error al obtener libro";
		
	}

	public List<Libro> getAllLibros() {

        
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Libro.class).buildSessionFactory();

        
        try (Session session = sessionFactory.openSession()) {
            
            session.beginTransaction();

            // Obtener todos los libros disponibles
            List<Libro> libros = session.createQuery("FROM Libro", Libro.class).list();

            
            session.getTransaction().commit();

            return libros;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al obtener lista de libros");
        } finally {
            
            sessionFactory.close();
        }

        return null;
    }

	public List<Libro> getAllLibrosDisponibles() {
        
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Libro.class).buildSessionFactory();

        
        try (Session session = sessionFactory.openSession()) {
            
            session.beginTransaction();

            // Consulta para obtener todos los libros disponibles
            List<Libro> librosDisponibles = session.createQuery("FROM Libro WHERE disponible = true", Libro.class).list();
            
            session.getTransaction().commit();

            return librosDisponibles;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al obtener lista de libros disponibles para prestamo");
        } finally {
            
            sessionFactory.close();
        }

        return null;
    }
			
	public static int getLastId() {
	    try (SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Libro.class).buildSessionFactory();
	         Session session = sessionFactory.openSession()) {
	        
	        BigInteger lastId = (BigInteger) session.createNativeQuery("SELECT MAX(id) FROM Libro", BigInteger.class).uniqueResult();
	        if (lastId != null) {
	            return lastId.intValue();
	        } else {
	            throw new IllegalStateException("No se pudo obtener el último ID de la tabla Libro");
	        }
	    } catch (HibernateException e) {
	        throw new IllegalStateException("Error al obtener el último ID de la tabla Libro", e);
	    }
	}

}
