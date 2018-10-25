package Entities;

import Entities.Atleta;
import Entities.Estado;
import Entities.Usuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2018-10-24T21:01:11")
@StaticMetamodel(Tutor.class)
public class Tutor_ { 

    public static volatile SingularAttribute<Tutor, Integer> movil;
    public static volatile SingularAttribute<Tutor, Date> fechaNacimiento;
    public static volatile SingularAttribute<Tutor, String> nombre;
    public static volatile SingularAttribute<Tutor, Date> fechaIngreso;
    public static volatile SingularAttribute<Tutor, Integer> idTutor;
    public static volatile SingularAttribute<Tutor, Estado> idEstado;
    public static volatile SingularAttribute<Tutor, String> direccionLaboral;
    public static volatile SingularAttribute<Tutor, Usuarios> idUsuarioActualizacion;
    public static volatile SingularAttribute<Tutor, Usuarios> idUsuarioIngreso;
    public static volatile SingularAttribute<Tutor, Date> fechaActualizacion;
    public static volatile SingularAttribute<Tutor, Integer> dpi;
    public static volatile SingularAttribute<Tutor, Integer> telefono;
    public static volatile SingularAttribute<Tutor, Atleta> idAtleta;

}