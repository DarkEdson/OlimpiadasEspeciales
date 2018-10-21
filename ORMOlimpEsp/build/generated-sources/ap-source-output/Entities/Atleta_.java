package Entities;

import Entities.AtletaDisciplina;
import Entities.Departamento;
import Entities.Diagnostico;
import Entities.Estado;
import Entities.Institucion;
import Entities.SitioEntrenamiento;
import Entities.Tutor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2018-10-20T11:46:38")
@StaticMetamodel(Atleta.class)
public class Atleta_ { 

    public static volatile SingularAttribute<Atleta, Integer> movil;
    public static volatile SingularAttribute<Atleta, Date> fechaNacimiento;
    public static volatile ListAttribute<Atleta, AtletaDisciplina> atletaDisciplinaList;
    public static volatile ListAttribute<Atleta, Tutor> tutorList;
    public static volatile SingularAttribute<Atleta, Diagnostico> idDiagnostico;
    public static volatile SingularAttribute<Atleta, Institucion> idIntitucion;
    public static volatile SingularAttribute<Atleta, SitioEntrenamiento> idSitioEntrenamiento;
    public static volatile SingularAttribute<Atleta, Departamento> idDepartamento;
    public static volatile SingularAttribute<Atleta, Estado> idEstado;
    public static volatile SingularAttribute<Atleta, String> nombreAtleta;
    public static volatile SingularAttribute<Atleta, String> domicilio;
    public static volatile SingularAttribute<Atleta, Integer> dpi;
    public static volatile SingularAttribute<Atleta, Integer> telefono;
    public static volatile SingularAttribute<Atleta, String> comentarios;
    public static volatile SingularAttribute<Atleta, String> idAtleta;

}