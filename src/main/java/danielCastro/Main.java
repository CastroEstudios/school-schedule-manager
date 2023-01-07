package danielCastro;

import javax.persistence.*;
import java.util.List;

public class Main {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("CRM");
    public static void main(String[] args) {
        addEspecialidad(1, "Antonio");
        getAllEspecialidad();
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
        String query = "SELECT e FROM Especialidad e WHERE e.idEspecialidad IS NOT NULL";
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
}