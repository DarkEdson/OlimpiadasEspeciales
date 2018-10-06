package Entities;

import Entities.AtletaDisciplina;
import Entities.Estado;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-05T14:25:30")
@StaticMetamodel(Disciplinas.class)
public class Disciplinas_ { 

    public static volatile SingularAttribute<Disciplinas, Date> fechaIngreso;
    public static volatile SingularAttribute<Disciplinas, Estado> idEstado;
    public static volatile SingularAttribute<Disciplinas, Integer> idUsuarioActualizacion;
    public static volatile ListAttribute<Disciplinas, AtletaDisciplina> atletaDisciplinaList;
    public static volatile SingularAttribute<Disciplinas, Integer> idDisciplina;
    public static volatile SingularAttribute<Disciplinas, Integer> idUsuarioIngreso;
    public static volatile SingularAttribute<Disciplinas, Date> fechaActualizacion;
    public static volatile SingularAttribute<Disciplinas, String> nombreDisciplina;

}