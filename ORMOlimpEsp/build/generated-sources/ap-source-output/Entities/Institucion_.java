package Entities;

import Entities.Atleta;
import Entities.Estado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2018-10-20T03:53:03")
@StaticMetamodel(Institucion.class)
public class Institucion_ { 

    public static volatile ListAttribute<Institucion, Atleta> atletaList;
    public static volatile SingularAttribute<Institucion, Estado> idEstado;
    public static volatile SingularAttribute<Institucion, Integer> idInstitucion;
    public static volatile SingularAttribute<Institucion, String> institucion;

}