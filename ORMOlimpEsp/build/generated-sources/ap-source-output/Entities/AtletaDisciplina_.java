package Entities;

import Entities.Atleta;
import Entities.Disciplinas;
import Entities.Estado;
import Entities.Institucion;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-10-05T14:25:30")
@StaticMetamodel(AtletaDisciplina.class)
public class AtletaDisciplina_ { 

    public static volatile SingularAttribute<AtletaDisciplina, Date> fechaIngreso;
    public static volatile SingularAttribute<AtletaDisciplina, Estado> idEstado;
    public static volatile SingularAttribute<AtletaDisciplina, Integer> idUsuarioActualizacion;
    public static volatile SingularAttribute<AtletaDisciplina, Disciplinas> idDisciplina;
    public static volatile SingularAttribute<AtletaDisciplina, Integer> idUsuarioIngreso;
    public static volatile SingularAttribute<AtletaDisciplina, Institucion> idIntitucion;
    public static volatile SingularAttribute<AtletaDisciplina, Date> fechaActualizacion;
    public static volatile SingularAttribute<AtletaDisciplina, Integer> idAtletaDisciplina;
    public static volatile SingularAttribute<AtletaDisciplina, Atleta> idAtleta;

}