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
import Entities.Usuarios;
import Entities.Estado;
import Entities.Atleta;
import Entities.Tutor;
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
public class TutorJpaController implements Serializable {

    private static final long serialVersionUID = 4190429098521769836L;

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
        if (tutor.getAtletaList() == null) {
            tutor.setAtletaList(new ArrayList<Atleta>());
        }
        if (tutor.getAtletaList1() == null) {
            tutor.setAtletaList1(new ArrayList<Atleta>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuarios idUsuarioIngreso = tutor.getIdUsuarioIngreso();
            if (idUsuarioIngreso != null) {
                idUsuarioIngreso = em.getReference(idUsuarioIngreso.getClass(), idUsuarioIngreso.getIdUsuario());
                tutor.setIdUsuarioIngreso(idUsuarioIngreso);
            }
            Usuarios idUsuarioActualizacion = tutor.getIdUsuarioActualizacion();
            if (idUsuarioActualizacion != null) {
                idUsuarioActualizacion = em.getReference(idUsuarioActualizacion.getClass(), idUsuarioActualizacion.getIdUsuario());
                tutor.setIdUsuarioActualizacion(idUsuarioActualizacion);
            }
            Estado idEstado = tutor.getIdEstado();
            if (idEstado != null) {
                idEstado = em.getReference(idEstado.getClass(), idEstado.getIdEstado());
                tutor.setIdEstado(idEstado);
            }
            List<Atleta> attachedAtletaList = new ArrayList<Atleta>();
            for (Atleta atletaListAtletaToAttach : tutor.getAtletaList()) {
                atletaListAtletaToAttach = em.getReference(atletaListAtletaToAttach.getClass(), atletaListAtletaToAttach.getIdAtleta());
                attachedAtletaList.add(atletaListAtletaToAttach);
            }
            tutor.setAtletaList(attachedAtletaList);
            List<Atleta> attachedAtletaList1 = new ArrayList<Atleta>();
            for (Atleta atletaList1AtletaToAttach : tutor.getAtletaList1()) {
                atletaList1AtletaToAttach = em.getReference(atletaList1AtletaToAttach.getClass(), atletaList1AtletaToAttach.getIdAtleta());
                attachedAtletaList1.add(atletaList1AtletaToAttach);
            }
            tutor.setAtletaList1(attachedAtletaList1);
            em.persist(tutor);
            if (idUsuarioIngreso != null) {
                idUsuarioIngreso.getTutorList().add(tutor);
                idUsuarioIngreso = em.merge(idUsuarioIngreso);
            }
            if (idUsuarioActualizacion != null) {
                idUsuarioActualizacion.getTutorList().add(tutor);
                idUsuarioActualizacion = em.merge(idUsuarioActualizacion);
            }
            if (idEstado != null) {
                idEstado.getTutorList().add(tutor);
                idEstado = em.merge(idEstado);
            }
            for (Atleta atletaListAtleta : tutor.getAtletaList()) {
                Tutor oldIdTutor1OfAtletaListAtleta = atletaListAtleta.getIdTutor1();
                atletaListAtleta.setIdTutor1(tutor);
                atletaListAtleta = em.merge(atletaListAtleta);
                if (oldIdTutor1OfAtletaListAtleta != null) {
                    oldIdTutor1OfAtletaListAtleta.getAtletaList().remove(atletaListAtleta);
                    oldIdTutor1OfAtletaListAtleta = em.merge(oldIdTutor1OfAtletaListAtleta);
                }
            }
            for (Atleta atletaList1Atleta : tutor.getAtletaList1()) {
                Tutor oldIdTutor2OfAtletaList1Atleta = atletaList1Atleta.getIdTutor2();
                atletaList1Atleta.setIdTutor2(tutor);
                atletaList1Atleta = em.merge(atletaList1Atleta);
                if (oldIdTutor2OfAtletaList1Atleta != null) {
                    oldIdTutor2OfAtletaList1Atleta.getAtletaList1().remove(atletaList1Atleta);
                    oldIdTutor2OfAtletaList1Atleta = em.merge(oldIdTutor2OfAtletaList1Atleta);
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

    public void edit(Tutor tutor) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Tutor persistentTutor = em.find(Tutor.class, tutor.getIdTutor());
            Usuarios idUsuarioIngresoOld = persistentTutor.getIdUsuarioIngreso();
            Usuarios idUsuarioIngresoNew = tutor.getIdUsuarioIngreso();
            Usuarios idUsuarioActualizacionOld = persistentTutor.getIdUsuarioActualizacion();
            Usuarios idUsuarioActualizacionNew = tutor.getIdUsuarioActualizacion();
            Estado idEstadoOld = persistentTutor.getIdEstado();
            Estado idEstadoNew = tutor.getIdEstado();
            List<Atleta> atletaListOld = persistentTutor.getAtletaList();
            List<Atleta> atletaListNew = tutor.getAtletaList();
            List<Atleta> atletaList1Old = persistentTutor.getAtletaList1();
            List<Atleta> atletaList1New = tutor.getAtletaList1();
            if (idUsuarioIngresoNew != null) {
                idUsuarioIngresoNew = em.getReference(idUsuarioIngresoNew.getClass(), idUsuarioIngresoNew.getIdUsuario());
                tutor.setIdUsuarioIngreso(idUsuarioIngresoNew);
            }
            if (idUsuarioActualizacionNew != null) {
                idUsuarioActualizacionNew = em.getReference(idUsuarioActualizacionNew.getClass(), idUsuarioActualizacionNew.getIdUsuario());
                tutor.setIdUsuarioActualizacion(idUsuarioActualizacionNew);
            }
            if (idEstadoNew != null) {
                idEstadoNew = em.getReference(idEstadoNew.getClass(), idEstadoNew.getIdEstado());
                tutor.setIdEstado(idEstadoNew);
            }
            List<Atleta> attachedAtletaListNew = new ArrayList<Atleta>();
            for (Atleta atletaListNewAtletaToAttach : atletaListNew) {
                atletaListNewAtletaToAttach = em.getReference(atletaListNewAtletaToAttach.getClass(), atletaListNewAtletaToAttach.getIdAtleta());
                attachedAtletaListNew.add(atletaListNewAtletaToAttach);
            }
            atletaListNew = attachedAtletaListNew;
            tutor.setAtletaList(atletaListNew);
            List<Atleta> attachedAtletaList1New = new ArrayList<Atleta>();
            for (Atleta atletaList1NewAtletaToAttach : atletaList1New) {
                atletaList1NewAtletaToAttach = em.getReference(atletaList1NewAtletaToAttach.getClass(), atletaList1NewAtletaToAttach.getIdAtleta());
                attachedAtletaList1New.add(atletaList1NewAtletaToAttach);
            }
            atletaList1New = attachedAtletaList1New;
            tutor.setAtletaList1(atletaList1New);
            tutor = em.merge(tutor);
            if (idUsuarioIngresoOld != null && !idUsuarioIngresoOld.equals(idUsuarioIngresoNew)) {
                idUsuarioIngresoOld.getTutorList().remove(tutor);
                idUsuarioIngresoOld = em.merge(idUsuarioIngresoOld);
            }
            if (idUsuarioIngresoNew != null && !idUsuarioIngresoNew.equals(idUsuarioIngresoOld)) {
                idUsuarioIngresoNew.getTutorList().add(tutor);
                idUsuarioIngresoNew = em.merge(idUsuarioIngresoNew);
            }
            if (idUsuarioActualizacionOld != null && !idUsuarioActualizacionOld.equals(idUsuarioActualizacionNew)) {
                idUsuarioActualizacionOld.getTutorList().remove(tutor);
                idUsuarioActualizacionOld = em.merge(idUsuarioActualizacionOld);
            }
            if (idUsuarioActualizacionNew != null && !idUsuarioActualizacionNew.equals(idUsuarioActualizacionOld)) {
                idUsuarioActualizacionNew.getTutorList().add(tutor);
                idUsuarioActualizacionNew = em.merge(idUsuarioActualizacionNew);
            }
            if (idEstadoOld != null && !idEstadoOld.equals(idEstadoNew)) {
                idEstadoOld.getTutorList().remove(tutor);
                idEstadoOld = em.merge(idEstadoOld);
            }
            if (idEstadoNew != null && !idEstadoNew.equals(idEstadoOld)) {
                idEstadoNew.getTutorList().add(tutor);
                idEstadoNew = em.merge(idEstadoNew);
            }
            for (Atleta atletaListOldAtleta : atletaListOld) {
                if (!atletaListNew.contains(atletaListOldAtleta)) {
                    atletaListOldAtleta.setIdTutor1(null);
                    atletaListOldAtleta = em.merge(atletaListOldAtleta);
                }
            }
            for (Atleta atletaListNewAtleta : atletaListNew) {
                if (!atletaListOld.contains(atletaListNewAtleta)) {
                    Tutor oldIdTutor1OfAtletaListNewAtleta = atletaListNewAtleta.getIdTutor1();
                    atletaListNewAtleta.setIdTutor1(tutor);
                    atletaListNewAtleta = em.merge(atletaListNewAtleta);
                    if (oldIdTutor1OfAtletaListNewAtleta != null && !oldIdTutor1OfAtletaListNewAtleta.equals(tutor)) {
                        oldIdTutor1OfAtletaListNewAtleta.getAtletaList().remove(atletaListNewAtleta);
                        oldIdTutor1OfAtletaListNewAtleta = em.merge(oldIdTutor1OfAtletaListNewAtleta);
                    }
                }
            }
            for (Atleta atletaList1OldAtleta : atletaList1Old) {
                if (!atletaList1New.contains(atletaList1OldAtleta)) {
                    atletaList1OldAtleta.setIdTutor2(null);
                    atletaList1OldAtleta = em.merge(atletaList1OldAtleta);
                }
            }
            for (Atleta atletaList1NewAtleta : atletaList1New) {
                if (!atletaList1Old.contains(atletaList1NewAtleta)) {
                    Tutor oldIdTutor2OfAtletaList1NewAtleta = atletaList1NewAtleta.getIdTutor2();
                    atletaList1NewAtleta.setIdTutor2(tutor);
                    atletaList1NewAtleta = em.merge(atletaList1NewAtleta);
                    if (oldIdTutor2OfAtletaList1NewAtleta != null && !oldIdTutor2OfAtletaList1NewAtleta.equals(tutor)) {
                        oldIdTutor2OfAtletaList1NewAtleta.getAtletaList1().remove(atletaList1NewAtleta);
                        oldIdTutor2OfAtletaList1NewAtleta = em.merge(oldIdTutor2OfAtletaList1NewAtleta);
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
            Usuarios idUsuarioIngreso = tutor.getIdUsuarioIngreso();
            if (idUsuarioIngreso != null) {
                idUsuarioIngreso.getTutorList().remove(tutor);
                idUsuarioIngreso = em.merge(idUsuarioIngreso);
            }
            Usuarios idUsuarioActualizacion = tutor.getIdUsuarioActualizacion();
            if (idUsuarioActualizacion != null) {
                idUsuarioActualizacion.getTutorList().remove(tutor);
                idUsuarioActualizacion = em.merge(idUsuarioActualizacion);
            }
            Estado idEstado = tutor.getIdEstado();
            if (idEstado != null) {
                idEstado.getTutorList().remove(tutor);
                idEstado = em.merge(idEstado);
            }
            List<Atleta> atletaList = tutor.getAtletaList();
            for (Atleta atletaListAtleta : atletaList) {
                atletaListAtleta.setIdTutor1(null);
                atletaListAtleta = em.merge(atletaListAtleta);
            }
            List<Atleta> atletaList1 = tutor.getAtletaList1();
            for (Atleta atletaList1Atleta : atletaList1) {
                atletaList1Atleta.setIdTutor2(null);
                atletaList1Atleta = em.merge(atletaList1Atleta);
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
