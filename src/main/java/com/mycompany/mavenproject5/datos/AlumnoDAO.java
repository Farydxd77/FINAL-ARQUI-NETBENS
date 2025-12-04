package com.mycompany.mavenproject5.datos;

import jakarta.persistence.*;
import java.util.List;

public class AlumnoDAO {
    
    private EntityManagerFactory emf;
    
    public AlumnoDAO() {
        this.emf = Persistence.createEntityManagerFactory("AlumnosPU");
    }
    
    // CREATE
    public void crear(Alumno alumno) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(alumno);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al crear alumno: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    // READ - Buscar por ID
    public Alumno buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Alumno.class, id);
        } finally {
            em.close();
        }
    }
    
    // READ - Listar todos
    public List<Alumno> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Alumno> query = em.createQuery("SELECT a FROM Alumno a", Alumno.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    // UPDATE
    public void actualizar(Alumno alumno) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(alumno);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al actualizar alumno: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    // DELETE
    public void eliminar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Alumno alumno = em.find(Alumno.class, id);
            if (alumno != null) {
                em.remove(alumno);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al eliminar alumno: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    // Cerrar el EntityManagerFactory
    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}