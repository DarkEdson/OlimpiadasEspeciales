/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Estado;
import Entities.AtletaDisciplina;
import Entities.Disciplinas;
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
public class DisciplinasJpaController implements Serializable {

    private static final long serialVersionUID = 8696205449543049202L;

    public DisciplinasJpaController() {
        this.utx = utx;
        this.emf = Persistence.createEntityManagerFactory("ORMOlimpEspPU");
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Disciplinas disciplinas) throws RollbackFailureException, Exception {
        if (disciplinas.getAtletaDisciplinaList() == null) {
            disciplinas.setAtletaDisciplinaList(new ArrayList<AtletaDisciplina>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Estado idEstado = disciplinas.getIdEstado();
            if (idEstado != null) {
                idEstado = em.getReference(idEstado.getClass(), idEstado.getIdEstado());
                disciplinas.setIdEstado(idEstado);
            }
            List<AtletaDisciplina> attachedAtletaDisciplinaList = new ArrayList<AtletaDisciplina>();
            for (AtletaDisciplina atletaDisciplinaListAtletaDisciplinaToAttach : disciplinas.getAtletaDisciplinaList()) {
                atletaDisciplinaListAtletaDisciplinaToAttach = em.getReference(atletaDisciplinaListAtletaDisciplinaToAttach.getClass(), atletaDisciplinaListAtletaDisciplinaToAttach.getIdAtletaDisciplina());
                attachedAtletaDisciplinaList.add(atletaDisciplinaListAtletaDisciplinaToAttach);
            }
            disciplinas.setAtletaDisciplinaList(attachedAtletaDisciplinaList);
            em.persist(disciplinas);
            if (idEstado != null) {
                idEstado.getDisciplinasList().add(disciplinas);
                idEstado = em.merge(idEstado);
            }
            for (AtletaDisciplina atletaDisciplinaListAtletaDisciplina : disciplinas.getAtletaDisciplinaList()) {
                Disciplinas oldIdDisciplinaOfAtletaDisciplinaListAtletaDisciplina = atletaDisciplinaListAtletaDisciplina.getIdDisciplina();
                atletaDisciplinaListAtletaDisciplina.setIdDisciplina(disciplinas);
                atletaDisciplinaListAtletaDisciplina = em.merge(atletaDisciplinaListAtletaDisciplina);
                if (oldIdDisciplinaOfAtletaDisciplinaListAtletaDisciplina != null) {
                    oldIdDisciplinaOfAtletaDisciplinaListAtletaDisciplina.getAtletaDisciplinaList().remove(atletaDisciplinaListAtletaDisciplina);
                    oldIdDisciplinaOfAtletaDisciplinaListAtletaDisciplina = em.merge(oldIdDisciplinaOfAtletaDisciplinaListAtletaDisciplina);
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

    public void edit(Disciplinas disciplinas) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Disciplinas persistentDisciplinas = em.find(Disciplinas.class, disciplinas.getIdDisciplina());
            Estado idEstadoOld = persistentDisciplinas.getIdEstado();
            Estado idEstadoNew = disciplinas.getIdEstado();
            List<AtletaDisciplina> atletaDisciplinaListOld = persistentDisciplinas.getAtletaDisciplinaList();
            List<AtletaDisciplina> atletaDisciplinaListNew = disciplinas.getAtletaDisciplinaList();
            List<String> illegalOrphanMessages = null;
            for (AtletaDisciplina atletaDisciplinaListOldAtletaDisciplina : atletaDisciplinaListOld) {
                if (!atletaDisciplinaListNew.contains(atletaDisciplinaListOldAtletaDisciplina)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain AtletaDisciplina " + atletaDisciplinaListOldAtletaDisciplina + " since its idDisciplina field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idEstadoNew != null) {
                idEstadoNew = em.getReference(idEstadoNew.getClass(), idEstadoNew.getIdEstado());
                disciplinas.setIdEstado(idEstadoNew);
            }
            List<AtletaDisciplina> attachedAtletaDisciplinaListNew = new ArrayList<AtletaDisciplina>();
            for (AtletaDisciplina atletaDisciplinaListNewAtletaDisciplinaToAttach : atletaDisciplinaListNew) {
                atletaDisciplinaListNewAtletaDisciplinaToAttach = em.getReference(atletaDisciplinaListNewAtletaDisciplinaToAttach.getClass(), atletaDisciplinaListNewAtletaDisciplinaToAttach.getIdAtletaDisciplina());
                attachedAtletaDisciplinaListNew.add(atletaDisciplinaListNewAtletaDisciplinaToAttach);
            }
            atletaDisciplinaListNew = attachedAtletaDisciplinaListNew;
            disciplinas.setAtletaDisciplinaList(atletaDisciplinaListNew);
            disciplinas = em.merge(disciplinas);
            if (idEstadoOld != null && !idEstadoOld.equals(idEstadoNew)) {
                idEstadoOld.getDisciplinasList().remove(disciplinas);
                idEstadoOld = em.merge(idEstadoOld);
            }
            if (idEstadoNew != null && !idEstadoNew.equals(idEstadoOld)) {
                idEstadoNew.getDisciplinasList().add(disciplinas);
                idEstadoNew = em.merge(idEstadoNew);
            }
            for (AtletaDisciplina atletaDisciplinaListNewAtletaDisciplina : atletaDisciplinaListNew) {
                if (!atletaDisciplinaListOld.contains(atletaDisciplinaListNewAtletaDisciplina)) {
                    Disciplinas oldIdDisciplinaOfAtletaDisciplinaListNewAtletaDisciplina = atletaDisciplinaListNewAtletaDisciplina.getIdDisciplina();
                    atletaDisciplinaListNewAtletaDisciplina.setIdDisciplina(disciplinas);
                    atletaDisciplinaListNewAtletaDisciplina = em.merge(atletaDisciplinaListNewAtletaDisciplina);
                    if (oldIdDisciplinaOfAtletaDisciplinaListNewAtletaDisciplina != null && !oldIdDisciplinaOfAtletaDisciplinaListNewAtletaDisciplina.equals(disciplinas)) {
                        oldIdDisciplinaOfAtletaDisciplinaListNewAtletaDisciplina.getAtletaDisciplinaList().remove(atletaDisciplinaListNewAtletaDisciplina);
                        oldIdDisciplinaOfAtletaDisciplinaListNewAtletaDisciplina = em.merge(oldIdDisciplinaOfAtletaDisciplinaListNewAtletaDisciplina);
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
                Integer id = disciplinas.getIdDisciplina();
                if (findDisciplinas(id) == null) {
                    throw new NonexistentEntityException("The disciplinas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Disciplinas disciplinas;
            try {
                disciplinas = em.getReference(Disciplinas.class, id);
                disciplinas.getIdDisciplina();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The disciplinas with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<AtletaDisciplina> atletaDisciplinaListOrphanCheck = disciplinas.getAtletaDisciplinaList();
            for (AtletaDisciplina atletaDisciplinaListOrphanCheckAtletaDisciplina : atletaDisciplinaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Disciplinas (" + disciplinas + ") cannot be destroyed since the AtletaDisciplina " + atletaDisciplinaListOrphanCheckAtletaDisciplina + " in its atletaDisciplinaList field has a non-nullable idDisciplina field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Estado idEstado = disciplinas.getIdEstado();
            if (idEstado != null) {
                idEstado.getDisciplinasList().remove(disciplinas);
                idEstado = em.merge(idEstado);
            }
            em.remove(disciplinas);
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

    public List<Disciplinas> findDisciplinasEntities() {
        return findDisciplinasEntities(true, -1, -1);
    }

    public List<Disciplinas> findDisciplinasEntities(int maxResults, int firstResult) {
        return findDisciplinasEntities(false, maxResults, firstResult);
    }

    private List<Disciplinas> findDisciplinasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Disciplinas.class));
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

    public Disciplinas findDisciplinas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Disciplinas.class, id);
        } finally {
            em.close();
        }
    }

    public int getDisciplinasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Disciplinas> rt = cq.from(Disciplinas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
