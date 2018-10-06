/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;
import MagedBeans.BeanLogin;
import javax.persistence.*;  
import java.util.*;  
/**
 *
 * @author axel.medina
 */
public class TestA {
BeanLogin ben = new BeanLogin();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    // TODO code application logic here
    EntityManagerFactory emf = Persistence.createEntityManagerFactory( "ORMOlimpEspPU" );  
          EntityManager em = emf.createEntityManager();  
          em.getTransaction().begin( );  
                    
          Query query = (Query) em.createQuery("SELECT u.usuario FROM Usuarios u where u.usuario ='AXEL' and u.password =''");  
          @SuppressWarnings("unchecked")  
        List<String> list =query.getResultList();  
          System.out.println("Student Name :");  
          for(String s:list) {  
                
             System.out.println(s);  
          
               
          }  
            
        
          em.close();  
          emf.close();   
    }
    
}
