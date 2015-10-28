/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import static session.GestionnaireDeCompteBancaire.TABNOM;

/**
 *
 * @author Florian
 */
@Singleton 
@LocalBean
@Startup 
public class InitDB {
    @EJB
    private GestionnaireDeCompteBancaire gc;
    @EJB
    private GestionnaireUtilisateur gu;
    @PostConstruct
    public void InitDB(){
       System.out.println("#### BD EN CREATION ###");
       System.out.println("#### Création des utilisateurs ###");
       gu.genererUtilisateurs();
       System.out.println("#### Création des comptes ###");
       gc.creer2000Comptes();
       System.out.println("#### Création des opérations ###");
       gc.generer10000Operations();
       System.out.println("#### BD REMPLIE ###");
      
    }
}
