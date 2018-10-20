package Entities;

import Entities.Atleta;
import Entities.Estado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2018-10-20T03:53:03")
@StaticMetamodel(Departamento.class)
public class Departamento_ { 

    public static volatile ListAttribute<Departamento, Atleta> atletaList;
    public static volatile SingularAttribute<Departamento, Integer> idDepartamento;
    public static volatile SingularAttribute<Departamento, Estado> idEstado;
    public static volatile SingularAttribute<Departamento, String> departamento;

}