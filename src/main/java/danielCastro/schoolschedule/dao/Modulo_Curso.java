/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danielCastro.schoolschedule.dao;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Anima
 */
@Entity
@Table(name = "MODULO_CURSO")
public class Modulo_Curso implements Serializable {
    
    @Id
    @Column(name = "MODULO_IDMODULO")
    private int idModulo;
    @Id
    @Column(name = "CURSO_IDCURSO")
    private int idCurso;

    public Modulo_Curso() {
    }

    public Modulo_Curso(int idModulo, int idCurso) {
        this.idModulo = idModulo;
        this.idCurso = idCurso;
    }

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }
    
    
}
