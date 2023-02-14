/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danielCastro.schoolschedule.dao;

import java.io.Serializable;

public class Login implements Serializable {
    
    private String profesor_nif;
    private String permisos;
    private String clave;

    public Login() {
    }

    public Login(String profesor_nif, String permisos, String clave) {
        this.profesor_nif = profesor_nif;
        this.permisos = permisos;
        this.clave = clave;
    }

    public String getProfesor_nif() {
        return profesor_nif;
    }

    public void setProfesor_nif(String profesor_nif) {
        this.profesor_nif = profesor_nif;
    }

    public String getPermisos() {
        return permisos;
    }

    public void setPermisos(String permisos) {
        this.permisos = permisos;
    }

    public String getClave() {
        return clave;
    }

    @Override
    public String toString() {
        return profesor_nif + " | " + permisos + " | " +  clave;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Login parametreObject = (Login) obj;
        return this.getProfesor_nif().equals(parametreObject.getProfesor_nif());
    }
    
}
