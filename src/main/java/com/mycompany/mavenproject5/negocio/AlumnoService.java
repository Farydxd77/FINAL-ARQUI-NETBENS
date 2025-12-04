package com.mycompany.mavenproject5.negocio;

import com.mycompany.mavenproject5.datos.Alumno;
import com.mycompany.mavenproject5.datos.AlumnoDAO;
import java.util.List;

public class AlumnoService {
    
    private AlumnoDAO alumnoDAO;
    
    public AlumnoService() {
        this.alumnoDAO = new AlumnoDAO();
    }
    
    // Validar datos del alumno
    private void validarAlumno(Alumno alumno) throws Exception {
        if (alumno.getNombre() == null || alumno.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre es obligatorio");
        }
        if (alumno.getApellido() == null || alumno.getApellido().trim().isEmpty()) {
            throw new Exception("El apellido es obligatorio");
        }
        if (alumno.getMatricula() == null || alumno.getMatricula().trim().isEmpty()) {
            throw new Exception("La matrícula es obligatoria");
        }
    }
    
    public void crearAlumno(Alumno alumno) throws Exception {
        validarAlumno(alumno);
        alumnoDAO.crear(alumno);
    }
    
    public Alumno buscarAlumnoPorId(Long id) {
        return alumnoDAO.buscarPorId(id);
    }
    
    public List<Alumno> listarTodosLosAlumnos() {
        return alumnoDAO.listarTodos();
    }
    
    public void actualizarAlumno(Alumno alumno) throws Exception {
        validarAlumno(alumno);
        alumnoDAO.actualizar(alumno);
    }
    
    public void eliminarAlumno(Long id) {
        alumnoDAO.eliminar(id);
    }
    
    public void cerrar() {
        alumnoDAO.cerrar();
    }
}