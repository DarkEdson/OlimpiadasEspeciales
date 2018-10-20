package Entities;

import Entities.Atleta;
import Entities.Estado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2018-10-20T03:53:03")
@StaticMetamodel(SitioEntrenamiento.class)
public class SitioEntrenamiento_ { 

    public static volatile ListAttribute<SitioEntrenamiento, Atleta> atletaList;
    public static volatile SingularAttribute<SitioEntrenamiento, Integer> idSitioEntrenamiento;
    public static volatile SingularAttribute<SitioEntrenamiento, Estado> idEstado;
    public static volatile SingularAttribute<SitioEntrenamiento, String> sitioEntrenamiento;

}