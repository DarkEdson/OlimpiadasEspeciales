/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Roles;
import Entities.Estado;
import Entities.Usuarios;
import static Security.getMD5.getMD5;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 *
 * @author axel.medina
 */
public class UsuariosJpaController implements Serializable {

    public UsuariosJpaController() {
        this.utx = utx;
        this.emf = Persistence.createEntityManagerFactory("ORMOlimpEspPU");
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarios usuarios) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            //utx.begin();
            em = getEntityManager();
            em.getTransaction().begin();
            Roles idRol = usuarios.getIdRol();
            if (idRol != null) {
                idRol = em.getReference(idRol.getClass(), idRol.getIdRol());
                usuarios.setIdRol(idRol);
            }
            Estado idEstado = usuarios.getIdEstado();
            if (idEstado != null) {
                idEstado = em.getReference(idEstado.getClass(), idEstado.getIdEstado());
                usuarios.setIdEstado(idEstado);
            }
            em.persist(usuarios);
            if (idRol != null) {
                idRol.getUsuariosList().add(usuarios);
                idRol = em.merge(idRol);
            }
            if (idEstado != null) {
                idEstado.getUsuariosList().add(usuarios);
                idEstado = em.merge(idEstado);
            }
            //utx.commit();
            em.getTransaction().commit();
        } catch (Exception ex) {
            try {
                //utx.rollback();
                 em.getTransaction().rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarios usuarios) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuarios persistentUsuarios = em.find(Usuarios.class, usuarios.getIdUsuario());
            Roles idRolOld = persistentUsuarios.getIdRol();
            Roles idRolNew = usuarios.getIdRol();
            Estado idEstadoOld = persistentUsuarios.getIdEstado();
            Estado idEstadoNew = usuarios.getIdEstado();
            if (idRolNew != null) {
                idRolNew = em.getReference(idRolNew.getClass(), idRolNew.getIdRol());
                usuarios.setIdRol(idRolNew);
            }
            if (idEstadoNew != null) {
                idEstadoNew = em.getReference(idEstadoNew.getClass(), idEstadoNew.getIdEstado());
                usuarios.setIdEstado(idEstadoNew);
            }
            usuarios = em.merge(usuarios);
            if (idRolOld != null && !idRolOld.equals(idRolNew)) {
                idRolOld.getUsuariosList().remove(usuarios);
                idRolOld = em.merge(idRolOld);
            }
            if (idRolNew != null && !idRolNew.equals(idRolOld)) {
                idRolNew.getUsuariosList().add(usuarios);
                idRolNew = em.merge(idRolNew);
            }
            if (idEstadoOld != null && !idEstadoOld.equals(idEstadoNew)) {
                idEstadoOld.getUsuariosList().remove(usuarios);
                idEstadoOld = em.merge(idEstadoOld);
            }
            if (idEstadoNew != null && !idEstadoNew.equals(idEstadoOld)) {
                idEstadoNew.getUsuariosList().add(usuarios);
                idEstadoNew = em.merge(idEstadoNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarios.getIdUsuario();
                if (findUsuarios(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            Roles idRol = usuarios.getIdRol();
            if (idRol != null) {
                idRol.getUsuariosList().remove(usuarios);
                idRol = em.merge(idRol);
            }
            Estado idEstado = usuarios.getIdEstado();
            if (idEstado != null) {
                idEstado.getUsuariosList().remove(usuarios);
                idEstado = em.merge(idEstado);
            }
            em.remove(usuarios);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarios.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuarios findUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
      public String getLogin(String name, String passwd) {
        EntityManager em = getEntityManager();
        boolean true_false = false;//70f4c687710d65276370d15781c4f48f
        String rol="";
        try {
            
            Query login = em.createNamedQuery("Usuarios.findByLogin", Usuarios.class);
            login.setParameter("usuario", name).setParameter("password", getMD5(passwd));
            List<Usuarios> lista = login.getResultList();
            for (Usuarios s : lista) {
                true_false = true;
                rol=s.getUsuario()+","+s.getIdRol().getIdRol();
            }
            return rol;
        } finally {
            em.close();
        }
    }
    
}
