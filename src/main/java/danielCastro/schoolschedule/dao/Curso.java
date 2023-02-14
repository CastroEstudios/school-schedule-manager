/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danielCastro.schoolschedule.dao;

import java.io.Serializable;
/**
 *
 * @author Anima
 */
public class Curso implements Serializable {
    
    private String idCurso;
    private String turno;
    private int nivel;
    private int idCiclo;

    public Curso() {
    }

    public Curso(String idCurso, String turno, int nivel, int idCiclo) {
        this.idCurso = idCurso;
        this.turno = turno;
        this.nivel = nivel;
        this.idCiclo = idCiclo;
    }

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idcurso) {
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
        return this.getIdCurso().equals(parametreObject.getIdCurso());
    }
    
}
