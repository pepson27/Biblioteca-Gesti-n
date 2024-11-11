package controllers;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import models.Lector;

public class LectorController {

	public String createLector(String nombre, String apellido, String email){
		
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Lector.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		try {
			
			Lector lector = new Lector(nombre, apellido, email);
			
			session.beginTransaction();
			
			session.persist(lector);
			
			session.getTransaction().commit();
			
			sessionFactory.close();
			
			return "Lector registrado";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Error al registrar lector";
		
	}
	
	public String deleteLector(int id){
		
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Lector.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		try {
						
			
			session.beginTransaction();
			
			Lector lector = session.get(Lector.class, id);
			
			session.remove(lector);
			
			session.getTransaction().commit();
			
			sessionFactory.close();
			
			return "Lector eliminado correctamente";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Error al eliminar lector";
		
	}
	
	public String updateLector(int id, String campo, String valor){
		
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Lector.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		try {
						
			
			session.beginTransaction();
			
			Lector lector = session.get(Lector.class, id);
			
			switch (campo) {
			case "nombre":
				
				lector.setNombre(valor);
				
				break;
				
			case "apellido":
				
				lector.setApellido(valor);
				
				break;		
				
			case "email":
								
				lector.setEmail(valor);
				
				break;		
								
			}						
			
			session.merge(lector);
			
			session.getTransaction().commit();
			
			sessionFactory.close();
			
			return "Lector actualizado correctamente";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Error al actualizar lector";
		
	}
	
	public String readLector(int id){
		
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Lector.class).buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		try {						
			
			session.beginTransaction();
			
			Lector lector = session.get(Lector.class, id);						
			
			session.getTransaction().commit();
			
			sessionFactory.close();
			
			return lector.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Error al obtener lector";
		
	}

	public List<Lector> getAllLectores() {
        
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Lector.class).buildSessionFactory();

        
        try (Session session = sessionFactory.openSession()) {
            
            session.beginTransaction();
            
            // Obtener todos los libros disponibles
            List<Lector> lectores = session.createQuery("FROM Lector", Lector.class).list();
            
            session.getTransaction().commit();

            return lectores;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al obtener lista de lectores");
        } finally {
            
            sessionFactory.close();
        }

        return null;
    }

	public static int getLastId() {
	    try (SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Lector.class).buildSessionFactory();
	         Session session = sessionFactory.openSession()) {
	        
	        BigInteger lastId = (BigInteger) session.createNativeQuery("SELECT MAX(id) FROM Lector", BigInteger.class).uniqueResult();
	        if (lastId != null) {
	            return lastId.intValue();
	        } else {
	            throw new IllegalStateException("No se pudo obtener el último ID de la tabla Lector");
	        }
	    } catch (HibernateException e) {
	        throw new IllegalStateException("Error al obtener el último ID de la tabla Lector", e);
	    }
	}

}