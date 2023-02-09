package danielCastro.schoolschedule.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "Profesor")
public class Profesor {

    @Id
    @Column(name = "NIF", unique = true)
    private String nif;
    @Column(name = "Nombre", nullable = false)
    private String nombre;
    @Column(name = "Apellidos", nullable = false)
    private String apellidos;
    @Column(name = "Antiguedad", nullable = false)
    private int antiguedad;
    @Column(name = "HORASCONTRATADAS", nullable = false)
    private int horasContratadas;
    @Column(name = "Especialidad_idEspecialidad", nullable = false)
    private int idEspecialidad;

    public Profesor() {
    }

    public Profesor(String nif, String nombre, String apellidos, int antiguedad, int horasContratadas, int idEspecialidad) {
        this.nif = nif;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.antiguedad = antiguedad;
        this.horasContratadas = horasContratadas;
        this.idEspecialidad = idEspecialidad;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
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
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Profesor parametreObject = (Profesor) obj;
        return this.getNif().equals(parametreObject.getNif());
    }
    
}
