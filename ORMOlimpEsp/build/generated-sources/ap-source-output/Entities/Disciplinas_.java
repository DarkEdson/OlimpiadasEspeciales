package Entities;

import Entities.AtletaDisciplina;
import Entities.Estado;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.3.v20180807-rNA", date="2018-10-24T21:01:11")
@StaticMetamodel(Disciplinas.class)
public class Disciplinas_ { 

    public static volatile SingularAttribute<Disciplinas, Estado> idEstado;
    public static volatile ListAttribute<Disciplinas, AtletaDisciplina> atletaDisciplinaList;
    public static volatile SingularAttribute<Disciplinas, String> idDisciplina;
    public static volatile SingularAttribute<Disciplinas, String> disciplina;

}