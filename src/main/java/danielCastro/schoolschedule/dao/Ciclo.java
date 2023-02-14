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

public class Ciclo implements Serializable {
    
    private int idCiclo;
    private String nombre;
    private String nivel;
    private int idEspecialidad;

    public Ciclo() {
    }

    public Ciclo(int idCiclo, String nombre, String nivel, int idEspecialidad) {
        this.idCiclo = idCiclo;
        this.nombre = nombre;
        this.nivel = nivel;
        this.idEspecialidad = idEspecialidad;
    }

    public int getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(int idCiclo) {
        this.idCiclo = idCiclo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
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
        Ciclo parametreObject = (Ciclo) obj;
        return this.getIdCiclo() == parametreObject.getIdCiclo();
    }    
    
}
