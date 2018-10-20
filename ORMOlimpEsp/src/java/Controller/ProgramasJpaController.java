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
import Entities.Programas;
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
public class ProgramasJpaController implements Serializable {

    public ProgramasJpaController() {
        this.utx = utx;
        this.emf = Persistence.createEntityManagerFactory("ORMOlimpEspPU");
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programas programas) throws RollbackFailureException, Exception {
        if (programas.getAtletaDisciplinaList() == null) {
            programas.setAtletaDisciplinaList(new ArrayList<AtletaDisciplina>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Estado idEstado = programas.getIdEstado();
            if (idEstado != null) {
                idEstado = em.getReference(idEstado.getClass(), idEstado.getIdEstado());
                programas.setIdEstado(idEstado);
            }
            List<AtletaDisciplina> attachedAtletaDisciplinaList = new ArrayList<AtletaDisciplina>();
            for (AtletaDisciplina atletaDisciplinaListAtletaDisciplinaToAttach : programas.getAtletaDisciplinaList()) {
                atletaDisciplinaListAtletaDisciplinaToAttach = em.getReference(atletaDisciplinaListAtletaDisciplinaToAttach.getClass(), atletaDisciplinaListAtletaDisciplinaToAttach.getIdAtletaDisciplina());
                attachedAtletaDisciplinaList.add(atletaDisciplinaListAtletaDisciplinaToAttach);
            }
            programas.setAtletaDisciplinaList(attachedAtletaDisciplinaList);
            em.persist(programas);
            if (idEstado != null) {
                idEstado.getProgramasList().add(programas);
                idEstado = em.merge(idEstado);
            }
            for (AtletaDisciplina atletaDisciplinaListAtletaDisciplina : programas.getAtletaDisciplinaList()) {
                Programas oldIdProgramaOfAtletaDisciplinaListAtletaDisciplina = atletaDisciplinaListAtletaDisciplina.getIdPrograma();
                atletaDisciplinaListAtletaDisciplina.setIdPrograma(programas);
                atletaDisciplinaListAtletaDisciplina = em.merge(atletaDisciplinaListAtletaDisciplina);
                if (oldIdProgramaOfAtletaDisciplinaListAtletaDisciplina != null) {
                    oldIdProgramaOfAtletaDisciplinaListAtletaDisciplina.getAtletaDisciplinaList().remove(atletaDisciplinaListAtletaDisciplina);
                    oldIdProgramaOfAtletaDisciplinaListAtletaDisciplina = em.merge(oldIdProgramaOfAtletaDisciplinaListAtletaDisciplina);
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

    public void edit(Programas programas) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Programas persistentProgramas = em.find(Programas.class, programas.getIdPrograma());
            Estado idEstadoOld = persistentProgramas.getIdEstado();
            Estado idEstadoNew = programas.getIdEstado();
            List<AtletaDisciplina> atletaDisciplinaListOld = persistentProgramas.getAtletaDisciplinaList();
            List<AtletaDisciplina> atletaDisciplinaListNew = programas.getAtletaDisciplinaList();
            if (idEstadoNew != null) {
                idEstadoNew = em.getReference(idEstadoNew.getClass(), idEstadoNew.getIdEstado());
                programas.setIdEstado(idEstadoNew);
            }
            List<AtletaDisciplina> attachedAtletaDisciplinaListNew = new ArrayList<AtletaDisciplina>();
            for (AtletaDisciplina atletaDisciplinaListNewAtletaDisciplinaToAttach : atletaDisciplinaListNew) {
                atletaDisciplinaListNewAtletaDisciplinaToAttach = em.getReference(atletaDisciplinaListNewAtletaDisciplinaToAttach.getClass(), atletaDisciplinaListNewAtletaDisciplinaToAttach.getIdAtletaDisciplina());
                attachedAtletaDisciplinaListNew.add(atletaDisciplinaListNewAtletaDisciplinaToAttach);
            }
            atletaDisciplinaListNew = attachedAtletaDisciplinaListNew;
            programas.setAtletaDisciplinaList(atletaDisciplinaListNew);
            programas = em.merge(programas);
            if (idEstadoOld != null && !idEstadoOld.equals(idEstadoNew)) {
                idEstadoOld.getProgramasList().remove(programas);
                idEstadoOld = em.merge(idEstadoOld);
            }
            if (idEstadoNew != null && !idEstadoNew.equals(idEstadoOld)) {
                idEstadoNew.getProgramasList().add(programas);
                idEstadoNew = em.merge(idEstadoNew);
            }
            for (AtletaDisciplina atletaDisciplinaListOldAtletaDisciplina : atletaDisciplinaListOld) {
                if (!atletaDisciplinaListNew.contains(atletaDisciplinaListOldAtletaDisciplina)) {
                    atletaDisciplinaListOldAtletaDisciplina.setIdPrograma(null);
                    atletaDisciplinaListOldAtletaDisciplina = em.merge(atletaDisciplinaListOldAtletaDisciplina);
                }
            }
            for (AtletaDisciplina atletaDisciplinaListNewAtletaDisciplina : atletaDisciplinaListNew) {
                if (!atletaDisciplinaListOld.contains(atletaDisciplinaListNewAtletaDisciplina)) {
                    Programas oldIdProgramaOfAtletaDisciplinaListNewAtletaDisciplina = atletaDisciplinaListNewAtletaDisciplina.getIdPrograma();
                    atletaDisciplinaListNewAtletaDisciplina.setIdPrograma(programas);
                    atletaDisciplinaListNewAtletaDisciplina = em.merge(atletaDisciplinaListNewAtletaDisciplina);
                    if (oldIdProgramaOfAtletaDisciplinaListNewAtletaDisciplina != null && !oldIdProgramaOfAtletaDisciplinaListNewAtletaDisciplina.equals(programas)) {
                        oldIdProgramaOfAtletaDisciplinaListNewAtletaDisciplina.getAtletaDisciplinaList().remove(atletaDisciplinaListNewAtletaDisciplina);
                        oldIdProgramaOfAtletaDisciplinaListNewAtletaDisciplina = em.merge(oldIdProgramaOfAtletaDisciplinaListNewAtletaDisciplina);
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
                Integer id = programas.getIdPrograma();
                if (findProgramas(id) == null) {
                    throw new NonexistentEntityException("The programas with id " + id + " no longer exists.");
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
            Programas programas;
            try {
                programas = em.getReference(Programas.class, id);
                programas.getIdPrograma();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programas with id " + id + " no longer exists.", enfe);
            }
            Estado idEstado = programas.getIdEstado();
            if (idEstado != null) {
                idEstado.getProgramasList().remove(programas);
                idEstado = em.merge(idEstado);
            }
            List<AtletaDisciplina> atletaDisciplinaList = programas.getAtletaDisciplinaList();
            for (AtletaDisciplina atletaDisciplinaListAtletaDisciplina : atletaDisciplinaList) {
                atletaDisciplinaListAtletaDisciplina.setIdPrograma(null);
                atletaDisciplinaListAtletaDisciplina = em.merge(atletaDisciplinaListAtletaDisciplina);
            }
            em.remove(programas);
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

    public List<Programas> findProgramasEntities() {
        return findProgramasEntities(true, -1, -1);
    }

    public List<Programas> findProgramasEntities(int maxResults, int firstResult) {
        return findProgramasEntities(false, maxResults, firstResult);
    }

    private List<Programas> findProgramasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Programas.class));
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

    public Programas findProgramas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programas.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Programas> rt = cq.from(Programas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
