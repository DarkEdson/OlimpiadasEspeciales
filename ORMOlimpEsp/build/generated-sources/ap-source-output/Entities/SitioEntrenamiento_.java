package Entities;

import Entities.Atleta;
import Entities.Estado;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-05T14:25:30")
@StaticMetamodel(SitioEntrenamiento.class)
public class SitioEntrenamiento_ { 

    public static volatile ListAttribute<SitioEntrenamiento, Atleta> atletaList;
    public static volatile SingularAttribute<SitioEntrenamiento, Integer> idSitioEntrenamiento;
    public static volatile SingularAttribute<SitioEntrenamiento, Date> fechaIngreso;
    public static volatile SingularAttribute<SitioEntrenamiento, Estado> idEstado;
    public static volatile SingularAttribute<SitioEntrenamiento, Integer> idUsuarioActualizacion;
    public static volatile SingularAttribute<SitioEntrenamiento, Integer> idUsuarioIngreso;
    public static volatile SingularAttribute<SitioEntrenamiento, Date> fechaActualizacion;
    public static volatile SingularAttribute<SitioEntrenamiento, String> sitioEntrenamiento;

}