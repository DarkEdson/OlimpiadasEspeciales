package Entities;

import Entities.Estado;
import Entities.Usuarios;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2018-10-24T21:01:11")
@StaticMetamodel(Roles.class)
public class Roles_ { 

    public static volatile ListAttribute<Roles, Usuarios> usuariosList;
    public static volatile SingularAttribute<Roles, Integer> idRol;
    public static volatile SingularAttribute<Roles, Estado> idEstado;
    public static volatile SingularAttribute<Roles, String> rol;

}