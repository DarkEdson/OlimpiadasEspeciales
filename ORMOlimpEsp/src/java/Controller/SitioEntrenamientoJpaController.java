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
import Entities.SitioEntrenamiento;
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
public class SitioEntrenamientoJpaController implements Serializable {

    private static final long serialVersionUID = 8148416024540607493L;

    public SitioEntrenamientoJpaController() {
        this.utx = utx;
        this.emf = Persistence.createEntityManagerFactory("ORMOlimpEspPU");
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SitioEntrenamiento sitioEntrenamiento) throws RollbackFailureException, Exception {
        if (sitioEntrenamiento.getAtletaList() == null) {
            sitioEntrenamiento.setAtletaList(new ArrayList<Atleta>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Estado idEstado = sitioEntrenamiento.getIdEstado();
            if (idEstado != null) {
                idEstado = em.getReference(idEstado.getClass(), idEstado.getIdEstado());
                sitioEntrenamiento.setIdEstado(idEstado);
            }
            List<Atleta> attachedAtletaList = new ArrayList<Atleta>();
            for (Atleta atletaListAtletaToAttach : sitioEntrenamiento.getAtletaList()) {
                atletaListAtletaToAttach = em.getReference(atletaListAtletaToAttach.getClass(), atletaListAtletaToAttach.getIdAtleta());
                attachedAtletaList.add(atletaListAtletaToAttach);
            }
            sitioEntrenamiento.setAtletaList(attachedAtletaList);
            em.persist(sitioEntrenamiento);
            if (idEstado != null) {
                idEstado.getSitioEntrenamientoList().add(sitioEntrenamiento);
                idEstado = em.merge(idEstado);
            }
            for (Atleta atletaListAtleta : sitioEntrenamiento.getAtletaList()) {
                SitioEntrenamiento oldIdSitioEntrenamientoOfAtletaListAtleta = atletaListAtleta.getIdSitioEntrenamiento();
                atletaListAtleta.setIdSitioEntrenamiento(sitioEntrenamiento);
                atletaListAtleta = em.merge(atletaListAtleta);
                if (oldIdSitioEntrenamientoOfAtletaListAtleta != null) {
                    oldIdSitioEntrenamientoOfAtletaListAtleta.getAtletaList().remove(atletaListAtleta);
                    oldIdSitioEntrenamientoOfAtletaListAtleta = em.merge(oldIdSitioEntrenamientoOfAtletaListAtleta);
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

    public void edit(SitioEntrenamiento sitioEntrenamiento) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            SitioEntrenamiento persistentSitioEntrenamiento = em.find(SitioEntrenamiento.class, sitioEntrenamiento.getIdSitioEntrenamiento());
            Estado idEstadoOld = persistentSitioEntrenamiento.getIdEstado();
            Estado idEstadoNew = sitioEntrenamiento.getIdEstado();
            List<Atleta> atletaListOld = persistentSitioEntrenamiento.getAtletaList();
            List<Atleta> atletaListNew = sitioEntrenamiento.getAtletaList();
            if (idEstadoNew != null) {
                idEstadoNew = em.getReference(idEstadoNew.getClass(), idEstadoNew.getIdEstado());
                sitioEntrenamiento.setIdEstado(idEstadoNew);
            }
            List<Atleta> attachedAtletaListNew = new ArrayList<Atleta>();
            for (Atleta atletaListNewAtletaToAttach : atletaListNew) {
                atletaListNewAtletaToAttach = em.getReference(atletaListNewAtletaToAttach.getClass(), atletaListNewAtletaToAttach.getIdAtleta());
                attachedAtletaListNew.add(atletaListNewAtletaToAttach);
            }
            atletaListNew = attachedAtletaListNew;
            sitioEntrenamiento.setAtletaList(atletaListNew);
            sitioEntrenamiento = em.merge(sitioEntrenamiento);
            if (idEstadoOld != null && !idEstadoOld.equals(idEstadoNew)) {
                idEstadoOld.getSitioEntrenamientoList().remove(sitioEntrenamiento);
                idEstadoOld = em.merge(idEstadoOld);
            }
            if (idEstadoNew != null && !idEstadoNew.equals(idEstadoOld)) {
                idEstadoNew.getSitioEntrenamientoList().add(sitioEntrenamiento);
                idEstadoNew = em.merge(idEstadoNew);
            }
            for (Atleta atletaListOldAtleta : atletaListOld) {
                if (!atletaListNew.contains(atletaListOldAtleta)) {
                    atletaListOldAtleta.setIdSitioEntrenamiento(null);
                    atletaListOldAtleta = em.merge(atletaListOldAtleta);
                }
            }
            for (Atleta atletaListNewAtleta : atletaListNew) {
                if (!atletaListOld.contains(atletaListNewAtleta)) {
                    SitioEntrenamiento oldIdSitioEntrenamientoOfAtletaListNewAtleta = atletaListNewAtleta.getIdSitioEntrenamiento();
                    atletaListNewAtleta.setIdSitioEntrenamiento(sitioEntrenamiento);
                    atletaListNewAtleta = em.merge(atletaListNewAtleta);
                    if (oldIdSitioEntrenamientoOfAtletaListNewAtleta != null && !oldIdSitioEntrenamientoOfAtletaListNewAtleta.equals(sitioEntrenamiento)) {
                        oldIdSitioEntrenamientoOfAtletaListNewAtleta.getAtletaList().remove(atletaListNewAtleta);
                        oldIdSitioEntrenamientoOfAtletaListNewAtleta = em.merge(oldIdSitioEntrenamientoOfAtletaListNewAtleta);
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
                Integer id = sitioEntrenamiento.getIdSitioEntrenamiento();
                if (findSitioEntrenamiento(id) == null) {
                    throw new NonexistentEntityException("The sitioEntrenamiento with id " + id + " no longer exists.");
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
            SitioEntrenamiento sitioEntrenamiento;
            try {
                sitioEntrenamiento = em.getReference(SitioEntrenamiento.class, id);
                sitioEntrenamiento.getIdSitioEntrenamiento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sitioEntrenamiento with id " + id + " no longer exists.", enfe);
            }
            Estado idEstado = sitioEntrenamiento.getIdEstado();
            if (idEstado != null) {
                idEstado.getSitioEntrenamientoList().remove(sitioEntrenamiento);
                idEstado = em.merge(idEstado);
            }
            List<Atleta> atletaList = sitioEntrenamiento.getAtletaList();
            for (Atleta atletaListAtleta : atletaList) {
                atletaListAtleta.setIdSitioEntrenamiento(null);
                atletaListAtleta = em.merge(atletaListAtleta);
            }
            em.remove(sitioEntrenamiento);
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

    public List<SitioEntrenamiento> findSitioEntrenamientoEntities() {
        return findSitioEntrenamientoEntities(true, -1, -1);
    }

    public List<SitioEntrenamiento> findSitioEntrenamientoEntities(int maxResults, int firstResult) {
        return findSitioEntrenamientoEntities(false, maxResults, firstResult);
    }

    private List<SitioEntrenamiento> findSitioEntrenamientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SitioEntrenamiento.class));
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

    public SitioEntrenamiento findSitioEntrenamiento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SitioEntrenamiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getSitioEntrenamientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SitioEntrenamiento> rt = cq.from(SitioEntrenamiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
