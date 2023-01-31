/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danielCastro.schoolschedule.dao;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Anima
 */
@Entity
@Table(name = "MODULO_CURSO")
public class Modulo_Curso implements Serializable {
    
    @Id
    @ManyToOne
    private Curso curso;
    @Id
    @ManyToOne
    private Modulo modulo;

    public Modulo_Curso() {
    }

    public Modulo_Curso(Curso curso, Modulo modulo) {
        this.curso = curso;
        this.modulo = modulo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    @Override
    public String toString() {
        return "Modulo_Curso{" + "curso=" + curso + ", modulo=" + modulo + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Modulo_Curso parametreObject = (Modulo_Curso) obj;
        return modulo.getIdModulo() == parametreObject.getModulo().getIdModulo()
                && curso.getIdCurso() == parametreObject.getCurso().getIdCurso();
    }

    @Override
    public int hashCode() {
        return Objects.hash(modulo.getIdModulo(), curso.getIdCurso());
    }

}
