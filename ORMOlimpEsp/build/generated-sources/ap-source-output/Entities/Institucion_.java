package Entities;

import Entities.AtletaDisciplina;
import Entities.Estado;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-05T14:25:30")
@StaticMetamodel(Institucion.class)
public class Institucion_ { 

    public static volatile SingularAttribute<Institucion, Date> fechaIngreso;
    public static volatile SingularAttribute<Institucion, Estado> idEstado;
    public static volatile SingularAttribute<Institucion, Integer> idUsuarioActualizacion;
    public static volatile ListAttribute<Institucion, AtletaDisciplina> atletaDisciplinaList;
    public static volatile SingularAttribute<Institucion, Integer> idIntitucion;
    public static volatile SingularAttribute<Institucion, Integer> idUsuarioIngreso;
    public static volatile SingularAttribute<Institucion, Date> fechaActualizacion;
    public static volatile SingularAttribute<Institucion, String> nombreInstitucion;

}