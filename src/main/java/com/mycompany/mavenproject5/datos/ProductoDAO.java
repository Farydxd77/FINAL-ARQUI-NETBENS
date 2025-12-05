package com.mycompany.mavenproject5.datos;

import jakarta.persistence.*;
import java.util.List;

public class ProductoDAO {
    
    private EntityManagerFactory emf;
    
    public ProductoDAO() {
        this.emf = Persistence.createEntityManagerFactory("AlumnosPU");
    }
    
    // CREATE
    public void crear(Producto producto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al crear producto: " + e.getMessage());
        } finally {
            em.close();
        }
    }
    
    // READ - Listar todos
    public List<Producto> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Producto> query = em.createQuery(
                "SELECT p FROM Producto p JOIN FETCH p.tipoProducto", Producto.class);
            return query.getResultList();
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