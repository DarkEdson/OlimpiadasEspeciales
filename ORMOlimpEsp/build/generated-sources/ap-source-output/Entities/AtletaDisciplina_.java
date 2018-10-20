package Entities;

import Entities.Atleta;
import Entities.Disciplinas;
import Entities.Estado;
import Entities.Programas;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2018-10-20T03:53:03")
@StaticMetamodel(AtletaDisciplina.class)
public class AtletaDisciplina_ { 

    public static volatile SingularAttribute<AtletaDisciplina, Programas> idPrograma;
    public static volatile SingularAttribute<AtletaDisciplina, Estado> idEstado;
    public static volatile SingularAttribute<AtletaDisciplina, Disciplinas> idDisciplina;
    public static volatile SingularAttribute<AtletaDisciplina, Integer> idAtletaDisciplina;
    public static volatile SingularAttribute<AtletaDisciplina, Atleta> idAtleta;

}