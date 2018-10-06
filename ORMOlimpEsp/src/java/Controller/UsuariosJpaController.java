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
import Entities.Roles;
import Entities.Estado;
import Entities.Atleta;
import java.util.ArrayList;
import java.util.List;
import Entities.Tutor;
import Entities.Usuarios;
import static Security.getMD5.getMD5;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;

/**
 *
 * @author axel.medina
 */
public class UsuariosJpaController implements Serializable {

    private static final long serialVersionUID = 8090706556962848352L;

    public UsuariosJpaController() {
        this.utx = utx;
        this.emf = Persistence.createEntityManagerFactory("ORMOlimpEspPU");
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarios usuarios) throws RollbackFailureException, Exception {
        if (usuarios.getAtletaList() == null) {
            usuarios.setAtletaList(new ArrayList<Atleta>());
        }
        if (usuarios.getAtletaList1() == null) {
            usuarios.setAtletaList1(new ArrayList<Atleta>());
        }
        if (usuarios.getTutorList() == null) {
            usuarios.setTutorList(new ArrayList<Tutor>());
        }
        if (usuarios.getTutorList1() == null) {
            usuarios.setTutorList1(new ArrayList<Tutor>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Roles idRol = usuarios.getIdRol();
            if (idRol != null) {
                idRol = em.getReference(idRol.getClass(), idRol.getIdRol());
                usuarios.setIdRol(idRol);
            }
            Estado idEstado = usuarios.getIdEstado();
            if (idEstado != null) {
                idEstado = em.getReference(idEstado.getClass(), idEstado.getIdEstado());
                usuarios.setIdEstado(idEstado);
            }
            List<Atleta> attachedAtletaList = new ArrayList<Atleta>();
            for (Atleta atletaListAtletaToAttach : usuarios.getAtletaList()) {
                atletaListAtletaToAttach = em.getReference(atletaListAtletaToAttach.getClass(), atletaListAtletaToAttach.getIdAtleta());
                attachedAtletaList.add(atletaListAtletaToAttach);
            }
            usuarios.setAtletaList(attachedAtletaList);
            List<Atleta> attachedAtletaList1 = new ArrayList<Atleta>();
            for (Atleta atletaList1AtletaToAttach : usuarios.getAtletaList1()) {
                atletaList1AtletaToAttach = em.getReference(atletaList1AtletaToAttach.getClass(), atletaList1AtletaToAttach.getIdAtleta());
                attachedAtletaList1.add(atletaList1AtletaToAttach);
            }
            usuarios.setAtletaList1(attachedAtletaList1);
            List<Tutor> attachedTutorList = new ArrayList<Tutor>();
            for (Tutor tutorListTutorToAttach : usuarios.getTutorList()) {
                tutorListTutorToAttach = em.getReference(tutorListTutorToAttach.getClass(), tutorListTutorToAttach.getIdTutor());
                attachedTutorList.add(tutorListTutorToAttach);
            }
            usuarios.setTutorList(attachedTutorList);
            List<Tutor> attachedTutorList1 = new ArrayList<Tutor>();
            for (Tutor tutorList1TutorToAttach : usuarios.getTutorList1()) {
                tutorList1TutorToAttach = em.getReference(tutorList1TutorToAttach.getClass(), tutorList1TutorToAttach.getIdTutor());
                attachedTutorList1.add(tutorList1TutorToAttach);
            }
            usuarios.setTutorList1(attachedTutorList1);
            em.persist(usuarios);
            if (idRol != null) {
                idRol.getUsuariosList().add(usuarios);
                idRol = em.merge(idRol);
            }
            if (idEstado != null) {
                idEstado.getUsuariosList().add(usuarios);
                idEstado = em.merge(idEstado);
            }
            for (Atleta atletaListAtleta : usuarios.getAtletaList()) {
                Usuarios oldIdUsuarioIngresoOfAtletaListAtleta = atletaListAtleta.getIdUsuarioIngreso();
                atletaListAtleta.setIdUsuarioIngreso(usuarios);
                atletaListAtleta = em.merge(atletaListAtleta);
                if (oldIdUsuarioIngresoOfAtletaListAtleta != null) {
                    oldIdUsuarioIngresoOfAtletaListAtleta.getAtletaList().remove(atletaListAtleta);
                    oldIdUsuarioIngresoOfAtletaListAtleta = em.merge(oldIdUsuarioIngresoOfAtletaListAtleta);
                }
            }
            for (Atleta atletaList1Atleta : usuarios.getAtletaList1()) {
                Usuarios oldIdUsuarioActualizacionOfAtletaList1Atleta = atletaList1Atleta.getIdUsuarioActualizacion();
                atletaList1Atleta.setIdUsuarioActualizacion(usuarios);
                atletaList1Atleta = em.merge(atletaList1Atleta);
                if (oldIdUsuarioActualizacionOfAtletaList1Atleta != null) {
                    oldIdUsuarioActualizacionOfAtletaList1Atleta.getAtletaList1().remove(atletaList1Atleta);
                    oldIdUsuarioActualizacionOfAtletaList1Atleta = em.merge(oldIdUsuarioActualizacionOfAtletaList1Atleta);
                }
            }
            for (Tutor tutorListTutor : usuarios.getTutorList()) {
                Usuarios oldIdUsuarioIngresoOfTutorListTutor = tutorListTutor.getIdUsuarioIngreso();
                tutorListTutor.setIdUsuarioIngreso(usuarios);
                tutorListTutor = em.merge(tutorListTutor);
                if (oldIdUsuarioIngresoOfTutorListTutor != null) {
                    oldIdUsuarioIngresoOfTutorListTutor.getTutorList().remove(tutorListTutor);
                    oldIdUsuarioIngresoOfTutorListTutor = em.merge(oldIdUsuarioIngresoOfTutorListTutor);
                }
            }
            for (Tutor tutorList1Tutor : usuarios.getTutorList1()) {
                Usuarios oldIdUsuarioActualizacionOfTutorList1Tutor = tutorList1Tutor.getIdUsuarioActualizacion();
                tutorList1Tutor.setIdUsuarioActualizacion(usuarios);
                tutorList1Tutor = em.merge(tutorList1Tutor);
                if (oldIdUsuarioActualizacionOfTutorList1Tutor != null) {
                    oldIdUsuarioActualizacionOfTutorList1Tutor.getTutorList1().remove(tutorList1Tutor);
                    oldIdUsuarioActualizacionOfTutorList1Tutor = em.merge(oldIdUsuarioActualizacionOfTutorList1Tutor);
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

    public void edit(Usuarios usuarios) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Usuarios persistentUsuarios = em.find(Usuarios.class, usuarios.getIdUsuario());
            Roles idRolOld = persistentUsuarios.getIdRol();
            Roles idRolNew = usuarios.getIdRol();
            Estado idEstadoOld = persistentUsuarios.getIdEstado();
            Estado idEstadoNew = usuarios.getIdEstado();
            List<Atleta> atletaListOld = persistentUsuarios.getAtletaList();
            List<Atleta> atletaListNew = usuarios.getAtletaList();
            List<Atleta> atletaList1Old = persistentUsuarios.getAtletaList1();
            List<Atleta> atletaList1New = usuarios.getAtletaList1();
            List<Tutor> tutorListOld = persistentUsuarios.getTutorList();
            List<Tutor> tutorListNew = usuarios.getTutorList();
            List<Tutor> tutorList1Old = persistentUsuarios.getTutorList1();
            List<Tutor> tutorList1New = usuarios.getTutorList1();
            if (idRolNew != null) {
                idRolNew = em.getReference(idRolNew.getClass(), idRolNew.getIdRol());
                usuarios.setIdRol(idRolNew);
            }
            if (idEstadoNew != null) {
                idEstadoNew = em.getReference(idEstadoNew.getClass(), idEstadoNew.getIdEstado());
                usuarios.setIdEstado(idEstadoNew);
            }
            List<Atleta> attachedAtletaListNew = new ArrayList<Atleta>();
            for (Atleta atletaListNewAtletaToAttach : atletaListNew) {
                atletaListNewAtletaToAttach = em.getReference(atletaListNewAtletaToAttach.getClass(), atletaListNewAtletaToAttach.getIdAtleta());
                attachedAtletaListNew.add(atletaListNewAtletaToAttach);
            }
            atletaListNew = attachedAtletaListNew;
            usuarios.setAtletaList(atletaListNew);
            List<Atleta> attachedAtletaList1New = new ArrayList<Atleta>();
            for (Atleta atletaList1NewAtletaToAttach : atletaList1New) {
                atletaList1NewAtletaToAttach = em.getReference(atletaList1NewAtletaToAttach.getClass(), atletaList1NewAtletaToAttach.getIdAtleta());
                attachedAtletaList1New.add(atletaList1NewAtletaToAttach);
            }
            atletaList1New = attachedAtletaList1New;
            usuarios.setAtletaList1(atletaList1New);
            List<Tutor> attachedTutorListNew = new ArrayList<Tutor>();
            for (Tutor tutorListNewTutorToAttach : tutorListNew) {
                tutorListNewTutorToAttach = em.getReference(tutorListNewTutorToAttach.getClass(), tutorListNewTutorToAttach.getIdTutor());
                attachedTutorListNew.add(tutorListNewTutorToAttach);
            }
            tutorListNew = attachedTutorListNew;
            usuarios.setTutorList(tutorListNew);
            List<Tutor> attachedTutorList1New = new ArrayList<Tutor>();
            for (Tutor tutorList1NewTutorToAttach : tutorList1New) {
                tutorList1NewTutorToAttach = em.getReference(tutorList1NewTutorToAttach.getClass(), tutorList1NewTutorToAttach.getIdTutor());
                attachedTutorList1New.add(tutorList1NewTutorToAttach);
            }
            tutorList1New = attachedTutorList1New;
            usuarios.setTutorList1(tutorList1New);
            usuarios = em.merge(usuarios);
            if (idRolOld != null && !idRolOld.equals(idRolNew)) {
                idRolOld.getUsuariosList().remove(usuarios);
                idRolOld = em.merge(idRolOld);
            }
            if (idRolNew != null && !idRolNew.equals(idRolOld)) {
                idRolNew.getUsuariosList().add(usuarios);
                idRolNew = em.merge(idRolNew);
            }
            if (idEstadoOld != null && !idEstadoOld.equals(idEstadoNew)) {
                idEstadoOld.getUsuariosList().remove(usuarios);
                idEstadoOld = em.merge(idEstadoOld);
            }
            if (idEstadoNew != null && !idEstadoNew.equals(idEstadoOld)) {
                idEstadoNew.getUsuariosList().add(usuarios);
                idEstadoNew = em.merge(idEstadoNew);
            }
            for (Atleta atletaListOldAtleta : atletaListOld) {
                if (!atletaListNew.contains(atletaListOldAtleta)) {
                    atletaListOldAtleta.setIdUsuarioIngreso(null);
                    atletaListOldAtleta = em.merge(atletaListOldAtleta);
                }
            }
            for (Atleta atletaListNewAtleta : atletaListNew) {
                if (!atletaListOld.contains(atletaListNewAtleta)) {
                    Usuarios oldIdUsuarioIngresoOfAtletaListNewAtleta = atletaListNewAtleta.getIdUsuarioIngreso();
                    atletaListNewAtleta.setIdUsuarioIngreso(usuarios);
                    atletaListNewAtleta = em.merge(atletaListNewAtleta);
                    if (oldIdUsuarioIngresoOfAtletaListNewAtleta != null && !oldIdUsuarioIngresoOfAtletaListNewAtleta.equals(usuarios)) {
                        oldIdUsuarioIngresoOfAtletaListNewAtleta.getAtletaList().remove(atletaListNewAtleta);
                        oldIdUsuarioIngresoOfAtletaListNewAtleta = em.merge(oldIdUsuarioIngresoOfAtletaListNewAtleta);
                    }
                }
            }
            for (Atleta atletaList1OldAtleta : atletaList1Old) {
                if (!atletaList1New.contains(atletaList1OldAtleta)) {
                    atletaList1OldAtleta.setIdUsuarioActualizacion(null);
                    atletaList1OldAtleta = em.merge(atletaList1OldAtleta);
                }
            }
            for (Atleta atletaList1NewAtleta : atletaList1New) {
                if (!atletaList1Old.contains(atletaList1NewAtleta)) {
                    Usuarios oldIdUsuarioActualizacionOfAtletaList1NewAtleta = atletaList1NewAtleta.getIdUsuarioActualizacion();
                    atletaList1NewAtleta.setIdUsuarioActualizacion(usuarios);
                    atletaList1NewAtleta = em.merge(atletaList1NewAtleta);
                    if (oldIdUsuarioActualizacionOfAtletaList1NewAtleta != null && !oldIdUsuarioActualizacionOfAtletaList1NewAtleta.equals(usuarios)) {
                        oldIdUsuarioActualizacionOfAtletaList1NewAtleta.getAtletaList1().remove(atletaList1NewAtleta);
                        oldIdUsuarioActualizacionOfAtletaList1NewAtleta = em.merge(oldIdUsuarioActualizacionOfAtletaList1NewAtleta);
                    }
                }
            }
            for (Tutor tutorListOldTutor : tutorListOld) {
                if (!tutorListNew.contains(tutorListOldTutor)) {
                    tutorListOldTutor.setIdUsuarioIngreso(null);
                    tutorListOldTutor = em.merge(tutorListOldTutor);
                }
            }
            for (Tutor tutorListNewTutor : tutorListNew) {
                if (!tutorListOld.contains(tutorListNewTutor)) {
                    Usuarios oldIdUsuarioIngresoOfTutorListNewTutor = tutorListNewTutor.getIdUsuarioIngreso();
                    tutorListNewTutor.setIdUsuarioIngreso(usuarios);
                    tutorListNewTutor = em.merge(tutorListNewTutor);
                    if (oldIdUsuarioIngresoOfTutorListNewTutor != null && !oldIdUsuarioIngresoOfTutorListNewTutor.equals(usuarios)) {
                        oldIdUsuarioIngresoOfTutorListNewTutor.getTutorList().remove(tutorListNewTutor);
                        oldIdUsuarioIngresoOfTutorListNewTutor = em.merge(oldIdUsuarioIngresoOfTutorListNewTutor);
                    }
                }
            }
            for (Tutor tutorList1OldTutor : tutorList1Old) {
                if (!tutorList1New.contains(tutorList1OldTutor)) {
                    tutorList1OldTutor.setIdUsuarioActualizacion(null);
                    tutorList1OldTutor = em.merge(tutorList1OldTutor);
                }
            }
            for (Tutor tutorList1NewTutor : tutorList1New) {
                if (!tutorList1Old.contains(tutorList1NewTutor)) {
                    Usuarios oldIdUsuarioActualizacionOfTutorList1NewTutor = tutorList1NewTutor.getIdUsuarioActualizacion();
                    tutorList1NewTutor.setIdUsuarioActualizacion(usuarios);
                    tutorList1NewTutor = em.merge(tutorList1NewTutor);
                    if (oldIdUsuarioActualizacionOfTutorList1NewTutor != null && !oldIdUsuarioActualizacionOfTutorList1NewTutor.equals(usuarios)) {
                        oldIdUsuarioActualizacionOfTutorList1NewTutor.getTutorList1().remove(tutorList1NewTutor);
                        oldIdUsuarioActualizacionOfTutorList1NewTutor = em.merge(oldIdUsuarioActualizacionOfTutorList1NewTutor);
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
                Integer id = usuarios.getIdUsuario();
                if (findUsuarios(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
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
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            Roles idRol = usuarios.getIdRol();
            if (idRol != null) {
                idRol.getUsuariosList().remove(usuarios);
                idRol = em.merge(idRol);
            }
            Estado idEstado = usuarios.getIdEstado();
            if (idEstado != null) {
                idEstado.getUsuariosList().remove(usuarios);
                idEstado = em.merge(idEstado);
            }
            List<Atleta> atletaList = usuarios.getAtletaList();
            for (Atleta atletaListAtleta : atletaList) {
                atletaListAtleta.setIdUsuarioIngreso(null);
                atletaListAtleta = em.merge(atletaListAtleta);
            }
            List<Atleta> atletaList1 = usuarios.getAtletaList1();
            for (Atleta atletaList1Atleta : atletaList1) {
                atletaList1Atleta.setIdUsuarioActualizacion(null);
                atletaList1Atleta = em.merge(atletaList1Atleta);
            }
            List<Tutor> tutorList = usuarios.getTutorList();
            for (Tutor tutorListTutor : tutorList) {
                tutorListTutor.setIdUsuarioIngreso(null);
                tutorListTutor = em.merge(tutorListTutor);
            }
            List<Tutor> tutorList1 = usuarios.getTutorList1();
            for (Tutor tutorList1Tutor : tutorList1) {
                tutorList1Tutor.setIdUsuarioActualizacion(null);
                tutorList1Tutor = em.merge(tutorList1Tutor);
            }
            em.remove(usuarios);
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

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarios.class));
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

    public Usuarios findUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public boolean getLogin(String name, String passwd) {
        EntityManager em = getEntityManager();
        boolean true_false = false;//70f4c687710d65276370d15781c4f48f
        try {
            
            Query login = em.createNamedQuery("Usuarios.findByLogin", Usuarios.class);
            login.setParameter("usuario", name).setParameter("password", getMD5(passwd));
            List<String> lista = login.getResultList();
            for (String s : lista) {
                true_false = true;
            }
            return true_false;
        } finally {
            em.close();
        }
    }
}
