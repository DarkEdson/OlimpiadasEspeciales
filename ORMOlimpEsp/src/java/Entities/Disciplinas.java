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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author axel.medina
 */
@Entity
@Table(name = "disciplinas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Disciplinas.findAll", query = "SELECT d FROM Disciplinas d")
    , @NamedQuery(name = "Disciplinas.findByIdDisciplina", query = "SELECT d FROM Disciplinas d WHERE d.idDisciplina = :idDisciplina")
    , @NamedQuery(name = "Disciplinas.findByDisciplina", query = "SELECT d FROM Disciplinas d WHERE d.disciplina = :disciplina")})
public class Disciplinas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ID_DISCIPLINA")
    private String idDisciplina;
    @Size(max = 100)
    @Column(name = "DISCIPLINA")
    private String disciplina;
    @JoinColumn(name = "ID_ESTADO", referencedColumnName = "ID_ESTADO")
    @ManyToOne
    private Estado idEstado;
    @OneToMany(mappedBy = "idDisciplina")
    private List<AtletaDisciplina> atletaDisciplinaList;

    public Disciplinas() {
    }

    public Disciplinas(String idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(String idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
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
