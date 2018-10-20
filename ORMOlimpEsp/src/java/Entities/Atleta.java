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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author axel.medina
 */
@Entity
@Table(name = "atleta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Atleta.findAll", query = "SELECT a FROM Atleta a")
    , @NamedQuery(name = "Atleta.findByIdAtleta", query = "SELECT a FROM Atleta a WHERE a.idAtleta = :idAtleta")
    , @NamedQuery(name = "Atleta.findByNombreAtleta", query = "SELECT a FROM Atleta a WHERE a.nombreAtleta = :nombreAtleta")
    , @NamedQuery(name = "Atleta.findByFechaNacimiento", query = "SELECT a FROM Atleta a WHERE a.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Atleta.findByDpi", query = "SELECT a FROM Atleta a WHERE a.dpi = :dpi")
    , @NamedQuery(name = "Atleta.findByDomicilio", query = "SELECT a FROM Atleta a WHERE a.domicilio = :domicilio")
    , @NamedQuery(name = "Atleta.findByTelefono", query = "SELECT a FROM Atleta a WHERE a.telefono = :telefono")
    , @NamedQuery(name = "Atleta.findByMovil", query = "SELECT a FROM Atleta a WHERE a.movil = :movil")})
public class Atleta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ID_ATLETA")
    private String idAtleta;
    @Size(max = 100)
    @Column(name = "NOMBRE_ATLETA")
    private String nombreAtleta;
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "DPI")
    private Integer dpi;
    @Size(max = 100)
    @Column(name = "DOMICILIO")
    private String domicilio;
    @Column(name = "TELEFONO")
    private Integer telefono;
    @Column(name = "MOVIL")
    private Integer movil;
    @Lob
    @Size(max = 65535)
    @Column(name = "COMENTARIOS")
    private String comentarios;
    @JoinColumn(name = "ID_INSTITUCION", referencedColumnName = "ID_INSTITUCION")
    @ManyToOne
    private Institucion idInstitucion;
    @JoinColumn(name = "ID_DEPARTAMENTO", referencedColumnName = "ID_DEPARTAMENTO")
    @ManyToOne
    private Departamento idDepartamento;
    @JoinColumn(name = "ID_DIAGNOSTICO", referencedColumnName = "ID_DIAGNOSTICO")
    @ManyToOne
    private Diagnostico idDiagnostico;
    @JoinColumn(name = "ID_SITIO_ENTRENAMIENTO", referencedColumnName = "ID_SITIO_ENTRENAMIENTO")
    @ManyToOne
    private SitioEntrenamiento idSitioEntrenamiento;
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO")
    @ManyToOne
    private Estado idEstado;
    @OneToMany(mappedBy = "idAtleta")
    private List<AtletaDisciplina> atletaDisciplinaList;
    @OneToMany(mappedBy = "idAtleta")
    private List<Tutor> tutorList;

    public Atleta() {
    }

    public Atleta(String idAtleta) {
        this.idAtleta = idAtleta;
    }

    public String getIdAtleta() {
        return idAtleta;
    }

    public void setIdAtleta(String idAtleta) {
        this.idAtleta = idAtleta;
    }

    public String getNombreAtleta() {
        return nombreAtleta;
    }

    public void setNombreAtleta(String nombreAtleta) {
        this.nombreAtleta = nombreAtleta;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getDpi() {
        return dpi;
    }

    public void setDpi(Integer dpi) {
        this.dpi = dpi;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Integer getMovil() {
        return movil;
    }

    public void setMovil(Integer movil) {
        this.movil = movil;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Institucion getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(Institucion idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public Departamento getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(Departamento idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Diagnostico getIdDiagnostico() {
        return idDiagnostico;
    }

    public void setIdDiagnostico(Diagnostico idDiagnostico) {
        this.idDiagnostico = idDiagnostico;
    }

    public SitioEntrenamiento getIdSitioEntrenamiento() {
        return idSitioEntrenamiento;
    }

    public void setIdSitioEntrenamiento(SitioEntrenamiento idSitioEntrenamiento) {
        this.idSitioEntrenamiento = idSitioEntrenamiento;
    }

    public Estado getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Estado idEstado) {
        this.idEstado = idEstado;
    }

    @XmlTransient
    public List<AtletaDisciplina> getAtletaDisciplinaList() {
        return atletaDisciplinaList;
    }

    public void setAtletaDisciplinaList(List<AtletaDisciplina> atletaDisciplinaList) {
        this.atletaDisciplinaList = atletaDisciplinaList;
    }

    @XmlTransient
    public List<Tutor> getTutorList() {
        return tutorList;
    }

    public void setTutorList(List<Tutor> tutorList) {
        this.tutorList = tutorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAtleta != null ? idAtleta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Atleta)) {
            return false;
        }
        Atleta other = (Atleta) object;
        if ((this.idAtleta == null && other.idAtleta != null) || (this.idAtleta != null && !this.idAtleta.equals(other.idAtleta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Atleta[ idAtleta=" + idAtleta + " ]";
    }
    
}
