package Entities;

import Entities.Atleta;
import Entities.AtletaDisciplina;
import Entities.Departamento;
import Entities.Diagnostico;
import Entities.Disciplinas;
import Entities.Institucion;
import Entities.Programas;
import Entities.Roles;
import Entities.SitioEntrenamiento;
import Entities.Tutor;
import Entities.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2018-10-20T03:53:03")
@StaticMetamodel(Estado.class)
public class Estado_ { 

    public static volatile ListAttribute<Estado, Atleta> atletaList;
    public static volatile ListAttribute<Estado, Programas> programasList;
    public static volatile ListAttribute<Estado, Departamento> departamentoList;
    public static volatile ListAttribute<Estado, Usuarios> usuariosList;
    public static volatile SingularAttribute<Estado, String> estado;
    public static volatile ListAttribute<Estado, SitioEntrenamiento> sitioEntrenamientoList;
    public static volatile ListAttribute<Estado, AtletaDisciplina> atletaDisciplinaList;
    public static volatile ListAttribute<Estado, Institucion> institucionList;
    public static volatile ListAttribute<Estado, Tutor> tutorList;
    public static volatile ListAttribute<Estado, Diagnostico> diagnosticoList;
    public static volatile SingularAttribute<Estado, Integer> idEstado;
    public static volatile ListAttribute<Estado, Disciplinas> disciplinasList;
    public static volatile ListAttribute<Estado, Roles> rolesList;

}