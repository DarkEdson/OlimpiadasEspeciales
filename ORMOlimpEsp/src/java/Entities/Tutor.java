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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author axel.medina
 */
@Entity
@Table(name = "tutor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tutor.findAll", query = "SELECT t FROM Tutor t")
    , @NamedQuery(name = "Tutor.findByIdTutor", query = "SELECT t FROM Tutor t WHERE t.idTutor = :idTutor")
    , @NamedQuery(name = "Tutor.findByNombre", query = "SELECT t FROM Tutor t WHERE t.nombre = :nombre")
    , @NamedQuery(name = "Tutor.findByFechaNacimiento", query = "SELECT t FROM Tutor t WHERE t.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Tutor.findByDpi", query = "SELECT t FROM Tutor t WHERE t.dpi = :dpi")
    , @NamedQuery(name = "Tutor.findByDireccionLaboral", query = "SELECT t FROM Tutor t WHERE t.direccionLaboral = :direccionLaboral")
    , @NamedQuery(name = "Tutor.findByTelefono", query = "SELECT t FROM Tutor t WHERE t.telefono = :telefono")
    , @NamedQuery(name = "Tutor.findByMovil", query = "SELECT t FROM Tutor t WHERE t.movil = :movil")})
public class Tutor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TUTOR")
    private Integer idTutor;
    @Size(max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "FECHA_NACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "DPI")
    private Integer dpi;
    @Size(max = 200)
    @Column(name = "DIRECCION_LABORAL")
    private String direccionLaboral;
    @Column(name = "TELEFONO")
    private Integer telefono;
    @Column(name = "MOVIL")
    private Integer movil;
    @JoinColumn(name = "ID_ATLETA", referencedColumnName = "ID_ATLETA")
    @ManyToOne
    private Atleta idAtleta;
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO")
    @ManyToOne
    private Estado idEstado;

    public Tutor() {
    }

    public Tutor(Integer idTutor) {
        this.idTutor = idTutor;
    }

    public Integer getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Integer idTutor) {
        this.idTutor = idTutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getDireccionLaboral() {
        return direccionLaboral;
    }

    public void setDireccionLaboral(String direccionLaboral) {
        this.direccionLaboral = direccionLaboral;
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

    public Atleta getIdAtleta() {
        return idAtleta;
    }

    public void setIdAtleta(Atleta idAtleta) {
        this.idAtleta = idAtleta;
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
        hash += (idTutor != null ? idTutor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tutor)) {
            return false;
        }
        Tutor other = (Tutor) object;
        if ((this.idTutor == null && other.idTutor != null) || (this.idTutor != null && !this.idTutor.equals(other.idTutor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Tutor[ idTutor=" + idTutor + " ]";
    }
    
}
