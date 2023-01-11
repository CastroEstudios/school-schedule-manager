package danielCastro;

import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.*;
import java.util.List;

public class Main {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRM");
    public static void main(String[] args) {
        addEspecialidad(1, "Administración y Gestión");
        addEspecialidad(2, "Comercio y Marketing");
        addEspecialidad(3, "Electricidad y Electrónica");
        addEspecialidad(4, "Imagen y Sonido");
        addEspecialidad(5, "Informática y Comunicaciones");
        getAllEspecialidad();
        addProfesor("Wilfredo", "Wilfredo", 2277, 26, 1);
        getAllProfesor();
        emf.close();
    }

    public static void addEspecialidad(int idEspecialidad, String nombre) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Especialidad especialidad = new Especialidad(idEspecialidad, nombre);
            em.persist(especialidad);
            et.commit();
        }catch (ConstraintViolationException cve) {
            System.out.println("Violación de clave primaria o foreign key");
            System.out.println("Compruebe que los datos no se superpongan");
            cve.printStackTrace();
        }catch (RollbackException re) {
            System.out.println("Error en la transacción");
            re.printStackTrace();
        }catch (Exception ex) {
            if(et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        }finally {
            em.close();
        }
    }
    public static void getAllEspecialidad() {
        EntityManager em = emf.createEntityManager();
        String query = "SELECT e FROM danielCastro.Especialidad e";
        TypedQuery<Especialidad> tq = em.createQuery(query, Especialidad.class);
        List<Especialidad> listaEspecialidad;
        try {
            listaEspecialidad = tq.getResultList();
            listaEspecialidad.forEach(especialidad ->System.out.println(
                    especialidad.getIdEspecialidad() + " | " + especialidad.getNombre()));
        }catch (NoResultException nrex) {
            nrex.printStackTrace();
        }finally {
            em.close();
        }
    }
    public static void addProfesor(String nombre, String apellidos, int antiguedad, int horasContratadas
            , int idEspecialidad) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = null;
        try {
            et = em.getTransaction();
            et.begin();
            Profesor profesor = new Profesor(nombre, apellidos, antiguedad, horasContratadas
            , idEspecialidad );
            em.persist(profesor);
            et.commit();
        }catch (ConstraintViolationException cve) {
            System.out.println("Violación de clave primaria o foreign key");
            System.out.println("Compruebe que los datos no se superpongan");
            cve.printStackTrace();
        }catch (RollbackException re) {
            System.out.println("Error en la transacción");
            re.printStackTrace();
        }catch (Exception ex) {
            if(et != null) {
                et.rollback();
            }
            ex.printStackTrace();
        }finally {
            em.close();
        }
    }
    public static void getAllProfesor() {
        EntityManager em = emf.createEntityManager();
        String query = "SELECT p FROM danielCastro.Profesor p";
        TypedQuery<Profesor> tq = em.createQuery(query, Profesor.class);
        List<Profesor> listaProfesor;
        try {
            listaProfesor = tq.getResultList();
            listaProfesor.forEach(profesor ->System.out.println(
                    profesor.getIdEspecialidad() + " | " + profesor.getNombre() + " | " + profesor.getApellidos() + " | "
                            + profesor.getAntiguedad() + " | " + profesor.getHorasContratadas()));
        }catch (NoResultException nrex) {
            nrex.printStackTrace();
            System.out.println("No hay profesores");
        }finally {
            em.close();
        }
    }
}