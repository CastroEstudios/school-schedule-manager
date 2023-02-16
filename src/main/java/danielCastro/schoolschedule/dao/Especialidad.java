package danielCastro.schoolschedule.dao;

import java.io.Serializable;
import java.util.Objects;

public class Especialidad implements Serializable {

    private int idEspecialidad;
    private int idFamiliaProfesional;
    private String nombre;

    public Especialidad() {
    }

    public Especialidad(int idEspecialidad, int idFamiliaProfesional, String nombre) {
        this.idEspecialidad = idEspecialidad;
        this.idFamiliaProfesional = idFamiliaProfesional;
        this.nombre = nombre;
    }  
    
    public int getIdEspecialidad() {
        return idEspecialidad;
    }
    
    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdFamiliaProfesional() {
        return idFamiliaProfesional;
    }

    public void setIdFamiliaProfesional(int idFamiliaProfesional) {
        this.idFamiliaProfesional = idFamiliaProfesional;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Especialidad parametreObject = (Especialidad) obj;
        return this.getIdEspecialidad() == parametreObject.getIdEspecialidad();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.idEspecialidad;
        hash = 17 * hash + Objects.hashCode(this.nombre);
        return hash;
    }
    
}
