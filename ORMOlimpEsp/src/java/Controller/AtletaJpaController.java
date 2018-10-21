/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import Controller.exceptions.RollbackFailureException;
import Entities.Atleta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Institucion;
import Entities.Departamento;
import Entities.Diagnostico;
import Entities.SitioEntrenamiento;
import Entities.Estado;
import Entities.AtletaDisciplina;
import java.util.ArrayList;
import java.util.List;
import Entities.Tutor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 *
 * @author axel.medina
 */
public class AtletaJpaController implements Serializable {

    public AtletaJpaController() {
        this.utx = utx;
        this.emf = Persistence.createEntityManagerFactory("ORMOlimpEspPU");
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Atleta atleta) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (atleta.getAtletaDisciplinaList() == null) {
            atleta.setAtletaDisciplinaList(new ArrayList<AtletaDisciplina>());
        }
        if (atleta.getTutorList() == null) {
            atleta.setTutorList(new ArrayList<Tutor>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Institucion idInstitucion = atleta.getIdIntitucion();
            if (idInstitucion != null) {
                idInstitucion = em.getReference(idInstitucion.getClass(), idInstitucion.getIdIntitucion());
                atleta.setIdIntitucion(idInstitucion);
            }
            Departamento idDepartamento = atleta.getIdDepartamento();
            if (idDepartamento != null) {
                idDepartamento = em.getReference(idDepartamento.getClass(), idDepartamento.getIdDepartamento());
                atleta.setIdDepartamento(idDepartamento);
            }
            Diagnostico idDiagnostico = atleta.getIdDiagnostico();
            if (idDiagnostico != null) {
                idDiagnostico = em.getReference(idDiagnostico.getClass(), idDiagnostico.getIdDiagnostico());
                atleta.setIdDiagnostico(idDiagnostico);
            }
            SitioEntrenamiento idSitioEntrenamiento = atleta.getIdSitioEntrenamiento();
            if (idSitioEntrenamiento != null) {
                idSitioEntrenamiento = em.getReference(idSitioEntrenamiento.getClass(), idSitioEntrenamiento.getIdSitioEntrenamiento());
                atleta.setIdSitioEntrenamiento(idSitioEntrenamiento);
            }
            Estado idEstado = atleta.getIdEstado();
            if (idEstado != null) {
                idEstado = em.getReference(idEstado.getClass(), idEstado.getIdEstado());
                atleta.setIdEstado(idEstado);
            }
            List<AtletaDisciplina> attachedAtletaDisciplinaList = new ArrayList<AtletaDisciplina>();
            for (AtletaDisciplina atletaDisciplinaListAtletaDisciplinaToAttach : atleta.getAtletaDisciplinaList()) {
                atletaDisciplinaListAtletaDisciplinaToAttach = em.getReference(atletaDisciplinaListAtletaDisciplinaToAttach.getClass(), atletaDisciplinaListAtletaDisciplinaToAttach.getIdAtletaDisciplina());
                attachedAtletaDisciplinaList.add(atletaDisciplinaListAtletaDisciplinaToAttach);
            }
            atleta.setAtletaDisciplinaList(attachedAtletaDisciplinaList);
            List<Tutor> attachedTutorList = new ArrayList<Tutor>();
            for (Tutor tutorListTutorToAttach : atleta.getTutorList()) {
                tutorListTutorToAttach = em.getReference(tutorListTutorToAttach.getClass(), tutorListTutorToAttach.getIdTutor());
                attachedTutorList.add(tutorListTutorToAttach);
            }
            atleta.setTutorList(attachedTutorList);
            em.persist(atleta);
            if (idInstitucion != null) {
                idInstitucion.getAtletaList().add(atleta);
                idInstitucion = em.merge(idInstitucion);
            }
            if (idDepartamento != null) {
                idDepartamento.getAtletaList().add(atleta);
                idDepartamento = em.merge(idDepartamento);
            }
            if (idDiagnostico != null) {
                idDiagnostico.getAtletaList().add(atleta);
                idDiagnostico = em.merge(idDiagnostico);
            }
            if (idSitioEntrenamiento != null) {
                idSitioEntrenamiento.getAtletaList().add(atleta);
                idSitioEntrenamiento = em.merge(idSitioEntrenamiento);
            }
            if (idEstado != null) {
                idEstado.getAtletaList().add(atleta);
                idEstado = em.merge(idEstado);
            }
            for (AtletaDisciplina atletaDisciplinaListAtletaDisciplina : atleta.getAtletaDisciplinaList()) {
                Atleta oldIdAtletaOfAtletaDisciplinaListAtletaDisciplina = atletaDisciplinaListAtletaDisciplina.getIdAtleta();
                atletaDisciplinaListAtletaDisciplina.setIdAtleta(atleta);
                atletaDisciplinaListAtletaDisciplina = em.merge(atletaDisciplinaListAtletaDisciplina);
                if (oldIdAtletaOfAtletaDisciplinaListAtletaDisciplina != null) {
                    oldIdAtletaOfAtletaDisciplinaListAtletaDisciplina.getAtletaDisciplinaList().remove(atletaDisciplinaListAtletaDisciplina);
                    oldIdAtletaOfAtletaDisciplinaListAtletaDisciplina = em.merge(oldIdAtletaOfAtletaDisciplinaListAtletaDisciplina);
                }
            }
            for (Tutor tutorListTutor : atleta.getTutorList()) {
                Atleta oldIdAtletaOfTutorListTutor = tutorListTutor.getIdAtleta();
                tutorListTutor.setIdAtleta(atleta);
                tutorListTutor = em.merge(tutorListTutor);
                if (oldIdAtletaOfTutorListTutor != null) {
                    oldIdAtletaOfTutorListTutor.getTutorList().remove(tutorListTutor);
                    oldIdAtletaOfTutorListTutor = em.merge(oldIdAtletaOfTutorListTutor);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findAtleta(atleta.getIdAtleta()) != null) {
                throw new PreexistingEntityException("Atleta " + atleta + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Atleta atleta) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Atleta persistentAtleta = em.find(Atleta.class, atleta.getIdAtleta());
            Institucion idInstitucionOld = persistentAtleta.getIdIntitucion();
            Institucion idInstitucionNew = atleta.getIdIntitucion();
            Departamento idDepartamentoOld = persistentAtleta.getIdDepartamento();
            Departamento idDepartamentoNew = atleta.getIdDepartamento();
            Diagnostico idDiagnosticoOld = persistentAtleta.getIdDiagnostico();
            Diagnostico idDiagnosticoNew = atleta.getIdDiagnostico();
            SitioEntrenamiento idSitioEntrenamientoOld = persistentAtleta.getIdSitioEntrenamiento();
            SitioEntrenamiento idSitioEntrenamientoNew = atleta.getIdSitioEntrenamiento();
            Estado idEstadoOld = persistentAtleta.getIdEstado();
            Estado idEstadoNew = atleta.getIdEstado();
            List<AtletaDisciplina> atletaDisciplinaListOld = persistentAtleta.getAtletaDisciplinaList();
            List<AtletaDisciplina> atletaDisciplinaListNew = atleta.getAtletaDisciplinaList();
            List<Tutor> tutorListOld = persistentAtleta.getTutorList();
            List<Tutor> tutorListNew = atleta.getTutorList();
            if (idInstitucionNew != null) {
                idInstitucionNew = em.getReference(idInstitucionNew.getClass(), idInstitucionNew.getIdIntitucion());
                atleta.setIdIntitucion(idInstitucionNew);
            }
            if (idDepartamentoNew != null) {
                idDepartamentoNew = em.getReference(idDepartamentoNew.getClass(), idDepartamentoNew.getIdDepartamento());
                atleta.setIdDepartamento(idDepartamentoNew);
            }
            if (idDiagnosticoNew != null) {
                idDiagnosticoNew = em.getReference(idDiagnosticoNew.getClass(), idDiagnosticoNew.getIdDiagnostico());
                atleta.setIdDiagnostico(idDiagnosticoNew);
            }
            if (idSitioEntrenamientoNew != null) {
                idSitioEntrenamientoNew = em.getReference(idSitioEntrenamientoNew.getClass(), idSitioEntrenamientoNew.getIdSitioEntrenamiento());
                atleta.setIdSitioEntrenamiento(idSitioEntrenamientoNew);
            }
            if (idEstadoNew != null) {
                idEstadoNew = em.getReference(idEstadoNew.getClass(), idEstadoNew.getIdEstado());
                atleta.setIdEstado(idEstadoNew);
            }
            List<AtletaDisciplina> attachedAtletaDisciplinaListNew = new ArrayList<AtletaDisciplina>();
            for (AtletaDisciplina atletaDisciplinaListNewAtletaDisciplinaToAttach : atletaDisciplinaListNew) {
                atletaDisciplinaListNewAtletaDisciplinaToAttach = em.getReference(atletaDisciplinaListNewAtletaDisciplinaToAttach.getClass(), atletaDisciplinaListNewAtletaDisciplinaToAttach.getIdAtletaDisciplina());
                attachedAtletaDisciplinaListNew.add(atletaDisciplinaListNewAtletaDisciplinaToAttach);
            }
            atletaDisciplinaListNew = attachedAtletaDisciplinaListNew;
            atleta.setAtletaDisciplinaList(atletaDisciplinaListNew);
            List<Tutor> attachedTutorListNew = new ArrayList<Tutor>();
            for (Tutor tutorListNewTutorToAttach : tutorListNew) {
                tutorListNewTutorToAttach = em.getReference(tutorListNewTutorToAttach.getClass(), tutorListNewTutorToAttach.getIdTutor());
                attachedTutorListNew.add(tutorListNewTutorToAttach);
            }
            tutorListNew = attachedTutorListNew;
            atleta.setTutorList(tutorListNew);
            atleta = em.merge(atleta);
            if (idInstitucionOld != null && !idInstitucionOld.equals(idInstitucionNew)) {
                idInstitucionOld.getAtletaList().remove(atleta);
                idInstitucionOld = em.merge(idInstitucionOld);
            }
            if (idInstitucionNew != null && !idInstitucionNew.equals(idInstitucionOld)) {
                idInstitucionNew.getAtletaList().add(atleta);
                idInstitucionNew = em.merge(idInstitucionNew);
            }
            if (idDepartamentoOld != null && !idDepartamentoOld.equals(idDepartamentoNew)) {
                idDepartamentoOld.getAtletaList().remove(atleta);
                idDepartamentoOld = em.merge(idDepartamentoOld);
            }
            if (idDepartamentoNew != null && !idDepartamentoNew.equals(idDepartamentoOld)) {
                idDepartamentoNew.getAtletaList().add(atleta);
                idDepartamentoNew = em.merge(idDepartamentoNew);
            }
            if (idDiagnosticoOld != null && !idDiagnosticoOld.equals(idDiagnosticoNew)) {
                idDiagnosticoOld.getAtletaList().remove(atleta);
                idDiagnosticoOld = em.merge(idDiagnosticoOld);
            }
            if (idDiagnosticoNew != null && !idDiagnosticoNew.equals(idDiagnosticoOld)) {
                idDiagnosticoNew.getAtletaList().add(atleta);
                idDiagnosticoNew = em.merge(idDiagnosticoNew);
            }
            if (idSitioEntrenamientoOld != null && !idSitioEntrenamientoOld.equals(idSitioEntrenamientoNew)) {
                idSitioEntrenamientoOld.getAtletaList().remove(atleta);
                idSitioEntrenamientoOld = em.merge(idSitioEntrenamientoOld);
            }
            if (idSitioEntrenamientoNew != null && !idSitioEntrenamientoNew.equals(idSitioEntrenamientoOld)) {
                idSitioEntrenamientoNew.getAtletaList().add(atleta);
                idSitioEntrenamientoNew = em.merge(idSitioEntrenamientoNew);
            }
            if (idEstadoOld != null && !idEstadoOld.equals(idEstadoNew)) {
                idEstadoOld.getAtletaList().remove(atleta);
                idEstadoOld = em.merge(idEstadoOld);
            }
            if (idEstadoNew != null && !idEstadoNew.equals(idEstadoOld)) {
                idEstadoNew.getAtletaList().add(atleta);
                idEstadoNew = em.merge(idEstadoNew);
            }
            for (AtletaDisciplina atletaDisciplinaListOldAtletaDisciplina : atletaDisciplinaListOld) {
                if (!atletaDisciplinaListNew.contains(atletaDisciplinaListOldAtletaDisciplina)) {
                    atletaDisciplinaListOldAtletaDisciplina.setIdAtleta(null);
                    atletaDisciplinaListOldAtletaDisciplina = em.merge(atletaDisciplinaListOldAtletaDisciplina);
                }
            }
            for (AtletaDisciplina atletaDisciplinaListNewAtletaDisciplina : atletaDisciplinaListNew) {
                if (!atletaDisciplinaListOld.contains(atletaDisciplinaListNewAtletaDisciplina)) {
                    Atleta oldIdAtletaOfAtletaDisciplinaListNewAtletaDisciplina = atletaDisciplinaListNewAtletaDisciplina.getIdAtleta();
                    atletaDisciplinaListNewAtletaDisciplina.setIdAtleta(atleta);
                    atletaDisciplinaListNewAtletaDisciplina = em.merge(atletaDisciplinaListNewAtletaDisciplina);
                    if (oldIdAtletaOfAtletaDisciplinaListNewAtletaDisciplina != null && !oldIdAtletaOfAtletaDisciplinaListNewAtletaDisciplina.equals(atleta)) {
                        oldIdAtletaOfAtletaDisciplinaListNewAtletaDisciplina.getAtletaDisciplinaList().remove(atletaDisciplinaListNewAtletaDisciplina);
                        oldIdAtletaOfAtletaDisciplinaListNewAtletaDisciplina = em.merge(oldIdAtletaOfAtletaDisciplinaListNewAtletaDisciplina);
                    }
                }
            }
            for (Tutor tutorListOldTutor : tutorListOld) {
                if (!tutorListNew.contains(tutorListOldTutor)) {
                    tutorListOldTutor.setIdAtleta(null);
                    tutorListOldTutor = em.merge(tutorListOldTutor);
                }
            }
            for (Tutor tutorListNewTutor : tutorListNew) {
                if (!tutorListOld.contains(tutorListNewTutor)) {
                    Atleta oldIdAtletaOfTutorListNewTutor = tutorListNewTutor.getIdAtleta();
                    tutorListNewTutor.setIdAtleta(atleta);
                    tutorListNewTutor = em.merge(tutorListNewTutor);
                    if (oldIdAtletaOfTutorListNewTutor != null && !oldIdAtletaOfTutorListNewTutor.equals(atleta)) {
                        oldIdAtletaOfTutorListNewTutor.getTutorList().remove(tutorListNewTutor);
                        oldIdAtletaOfTutorListNewTutor = em.merge(oldIdAtletaOfTutorListNewTutor);
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
                String id = atleta.getIdAtleta();
                if (findAtleta(id) == null) {
                    throw new NonexistentEntityException("The atleta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Atleta atleta;
            try {
                atleta = em.getReference(Atleta.class, id);
                atleta.getIdAtleta();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The atleta with id " + id + " no longer exists.", enfe);
            }
            Institucion idInstitucion = atleta.getIdIntitucion();
            if (idInstitucion != null) {
                idInstitucion.getAtletaList().remove(atleta);
                idInstitucion = em.merge(idInstitucion);
            }
            Departamento idDepartamento = atleta.getIdDepartamento();
            if (idDepartamento != null) {
                idDepartamento.getAtletaList().remove(atleta);
                idDepartamento = em.merge(idDepartamento);
            }
            Diagnostico idDiagnostico = atleta.getIdDiagnostico();
            if (idDiagnostico != null) {
                idDiagnostico.getAtletaList().remove(atleta);
                idDiagnostico = em.merge(idDiagnostico);
            }
            SitioEntrenamiento idSitioEntrenamiento = atleta.getIdSitioEntrenamiento();
            if (idSitioEntrenamiento != null) {
                idSitioEntrenamiento.getAtletaList().remove(atleta);
                idSitioEntrenamiento = em.merge(idSitioEntrenamiento);
            }
            Estado idEstado = atleta.getIdEstado();
            if (idEstado != null) {
                idEstado.getAtletaList().remove(atleta);
                idEstado = em.merge(idEstado);
            }
            List<AtletaDisciplina> atletaDisciplinaList = atleta.getAtletaDisciplinaList();
            for (AtletaDisciplina atletaDisciplinaListAtletaDisciplina : atletaDisciplinaList) {
                atletaDisciplinaListAtletaDisciplina.setIdAtleta(null);
                atletaDisciplinaListAtletaDisciplina = em.merge(atletaDisciplinaListAtletaDisciplina);
            }
            List<Tutor> tutorList = atleta.getTutorList();
            for (Tutor tutorListTutor : tutorList) {
                tutorListTutor.setIdAtleta(null);
                tutorListTutor = em.merge(tutorListTutor);
            }
            em.remove(atleta);
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

    public List<Atleta> findAtletaEntities() {
        return findAtletaEntities(true, -1, -1);
    }

    public List<Atleta> findAtletaEntities(int maxResults, int firstResult) {
        return findAtletaEntities(false, maxResults, firstResult);
    }

    private List<Atleta> findAtletaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Atleta.class));
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

    public Atleta findAtleta(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Atleta.class, id);
        } finally {
            em.close();
        }
    }

    public int getAtletaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Atleta> rt = cq.from(Atleta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
