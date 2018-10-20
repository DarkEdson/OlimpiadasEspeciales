package Controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ControllerCrud implements Serializable{

    private static final long serialVersionUID = 5357718476582701278L;

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORMOlimpEspPU");
    private final EntityManager manager = emf.createEntityManager();

    public void begin() {
        manager.getTransaction().begin();
    }

    public void close() {
        manager.close();
    }

    public boolean insert(Object t) {
        try {
            manager.persist(t);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        return false;
    }

    public boolean update(Object obj) {
        try {
            manager.merge(obj);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        return false;
    }

    public boolean delete(Object obj) {
        try {
            manager.remove(obj);
            manager.getTransaction().commit();
            return true;
        } catch (Exception e) {
            manager.getTransaction().rollback();
        }
        return false;
    }

    public Object select(Class T, int id) {
        try {
            Object t = manager.find(T, id);
            return t;
        } catch (Exception e) {
        }
        return null;
    }

   

}
