package Entities;

import Entities.Atleta;
import Entities.Estado;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2018-10-20T03:53:03")
@StaticMetamodel(Tutor.class)
public class Tutor_ { 

    public static volatile SingularAttribute<Tutor, Integer> idTutor;
    public static volatile SingularAttribute<Tutor, Estado> idEstado;
    public static volatile SingularAttribute<Tutor, String> direccionLaboral;
    public static volatile SingularAttribute<Tutor, Integer> movil;
    public static volatile SingularAttribute<Tutor, Date> fechaNacimiento;
    public static volatile SingularAttribute<Tutor, Integer> dpi;
    public static volatile SingularAttribute<Tutor, Integer> telefono;
    public static volatile SingularAttribute<Tutor, String> nombre;
    public static volatile SingularAttribute<Tutor, Atleta> idAtleta;

}