/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danielCastro.schoolschedule.dao;

import java.util.Objects;

/**
 *
 * @author 2dama
 */
public class FamiliaProfesional {
    private int idFamiliaProfesional;
    private String nombre;

    public FamiliaProfesional() {
    }

    public FamiliaProfesional(int idEspecialidad, String nombre) {
        this.idFamiliaProfesional = idEspecialidad;
        this.nombre = nombre;
    }  
    
    public int getIdFamiliaProfesional() {
        return idFamiliaProfesional;
    }
    
    public void setIdFamiliaProfesional(int idEspecialidad) {
        this.idFamiliaProfesional = idEspecialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Especialidad parametreObject = (Especialidad) obj;
        return this.getIdFamiliaProfesional() == parametreObject.getIdEspecialidad();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.idFamiliaProfesional;
        hash = 17 * hash + Objects.hashCode(this.nombre);
        return hash;
    }
}
