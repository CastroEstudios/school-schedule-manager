package danielCastro.schoolschedule.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Especialidad")
public class Especialidad implements Serializable {

    @Id
    @Column(name = "IDESPECIALIDAD")
    private int idEspecialidad;
    @Column(name = "NOMBRE")
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
}
