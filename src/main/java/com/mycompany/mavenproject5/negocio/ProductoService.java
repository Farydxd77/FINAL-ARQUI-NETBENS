package com.mycompany.mavenproject5.negocio;

import com.mycompany.mavenproject5.datos.Producto;
import com.mycompany.mavenproject5.datos.ProductoDAO;
import java.util.List;

public class ProductoService {
    
    private ProductoDAO productoDAO;
    
    public ProductoService() {
        this.productoDAO = new ProductoDAO();
    }
    
    private void validarProducto(Producto producto) throws Exception {
        if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre es obligatorio");
        }
        if (producto.getPrecio() == null) {
            throw new Exception("El precio es obligatorio");
        }
        if (producto.getTipoProducto() == null) {
            throw new Exception("Debe seleccionar un tipo de producto");
        }
    }
    
    public void crearProducto(Producto producto) throws Exception {
        validarProducto(producto);
        productoDAO.crear(producto);
    }
    
    public List<Producto> getProductos() {
        return productoDAO.listarTodos();
    }
    
    public void cerrar() {
        productoDAO.cerrar();
    }
}