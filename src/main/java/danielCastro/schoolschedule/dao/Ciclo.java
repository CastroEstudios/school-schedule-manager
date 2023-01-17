/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package danielCastro.schoolschedule.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Anima
 */

@Entity
@Table(name = "CICLO")
public class Ciclo {
    
    @Id
    @Column(name = "IDCICLO")
    private int idCiclo;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "NIVEL")
    private String nivel;
    @Column(name = "ESPECIALIDAD_IDESPECIALIDAD")
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
    
    
    
}
