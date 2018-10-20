/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author axel.medina
 */
@Entity
@Table(name = "programas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programas.findAll", query = "SELECT p FROM Programas p")
    , @NamedQuery(name = "Programas.findByIdPrograma", query = "SELECT p FROM Programas p WHERE p.idPrograma = :idPrograma")
    , @NamedQuery(name = "Programas.findByPrograma", query = "SELECT p FROM Programas p WHERE p.programa = :programa")})
public class Programas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_PROGRAMA")
    private Integer idPrograma;
    @Size(max = 100)
    @Column(name = "PROGRAMA")
    private String programa;
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO")
    @ManyToOne
    private Estado idEstado;
    @OneToMany(mappedBy = "idPrograma")
    private List<AtletaDisciplina> atletaDisciplinaList;

    public Programas() {
    }

    public Programas(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public Integer getIdPrograma() {
        return idPrograma;
    }

    public void setIdPrograma(Integer idPrograma) {
        this.idPrograma = idPrograma;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPrograma != null ? idPrograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programas)) {
            return false;
        }
        Programas other = (Programas) object;
        if ((this.idPrograma == null && other.idPrograma != null) || (this.idPrograma != null && !this.idPrograma.equals(other.idPrograma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.Programas[ idPrograma=" + idPrograma + " ]";
    }
    
}
