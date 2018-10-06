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
import Entities.Estado;
import Entities.AtletaDisciplina;
import Entities.Institucion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 *
 * @author axel.medina
 */
public class InstitucionJpaController implements Serializable {

    private static final long serialVersionUID = 5319174313422851404L;

    public InstitucionJpaController() {
        this.utx = utx;
        this.emf = Persistence.createEntityManagerFactory("ORMOlimpEspPU");
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Institucion institucion) throws RollbackFailureException, Exception {
        if (institucion.getAtletaDisciplinaList() == null) {
            institucion.setAtletaDisciplinaList(new ArrayList<AtletaDisciplina>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Estado idEstado = institucion.getIdEstado();
            if (idEstado != null) {
                idEstado = em.getReference(idEstado.getClass(), idEstado.getIdEstado());
                institucion.setIdEstado(idEstado);
            }
            List<AtletaDisciplina> attachedAtletaDisciplinaList = new ArrayList<AtletaDisciplina>();
            for (AtletaDisciplina atletaDisciplinaListAtletaDisciplinaToAttach : institucion.getAtletaDisciplinaList()) {
                atletaDisciplinaListAtletaDisciplinaToAttach = em.getReference(atletaDisciplinaListAtletaDisciplinaToAttach.getClass(), atletaDisciplinaListAtletaDisciplinaToAttach.getIdAtletaDisciplina());
                attachedAtletaDisciplinaList.add(atletaDisciplinaListAtletaDisciplinaToAttach);
            }
            institucion.setAtletaDisciplinaList(attachedAtletaDisciplinaList);
            em.persist(institucion);
            if (idEstado != null) {
                idEstado.getInstitucionList().add(institucion);
                idEstado = em.merge(idEstado);
            }
            for (AtletaDisciplina atletaDisciplinaListAtletaDisciplina : institucion.getAtletaDisciplinaList()) {
                Institucion oldIdIntitucionOfAtletaDisciplinaListAtletaDisciplina = atletaDisciplinaListAtletaDisciplina.getIdIntitucion();
                atletaDisciplinaListAtletaDisciplina.setIdIntitucion(institucion);
                atletaDisciplinaListAtletaDisciplina = em.merge(atletaDisciplinaListAtletaDisciplina);
                if (oldIdIntitucionOfAtletaDisciplinaListAtletaDisciplina != null) {
                    oldIdIntitucionOfAtletaDisciplinaListAtletaDisciplina.getAtletaDisciplinaList().remove(atletaDisciplinaListAtletaDisciplina);
                    oldIdIntitucionOfAtletaDisciplinaListAtletaDisciplina = em.merge(oldIdIntitucionOfAtletaDisciplinaListAtletaDisciplina);
                }
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

    public void edit(Institucion institucion) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Institucion persistentInstitucion = em.find(Institucion.class, institucion.getIdIntitucion());
            Estado idEstadoOld = persistentInstitucion.getIdEstado();
            Estado idEstadoNew = institucion.getIdEstado();
            List<AtletaDisciplina> atletaDisciplinaListOld = persistentInstitucion.getAtletaDisciplinaList();
            List<AtletaDisciplina> atletaDisciplinaListNew = institucion.getAtletaDisciplinaList();
            if (idEstadoNew != null) {
                idEstadoNew = em.getReference(idEstadoNew.getClass(), idEstadoNew.getIdEstado());
                institucion.setIdEstado(idEstadoNew);
            }
            List<AtletaDisciplina> attachedAtletaDisciplinaListNew = new ArrayList<AtletaDisciplina>();
            for (AtletaDisciplina atletaDisciplinaListNewAtletaDisciplinaToAttach : atletaDisciplinaListNew) {
                atletaDisciplinaListNewAtletaDisciplinaToAttach = em.getReference(atletaDisciplinaListNewAtletaDisciplinaToAttach.getClass(), atletaDisciplinaListNewAtletaDisciplinaToAttach.getIdAtletaDisciplina());
                attachedAtletaDisciplinaListNew.add(atletaDisciplinaListNewAtletaDisciplinaToAttach);
            }
            atletaDisciplinaListNew = attachedAtletaDisciplinaListNew;
            institucion.setAtletaDisciplinaList(atletaDisciplinaListNew);
            institucion = em.merge(institucion);
            if (idEstadoOld != null && !idEstadoOld.equals(idEstadoNew)) {
                idEstadoOld.getInstitucionList().remove(institucion);
                idEstadoOld = em.merge(idEstadoOld);
            }
            if (idEstadoNew != null && !idEstadoNew.equals(idEstadoOld)) {
                idEstadoNew.getInstitucionList().add(institucion);
                idEstadoNew = em.merge(idEstadoNew);
            }
            for (AtletaDisciplina atletaDisciplinaListOldAtletaDisciplina : atletaDisciplinaListOld) {
                if (!atletaDisciplinaListNew.contains(atletaDisciplinaListOldAtletaDisciplina)) {
                    atletaDisciplinaListOldAtletaDisciplina.setIdIntitucion(null);
                    atletaDisciplinaListOldAtletaDisciplina = em.merge(atletaDisciplinaListOldAtletaDisciplina);
                }
            }
            for (AtletaDisciplina atletaDisciplinaListNewAtletaDisciplina : atletaDisciplinaListNew) {
                if (!atletaDisciplinaListOld.contains(atletaDisciplinaListNewAtletaDisciplina)) {
                    Institucion oldIdIntitucionOfAtletaDisciplinaListNewAtletaDisciplina = atletaDisciplinaListNewAtletaDisciplina.getIdIntitucion();
                    atletaDisciplinaListNewAtletaDisciplina.setIdIntitucion(institucion);
                    atletaDisciplinaListNewAtletaDisciplina = em.merge(atletaDisciplinaListNewAtletaDisciplina);
                    if (oldIdIntitucionOfAtletaDisciplinaListNewAtletaDisciplina != null && !oldIdIntitucionOfAtletaDisciplinaListNewAtletaDisciplina.equals(institucion)) {
                        oldIdIntitucionOfAtletaDisciplinaListNewAtletaDisciplina.getAtletaDisciplinaList().remove(atletaDisciplinaListNewAtletaDisciplina);
                        oldIdIntitucionOfAtletaDisciplinaListNewAtletaDisciplina = em.merge(oldIdIntitucionOfAtletaDisciplinaListNewAtletaDisciplina);
                    }
                }
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
                Integer id = institucion.getIdIntitucion();
                if (findInstitucion(id) == null) {
                    throw new NonexistentEntityException("The institucion with id " + id + " no longer exists.");
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
            Institucion institucion;
            try {
                institucion = em.getReference(Institucion.class, id);
                institucion.getIdIntitucion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The institucion with id " + id + " no longer exists.", enfe);
            }
            Estado idEstado = institucion.getIdEstado();
            if (idEstado != null) {
                idEstado.getInstitucionList().remove(institucion);
                idEstado = em.merge(idEstado);
            }
            List<AtletaDisciplina> atletaDisciplinaList = institucion.getAtletaDisciplinaList();
            for (AtletaDisciplina atletaDisciplinaListAtletaDisciplina : atletaDisciplinaList) {
                atletaDisciplinaListAtletaDisciplina.setIdIntitucion(null);
                atletaDisciplinaListAtletaDisciplina = em.merge(atletaDisciplinaListAtletaDisciplina);
            }
            em.remove(institucion);
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

    public List<Institucion> findInstitucionEntities() {
        return findInstitucionEntities(true, -1, -1);
    }

    public List<Institucion> findInstitucionEntities(int maxResults, int firstResult) {
        return findInstitucionEntities(false, maxResults, firstResult);
    }

    private List<Institucion> findInstitucionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Institucion.class));
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

    public Institucion findInstitucion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Institucion.class, id);
        } finally {
            em.close();
        }
    }

    public int getInstitucionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Institucion> rt = cq.from(Institucion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
