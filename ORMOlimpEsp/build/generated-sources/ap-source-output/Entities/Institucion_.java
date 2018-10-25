package Entities;

import Entities.Atleta;
import Entities.Estado;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2018-10-24T21:01:11")
@StaticMetamodel(Institucion.class)
public class Institucion_ { 

    public static volatile ListAttribute<Institucion, Atleta> atletaList;
    public static volatile SingularAttribute<Institucion, Date> fechaIngreso;
    public static volatile SingularAttribute<Institucion, Estado> idEstado;
    public static volatile SingularAttribute<Institucion, Integer> idUsuarioActualizacion;
    public static volatile SingularAttribute<Institucion, Integer> idIntitucion;
    public static volatile SingularAttribute<Institucion, String> institucion;
    public static volatile SingularAttribute<Institucion, Integer> idUsuarioIngreso;
    public static volatile SingularAttribute<Institucion, Date> fechaActualizacion;

}