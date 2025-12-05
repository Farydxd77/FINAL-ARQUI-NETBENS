package com.mycompany.mavenproject5.negocio;

import com.mycompany.mavenproject5.datos.TipoProducto;
import com.mycompany.mavenproject5.datos.TipoProductoDAO;
import java.util.List;

public class TipoProductoService {
    
    private TipoProductoDAO tipoProductoDAO;
    
    public TipoProductoService() {
        this.tipoProductoDAO = new TipoProductoDAO();
    }
    
    private void validarTipoProducto(TipoProducto tipoProducto) throws Exception {
        if (tipoProducto.getDescripcion() == null || tipoProducto.getDescripcion().trim().isEmpty()) {
            throw new Exception("La descripción es obligatoria");
        }
    }
    
    public void crearTipoProducto(TipoProducto tipoProducto) throws Exception {
        validarTipoProducto(tipoProducto);
        tipoProductoDAO.crear(tipoProducto);
    }
    
    public List<TipoProducto> getTipoProductos() {
        return tipoProductoDAO.listarTodos();
    }
    
    public TipoProducto buscarPorId(Long id) {
        return tipoProductoDAO.buscarPorId(id);
    }
    
    public void cerrar() {
        tipoProductoDAO.cerrar();
    }
}