/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danielCastro.schoolschedule.dao;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Anima
 */
public class Modulo_Profesor implements Serializable {
    
    private String profesor_nif;
    private int idModulo;
    private String idCurso;

    public Modulo_Profesor() {
    }

    public Modulo_Profesor(String profesor_nif, int idModulo, String idCurso) {
        this.profesor_nif = profesor_nif;
        this.idModulo = idModulo;
        this.idCurso = idCurso;
    }
    
    public String getProfesor_nif() {
        return profesor_nif;
    }

    public void setProfesor_nif(String profesor_nif) {
        this.profesor_nif = profesor_nif;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Modulo_Profesor parametreObject = (Modulo_Profesor) obj;
        return this.getIdModulo() == parametreObject.getIdModulo() 
                && this.getIdCurso().equals(parametreObject.getIdCurso())
                && this.getProfesor_nif().equals(parametreObject.getProfesor_nif());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idModulo, idCurso);
    }
}
