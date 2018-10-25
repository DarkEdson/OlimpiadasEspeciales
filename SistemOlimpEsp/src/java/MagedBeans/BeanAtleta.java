/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MagedBeans;

import Controller.AtletaJpaController;
import Controller.DepartamentoJpaController;
import Controller.DiagnosticoJpaController;
import Controller.DisciplinasJpaController;
import Controller.EstadoJpaController;
import Controller.InstitucionJpaController;
import Controller.ProgramasJpaController;
import Controller.SitioEntrenamientoJpaController;
import Entities.Atleta;
import Entities.Departamento;
import Entities.Diagnostico;
import Entities.Disciplinas;
import Entities.Estado;
import Entities.Institucion;
import Entities.Programas;
import Entities.SitioEntrenamiento;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author axel.medina
 */
@ManagedBean(name = "beanAtleta")
@ViewScoped
public class BeanAtleta implements Serializable {

    private static final long serialVersionUID = -8757285597845344548L;
    AtletaJpaController ControlTabla;
    InstitucionJpaController ControlInstitucion;//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
    EstadoJpaController ControlForeingKey;//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
    DiagnosticoJpaController ControlDiagnostico;//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
    SitioEntrenamientoJpaController ControlSitioEntrenamiento;//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
    DepartamentoJpaController ControlDepartamento;//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
    DisciplinasJpaController ControlDisciplinas;//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
    ProgramasJpaController ControlProgramas;//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
    /**
     * Creates a new instance of BeanAtleta
     */
    /*VARIABLES PARA EL FORMULARIO INSERT*/
    private String codigoInsert;
    /**
     * *DATOS DEL ATLETA FORMULARIO INSERT**
     */
    private String nombreInsert;
    private Integer dpiInsert;
    private Integer estadoInsert;
    private Integer institucionInsert;
    private Date fechaNacimientoInsert;
    private Integer diagnosticoInsert;
    private Integer sitioEntrenamientoInsert;
    private Integer telefonoInsert;
    private Integer celularInsert;
    private Integer departamentoInsert;
    private String direccionInsert;
    private String comentarioInsert;
    /**
     * *DATOS DEL MADFRE FORMULARIO INSERT**
     */
    private String nombreMadreInsert;
    private Date fechaNacimientoMadreInsert;
    private Integer dpiMadreInsert;
    private Integer telefonoMadreInsert;
    private Integer celularMadreInsert;
    private String direccionMadreInsert;
    /**
     * *DATOS DEL PADRE FORMULARIO INSERT**
     */
    private String nombrePadreInsert;
    private Date fechaNacimientoPadreInsert;
    private Integer dpiPadreInsert;
    private Integer telefonoPadreInsert;
    private Integer celularPadreInsert;
    private String direccionPadreInsert;
    /**
     * *DATOS DEL DISCIPLINA/ PROGRAMA FORMULARIO INSERT**
     */
    private Integer disciplinaInsert;
    private Integer programaInsert;

    @PostConstruct
    public void initBeanAtleta() {
        ControlTabla = new AtletaJpaController();
        this.ControlInstitucion = new InstitucionJpaController();//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
        this.ControlForeingKey = new EstadoJpaController();//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
        this.ControlDiagnostico = new DiagnosticoJpaController();//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
        this.ControlSitioEntrenamiento = new SitioEntrenamientoJpaController();//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
        this.ControlDepartamento = new DepartamentoJpaController();//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
        this.ControlDisciplinas = new DisciplinasJpaController();//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
        this.ControlProgramas = new ProgramasJpaController();//REFERENCIA PARA EL CONTENIDO DEL COMBOBOX
    }

    /*CARGA EL CONTENIDO DE LA DATATABLE*/
    public List<Atleta> getTabla() {
        return ControlTabla.findAtletaEntities();
    }

    /*CARGA EL CONTENIDO DEL COMOBOBOX*/
    public List<Institucion> getListInstitucion() {
        return ControlInstitucion.findInstitucionEntities();
    }

    /*CARGA EL CONTENIDO DEL COMOBOBOX*/
    public List<Estado> getListEstado() {
        return ControlForeingKey.findEstadoEntities(1, 0);
    }

    /*CARGA EL CONTENIDO DEL COMOBOBOX*/
    public List<Diagnostico> getListDiagnostico() {
        return ControlDiagnostico.findDiagnosticoEntities();
    }

    /*CARGA EL CONTENIDO DEL COMOBOBOX*/
    public List<SitioEntrenamiento> getListSitioEntrenamiento() {
        return ControlSitioEntrenamiento.findSitioEntrenamientoEntities();
    }

    /*CARGA EL CONTENIDO DEL COMOBOBOX*/
    public List<Departamento> getListDepartamento() {
        return ControlDepartamento.findDepartamentoEntities();
    }
    /*CARGA EL CONTENIDO DEL COMOBOBOX*/
    public List<Disciplinas> getListDisciplinas() {
        return ControlDisciplinas.findDisciplinasEntities();
    }

    /*CARGA EL CONTENIDO DEL COMOBOBOX*/
    public List<Programas> getListProgramas() {
        return ControlProgramas.findProgramasEntities();
    }

    public void formClear() {
        System.out.println("");
    }

    /**
     * DATOS DEL FORMULARIO*
     */
    public String getCodigoInsert() {
        return codigoInsert;
    }

    public void setCodigoInsert(String codigoInsert) {
        this.codigoInsert = codigoInsert;
    }

    /**
     * DATOS DE LA ATLETA FORMULARIO INSERT*
     */
    public String getNombreInsert() {
        return nombreInsert;
    }

    public void setNombreInsert(String nombreInsert) {
        this.nombreInsert = nombreInsert;
    }

