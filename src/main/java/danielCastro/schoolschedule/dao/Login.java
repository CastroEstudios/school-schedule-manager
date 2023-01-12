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
@Table (name = "Login")
public class Login implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "PROFESOR_NIF")
    private String profesor_nif;
    @Column(name = "PERMISOS")
    private String permisos;
    @Column(name = "CLAVE")
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

}
