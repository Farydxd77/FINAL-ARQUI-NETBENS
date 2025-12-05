package com.mycompany.mavenproject5.datos;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipo_producto")
public class TipoProducto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String descripcion;
    
    @OneToMany(mappedBy = "tipoProducto", cascade = CascadeType.ALL)
    private List<Producto> productos;

    // Constructores
    public TipoProducto() {
    }

    public TipoProducto(String descripcion) {
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}