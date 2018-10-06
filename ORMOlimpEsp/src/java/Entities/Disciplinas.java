/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author axel.medina
 */
@Entity
@Table(name = "disciplinas")
@NamedQueries({
    @NamedQuery(name = "Disciplinas.findAll", query = "SELECT d FROM Disciplinas d")
    , @NamedQuery(name = "Disciplinas.findByIdDisciplina", query = "SELECT d FROM Disciplinas d WHERE d.idDisciplina = :idDisciplina")
    , @NamedQuery(name = "Disciplinas.findByNombreDisciplina", query = "SELECT d FROM Disciplinas d WHERE d.nombreDisciplina = :nombreDisciplina")
    , @NamedQuery(name = "Disciplinas.findByFechaIngreso", query = "SELECT d FROM Disciplinas d WHERE d.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "Disciplinas.findByIdUsuarioIngreso", query = "SELECT d FROM Disciplinas d WHERE d.idUsuarioIngreso = :idUsuarioIngreso")
    , @NamedQuery(name = "Disciplinas.findByFechaActualizacion", query = "SELECT d FROM Disciplinas d WHERE d.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "Disciplinas.findByIdUsuarioActualizacion", query = "SELECT d FROM Disciplinas d WHERE d.idUsuarioActualizacion = :idUsuarioActualizacion")})
public class Disciplinas implements Serializable {

    private static final long serialVersionUID = -1282096363315192622L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_DISCIPLINA")
    private Integer idDisciplina;
    @Size(max = 100)
    @Column(name = "NOMBRE_DISCIPLINA")
    private String nombreDisciplina;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Column(name = "ID_USUARIO_INGRESO")
    private Integer idUsuarioIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Column(name = "ID_USUARIO_ACTUALIZACION")
    private Integer idUsuarioActualizacion;
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO")
    @ManyToOne
    private Estado idEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idDisciplina")
    private List<AtletaDisciplina> atletaDisciplinaList;

    public Disciplinas() {
    }

    public Disciplinas(Integer idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public Disciplinas(Integer idDisciplina, Date fechaIngreso, Date fechaActualizacion) {
        this.idDisciplina = idDisciplina;
        this.fechaIngreso = fechaIngreso;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Integer idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getNombreDisciplina() {
        return nombreDisciplina;
    }

    public void setNombreDisciplina(String nombreDisciplina) {
        this.nombreDisciplina = nombreDisciplina;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Integer getIdUsuarioIngreso() {
        return idUsuarioIngreso;
    }

    public void setIdUsuarioIngreso(Integer idUsuarioIngreso) {
        this.idUsuarioIngreso = idUsuarioIngreso;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getIdUsuarioActualizacion() {
        return idUsuarioActualizacion;
    }

    public void setIdUsuarioActualizacion(Integer idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
    }

    public Estado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
    }

    public List<AtletaDisciplina> getAtletaDisciplinaList() {
        return atletaDisciplinaList;
    }

    public void setAtletaDisciplinaList(List<AtletaDisciplina> atletaDisciplinaList) {
        this.atletaDisciplinaList = atletaDisciplinaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDisciplina != null ? idDisciplina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Disciplinas)) {
            return false;
        }
        Disciplinas other = (Disciplinas) object;
        if ((this.idDisciplina == null && other.idDisciplina != null) || (this.idDisciplina != null && !this.idDisciplina.equals(other.idDisciplina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Disciplinas[ idDisciplina=" + idDisciplina + " ]";
    }
    
}
