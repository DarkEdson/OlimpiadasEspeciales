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
@Table(name = "tutor")
@NamedQueries({
    @NamedQuery(name = "Tutor.findAll", query = "SELECT t FROM Tutor t")
    , @NamedQuery(name = "Tutor.findByIdTutor", query = "SELECT t FROM Tutor t WHERE t.idTutor = :idTutor")
    , @NamedQuery(name = "Tutor.findByNombreTutor", query = "SELECT t FROM Tutor t WHERE t.nombreTutor = :nombreTutor")
    , @NamedQuery(name = "Tutor.findByFechaNacimiento", query = "SELECT t FROM Tutor t WHERE t.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Tutor.findByDpi", query = "SELECT t FROM Tutor t WHERE t.dpi = :dpi")
    , @NamedQuery(name = "Tutor.findByDireccionLaboral", query = "SELECT t FROM Tutor t WHERE t.direccionLaboral = :direccionLaboral")
    , @NamedQuery(name = "Tutor.findByTelefono", query = "SELECT t FROM Tutor t WHERE t.telefono = :telefono")
    , @NamedQuery(name = "Tutor.findByMovil", query = "SELECT t FROM Tutor t WHERE t.movil = :movil")
    , @NamedQuery(name = "Tutor.findByFechaIngreso", query = "SELECT t FROM Tutor t WHERE t.fechaIngreso = :fechaIngreso")
    , @NamedQuery(name = "Tutor.findByFechaActualizacion", query = "SELECT t FROM Tutor t WHERE t.fechaActualizacion = :fechaActualizacion")})
public class Tutor implements Serializable {

    private static final long serialVersionUID = -4992152249523973903L;

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_TUTOR")
    private Integer idTutor;
    @Size(max = 100)
    @Column(name = "NOMBRE_TUTOR")
    private String nombreTutor;
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
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_INGRESO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @OneToMany(mappedBy = "idTutor1")
    private List<Atleta> atletaList;
    @OneToMany(mappedBy = "idTutor2")
    private List<Atleta> atletaList1;
    @JoinColumn(name = "ID_USUARIO_INGRESO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuarios idUsuarioIngreso;
    @JoinColumn(name = "ID_USUARIO_ACTUALIZACION", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private Usuarios idUsuarioActualizacion;
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO")
    @ManyToOne
    private Estado idEstado;

    public Tutor() {
    }

    public Tutor(Integer idTutor) {
        this.idTutor = idTutor;
    }

    public Tutor(Integer idTutor, Date fechaIngreso, Date fechaActualizacion) {
        this.idTutor = idTutor;
        this.fechaIngreso = fechaIngreso;
        this.fechaActualizacion = fechaActualizacion;
    }

    public Integer getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Integer idTutor) {
        this.idTutor = idTutor;
    }

    public String getNombreTutor() {
        return nombreTutor;
    }

    public void setNombreTutor(String nombreTutor) {
        this.nombreTutor = nombreTutor;
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

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public List<Atleta> getAtletaList() {
        return atletaList;
    }

    public void setAtletaList(List<Atleta> atletaList) {
        this.atletaList = atletaList;
    }

    public List<Atleta> getAtletaList1() {
        return atletaList1;
    }

    public void setAtletaList1(List<Atleta> atletaList1) {
        this.atletaList1 = atletaList1;
    }

    public Usuarios getIdUsuarioIngreso() {
        return idUsuarioIngreso;
    }

    public void setIdUsuarioIngreso(Usuarios idUsuarioIngreso) {
        this.idUsuarioIngreso = idUsuarioIngreso;
    }

    public Usuarios getIdUsuarioActualizacion() {
        return idUsuarioActualizacion;
    }

    public void setIdUsuarioActualizacion(Usuarios idUsuarioActualizacion) {
        this.idUsuarioActualizacion = idUsuarioActualizacion;
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
