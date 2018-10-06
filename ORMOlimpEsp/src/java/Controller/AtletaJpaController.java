/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.RollbackFailureException;
import Entities.Atleta;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Entities.Departamento;
import Entities.Diagnostico;
import Entities.Tutor;
import Entities.SitioEntrenamiento;
import Entities.Usuarios;
import Entities.Estado;
import Entities.AtletaDisciplina;
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
public class AtletaJpaController implements Serializable {

    private static final long serialVersionUID = 3391549955219486187L;

    public AtletaJpaController() {
        this.utx = utx;
        this.emf = Persistence.createEntityManagerFactory("ORMOlimpEspPU");
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Atleta atleta) throws RollbackFailureException, Exception {
        if (atleta.getAtletaDisciplinaList() == null) {
            atleta.setAtletaDisciplinaList(new ArrayList<AtletaDisciplina>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
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
            Tutor idTutor1 = atleta.getIdTutor1();
            if (idTutor1 != null) {
                idTutor1 = em.getReference(idTutor1.getClass(), idTutor1.getIdTutor());
                atleta.setIdTutor1(idTutor1);
            }
            Tutor idTutor2 = atleta.getIdTutor2();
            if (idTutor2 != null) {
                idTutor2 = em.getReference(idTutor2.getClass(), idTutor2.getIdTutor());
                atleta.setIdTutor2(idTutor2);
            }
            SitioEntrenamiento idSitioEntrenamiento = atleta.getIdSitioEntrenamiento();
            if (idSitioEntrenamiento != null) {
                idSitioEntrenamiento = em.getReference(idSitioEntrenamiento.getClass(), idSitioEntrenamiento.getIdSitioEntrenamiento());
                atleta.setIdSitioEntrenamiento(idSitioEntrenamiento);
            }
            Usuarios idUsuarioIngreso = atleta.getIdUsuarioIngreso();
            if (idUsuarioIngreso != null) {
                idUsuarioIngreso = em.getReference(idUsuarioIngreso.getClass(), idUsuarioIngreso.getIdUsuario());
                atleta.setIdUsuarioIngreso(idUsuarioIngreso);
            }
            Usuarios idUsuarioActualizacion = atleta.getIdUsuarioActualizacion();
            if (idUsuarioActualizacion != null) {
                idUsuarioActualizacion = em.getReference(idUsuarioActualizacion.getClass(), idUsuarioActualizacion.getIdUsuario());
                atleta.setIdUsuarioActualizacion(idUsuarioActualizacion);
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
            em.persist(atleta);
            if (idDepartamento != null) {
                idDepartamento.getAtletaList().add(atleta);
                idDepartamento = em.merge(idDepartamento);
            }
            if (idDiagnostico != null) {
                idDiagnostico.getAtletaList().add(atleta);
                idDiagnostico = em.merge(idDiagnostico);
            }
            if (idTutor1 != null) {
                idTutor1.getAtletaList().add(atleta);
                idTutor1 = em.merge(idTutor1);
            }
            if (idTutor2 != null) {
                idTutor2.getAtletaList().add(atleta);
                idTutor2 = em.merge(idTutor2);
            }
            if (idSitioEntrenamiento != null) {
                idSitioEntrenamiento.getAtletaList().add(atleta);
                idSitioEntrenamiento = em.merge(idSitioEntrenamiento);
            }
            if (idUsuarioIngreso != null) {
                idUsuarioIngreso.getAtletaList().add(atleta);
                idUsuarioIngreso = em.merge(idUsuarioIngreso);
            }
            if (idUsuarioActualizacion != null) {
                idUsuarioActualizacion.getAtletaList().add(atleta);
                idUsuarioActualizacion = em.merge(idUsuarioActualizacion);
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

    public void edit(Atleta atleta) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Atleta persistentAtleta = em.find(Atleta.class, atleta.getIdAtleta());
            Departamento idDepartamentoOld = persistentAtleta.getIdDepartamento();
            Departamento idDepartamentoNew = atleta.getIdDepartamento();
            Diagnostico idDiagnosticoOld = persistentAtleta.getIdDiagnostico();
            Diagnostico idDiagnosticoNew = atleta.getIdDiagnostico();
            Tutor idTutor1Old = persistentAtleta.getIdTutor1();
            Tutor idTutor1New = atleta.getIdTutor1();
            Tutor idTutor2Old = persistentAtleta.getIdTutor2();
            Tutor idTutor2New = atleta.getIdTutor2();
            SitioEntrenamiento idSitioEntrenamientoOld = persistentAtleta.getIdSitioEntrenamiento();
            SitioEntrenamiento idSitioEntrenamientoNew = atleta.getIdSitioEntrenamiento();
            Usuarios idUsuarioIngresoOld = persistentAtleta.getIdUsuarioIngreso();
            Usuarios idUsuarioIngresoNew = atleta.getIdUsuarioIngreso();
            Usuarios idUsuarioActualizacionOld = persistentAtleta.getIdUsuarioActualizacion();
            Usuarios idUsuarioActualizacionNew = atleta.getIdUsuarioActualizacion();
            Estado idEstadoOld = persistentAtleta.getIdEstado();
            Estado idEstadoNew = atleta.getIdEstado();
            List<AtletaDisciplina> atletaDisciplinaListOld = persistentAtleta.getAtletaDisciplinaList();
            List<AtletaDisciplina> atletaDisciplinaListNew = atleta.getAtletaDisciplinaList();
            if (idDepartamentoNew != null) {
                idDepartamentoNew = em.getReference(idDepartamentoNew.getClass(), idDepartamentoNew.getIdDepartamento());
                atleta.setIdDepartamento(idDepartamentoNew);
            }
            if (idDiagnosticoNew != null) {
                idDiagnosticoNew = em.getReference(idDiagnosticoNew.getClass(), idDiagnosticoNew.getIdDiagnostico());
                atleta.setIdDiagnostico(idDiagnosticoNew);
            }
            if (idTutor1New != null) {
                idTutor1New = em.getReference(idTutor1New.getClass(), idTutor1New.getIdTutor());
                atleta.setIdTutor1(idTutor1New);
            }
            if (idTutor2New != null) {
                idTutor2New = em.getReference(idTutor2New.getClass(), idTutor2New.getIdTutor());
                atleta.setIdTutor2(idTutor2New);
            }
            if (idSitioEntrenamientoNew != null) {
                idSitioEntrenamientoNew = em.getReference(idSitioEntrenamientoNew.getClass(), idSitioEntrenamientoNew.getIdSitioEntrenamiento());
                atleta.setIdSitioEntrenamiento(idSitioEntrenamientoNew);
            }
            if (idUsuarioIngresoNew != null) {
                idUsuarioIngresoNew = em.getReference(idUsuarioIngresoNew.getClass(), idUsuarioIngresoNew.getIdUsuario());
                atleta.setIdUsuarioIngreso(idUsuarioIngresoNew);
            }
            if (idUsuarioActualizacionNew != null) {
                idUsuarioActualizacionNew = em.getReference(idUsuarioActualizacionNew.getClass(), idUsuarioActualizacionNew.getIdUsuario());
                atleta.setIdUsuarioActualizacion(idUsuarioActualizacionNew);
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
            atleta = em.merge(atleta);
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
            if (idTutor1Old != null && !idTutor1Old.equals(idTutor1New)) {
                idTutor1Old.getAtletaList().remove(atleta);
                idTutor1Old = em.merge(idTutor1Old);
            }
            if (idTutor1New != null && !idTutor1New.equals(idTutor1Old)) {
                idTutor1New.getAtletaList().add(atleta);
                idTutor1New = em.merge(idTutor1New);
            }
            if (idTutor2Old != null && !idTutor2Old.equals(idTutor2New)) {
                idTutor2Old.getAtletaList().remove(atleta);
                idTutor2Old = em.merge(idTutor2Old);
            }
            if (idTutor2New != null && !idTutor2New.equals(idTutor2Old)) {
                idTutor2New.getAtletaList().add(atleta);
                idTutor2New = em.merge(idTutor2New);
            }
            if (idSitioEntrenamientoOld != null && !idSitioEntrenamientoOld.equals(idSitioEntrenamientoNew)) {
                idSitioEntrenamientoOld.getAtletaList().remove(atleta);
                idSitioEntrenamientoOld = em.merge(idSitioEntrenamientoOld);
            }
            if (idSitioEntrenamientoNew != null && !idSitioEntrenamientoNew.equals(idSitioEntrenamientoOld)) {
                idSitioEntrenamientoNew.getAtletaList().add(atleta);
                idSitioEntrenamientoNew = em.merge(idSitioEntrenamientoNew);
            }
            if (idUsuarioIngresoOld != null && !idUsuarioIngresoOld.equals(idUsuarioIngresoNew)) {
                idUsuarioIngresoOld.getAtletaList().remove(atleta);
                idUsuarioIngresoOld = em.merge(idUsuarioIngresoOld);
            }
            if (idUsuarioIngresoNew != null && !idUsuarioIngresoNew.equals(idUsuarioIngresoOld)) {
                idUsuarioIngresoNew.getAtletaList().add(atleta);
                idUsuarioIngresoNew = em.merge(idUsuarioIngresoNew);
            }
            if (idUsuarioActualizacionOld != null && !idUsuarioActualizacionOld.equals(idUsuarioActualizacionNew)) {
                idUsuarioActualizacionOld.getAtletaList().remove(atleta);
                idUsuarioActualizacionOld = em.merge(idUsuarioActualizacionOld);
            }
            if (idUsuarioActualizacionNew != null && !idUsuarioActualizacionNew.equals(idUsuarioActualizacionOld)) {
                idUsuarioActualizacionNew.getAtletaList().add(atleta);
                idUsuarioActualizacionNew = em.merge(idUsuarioActualizacionNew);
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
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = atleta.getIdAtleta();
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

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
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
            Tutor idTutor1 = atleta.getIdTutor1();
            if (idTutor1 != null) {
                idTutor1.getAtletaList().remove(atleta);
                idTutor1 = em.merge(idTutor1);
            }
            Tutor idTutor2 = atleta.getIdTutor2();
            if (idTutor2 != null) {
                idTutor2.getAtletaList().remove(atleta);
                idTutor2 = em.merge(idTutor2);
            }
            SitioEntrenamiento idSitioEntrenamiento = atleta.getIdSitioEntrenamiento();
            if (idSitioEntrenamiento != null) {
                idSitioEntrenamiento.getAtletaList().remove(atleta);
                idSitioEntrenamiento = em.merge(idSitioEntrenamiento);
            }
            Usuarios idUsuarioIngreso = atleta.getIdUsuarioIngreso();
            if (idUsuarioIngreso != null) {
                idUsuarioIngreso.getAtletaList().remove(atleta);
                idUsuarioIngreso = em.merge(idUsuarioIngreso);
            }
            Usuarios idUsuarioActualizacion = atleta.getIdUsuarioActualizacion();
            if (idUsuarioActualizacion != null) {
                idUsuarioActualizacion.getAtletaList().remove(atleta);
                idUsuarioActualizacion = em.merge(idUsuarioActualizacion);
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

    public Atleta findAtleta(Integer id) {
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
