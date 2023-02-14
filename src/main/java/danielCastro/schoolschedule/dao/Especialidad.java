package danielCastro.schoolschedule.dao;

import java.io.Serializable;

public class Especialidad implements Serializable {

    private int idEspecialidad;
    private String nombre;

    public Especialidad() {
    }

    public Especialidad(int idEspecialidad, String nombre) {
        this.idEspecialidad = idEspecialidad;
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
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Especialidad parametreObject = (Especialidad) obj;
        return this.getIdEspecialidad() == parametreObject.getIdEspecialidad();
    }
    
}
