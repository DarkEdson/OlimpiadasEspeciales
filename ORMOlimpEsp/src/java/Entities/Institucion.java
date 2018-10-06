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
@Table(name = "institucion")
@NamedQueries({
    @NamedQuery(name = "Institucion.findAll", query = "SELECT i FROM Institucion i")
    , @NamedQuery(name = "Institucion.findByIdIntitucion", query = "SELECT i FROM Institucion i WHERE i.idIntitucion = :idIntitucion")
    , @NamedQuery(name = "Institucion.findByNombreInstitucion", query = "SELECT i FROM Institucion i WHERE i.nombreInstitucion = :nombreInstitucion")
    , @NamedQuery(name = "Institucion.findByFechaIngreso", query = "SELECT i FROM Institucion i WHERE i.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "Institucion.findByIdUsuarioIngreso", query = "SELECT i FROM Institucion i WHERE i.idUsuarioIngreso = :idUsuarioIngreso")
    , @NamedQuery(name = "Institucion.findByFechaActualizacion", query = "SELECT i FROM Institucion i WHERE i.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "Institucion.findByIdUsuarioActualizacion", query = "SELECT i FROM Institucion i WHERE i.idUsuarioActualizacion = :idUsuarioActualizacion")})
public class Institucion implements Serializable {

    private static final long serialVersionUID = -609763852329481567L;

 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_INTITUCION")
    private Integer idIntitucion;
    @Size(max = 100)
    @Column(name = "NOMBRE_INSTITUCION")
    private String nombreInstitucion;
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
    @OneToMany(mappedBy = "idIntitucion")
    private List<AtletaDisciplina> atletaDisciplinaList;

    public Institucion() {
    }

    public Institucion(Integer idIntitucion) {
        this.idIntitucion = idIntitucion;
    }

    public Institucion(Integer idIntitucion, Date fechaIngreso, Date fechaActualizacion) {
        this.idIntitucion = idIntitucion;
        this.fechaIngreso = fechaIngreso;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getIdIntitucion() {
        return idIntitucion;
    }

    public void setIdIntitucion(Integer idIntitucion) {
        this.idIntitucion = idIntitucion;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
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
        hash += (idIntitucion != null ? idIntitucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Institucion)) {
            return false;
        }
        Institucion other = (Institucion) object;
        if ((this.idIntitucion == null && other.idIntitucion != null) || (this.idIntitucion != null && !this.idIntitucion.equals(other.idIntitucion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Institucion[ idIntitucion=" + idIntitucion + " ]";
    }
    
}
