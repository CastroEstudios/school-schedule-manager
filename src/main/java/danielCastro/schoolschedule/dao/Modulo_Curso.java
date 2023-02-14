/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danielCastro.schoolschedule.dao;

import java.io.Serializable;
import java.util.Objects;

public class Modulo_Curso implements Serializable {
    
    private int idModulo;
    private String idCurso;

    public Modulo_Curso() {
    }

    public Modulo_Curso(int idModulo, String idCurso) {
        this.idModulo = idModulo;
        this.idCurso = idCurso;
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
        Modulo_Curso parametreObject = (Modulo_Curso) obj;
        return this.getIdModulo() == parametreObject.getIdModulo() 
                && this.getIdCurso().equals(parametreObject.getIdCurso());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idModulo, idCurso);
    }

}
