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
import Entities.AtletaDisciplina;
import Entities.Disciplinas;
import Entities.Institucion;
import Entities.Estado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 *
 * @author axel.medina
 */
public class AtletaDisciplinaJpaController implements Serializable {

    private static final long serialVersionUID = 2349729552551466947L;

    public AtletaDisciplinaJpaController() {
        this.utx = utx;
        this.emf = Persistence.createEntityManagerFactory("ORMOlimpEspPU");
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AtletaDisciplina atletaDisciplina) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Atleta idAtleta = atletaDisciplina.getIdAtleta();
            if (idAtleta != null) {
                idAtleta = em.getReference(idAtleta.getClass(), idAtleta.getIdAtleta());
                atletaDisciplina.setIdAtleta(idAtleta);
            }
            Disciplinas idDisciplina = atletaDisciplina.getIdDisciplina();
            if (idDisciplina != null) {
                idDisciplina = em.getReference(idDisciplina.getClass(), idDisciplina.getIdDisciplina());
                atletaDisciplina.setIdDisciplina(idDisciplina);
            }
            Institucion idIntitucion = atletaDisciplina.getIdIntitucion();
            if (idIntitucion != null) {
                idIntitucion = em.getReference(idIntitucion.getClass(), idIntitucion.getIdIntitucion());
                atletaDisciplina.setIdIntitucion(idIntitucion);
            }
            Estado idEstado = atletaDisciplina.getIdEstado();
            if (idEstado != null) {
                idEstado = em.getReference(idEstado.getClass(), idEstado.getIdEstado());
                atletaDisciplina.setIdEstado(idEstado);
            }
            em.persist(atletaDisciplina);
            if (idAtleta != null) {
                idAtleta.getAtletaDisciplinaList().add(atletaDisciplina);
                idAtleta = em.merge(idAtleta);
            }
            if (idDisciplina != null) {
                idDisciplina.getAtletaDisciplinaList().add(atletaDisciplina);
                idDisciplina = em.merge(idDisciplina);
            }
            if (idIntitucion != null) {
                idIntitucion.getAtletaDisciplinaList().add(atletaDisciplina);
                idIntitucion = em.merge(idIntitucion);
            }
            if (idEstado != null) {
                idEstado.getAtletaDisciplinaList().add(atletaDisciplina);
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

    public void edit(AtletaDisciplina atletaDisciplina) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            AtletaDisciplina persistentAtletaDisciplina = em.find(AtletaDisciplina.class, atletaDisciplina.getIdAtletaDisciplina());
            Atleta idAtletaOld = persistentAtletaDisciplina.getIdAtleta();
            Atleta idAtletaNew = atletaDisciplina.getIdAtleta();
            Disciplinas idDisciplinaOld = persistentAtletaDisciplina.getIdDisciplina();
            Disciplinas idDisciplinaNew = atletaDisciplina.getIdDisciplina();
            Institucion idIntitucionOld = persistentAtletaDisciplina.getIdIntitucion();
            Institucion idIntitucionNew = atletaDisciplina.getIdIntitucion();
            Estado idEstadoOld = persistentAtletaDisciplina.getIdEstado();
            Estado idEstadoNew = atletaDisciplina.getIdEstado();
            if (idAtletaNew != null) {
                idAtletaNew = em.getReference(idAtletaNew.getClass(), idAtletaNew.getIdAtleta());
                atletaDisciplina.setIdAtleta(idAtletaNew);
            }
            if (idDisciplinaNew != null) {
                idDisciplinaNew = em.getReference(idDisciplinaNew.getClass(), idDisciplinaNew.getIdDisciplina());
                atletaDisciplina.setIdDisciplina(idDisciplinaNew);
            }
            if (idIntitucionNew != null) {
                idIntitucionNew = em.getReference(idIntitucionNew.getClass(), idIntitucionNew.getIdIntitucion());
                atletaDisciplina.setIdIntitucion(idIntitucionNew);
            }
            if (idEstadoNew != null) {
                idEstadoNew = em.getReference(idEstadoNew.getClass(), idEstadoNew.getIdEstado());
                atletaDisciplina.setIdEstado(idEstadoNew);
            }
            atletaDisciplina = em.merge(atletaDisciplina);
            if (idAtletaOld != null && !idAtletaOld.equals(idAtletaNew)) {
                idAtletaOld.getAtletaDisciplinaList().remove(atletaDisciplina);
                idAtletaOld = em.merge(idAtletaOld);
            }
            if (idAtletaNew != null && !idAtletaNew.equals(idAtletaOld)) {
                idAtletaNew.getAtletaDisciplinaList().add(atletaDisciplina);
                idAtletaNew = em.merge(idAtletaNew);
            }
            if (idDisciplinaOld != null && !idDisciplinaOld.equals(idDisciplinaNew)) {
                idDisciplinaOld.getAtletaDisciplinaList().remove(atletaDisciplina);
                idDisciplinaOld = em.merge(idDisciplinaOld);
            }
            if (idDisciplinaNew != null && !idDisciplinaNew.equals(idDisciplinaOld)) {
                idDisciplinaNew.getAtletaDisciplinaList().add(atletaDisciplina);
                idDisciplinaNew = em.merge(idDisciplinaNew);
            }
            if (idIntitucionOld != null && !idIntitucionOld.equals(idIntitucionNew)) {
                idIntitucionOld.getAtletaDisciplinaList().remove(atletaDisciplina);
                idIntitucionOld = em.merge(idIntitucionOld);
            }
            if (idIntitucionNew != null && !idIntitucionNew.equals(idIntitucionOld)) {
                idIntitucionNew.getAtletaDisciplinaList().add(atletaDisciplina);
                idIntitucionNew = em.merge(idIntitucionNew);
            }
            if (idEstadoOld != null && !idEstadoOld.equals(idEstadoNew)) {
                idEstadoOld.getAtletaDisciplinaList().remove(atletaDisciplina);
                idEstadoOld = em.merge(idEstadoOld);
            }
            if (idEstadoNew != null && !idEstadoNew.equals(idEstadoOld)) {
                idEstadoNew.getAtletaDisciplinaList().add(atletaDisciplina);
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
                Integer id = atletaDisciplina.getIdAtletaDisciplina();
                if (findAtletaDisciplina(id) == null) {
                    throw new NonexistentEntityException("The atletaDisciplina with id " + id + " no longer exists.");
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
            AtletaDisciplina atletaDisciplina;
            try {
                atletaDisciplina = em.getReference(AtletaDisciplina.class, id);
                atletaDisciplina.getIdAtletaDisciplina();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The atletaDisciplina with id " + id + " no longer exists.", enfe);
            }
            Atleta idAtleta = atletaDisciplina.getIdAtleta();
            if (idAtleta != null) {
                idAtleta.getAtletaDisciplinaList().remove(atletaDisciplina);
                idAtleta = em.merge(idAtleta);
            }
            Disciplinas idDisciplina = atletaDisciplina.getIdDisciplina();
            if (idDisciplina != null) {
                idDisciplina.getAtletaDisciplinaList().remove(atletaDisciplina);
                idDisciplina = em.merge(idDisciplina);
            }
            Institucion idIntitucion = atletaDisciplina.getIdIntitucion();
            if (idIntitucion != null) {
                idIntitucion.getAtletaDisciplinaList().remove(atletaDisciplina);
                idIntitucion = em.merge(idIntitucion);
            }
            Estado idEstado = atletaDisciplina.getIdEstado();
            if (idEstado != null) {
                idEstado.getAtletaDisciplinaList().remove(atletaDisciplina);
                idEstado = em.merge(idEstado);
            }
            em.remove(atletaDisciplina);
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

    public List<AtletaDisciplina> findAtletaDisciplinaEntities() {
        return findAtletaDisciplinaEntities(true, -1, -1);
    }

    public List<AtletaDisciplina> findAtletaDisciplinaEntities(int maxResults, int firstResult) {
        return findAtletaDisciplinaEntities(false, maxResults, firstResult);
    }

    private List<AtletaDisciplina> findAtletaDisciplinaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AtletaDisciplina.class));
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

    public AtletaDisciplina findAtletaDisciplina(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AtletaDisciplina.class, id);
        } finally {
            em.close();
        }
    }

    public int getAtletaDisciplinaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AtletaDisciplina> rt = cq.from(AtletaDisciplina.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
