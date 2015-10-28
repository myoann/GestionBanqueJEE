/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Utilisateur;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Florian
 */
@Stateless
@LocalBean
public class GestionnaireUtilisateur {
    @PersistenceContext(unitName = "TP3BanqueMassa-ejbPU")
    private EntityManager em;
    public static String[] TABLOGIN={"app", "admin", "root","user1","user2"};
    public static String[] TABMDP ={"app","admin","root","1234","56789"}; 
   

    public GestionnaireUtilisateur(){
        
    }
    public void persist(Object object) {
        em.persist(object);
    }
  
    public void genererUtilisateurs(){
        for(int i=0; i<TABLOGIN.length ; i++){
            this.creerUtilisateur(new Utilisateur(TABLOGIN[i],TABMDP[i]));
        }
    }
    public void creerUtilisateur(Utilisateur u) {
       em.persist(u);
   }
}
