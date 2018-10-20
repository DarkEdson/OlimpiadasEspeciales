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
import Entities.Atleta;
import Entities.Diagnostico;
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
public class DiagnosticoJpaController implements Serializable {

    public DiagnosticoJpaController() {
        this.utx = utx;
        this.emf = Persistence.createEntityManagerFactory("ORMOlimpEspPU");
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Diagnostico diagnostico) throws RollbackFailureException, Exception {
        if (diagnostico.getAtletaList() == null) {
            diagnostico.setAtletaList(new ArrayList<Atleta>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Estado idEstado = diagnostico.getIdEstado();
            if (idEstado != null) {
                idEstado = em.getReference(idEstado.getClass(), idEstado.getIdEstado());
                diagnostico.setIdEstado(idEstado);
            }
            List<Atleta> attachedAtletaList = new ArrayList<Atleta>();
            for (Atleta atletaListAtletaToAttach : diagnostico.getAtletaList()) {
                atletaListAtletaToAttach = em.getReference(atletaListAtletaToAttach.getClass(), atletaListAtletaToAttach.getIdAtleta());
                attachedAtletaList.add(atletaListAtletaToAttach);
            }
            diagnostico.setAtletaList(attachedAtletaList);
            em.persist(diagnostico);
            if (idEstado != null) {
                idEstado.getDiagnosticoList().add(diagnostico);
                idEstado = em.merge(idEstado);
            }
            for (Atleta atletaListAtleta : diagnostico.getAtletaList()) {
                Diagnostico oldIdDiagnosticoOfAtletaListAtleta = atletaListAtleta.getIdDiagnostico();
                atletaListAtleta.setIdDiagnostico(diagnostico);
                atletaListAtleta = em.merge(atletaListAtleta);
                if (oldIdDiagnosticoOfAtletaListAtleta != null) {
                    oldIdDiagnosticoOfAtletaListAtleta.getAtletaList().remove(atletaListAtleta);
                    oldIdDiagnosticoOfAtletaListAtleta = em.merge(oldIdDiagnosticoOfAtletaListAtleta);
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

    public void edit(Diagnostico diagnostico) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Diagnostico persistentDiagnostico = em.find(Diagnostico.class, diagnostico.getIdDiagnostico());
            Estado idEstadoOld = persistentDiagnostico.getIdEstado();
            Estado idEstadoNew = diagnostico.getIdEstado();
            List<Atleta> atletaListOld = persistentDiagnostico.getAtletaList();
            List<Atleta> atletaListNew = diagnostico.getAtletaList();
            if (idEstadoNew != null) {
                idEstadoNew = em.getReference(idEstadoNew.getClass(), idEstadoNew.getIdEstado());
                diagnostico.setIdEstado(idEstadoNew);
            }
            List<Atleta> attachedAtletaListNew = new ArrayList<Atleta>();
            for (Atleta atletaListNewAtletaToAttach : atletaListNew) {
                atletaListNewAtletaToAttach = em.getReference(atletaListNewAtletaToAttach.getClass(), atletaListNewAtletaToAttach.getIdAtleta());
                attachedAtletaListNew.add(atletaListNewAtletaToAttach);
            }
            atletaListNew = attachedAtletaListNew;
            diagnostico.setAtletaList(atletaListNew);
            diagnostico = em.merge(diagnostico);
            if (idEstadoOld != null && !idEstadoOld.equals(idEstadoNew)) {
                idEstadoOld.getDiagnosticoList().remove(diagnostico);
                idEstadoOld = em.merge(idEstadoOld);
            }
            if (idEstadoNew != null && !idEstadoNew.equals(idEstadoOld)) {
                idEstadoNew.getDiagnosticoList().add(diagnostico);
                idEstadoNew = em.merge(idEstadoNew);
            }
            for (Atleta atletaListOldAtleta : atletaListOld) {
                if (!atletaListNew.contains(atletaListOldAtleta)) {
                    atletaListOldAtleta.setIdDiagnostico(null);
                    atletaListOldAtleta = em.merge(atletaListOldAtleta);
                }
            }
            for (Atleta atletaListNewAtleta : atletaListNew) {
                if (!atletaListOld.contains(atletaListNewAtleta)) {
                    Diagnostico oldIdDiagnosticoOfAtletaListNewAtleta = atletaListNewAtleta.getIdDiagnostico();
                    atletaListNewAtleta.setIdDiagnostico(diagnostico);
                    atletaListNewAtleta = em.merge(atletaListNewAtleta);
                    if (oldIdDiagnosticoOfAtletaListNewAtleta != null && !oldIdDiagnosticoOfAtletaListNewAtleta.equals(diagnostico)) {
                        oldIdDiagnosticoOfAtletaListNewAtleta.getAtletaList().remove(atletaListNewAtleta);
                        oldIdDiagnosticoOfAtletaListNewAtleta = em.merge(oldIdDiagnosticoOfAtletaListNewAtleta);
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
                Integer id = diagnostico.getIdDiagnostico();
                if (findDiagnostico(id) == null) {
                    throw new NonexistentEntityException("The diagnostico with id " + id + " no longer exists.");
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
            Diagnostico diagnostico;
            try {
                diagnostico = em.getReference(Diagnostico.class, id);
                diagnostico.getIdDiagnostico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The diagnostico with id " + id + " no longer exists.", enfe);
            }
            Estado idEstado = diagnostico.getIdEstado();
            if (idEstado != null) {
                idEstado.getDiagnosticoList().remove(diagnostico);
                idEstado = em.merge(idEstado);
            }
            List<Atleta> atletaList = diagnostico.getAtletaList();
            for (Atleta atletaListAtleta : atletaList) {
                atletaListAtleta.setIdDiagnostico(null);
                atletaListAtleta = em.merge(atletaListAtleta);
            }
            em.remove(diagnostico);
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

    public List<Diagnostico> findDiagnosticoEntities() {
        return findDiagnosticoEntities(true, -1, -1);
    }

    public List<Diagnostico> findDiagnosticoEntities(int maxResults, int firstResult) {
        return findDiagnosticoEntities(false, maxResults, firstResult);
    }

    private List<Diagnostico> findDiagnosticoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Diagnostico.class));
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

    public Diagnostico findDiagnostico(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Diagnostico.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiagnosticoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Diagnostico> rt = cq.from(Diagnostico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
