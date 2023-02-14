package danielCastro.schoolschedule.dao;

import java.io.Serializable;

public class Profesor implements Serializable {

    private String nif;
    private String nombre;
    private String apellidos;
    private int antiguedad;
    private int horasContratadas;
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
