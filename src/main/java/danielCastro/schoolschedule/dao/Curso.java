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
@Table(name = "CURSO")
public class Curso implements Serializable {
    
    @Id
    @Column(name = "IDCURSO")
    private String idCurso;
    @Column(name = "TURNO")
    private String turno;
    @Column(name = "NIVEL")
    private int nivel;
    @Column(name = "CICLO_IDCICLO")
    private int idCiclo;

    public Curso() {
    }

    public Curso(String idcurso, String turno, int nivel, int idCiclo) {
        this.idCurso = idcurso;
        this.turno = turno;
        this.nivel = nivel;
        this.idCiclo = idCiclo;
    }

    public String getIdcurso() {
        return idCurso;
    }

    public void setIdcurso(String idcurso) {
        this.idCurso = idcurso;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(int idCiclo) {
        this.idCiclo = idCiclo;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Curso parametreObject = (Curso) obj;
        return this.getIdcurso().equals(parametreObject.getIdcurso());
    }
    
}
