package danielCastro.schoolschedule.dao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MODULO")
public class Modulo implements Serializable {
    
    @Id
    @Column(name = "IDMODULO")
    int idModulo;
    @Column(name = "NOMBRE")
    String nombre;
    @Column(name = "HORASREALES")
    int horasReales;
    @Column(name = "HORASPONDERADAS")
    int horasPonderadas;
    @Column(name = "DESCRIPCION")
    String descripcion;
    @Column(name = "ESPECIALIDAD_IDESPECIALIDAD")
    int idEspecialidad;

    public Modulo() {
    }

    public Modulo(int idmodulo, String nombre, int horasReales, int horasPonderadas, String descripcion, int especialidad) {
        this.idModulo = idmodulo;
        this.nombre = nombre;
        this.horasReales = horasReales;
        this.horasPonderadas = horasPonderadas;
        this.descripcion = descripcion;
        this.idEspecialidad = especialidad;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idmodulo) {
        this.idModulo = idmodulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHorasReales() {
        return horasReales;
    }

    public void setHorasReales(int horasReales) {
        this.horasReales = horasReales;
    }

    public int getHorasPonderadas() {
        return horasPonderadas;
    }

    public void setHorasPonderadas(int horasPonderadas) {
        this.horasPonderadas = horasPonderadas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEspecialidad() {
        return idEspecialidad;
    }

    public void setEspecialidad(int especialidad) {
        this.idEspecialidad = especialidad;
    }
    
    public String toString() {
        return descripcion;
    }
    
}
