package Entities;

import Entities.Atleta;
import Entities.Estado;
import Entities.Roles;
import Entities.Tutor;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-09T22:00:52")
@StaticMetamodel(Usuarios.class)
public class Usuarios_ { 

    public static volatile ListAttribute<Usuarios, Atleta> atletaList;
    public static volatile SingularAttribute<Usuarios, Roles> idRol;
    public static volatile SingularAttribute<Usuarios, Integer> idUsuario;
    public static volatile ListAttribute<Usuarios, Atleta> atletaList1;
    public static volatile ListAttribute<Usuarios, Tutor> tutorList;
    public static volatile ListAttribute<Usuarios, Tutor> tutorList1;
    public static volatile SingularAttribute<Usuarios, Date> fechaIngreso;
    public static volatile SingularAttribute<Usuarios, String> password;
    public static volatile SingularAttribute<Usuarios, Estado> idEstado;
    public static volatile SingularAttribute<Usuarios, Integer> idUsuarioActualizacion;
    public static volatile SingularAttribute<Usuarios, Integer> idUsuarioIngreso;
    public static volatile SingularAttribute<Usuarios, Date> fechaActualizacion;
    public static volatile SingularAttribute<Usuarios, String> usuario;

}