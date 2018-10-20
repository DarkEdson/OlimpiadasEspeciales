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
import Entities.Atleta;
import Entities.Estado;
import Entities.Tutor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 *
 * @author axel.medina
 */
public class TutorJpaController implements Serializable {

    public TutorJpaController() {
        this.utx = utx;
        this.emf = Persistence.createEntityManagerFactory("ORMOlimpEspPU");
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tutor tutor) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Atleta idAtleta = tutor.getIdAtleta();
            if (idAtleta != null) {
                idAtleta = em.getReference(idAtleta.getClass(), idAtleta.getIdAtleta());
                tutor.setIdAtleta(idAtleta);
            }
            Estado idEstado = tutor.getIdEstado();
            if (idEstado != null) {
                idEstado = em.getReference(idEstado.getClass(), idEstado.getIdEstado());
                tutor.setIdEstado(idEstado);
            }
            em.persist(tutor);
            if (idAtleta != null) {
                idAtleta.getTutorList().add(tutor);
                idAtleta = em.merge(idAtleta);
            }
            if (idEstado != null) {
                idEstado.getTutorList().add(tutor);
                idEstado = em.merge(idEstado);
            }
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

    public void edit(Tutor tutor) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Tutor persistentTutor = em.find(Tutor.class, tutor.getIdTutor());
            Atleta idAtletaOld = persistentTutor.getIdAtleta();
            Atleta idAtletaNew = tutor.getIdAtleta();
            Estado idEstadoOld = persistentTutor.getIdEstado();
            Estado idEstadoNew = tutor.getIdEstado();
            if (idAtletaNew != null) {
                idAtletaNew = em.getReference(idAtletaNew.getClass(), idAtletaNew.getIdAtleta());
                tutor.setIdAtleta(idAtletaNew);
            }
            if (idEstadoNew != null) {
                idEstadoNew = em.getReference(idEstadoNew.getClass(), idEstadoNew.getIdEstado());
                tutor.setIdEstado(idEstadoNew);
            }
            tutor = em.merge(tutor);
            if (idAtletaOld != null && !idAtletaOld.equals(idAtletaNew)) {
                idAtletaOld.getTutorList().remove(tutor);
                idAtletaOld = em.merge(idAtletaOld);
            }
            if (idAtletaNew != null && !idAtletaNew.equals(idAtletaOld)) {
                idAtletaNew.getTutorList().add(tutor);
                idAtletaNew = em.merge(idAtletaNew);
            }
            if (idEstadoOld != null && !idEstadoOld.equals(idEstadoNew)) {
                idEstadoOld.getTutorList().remove(tutor);
                idEstadoOld = em.merge(idEstadoOld);
            }
            if (idEstadoNew != null && !idEstadoNew.equals(idEstadoOld)) {
                idEstadoNew.getTutorList().add(tutor);
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
                Integer id = tutor.getIdTutor();
                if (findTutor(id) == null) {
                    throw new NonexistentEntityException("The tutor with id " + id + " no longer exists.");
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
            Tutor tutor;
            try {
                tutor = em.getReference(Tutor.class, id);
                tutor.getIdTutor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tutor with id " + id + " no longer exists.", enfe);
            }
            Atleta idAtleta = tutor.getIdAtleta();
            if (idAtleta != null) {
                idAtleta.getTutorList().remove(tutor);
                idAtleta = em.merge(idAtleta);
            }
            Estado idEstado = tutor.getIdEstado();
            if (idEstado != null) {
                idEstado.getTutorList().remove(tutor);
                idEstado = em.merge(idEstado);
            }
            em.remove(tutor);
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

    public List<Tutor> findTutorEntities() {
        return findTutorEntities(true, -1, -1);
    }

    public List<Tutor> findTutorEntities(int maxResults, int firstResult) {
        return findTutorEntities(false, maxResults, firstResult);
    }

    private List<Tutor> findTutorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tutor.class));
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

    public Tutor findTutor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tutor.class, id);
        } finally {
            em.close();
        }
    }

    public int getTutorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tutor> rt = cq.from(Tutor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