    public Date getFechaNacimientoInsert() {
        return fechaNacimientoInsert;
    }

    public void setFechaNacimientoInsert(Date fechaNacimientoInsert) {
        this.fechaNacimientoInsert = fechaNacimientoInsert;
    }

    public Integer getDpiInsert() {
        return dpiInsert;
    }

    public void setDpiInsert(Integer dpiInsert) {
        this.dpiInsert = dpiInsert;
    }

    public Integer getEstadoInsert() {
        return estadoInsert;
    }

    public void setEstadoInsert(Integer estadoInsert) {
        this.estadoInsert = estadoInsert;
    }

    public Integer getInstitucionInsert() {
        return institucionInsert;
    }

    public void setInstitucionInsert(Integer institucionInsert) {
        this.institucionInsert = institucionInsert;
    }

    public Integer getDiagnosticoInsert() {
        return diagnosticoInsert;
    }

    public void setDiagnosticoInsert(Integer diagnosticoInsert) {
        this.diagnosticoInsert = diagnosticoInsert;
    }

    public Integer getSitioEntrenamientoInsert() {
        return sitioEntrenamientoInsert;
    }

    public void setSitioEntrenamientoInsert(Integer sitioEntrenamientoInsert) {
        this.sitioEntrenamientoInsert = sitioEntrenamientoInsert;
    }

    public Integer getTelefonoInsert() {
        return telefonoInsert;
    }

    public void setTelefonoInsert(Integer telefonoInsert) {
        this.telefonoInsert = telefonoInsert;
    }

    public Integer getCelularInsert() {
        return celularInsert;
    }

    public void setCelularInsert(Integer celularInsert) {
        this.celularInsert = celularInsert;
    }

    public Integer getDepartamentoInsert() {
        return departamentoInsert;
    }

    public void setDepartamentoInsert(Integer departamentoInsert) {
        this.departamentoInsert = departamentoInsert;
    }

    public String getDireccionInsert() {
        return direccionInsert;
    }

    public void setDireccionInsert(String direccionInsert) {
        this.direccionInsert = direccionInsert;
    }

    public String getComentarioInsert() {
        return comentarioInsert;
    }

    public void setComentarioInsert(String comentarioInsert) {
        this.comentarioInsert = comentarioInsert;
    }

    /**
     * DATOS DE LA MADRE FORMULARIO INSERT*
     */
    public String getNombreMadreInsert() {
        return nombreMadreInsert;
    }

    public void setNombreMadreInsert(String nombreMadreInsert) {
        this.nombreMadreInsert = nombreMadreInsert;
    }

    public Date getFechaNacimientoMadreInsert() {
        return fechaNacimientoMadreInsert;
    }

    public void setFechaNacimientoMadreInsert(Date fechaNacimientoMadreInsert) {
        this.fechaNacimientoMadreInsert = fechaNacimientoMadreInsert;
    }

    public Integer getDpiMadreInsert() {
        return dpiMadreInsert;
    }

    public void setDpiMadreInsert(Integer dpiMadreInsert) {
        this.dpiMadreInsert = dpiMadreInsert;
    }

    public Integer getTelefonoMadreInsert() {
        return telefonoMadreInsert;
    }

    public void setTelefonoMadreInsert(Integer telefonoMadreInsert) {
        this.telefonoMadreInsert = telefonoMadreInsert;
    }

    public Integer getCelularMadreInsert() {
        return celularMadreInsert;
    }

    public void setCelularMadreInsert(Integer celularMadreInsert) {
        this.celularMadreInsert = celularMadreInsert;
    }

    public String getDireccionMadreInsert() {
        return direccionMadreInsert;
    }

    public void setDireccionMadreInsert(String direccionMadreInsert) {
        this.direccionMadreInsert = direccionMadreInsert;
    }

    /**
     * **DAATOS DEL PADRE FORMULARIO INSERT***
     */
    public String getNombrePadreInsert() {
        return nombrePadreInsert;
    }

    public void setNombrePadreInsert(String nombrePadreInsert) {
        this.nombrePadreInsert = nombrePadreInsert;
    }

    public Date getFechaNacimientoPadreInsert() {
        return fechaNacimientoPadreInsert;
    }

    public void setFechaNacimientoPadreInsert(Date fechaNacimientoPadreInsert) {
        this.fechaNacimientoPadreInsert = fechaNacimientoPadreInsert;
    }

    public Integer getDpiPadreInsert() {
        return dpiPadreInsert;
    }

    public void setDpiPadreInsert(Integer dpiPadreInsert) {
        this.dpiPadreInsert = dpiPadreInsert;
    }

    public Integer getTelefonoPadreInsert() {
        return telefonoPadreInsert;
    }

    public void setTelefonoPadreInsert(Integer telefonoPadreInsert) {
        this.telefonoPadreInsert = telefonoPadreInsert;
    }

    public Integer getCelularPadreInsert() {
        return celularPadreInsert;
    }

    public void setCelularPadreInsert(Integer celularPadreInsert) {
        this.celularPadreInsert = celularPadreInsert;
    }

    public String getDireccionPadreInsert() {
        return direccionPadreInsert;
    }

    public void setDireccionPadreInsert(String direccionPadreInsert) {
        this.direccionPadreInsert = direccionPadreInsert;
    }

    /**
     * *INSERT DISCIPLINAS / PROGRAMAS FORMULARIO INSERT**
     */
    public Integer getDisciplinaInsert() {
        return disciplinaInsert;
    }

    public void setDisciplinaInsert(Integer disciplinaInsert) {
        this.disciplinaInsert = disciplinaInsert;
    }

    public Integer getProgramaInsert() {
        return programaInsert;
    }

    public void setProgramaInsert(Integer programaInsert) {
        this.programaInsert = programaInsert;
    }

}
