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
import Entities.Departamento;
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
public class DepartamentoJpaController implements Serializable {

    public DepartamentoJpaController() {
        this.utx = utx;
        this.emf = Persistence.createEntityManagerFactory("ORMOlimpEspPU");
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Departamento departamento) throws RollbackFailureException, Exception {
        if (departamento.getAtletaList() == null) {
            departamento.setAtletaList(new ArrayList<Atleta>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Estado idEstado = departamento.getIdEstado();
            if (idEstado != null) {
                idEstado = em.getReference(idEstado.getClass(), idEstado.getIdEstado());
                departamento.setIdEstado(idEstado);
            }
            List<Atleta> attachedAtletaList = new ArrayList<Atleta>();
            for (Atleta atletaListAtletaToAttach : departamento.getAtletaList()) {
                atletaListAtletaToAttach = em.getReference(atletaListAtletaToAttach.getClass(), atletaListAtletaToAttach.getIdAtleta());
                attachedAtletaList.add(atletaListAtletaToAttach);
            }
            departamento.setAtletaList(attachedAtletaList);
            em.persist(departamento);
            if (idEstado != null) {
                idEstado.getDepartamentoList().add(departamento);
                idEstado = em.merge(idEstado);
            }
            for (Atleta atletaListAtleta : departamento.getAtletaList()) {
                Departamento oldIdDepartamentoOfAtletaListAtleta = atletaListAtleta.getIdDepartamento();
                atletaListAtleta.setIdDepartamento(departamento);
                atletaListAtleta = em.merge(atletaListAtleta);
                if (oldIdDepartamentoOfAtletaListAtleta != null) {
                    oldIdDepartamentoOfAtletaListAtleta.getAtletaList().remove(atletaListAtleta);
                    oldIdDepartamentoOfAtletaListAtleta = em.merge(oldIdDepartamentoOfAtletaListAtleta);
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

    public void edit(Departamento departamento) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Departamento persistentDepartamento = em.find(Departamento.class, departamento.getIdDepartamento());
            Estado idEstadoOld = persistentDepartamento.getIdEstado();
            Estado idEstadoNew = departamento.getIdEstado();
            List<Atleta> atletaListOld = persistentDepartamento.getAtletaList();
            List<Atleta> atletaListNew = departamento.getAtletaList();
            if (idEstadoNew != null) {
                idEstadoNew = em.getReference(idEstadoNew.getClass(), idEstadoNew.getIdEstado());
                departamento.setIdEstado(idEstadoNew);
            }
            List<Atleta> attachedAtletaListNew = new ArrayList<Atleta>();
            for (Atleta atletaListNewAtletaToAttach : atletaListNew) {
                atletaListNewAtletaToAttach = em.getReference(atletaListNewAtletaToAttach.getClass(), atletaListNewAtletaToAttach.getIdAtleta());
                attachedAtletaListNew.add(atletaListNewAtletaToAttach);
            }
            atletaListNew = attachedAtletaListNew;
            departamento.setAtletaList(atletaListNew);
            departamento = em.merge(departamento);
            if (idEstadoOld != null && !idEstadoOld.equals(idEstadoNew)) {
                idEstadoOld.getDepartamentoList().remove(departamento);
                idEstadoOld = em.merge(idEstadoOld);
            }
            if (idEstadoNew != null && !idEstadoNew.equals(idEstadoOld)) {
                idEstadoNew.getDepartamentoList().add(departamento);
                idEstadoNew = em.merge(idEstadoNew);
            }
            for (Atleta atletaListOldAtleta : atletaListOld) {
                if (!atletaListNew.contains(atletaListOldAtleta)) {
                    atletaListOldAtleta.setIdDepartamento(null);
                    atletaListOldAtleta = em.merge(atletaListOldAtleta);
                }
            }
            for (Atleta atletaListNewAtleta : atletaListNew) {
                if (!atletaListOld.contains(atletaListNewAtleta)) {
                    Departamento oldIdDepartamentoOfAtletaListNewAtleta = atletaListNewAtleta.getIdDepartamento();
                    atletaListNewAtleta.setIdDepartamento(departamento);
                    atletaListNewAtleta = em.merge(atletaListNewAtleta);
                    if (oldIdDepartamentoOfAtletaListNewAtleta != null && !oldIdDepartamentoOfAtletaListNewAtleta.equals(departamento)) {
                        oldIdDepartamentoOfAtletaListNewAtleta.getAtletaList().remove(atletaListNewAtleta);
                        oldIdDepartamentoOfAtletaListNewAtleta = em.merge(oldIdDepartamentoOfAtletaListNewAtleta);
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
                Integer id = departamento.getIdDepartamento();
                if (findDepartamento(id) == null) {
                    throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.");
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
            Departamento departamento;
            try {
                departamento = em.getReference(Departamento.class, id);
                departamento.getIdDepartamento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.", enfe);
            }
            Estado idEstado = departamento.getIdEstado();
            if (idEstado != null) {
                idEstado.getDepartamentoList().remove(departamento);
                idEstado = em.merge(idEstado);
            }
            List<Atleta> atletaList = departamento.getAtletaList();
            for (Atleta atletaListAtleta : atletaList) {
                atletaListAtleta.setIdDepartamento(null);
                atletaListAtleta = em.merge(atletaListAtleta);
            }
            em.remove(departamento);
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

    public List<Departamento> findDepartamentoEntities() {
        return findDepartamentoEntities(true, -1, -1);
    }

    public List<Departamento> findDepartamentoEntities(int maxResults, int firstResult) {
        return findDepartamentoEntities(false, maxResults, firstResult);
    }

    private List<Departamento> findDepartamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Departamento.class));
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

    public Departamento findDepartamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Departamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Departamento> rt = cq.from(Departamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
