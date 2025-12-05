package com.mycompany.mavenproject5.datos;

import jakarta.persistence.*;
import java.util.List;

public class TipoProductoDAO {
    
    private EntityManagerFactory emf;
    
    public TipoProductoDAO() {
        this.emf = Persistence.createEntityManagerFactory("AlumnosPU");
    }
    
    // CREATE
    public void crear(TipoProducto tipoProducto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(tipoProducto);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al crear tipo de producto: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    // READ - Listar todos
    public List<TipoProducto> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<TipoProducto> query = em.createQuery("SELECT t FROM TipoProducto t", TipoProducto.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    // Buscar por ID
    public TipoProducto buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(TipoProducto.class, id);
        } finally {
            em.close();
        }
    }
    
    public void cerrar() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}