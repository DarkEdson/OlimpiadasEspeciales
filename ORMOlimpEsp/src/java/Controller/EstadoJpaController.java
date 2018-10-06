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
import Entities.Disciplinas;
import java.util.ArrayList;
import java.util.List;
import Entities.Atleta;
import Entities.Diagnostico;
import Entities.Roles;
import Entities.Institucion;
import Entities.SitioEntrenamiento;
import Entities.Departamento;
import Entities.Usuarios;
import Entities.AtletaDisciplina;
import Entities.Estado;
import Entities.Tutor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 *
 * @author axel.medina
 */
public class EstadoJpaController implements Serializable {

    private static final long serialVersionUID = -3820186969823606166L;

    public EstadoJpaController() {
        this.utx = utx;
        this.emf = Persistence.createEntityManagerFactory("ORMOlimpEspPU");
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estado estado) throws RollbackFailureException, Exception {
        if (estado.getDisciplinasList() == null) {
            estado.setDisciplinasList(new ArrayList<Disciplinas>());
        }
        if (estado.getAtletaList() == null) {
            estado.setAtletaList(new ArrayList<Atleta>());
        }
        if (estado.getDiagnosticoList() == null) {
            estado.setDiagnosticoList(new ArrayList<Diagnostico>());
        }
        if (estado.getRolesList() == null) {
            estado.setRolesList(new ArrayList<Roles>());
        }
        if (estado.getInstitucionList() == null) {
            estado.setInstitucionList(new ArrayList<Institucion>());
        }
        if (estado.getSitioEntrenamientoList() == null) {
            estado.setSitioEntrenamientoList(new ArrayList<SitioEntrenamiento>());
        }
        if (estado.getDepartamentoList() == null) {
            estado.setDepartamentoList(new ArrayList<Departamento>());
        }
        if (estado.getUsuariosList() == null) {
            estado.setUsuariosList(new ArrayList<Usuarios>());
        }
        if (estado.getAtletaDisciplinaList() == null) {
            estado.setAtletaDisciplinaList(new ArrayList<AtletaDisciplina>());
        }
        if (estado.getTutorList() == null) {
            estado.setTutorList(new ArrayList<Tutor>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Disciplinas> attachedDisciplinasList = new ArrayList<Disciplinas>();
            for (Disciplinas disciplinasListDisciplinasToAttach : estado.getDisciplinasList()) {
                disciplinasListDisciplinasToAttach = em.getReference(disciplinasListDisciplinasToAttach.getClass(), disciplinasListDisciplinasToAttach.getIdDisciplina());
                attachedDisciplinasList.add(disciplinasListDisciplinasToAttach);
            }
            estado.setDisciplinasList(attachedDisciplinasList);
            List<Atleta> attachedAtletaList = new ArrayList<Atleta>();
            for (Atleta atletaListAtletaToAttach : estado.getAtletaList()) {
                atletaListAtletaToAttach = em.getReference(atletaListAtletaToAttach.getClass(), atletaListAtletaToAttach.getIdAtleta());
                attachedAtletaList.add(atletaListAtletaToAttach);
            }
            estado.setAtletaList(attachedAtletaList);
            List<Diagnostico> attachedDiagnosticoList = new ArrayList<Diagnostico>();
            for (Diagnostico diagnosticoListDiagnosticoToAttach : estado.getDiagnosticoList()) {
                diagnosticoListDiagnosticoToAttach = em.getReference(diagnosticoListDiagnosticoToAttach.getClass(), diagnosticoListDiagnosticoToAttach.getIdDiagnostico());
                attachedDiagnosticoList.add(diagnosticoListDiagnosticoToAttach);
            }
            estado.setDiagnosticoList(attachedDiagnosticoList);
            List<Roles> attachedRolesList = new ArrayList<Roles>();
            for (Roles rolesListRolesToAttach : estado.getRolesList()) {
                rolesListRolesToAttach = em.getReference(rolesListRolesToAttach.getClass(), rolesListRolesToAttach.getIdRol());
                attachedRolesList.add(rolesListRolesToAttach);
            }
            estado.setRolesList(attachedRolesList);
            List<Institucion> attachedInstitucionList = new ArrayList<Institucion>();
            for (Institucion institucionListInstitucionToAttach : estado.getInstitucionList()) {
                institucionListInstitucionToAttach = em.getReference(institucionListInstitucionToAttach.getClass(), institucionListInstitucionToAttach.getIdIntitucion());
                attachedInstitucionList.add(institucionListInstitucionToAttach);
            }
            estado.setInstitucionList(attachedInstitucionList);
            List<SitioEntrenamiento> attachedSitioEntrenamientoList = new ArrayList<SitioEntrenamiento>();
            for (SitioEntrenamiento sitioEntrenamientoListSitioEntrenamientoToAttach : estado.getSitioEntrenamientoList()) {
                sitioEntrenamientoListSitioEntrenamientoToAttach = em.getReference(sitioEntrenamientoListSitioEntrenamientoToAttach.getClass(), sitioEntrenamientoListSitioEntrenamientoToAttach.getIdSitioEntrenamiento());
                attachedSitioEntrenamientoList.add(sitioEntrenamientoListSitioEntrenamientoToAttach);
            }
            estado.setSitioEntrenamientoList(attachedSitioEntrenamientoList);
            List<Departamento> attachedDepartamentoList = new ArrayList<Departamento>();
            for (Departamento departamentoListDepartamentoToAttach : estado.getDepartamentoList()) {
                departamentoListDepartamentoToAttach = em.getReference(departamentoListDepartamentoToAttach.getClass(), departamentoListDepartamentoToAttach.getIdDepartamento());
                attachedDepartamentoList.add(departamentoListDepartamentoToAttach);
            }
            estado.setDepartamentoList(attachedDepartamentoList);
            List<Usuarios> attachedUsuariosList = new ArrayList<Usuarios>();
            for (Usuarios usuariosListUsuariosToAttach : estado.getUsuariosList()) {
                usuariosListUsuariosToAttach = em.getReference(usuariosListUsuariosToAttach.getClass(), usuariosListUsuariosToAttach.getIdUsuario());
                attachedUsuariosList.add(usuariosListUsuariosToAttach);
            }
            estado.setUsuariosList(attachedUsuariosList);
            List<AtletaDisciplina> attachedAtletaDisciplinaList = new ArrayList<AtletaDisciplina>();
            for (AtletaDisciplina atletaDisciplinaListAtletaDisciplinaToAttach : estado.getAtletaDisciplinaList()) {
                atletaDisciplinaListAtletaDisciplinaToAttach = em.getReference(atletaDisciplinaListAtletaDisciplinaToAttach.getClass(), atletaDisciplinaListAtletaDisciplinaToAttach.getIdAtletaDisciplina());
                attachedAtletaDisciplinaList.add(atletaDisciplinaListAtletaDisciplinaToAttach);
            }
            estado.setAtletaDisciplinaList(attachedAtletaDisciplinaList);
            List<Tutor> attachedTutorList = new ArrayList<Tutor>();
            for (Tutor tutorListTutorToAttach : estado.getTutorList()) {
                tutorListTutorToAttach = em.getReference(tutorListTutorToAttach.getClass(), tutorListTutorToAttach.getIdTutor());
                attachedTutorList.add(tutorListTutorToAttach);
            }
            estado.setTutorList(attachedTutorList);
            em.persist(estado);
            for (Disciplinas disciplinasListDisciplinas : estado.getDisciplinasList()) {
                Estado oldIdEstadoOfDisciplinasListDisciplinas = disciplinasListDisciplinas.getIdEstado();
                disciplinasListDisciplinas.setIdEstado(estado);
                disciplinasListDisciplinas = em.merge(disciplinasListDisciplinas);
                if (oldIdEstadoOfDisciplinasListDisciplinas != null) {
                    oldIdEstadoOfDisciplinasListDisciplinas.getDisciplinasList().remove(disciplinasListDisciplinas);
                    oldIdEstadoOfDisciplinasListDisciplinas = em.merge(oldIdEstadoOfDisciplinasListDisciplinas);
                }
            }
            for (Atleta atletaListAtleta : estado.getAtletaList()) {
                Estado oldIdEstadoOfAtletaListAtleta = atletaListAtleta.getIdEstado();
                atletaListAtleta.setIdEstado(estado);
                atletaListAtleta = em.merge(atletaListAtleta);
                if (oldIdEstadoOfAtletaListAtleta != null) {
                    oldIdEstadoOfAtletaListAtleta.getAtletaList().remove(atletaListAtleta);
                    oldIdEstadoOfAtletaListAtleta = em.merge(oldIdEstadoOfAtletaListAtleta);
                }
            }
            for (Diagnostico diagnosticoListDiagnostico : estado.getDiagnosticoList()) {
                Estado oldIdEstadoOfDiagnosticoListDiagnostico = diagnosticoListDiagnostico.getIdEstado();
                diagnosticoListDiagnostico.setIdEstado(estado);
                diagnosticoListDiagnostico = em.merge(diagnosticoListDiagnostico);
                if (oldIdEstadoOfDiagnosticoListDiagnostico != null) {
                    oldIdEstadoOfDiagnosticoListDiagnostico.getDiagnosticoList().remove(diagnosticoListDiagnostico);
                    oldIdEstadoOfDiagnosticoListDiagnostico = em.merge(oldIdEstadoOfDiagnosticoListDiagnostico);
                }
            }
            for (Roles rolesListRoles : estado.getRolesList()) {
                Estado oldIdEstadoOfRolesListRoles = rolesListRoles.getIdEstado();
                rolesListRoles.setIdEstado(estado);
                rolesListRoles = em.merge(rolesListRoles);
                if (oldIdEstadoOfRolesListRoles != null) {
                    oldIdEstadoOfRolesListRoles.getRolesList().remove(rolesListRoles);
                    oldIdEstadoOfRolesListRoles = em.merge(oldIdEstadoOfRolesListRoles);
                }
            }
            for (Institucion institucionListInstitucion : estado.getInstitucionList()) {
                Estado oldIdEstadoOfInstitucionListInstitucion = institucionListInstitucion.getIdEstado();
                institucionListInstitucion.setIdEstado(estado);
                institucionListInstitucion = em.merge(institucionListInstitucion);
                if (oldIdEstadoOfInstitucionListInstitucion != null) {
                    oldIdEstadoOfInstitucionListInstitucion.getInstitucionList().remove(institucionListInstitucion);
                    oldIdEstadoOfInstitucionListInstitucion = em.merge(oldIdEstadoOfInstitucionListInstitucion);
                }
            }
            for (SitioEntrenamiento sitioEntrenamientoListSitioEntrenamiento : estado.getSitioEntrenamientoList()) {
                Estado oldIdEstadoOfSitioEntrenamientoListSitioEntrenamiento = sitioEntrenamientoListSitioEntrenamiento.getIdEstado();
                sitioEntrenamientoListSitioEntrenamiento.setIdEstado(estado);
                sitioEntrenamientoListSitioEntrenamiento = em.merge(sitioEntrenamientoListSitioEntrenamiento);
                if (oldIdEstadoOfSitioEntrenamientoListSitioEntrenamiento != null) {
                    oldIdEstadoOfSitioEntrenamientoListSitioEntrenamiento.getSitioEntrenamientoList().remove(sitioEntrenamientoListSitioEntrenamiento);
                    oldIdEstadoOfSitioEntrenamientoListSitioEntrenamiento = em.merge(oldIdEstadoOfSitioEntrenamientoListSitioEntrenamiento);
                }
            }
            for (Departamento departamentoListDepartamento : estado.getDepartamentoList()) {
                Estado oldIdEstadoOfDepartamentoListDepartamento = departamentoListDepartamento.getIdEstado();
                departamentoListDepartamento.setIdEstado(estado);
                departamentoListDepartamento = em.merge(departamentoListDepartamento);
                if (oldIdEstadoOfDepartamentoListDepartamento != null) {
                    oldIdEstadoOfDepartamentoListDepartamento.getDepartamentoList().remove(departamentoListDepartamento);
                    oldIdEstadoOfDepartamentoListDepartamento = em.merge(oldIdEstadoOfDepartamentoListDepartamento);
                }
            }
            for (Usuarios usuariosListUsuarios : estado.getUsuariosList()) {
                Estado oldIdEstadoOfUsuariosListUsuarios = usuariosListUsuarios.getIdEstado();
                usuariosListUsuarios.setIdEstado(estado);
                usuariosListUsuarios = em.merge(usuariosListUsuarios);
                if (oldIdEstadoOfUsuariosListUsuarios != null) {
                    oldIdEstadoOfUsuariosListUsuarios.getUsuariosList().remove(usuariosListUsuarios);
                    oldIdEstadoOfUsuariosListUsuarios = em.merge(oldIdEstadoOfUsuariosListUsuarios);
                }
            }
            for (AtletaDisciplina atletaDisciplinaListAtletaDisciplina : estado.getAtletaDisciplinaList()) {
                Estado oldIdEstadoOfAtletaDisciplinaListAtletaDisciplina = atletaDisciplinaListAtletaDisciplina.getIdEstado();
                atletaDisciplinaListAtletaDisciplina.setIdEstado(estado);
                atletaDisciplinaListAtletaDisciplina = em.merge(atletaDisciplinaListAtletaDisciplina);
                if (oldIdEstadoOfAtletaDisciplinaListAtletaDisciplina != null) {
                    oldIdEstadoOfAtletaDisciplinaListAtletaDisciplina.getAtletaDisciplinaList().remove(atletaDisciplinaListAtletaDisciplina);
                    oldIdEstadoOfAtletaDisciplinaListAtletaDisciplina = em.merge(oldIdEstadoOfAtletaDisciplinaListAtletaDisciplina);
                }
            }
            for (Tutor tutorListTutor : estado.getTutorList()) {
                Estado oldIdEstadoOfTutorListTutor = tutorListTutor.getIdEstado();
                tutorListTutor.setIdEstado(estado);
                tutorListTutor = em.merge(tutorListTutor);
                if (oldIdEstadoOfTutorListTutor != null) {
                    oldIdEstadoOfTutorListTutor.getTutorList().remove(tutorListTutor);
                    oldIdEstadoOfTutorListTutor = em.merge(oldIdEstadoOfTutorListTutor);
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

    public void edit(Estado estado) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Estado persistentEstado = em.find(Estado.class, estado.getIdEstado());
            List<Disciplinas> disciplinasListOld = persistentEstado.getDisciplinasList();
            List<Disciplinas> disciplinasListNew = estado.getDisciplinasList();
            List<Atleta> atletaListOld = persistentEstado.getAtletaList();
            List<Atleta> atletaListNew = estado.getAtletaList();
            List<Diagnostico> diagnosticoListOld = persistentEstado.getDiagnosticoList();
            List<Diagnostico> diagnosticoListNew = estado.getDiagnosticoList();
            List<Roles> rolesListOld = persistentEstado.getRolesList();
            List<Roles> rolesListNew = estado.getRolesList();
            List<Institucion> institucionListOld = persistentEstado.getInstitucionList();
            List<Institucion> institucionListNew = estado.getInstitucionList();
            List<SitioEntrenamiento> sitioEntrenamientoListOld = persistentEstado.getSitioEntrenamientoList();
            List<SitioEntrenamiento> sitioEntrenamientoListNew = estado.getSitioEntrenamientoList();
            List<Departamento> departamentoListOld = persistentEstado.getDepartamentoList();
            List<Departamento> departamentoListNew = estado.getDepartamentoList();
            List<Usuarios> usuariosListOld = persistentEstado.getUsuariosList();
            List<Usuarios> usuariosListNew = estado.getUsuariosList();
            List<AtletaDisciplina> atletaDisciplinaListOld = persistentEstado.getAtletaDisciplinaList();
            List<AtletaDisciplina> atletaDisciplinaListNew = estado.getAtletaDisciplinaList();
            List<Tutor> tutorListOld = persistentEstado.getTutorList();
            List<Tutor> tutorListNew = estado.getTutorList();
            List<Disciplinas> attachedDisciplinasListNew = new ArrayList<Disciplinas>();
            for (Disciplinas disciplinasListNewDisciplinasToAttach : disciplinasListNew) {
                disciplinasListNewDisciplinasToAttach = em.getReference(disciplinasListNewDisciplinasToAttach.getClass(), disciplinasListNewDisciplinasToAttach.getIdDisciplina());
                attachedDisciplinasListNew.add(disciplinasListNewDisciplinasToAttach);
            }
            disciplinasListNew = attachedDisciplinasListNew;
            estado.setDisciplinasList(disciplinasListNew);
            List<Atleta> attachedAtletaListNew = new ArrayList<Atleta>();
            for (Atleta atletaListNewAtletaToAttach : atletaListNew) {
                atletaListNewAtletaToAttach = em.getReference(atletaListNewAtletaToAttach.getClass(), atletaListNewAtletaToAttach.getIdAtleta());
                attachedAtletaListNew.add(atletaListNewAtletaToAttach);
            }
            atletaListNew = attachedAtletaListNew;
            estado.setAtletaList(atletaListNew);
            List<Diagnostico> attachedDiagnosticoListNew = new ArrayList<Diagnostico>();
            for (Diagnostico diagnosticoListNewDiagnosticoToAttach : diagnosticoListNew) {
                diagnosticoListNewDiagnosticoToAttach = em.getReference(diagnosticoListNewDiagnosticoToAttach.getClass(), diagnosticoListNewDiagnosticoToAttach.getIdDiagnostico());
                attachedDiagnosticoListNew.add(diagnosticoListNewDiagnosticoToAttach);
            }
            diagnosticoListNew = attachedDiagnosticoListNew;
            estado.setDiagnosticoList(diagnosticoListNew);
            List<Roles> attachedRolesListNew = new ArrayList<Roles>();
            for (Roles rolesListNewRolesToAttach : rolesListNew) {
                rolesListNewRolesToAttach = em.getReference(rolesListNewRolesToAttach.getClass(), rolesListNewRolesToAttach.getIdRol());
                attachedRolesListNew.add(rolesListNewRolesToAttach);
            }
            rolesListNew = attachedRolesListNew;
            estado.setRolesList(rolesListNew);
            List<Institucion> attachedInstitucionListNew = new ArrayList<Institucion>();
            for (Institucion institucionListNewInstitucionToAttach : institucionListNew) {
                institucionListNewInstitucionToAttach = em.getReference(institucionListNewInstitucionToAttach.getClass(), institucionListNewInstitucionToAttach.getIdIntitucion());
                attachedInstitucionListNew.add(institucionListNewInstitucionToAttach);
            }
            institucionListNew = attachedInstitucionListNew;
            estado.setInstitucionList(institucionListNew);
            List<SitioEntrenamiento> attachedSitioEntrenamientoListNew = new ArrayList<SitioEntrenamiento>();
            for (SitioEntrenamiento sitioEntrenamientoListNewSitioEntrenamientoToAttach : sitioEntrenamientoListNew) {
                sitioEntrenamientoListNewSitioEntrenamientoToAttach = em.getReference(sitioEntrenamientoListNewSitioEntrenamientoToAttach.getClass(), sitioEntrenamientoListNewSitioEntrenamientoToAttach.getIdSitioEntrenamiento());
                attachedSitioEntrenamientoListNew.add(sitioEntrenamientoListNewSitioEntrenamientoToAttach);
            }
            sitioEntrenamientoListNew = attachedSitioEntrenamientoListNew;
            estado.setSitioEntrenamientoList(sitioEntrenamientoListNew);
            List<Departamento> attachedDepartamentoListNew = new ArrayList<Departamento>();
            for (Departamento departamentoListNewDepartamentoToAttach : departamentoListNew) {
                departamentoListNewDepartamentoToAttach = em.getReference(departamentoListNewDepartamentoToAttach.getClass(), departamentoListNewDepartamentoToAttach.getIdDepartamento());
                attachedDepartamentoListNew.add(departamentoListNewDepartamentoToAttach);
            }
            departamentoListNew = attachedDepartamentoListNew;
            estado.setDepartamentoList(departamentoListNew);
            List<Usuarios> attachedUsuariosListNew = new ArrayList<Usuarios>();
            for (Usuarios usuariosListNewUsuariosToAttach : usuariosListNew) {
                usuariosListNewUsuariosToAttach = em.getReference(usuariosListNewUsuariosToAttach.getClass(), usuariosListNewUsuariosToAttach.getIdUsuario());
                attachedUsuariosListNew.add(usuariosListNewUsuariosToAttach);
            }
            usuariosListNew = attachedUsuariosListNew;
            estado.setUsuariosList(usuariosListNew);
            List<AtletaDisciplina> attachedAtletaDisciplinaListNew = new ArrayList<AtletaDisciplina>();
            for (AtletaDisciplina atletaDisciplinaListNewAtletaDisciplinaToAttach : atletaDisciplinaListNew) {
                atletaDisciplinaListNewAtletaDisciplinaToAttach = em.getReference(atletaDisciplinaListNewAtletaDisciplinaToAttach.getClass(), atletaDisciplinaListNewAtletaDisciplinaToAttach.getIdAtletaDisciplina());
                attachedAtletaDisciplinaListNew.add(atletaDisciplinaListNewAtletaDisciplinaToAttach);
            }
            atletaDisciplinaListNew = attachedAtletaDisciplinaListNew;
            estado.setAtletaDisciplinaList(atletaDisciplinaListNew);
            List<Tutor> attachedTutorListNew = new ArrayList<Tutor>();
            for (Tutor tutorListNewTutorToAttach : tutorListNew) {
                tutorListNewTutorToAttach = em.getReference(tutorListNewTutorToAttach.getClass(), tutorListNewTutorToAttach.getIdTutor());
                attachedTutorListNew.add(tutorListNewTutorToAttach);
            }
            tutorListNew = attachedTutorListNew;
            estado.setTutorList(tutorListNew);
            estado = em.merge(estado);
            for (Disciplinas disciplinasListOldDisciplinas : disciplinasListOld) {
                if (!disciplinasListNew.contains(disciplinasListOldDisciplinas)) {
                    disciplinasListOldDisciplinas.setIdEstado(null);
                    disciplinasListOldDisciplinas = em.merge(disciplinasListOldDisciplinas);
                }
            }
            for (Disciplinas disciplinasListNewDisciplinas : disciplinasListNew) {
                if (!disciplinasListOld.contains(disciplinasListNewDisciplinas)) {
                    Estado oldIdEstadoOfDisciplinasListNewDisciplinas = disciplinasListNewDisciplinas.getIdEstado();
                    disciplinasListNewDisciplinas.setIdEstado(estado);
                    disciplinasListNewDisciplinas = em.merge(disciplinasListNewDisciplinas);
                    if (oldIdEstadoOfDisciplinasListNewDisciplinas != null && !oldIdEstadoOfDisciplinasListNewDisciplinas.equals(estado)) {
                        oldIdEstadoOfDisciplinasListNewDisciplinas.getDisciplinasList().remove(disciplinasListNewDisciplinas);
                        oldIdEstadoOfDisciplinasListNewDisciplinas = em.merge(oldIdEstadoOfDisciplinasListNewDisciplinas);
                    }
                }
            }
            for (Atleta atletaListOldAtleta : atletaListOld) {
                if (!atletaListNew.contains(atletaListOldAtleta)) {
                    atletaListOldAtleta.setIdEstado(null);
                    atletaListOldAtleta = em.merge(atletaListOldAtleta);
                }
            }
            for (Atleta atletaListNewAtleta : atletaListNew) {
                if (!atletaListOld.contains(atletaListNewAtleta)) {
                    Estado oldIdEstadoOfAtletaListNewAtleta = atletaListNewAtleta.getIdEstado();
                    atletaListNewAtleta.setIdEstado(estado);
                    atletaListNewAtleta = em.merge(atletaListNewAtleta);
                    if (oldIdEstadoOfAtletaListNewAtleta != null && !oldIdEstadoOfAtletaListNewAtleta.equals(estado)) {
                        oldIdEstadoOfAtletaListNewAtleta.getAtletaList().remove(atletaListNewAtleta);
                        oldIdEstadoOfAtletaListNewAtleta = em.merge(oldIdEstadoOfAtletaListNewAtleta);
                    }
                }
            }
            for (Diagnostico diagnosticoListOldDiagnostico : diagnosticoListOld) {
                if (!diagnosticoListNew.contains(diagnosticoListOldDiagnostico)) {
                    diagnosticoListOldDiagnostico.setIdEstado(null);
                    diagnosticoListOldDiagnostico = em.merge(diagnosticoListOldDiagnostico);
                }
            }
            for (Diagnostico diagnosticoListNewDiagnostico : diagnosticoListNew) {
                if (!diagnosticoListOld.contains(diagnosticoListNewDiagnostico)) {
                    Estado oldIdEstadoOfDiagnosticoListNewDiagnostico = diagnosticoListNewDiagnostico.getIdEstado();
                    diagnosticoListNewDiagnostico.setIdEstado(estado);
                    diagnosticoListNewDiagnostico = em.merge(diagnosticoListNewDiagnostico);
                    if (oldIdEstadoOfDiagnosticoListNewDiagnostico != null && !oldIdEstadoOfDiagnosticoListNewDiagnostico.equals(estado)) {
                        oldIdEstadoOfDiagnosticoListNewDiagnostico.getDiagnosticoList().remove(diagnosticoListNewDiagnostico);
                        oldIdEstadoOfDiagnosticoListNewDiagnostico = em.merge(oldIdEstadoOfDiagnosticoListNewDiagnostico);
                    }
                }
            }
            for (Roles rolesListOldRoles : rolesListOld) {
                if (!rolesListNew.contains(rolesListOldRoles)) {
                    rolesListOldRoles.setIdEstado(null);
                    rolesListOldRoles = em.merge(rolesListOldRoles);
                }
            }
            for (Roles rolesListNewRoles : rolesListNew) {
                if (!rolesListOld.contains(rolesListNewRoles)) {
                    Estado oldIdEstadoOfRolesListNewRoles = rolesListNewRoles.getIdEstado();
                    rolesListNewRoles.setIdEstado(estado);
                    rolesListNewRoles = em.merge(rolesListNewRoles);
                    if (oldIdEstadoOfRolesListNewRoles != null && !oldIdEstadoOfRolesListNewRoles.equals(estado)) {
                        oldIdEstadoOfRolesListNewRoles.getRolesList().remove(rolesListNewRoles);
                        oldIdEstadoOfRolesListNewRoles = em.merge(oldIdEstadoOfRolesListNewRoles);
                    }
                }
            }
            for (Institucion institucionListOldInstitucion : institucionListOld) {
                if (!institucionListNew.contains(institucionListOldInstitucion)) {
                    institucionListOldInstitucion.setIdEstado(null);
                    institucionListOldInstitucion = em.merge(institucionListOldInstitucion);
                }
            }
            for (Institucion institucionListNewInstitucion : institucionListNew) {
                if (!institucionListOld.contains(institucionListNewInstitucion)) {
                    Estado oldIdEstadoOfInstitucionListNewInstitucion = institucionListNewInstitucion.getIdEstado();
                    institucionListNewInstitucion.setIdEstado(estado);
                    institucionListNewInstitucion = em.merge(institucionListNewInstitucion);
                    if (oldIdEstadoOfInstitucionListNewInstitucion != null && !oldIdEstadoOfInstitucionListNewInstitucion.equals(estado)) {
                        oldIdEstadoOfInstitucionListNewInstitucion.getInstitucionList().remove(institucionListNewInstitucion);
                        oldIdEstadoOfInstitucionListNewInstitucion = em.merge(oldIdEstadoOfInstitucionListNewInstitucion);
                    }
                }
            }
            for (SitioEntrenamiento sitioEntrenamientoListOldSitioEntrenamiento : sitioEntrenamientoListOld) {
                if (!sitioEntrenamientoListNew.contains(sitioEntrenamientoListOldSitioEntrenamiento)) {
                    sitioEntrenamientoListOldSitioEntrenamiento.setIdEstado(null);
                    sitioEntrenamientoListOldSitioEntrenamiento = em.merge(sitioEntrenamientoListOldSitioEntrenamiento);
                }
            }
            for (SitioEntrenamiento sitioEntrenamientoListNewSitioEntrenamiento : sitioEntrenamientoListNew) {
                if (!sitioEntrenamientoListOld.contains(sitioEntrenamientoListNewSitioEntrenamiento)) {
                    Estado oldIdEstadoOfSitioEntrenamientoListNewSitioEntrenamiento = sitioEntrenamientoListNewSitioEntrenamiento.getIdEstado();
                    sitioEntrenamientoListNewSitioEntrenamiento.setIdEstado(estado);
                    sitioEntrenamientoListNewSitioEntrenamiento = em.merge(sitioEntrenamientoListNewSitioEntrenamiento);
                    if (oldIdEstadoOfSitioEntrenamientoListNewSitioEntrenamiento != null && !oldIdEstadoOfSitioEntrenamientoListNewSitioEntrenamiento.equals(estado)) {
                        oldIdEstadoOfSitioEntrenamientoListNewSitioEntrenamiento.getSitioEntrenamientoList().remove(sitioEntrenamientoListNewSitioEntrenamiento);
                        oldIdEstadoOfSitioEntrenamientoListNewSitioEntrenamiento = em.merge(oldIdEstadoOfSitioEntrenamientoListNewSitioEntrenamiento);
                    }
                }
            }
            for (Departamento departamentoListOldDepartamento : departamentoListOld) {
                if (!departamentoListNew.contains(departamentoListOldDepartamento)) {
                    departamentoListOldDepartamento.setIdEstado(null);
                    departamentoListOldDepartamento = em.merge(departamentoListOldDepartamento);
                }
            }
            for (Departamento departamentoListNewDepartamento : departamentoListNew) {
                if (!departamentoListOld.contains(departamentoListNewDepartamento)) {
                    Estado oldIdEstadoOfDepartamentoListNewDepartamento = departamentoListNewDepartamento.getIdEstado();
                    departamentoListNewDepartamento.setIdEstado(estado);
                    departamentoListNewDepartamento = em.merge(departamentoListNewDepartamento);
                    if (oldIdEstadoOfDepartamentoListNewDepartamento != null && !oldIdEstadoOfDepartamentoListNewDepartamento.equals(estado)) {
                        oldIdEstadoOfDepartamentoListNewDepartamento.getDepartamentoList().remove(departamentoListNewDepartamento);
                        oldIdEstadoOfDepartamentoListNewDepartamento = em.merge(oldIdEstadoOfDepartamentoListNewDepartamento);
                    }
                }
            }
            for (Usuarios usuariosListOldUsuarios : usuariosListOld) {
                if (!usuariosListNew.contains(usuariosListOldUsuarios)) {
                    usuariosListOldUsuarios.setIdEstado(null);
                    usuariosListOldUsuarios = em.merge(usuariosListOldUsuarios);
                }
            }
            for (Usuarios usuariosListNewUsuarios : usuariosListNew) {
                if (!usuariosListOld.contains(usuariosListNewUsuarios)) {
                    Estado oldIdEstadoOfUsuariosListNewUsuarios = usuariosListNewUsuarios.getIdEstado();
                    usuariosListNewUsuarios.setIdEstado(estado);
                    usuariosListNewUsuarios = em.merge(usuariosListNewUsuarios);
                    if (oldIdEstadoOfUsuariosListNewUsuarios != null && !oldIdEstadoOfUsuariosListNewUsuarios.equals(estado)) {
                        oldIdEstadoOfUsuariosListNewUsuarios.getUsuariosList().remove(usuariosListNewUsuarios);
                        oldIdEstadoOfUsuariosListNewUsuarios = em.merge(oldIdEstadoOfUsuariosListNewUsuarios);
                    }
                }
            }
            for (AtletaDisciplina atletaDisciplinaListOldAtletaDisciplina : atletaDisciplinaListOld) {
                if (!atletaDisciplinaListNew.contains(atletaDisciplinaListOldAtletaDisciplina)) {
                    atletaDisciplinaListOldAtletaDisciplina.setIdEstado(null);
                    atletaDisciplinaListOldAtletaDisciplina = em.merge(atletaDisciplinaListOldAtletaDisciplina);
                }
            }
            for (AtletaDisciplina atletaDisciplinaListNewAtletaDisciplina : atletaDisciplinaListNew) {
                if (!atletaDisciplinaListOld.contains(atletaDisciplinaListNewAtletaDisciplina)) {
                    Estado oldIdEstadoOfAtletaDisciplinaListNewAtletaDisciplina = atletaDisciplinaListNewAtletaDisciplina.getIdEstado();
                    atletaDisciplinaListNewAtletaDisciplina.setIdEstado(estado);
                    atletaDisciplinaListNewAtletaDisciplina = em.merge(atletaDisciplinaListNewAtletaDisciplina);
                    if (oldIdEstadoOfAtletaDisciplinaListNewAtletaDisciplina != null && !oldIdEstadoOfAtletaDisciplinaListNewAtletaDisciplina.equals(estado)) {
                        oldIdEstadoOfAtletaDisciplinaListNewAtletaDisciplina.getAtletaDisciplinaList().remove(atletaDisciplinaListNewAtletaDisciplina);
                        oldIdEstadoOfAtletaDisciplinaListNewAtletaDisciplina = em.merge(oldIdEstadoOfAtletaDisciplinaListNewAtletaDisciplina);
                    }
                }
            }
            for (Tutor tutorListOldTutor : tutorListOld) {
                if (!tutorListNew.contains(tutorListOldTutor)) {
                    tutorListOldTutor.setIdEstado(null);
                    tutorListOldTutor = em.merge(tutorListOldTutor);
                }
            }
            for (Tutor tutorListNewTutor : tutorListNew) {
                if (!tutorListOld.contains(tutorListNewTutor)) {
                    Estado oldIdEstadoOfTutorListNewTutor = tutorListNewTutor.getIdEstado();
                    tutorListNewTutor.setIdEstado(estado);
                    tutorListNewTutor = em.merge(tutorListNewTutor);
                    if (oldIdEstadoOfTutorListNewTutor != null && !oldIdEstadoOfTutorListNewTutor.equals(estado)) {
                        oldIdEstadoOfTutorListNewTutor.getTutorList().remove(tutorListNewTutor);
                        oldIdEstadoOfTutorListNewTutor = em.merge(oldIdEstadoOfTutorListNewTutor);
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
                Integer id = estado.getIdEstado();
                if (findEstado(id) == null) {
                    throw new NonexistentEntityException("The estado with id " + id + " no longer exists.");
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
            Estado estado;
            try {
                estado = em.getReference(Estado.class, id);
                estado.getIdEstado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estado with id " + id + " no longer exists.", enfe);
            }
            List<Disciplinas> disciplinasList = estado.getDisciplinasList();
            for (Disciplinas disciplinasListDisciplinas : disciplinasList) {
                disciplinasListDisciplinas.setIdEstado(null);
                disciplinasListDisciplinas = em.merge(disciplinasListDisciplinas);
            }
            List<Atleta> atletaList = estado.getAtletaList();
            for (Atleta atletaListAtleta : atletaList) {
                atletaListAtleta.setIdEstado(null);
                atletaListAtleta = em.merge(atletaListAtleta);
            }
            List<Diagnostico> diagnosticoList = estado.getDiagnosticoList();
            for (Diagnostico diagnosticoListDiagnostico : diagnosticoList) {
                diagnosticoListDiagnostico.setIdEstado(null);
                diagnosticoListDiagnostico = em.merge(diagnosticoListDiagnostico);
            }
            List<Roles> rolesList = estado.getRolesList();
            for (Roles rolesListRoles : rolesList) {
                rolesListRoles.setIdEstado(null);
                rolesListRoles = em.merge(rolesListRoles);
            }
            List<Institucion> institucionList = estado.getInstitucionList();
            for (Institucion institucionListInstitucion : institucionList) {
                institucionListInstitucion.setIdEstado(null);
                institucionListInstitucion = em.merge(institucionListInstitucion);
            }
            List<SitioEntrenamiento> sitioEntrenamientoList = estado.getSitioEntrenamientoList();
            for (SitioEntrenamiento sitioEntrenamientoListSitioEntrenamiento : sitioEntrenamientoList) {
                sitioEntrenamientoListSitioEntrenamiento.setIdEstado(null);
                sitioEntrenamientoListSitioEntrenamiento = em.merge(sitioEntrenamientoListSitioEntrenamiento);
            }
            List<Departamento> departamentoList = estado.getDepartamentoList();
            for (Departamento departamentoListDepartamento : departamentoList) {
                departamentoListDepartamento.setIdEstado(null);
                departamentoListDepartamento = em.merge(departamentoListDepartamento);
            }
            List<Usuarios> usuariosList = estado.getUsuariosList();
            for (Usuarios usuariosListUsuarios : usuariosList) {
                usuariosListUsuarios.setIdEstado(null);
                usuariosListUsuarios = em.merge(usuariosListUsuarios);
            }
            List<AtletaDisciplina> atletaDisciplinaList = estado.getAtletaDisciplinaList();
            for (AtletaDisciplina atletaDisciplinaListAtletaDisciplina : atletaDisciplinaList) {
                atletaDisciplinaListAtletaDisciplina.setIdEstado(null);
                atletaDisciplinaListAtletaDisciplina = em.merge(atletaDisciplinaListAtletaDisciplina);
            }
            List<Tutor> tutorList = estado.getTutorList();
            for (Tutor tutorListTutor : tutorList) {
                tutorListTutor.setIdEstado(null);
                tutorListTutor = em.merge(tutorListTutor);
            }
            em.remove(estado);
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

    public List<Estado> findEstadoEntities() {
        return findEstadoEntities(true, -1, -1);
    }

    public List<Estado> findEstadoEntities(int maxResults, int firstResult) {
        return findEstadoEntities(false, maxResults, firstResult);
    }

    private List<Estado> findEstadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estado.class));
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

    public Estado findEstado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estado> rt = cq.from(Estado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
