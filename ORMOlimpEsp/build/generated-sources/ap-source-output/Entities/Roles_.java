package Entities;

import Entities.Estado;
import Entities.Usuarios;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-09T22:00:52")
@StaticMetamodel(Roles.class)
public class Roles_ { 

    public static volatile SingularAttribute<Roles, Date> fechaIngreso;
    public static volatile ListAttribute<Roles, Usuarios> usuariosList;
    public static volatile SingularAttribute<Roles, Integer> idRol;
    public static volatile SingularAttribute<Roles, Estado> idEstado;
    public static volatile SingularAttribute<Roles, Integer> idUsuarioActualizacion;
    public static volatile SingularAttribute<Roles, Integer> idUsuarioIngreso;
    public static volatile SingularAttribute<Roles, Date> fechaActualizacion;
    public static volatile SingularAttribute<Roles, String> rol;

}