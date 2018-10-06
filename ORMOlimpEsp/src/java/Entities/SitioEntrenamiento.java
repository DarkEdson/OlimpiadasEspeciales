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
@Table(name = "sitio_entrenamiento")
@NamedQueries({
    @NamedQuery(name = "SitioEntrenamiento.findAll", query = "SELECT s FROM SitioEntrenamiento s")
    , @NamedQuery(name = "SitioEntrenamiento.findByIdSitioEntrenamiento", query = "SELECT s FROM SitioEntrenamiento s WHERE s.idSitioEntrenamiento = :idSitioEntrenamiento")
    , @NamedQuery(name = "SitioEntrenamiento.findBySitioEntrenamiento", query = "SELECT s FROM SitioEntrenamiento s WHERE s.sitioEntrenamiento = :sitioEntrenamiento")
    , @NamedQuery(name = "SitioEntrenamiento.findByFechaIngreso", query = "SELECT s FROM SitioEntrenamiento s WHERE s.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "SitioEntrenamiento.findByIdUsuarioIngreso", query = "SELECT s FROM SitioEntrenamiento s WHERE s.idUsuarioIngreso = :idUsuarioIngreso")
    , @NamedQuery(name = "SitioEntrenamiento.findByFechaActualizacion", query = "SELECT s FROM SitioEntrenamiento s WHERE s.fechaActualizacion = :fechaActualizacion")
    , @NamedQuery(name = "SitioEntrenamiento.findByIdUsuarioActualizacion", query = "SELECT s FROM SitioEntrenamiento s WHERE s.idUsuarioActualizacion = :idUsuarioActualizacion")})
public class SitioEntrenamiento implements Serializable {

    private static final long serialVersionUID = -3917157547471679983L;

  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SITIO_ENTRENAMIENTO")
    private Integer idSitioEntrenamiento;
    @Size(max = 100)
    @Column(name = "SITIO_ENTRENAMIENTO")
    private String sitioEntrenamiento;
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
    @OneToMany(mappedBy = "idSitioEntrenamiento")
    private List<Atleta> atletaList;
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO")
    @ManyToOne
    private Estado idEstado;

    public SitioEntrenamiento() {
    }

    public SitioEntrenamiento(Integer idSitioEntrenamiento) {
        this.idSitioEntrenamiento = idSitioEntrenamiento;
    }

    public SitioEntrenamiento(Integer idSitioEntrenamiento, Date fechaIngreso, Date fechaActualizacion) {
        this.idSitioEntrenamiento = idSitioEntrenamiento;
        this.fechaIngreso = fechaIngreso;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getIdSitioEntrenamiento() {
        return idSitioEntrenamiento;
    }

    public void setIdSitioEntrenamiento(Integer idSitioEntrenamiento) {
        this.idSitioEntrenamiento = idSitioEntrenamiento;
    }

    public String getSitioEntrenamiento() {
        return sitioEntrenamiento;
    }

    public void setSitioEntrenamiento(String sitioEntrenamiento) {
        this.sitioEntrenamiento = sitioEntrenamiento;
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

    public List<Atleta> getAtletaList() {
        return atletaList;
    }

    public void setAtletaList(List<Atleta> atletaList) {
        this.atletaList = atletaList;
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
        hash += (idSitioEntrenamiento != null ? idSitioEntrenamiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SitioEntrenamiento)) {
            return false;
        }
        SitioEntrenamiento other = (SitioEntrenamiento) object;
        if ((this.idSitioEntrenamiento == null && other.idSitioEntrenamiento != null) || (this.idSitioEntrenamiento != null && !this.idSitioEntrenamiento.equals(other.idSitioEntrenamiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.SitioEntrenamiento[ idSitioEntrenamiento=" + idSitioEntrenamiento + " ]";
    }
    
}
