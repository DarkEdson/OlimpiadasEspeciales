/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dark Edson
 */
@Entity
@Table(name = "ATLETA_DISCIPLINA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AtletaDisciplina.findAll", query = "SELECT a FROM AtletaDisciplina a")
    , @NamedQuery(name = "AtletaDisciplina.findByIdAtletaDisciplina", query = "SELECT a FROM AtletaDisciplina a WHERE a.idAtletaDisciplina = :idAtletaDisciplina")
    , @NamedQuery(name = "AtletaDisciplina.findByFechaIngreso", query = "SELECT a FROM AtletaDisciplina a WHERE a.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "AtletaDisciplina.findByIdUsuarioIngreso", query = "SELECT a FROM AtletaDisciplina a WHERE a.idUsuarioIngreso = :idUsuarioIngreso")
    , @NamedQuery(name = "AtletaDisciplina.findByFechaActualizacion", query = "SELECT a FROM AtletaDisciplina a WHERE a.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "AtletaDisciplina.findByIdUsuarioActualizacion", query = "SELECT a FROM AtletaDisciplina a WHERE a.idUsuarioActualizacion = :idUsuarioActualizacion")})
public class AtletaDisciplina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ATLETA_DISCIPLINA")
    private Integer idAtletaDisciplina;
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
    @JoinColumn(name = "ID_ATLETA", referencedColumnName = "ID_ATLETA")
    @ManyToOne(optional = false)
    private Atleta idAtleta;
    @JoinColumn(name = "ID_DISCIPLINA", referencedColumnName = "ID_DISCIPLINA")
    @ManyToOne(optional = false)
    private Disciplinas idDisciplina;
    @JoinColumn(name = "ID_PROGRAMA", referencedColumnName = "ID_PROGRAMA")
    @ManyToOne
    private Programas idPrograma;
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO")
    @ManyToOne
    private Estado idEstado;

    public AtletaDisciplina() {
    }

    public AtletaDisciplina(Integer idAtletaDisciplina) {
        this.idAtletaDisciplina = idAtletaDisciplina;
    }

    public AtletaDisciplina(Integer idAtletaDisciplina, Date fechaIngreso, Date fechaActualizacion) {
        this.idAtletaDisciplina = idAtletaDisciplina;
        this.fechaIngreso = fechaIngreso;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getIdAtletaDisciplina() {
        return idAtletaDisciplina;
    }

    public void setIdAtletaDisciplina(Integer idAtletaDisciplina) {
        this.idAtletaDisciplina = idAtletaDisciplina;
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

    public Atleta getIdAtleta() {
        return idAtleta;
    }

    public void setIdAtleta(Atleta idAtleta) {
        this.idAtleta = idAtleta;
    }

    public Disciplinas getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(Disciplinas idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public Programas getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Programas idPrograma) {
        this.idPrograma = idPrograma;
    }

    public Estado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAtletaDisciplina != null ? idAtletaDisciplina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AtletaDisciplina)) {
            return false;
        }
        AtletaDisciplina other = (AtletaDisciplina) object;
        if ((this.idAtletaDisciplina == null && other.idAtletaDisciplina != null) || (this.idAtletaDisciplina != null && !this.idAtletaDisciplina.equals(other.idAtletaDisciplina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.AtletaDisciplina[ idAtletaDisciplina=" + idAtletaDisciplina + " ]";
    }
    
}
