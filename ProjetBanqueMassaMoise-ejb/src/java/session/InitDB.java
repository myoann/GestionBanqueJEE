/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import java.util.Timer;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerService;

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
    @Resource
    TimerService timerService;

    @EJB
    private GestionnaireUtilisateur gu;
    @PostConstruct
    public void InitDB(){
       System.out.println("#### BD EN CREATION ###");
       System.out.println("#### Création des utilisateurs ###");
       gu.genererUtilisateurs();
       System.out.println("#### Création des comptes courants ###");
       gc.creer2000ComptesCourant();
       System.out.println("### Création des comptes épargne ###");
       gc.creer500ComptesEpargnes();
       System.out.println("#### Création des opérations ###");
       gc.generer10000Operations();
       System.out.println("#### BD REMPLIE ###");
       System.out.println("###Création du Timer###");
       ScheduleExpression scheduleExp = new ScheduleExpression().second("*/20").minute("*").hour("*");
       this.timerService.createCalendarTimer(scheduleExp);
       System.out.println("###Création Timer terminée###");
    }
    
    @Timeout
    public void executerTraitement() {
        System.out.println("###Timer déclanché###");
        this.gc.appliquerTaux();
    }
}
