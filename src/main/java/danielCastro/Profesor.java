package danielCastro;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "Profesor")
public class Profesor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "idProfesor", unique = true)
    private int id;
    @Column(name = "Nombre", nullable = false)
    private String nombre;
    @Column(name = "Apellidos", nullable = false)
    private String apellidos;
    @Column(name = "Antiguedad", nullable = false)
    private int antiguedad;
    @Column(name = "Horas_Contratadas", nullable = false)
    private int horasContratadas;
    @Column(name = "Especialidad_idEspecialidad", nullable = false)
    private int idEspecialidad;

    public Profesor() {
    }

    public Profesor(String nombre, String apellidos, int antiguedad, int horasContratadas, int idEspecialidad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.antiguedad = antiguedad;
        this.horasContratadas = horasContratadas;
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    public int getHorasContratadas() {
        return horasContratadas;
    }

    public void setHorasContratadas(int horasContratadas) {
        this.horasContratadas = horasContratadas;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }
}
