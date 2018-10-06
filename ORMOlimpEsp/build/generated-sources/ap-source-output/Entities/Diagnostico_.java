package Entities;

import Entities.Atleta;
import Entities.Estado;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-05T14:25:30")
@StaticMetamodel(Diagnostico.class)
public class Diagnostico_ { 

    public static volatile ListAttribute<Diagnostico, Atleta> atletaList;
    public static volatile SingularAttribute<Diagnostico, Date> fechaIngreso;
    public static volatile SingularAttribute<Diagnostico, Estado> idEstado;
    public static volatile SingularAttribute<Diagnostico, Integer> idUsuarioActualizacion;
    public static volatile SingularAttribute<Diagnostico, String> diagnostico;
    public static volatile SingularAttribute<Diagnostico, Integer> idDiagnostico;
    public static volatile SingularAttribute<Diagnostico, Integer> idUsuarioIngreso;
    public static volatile SingularAttribute<Diagnostico, Date> fechaActualizacion;

}