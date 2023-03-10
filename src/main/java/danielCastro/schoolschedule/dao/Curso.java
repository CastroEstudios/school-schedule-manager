/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danielCastro.schoolschedule.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Anima
 */
@Entity
@Table(name = "CURSO")
public class Curso {
    
    @Id @GeneratedValue
    @Column(name = "IDCURSO")
    private int idCurso;
    @Column(name = "TURNO")
    private String turno;
    @Column(name = "NIVEL")
    private int nivel;
    @Column(name = "CICLO_IDCICLO")
    private int idCiclo;

    public Curso() {
    }

    public Curso(int idcurso, String turno, int nivel, int idCiclo) {
        this.idCurso = idcurso;
        this.turno = turno;
        this.nivel = nivel;
        this.idCiclo = idCiclo;
    }

    public int getIdcurso() {
        return idCurso;
    }

    public void setIdcurso(int idcurso) {
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
    
    
}
