/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entity.CompteBancaire;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lazy.LazyCompteDataModel;
import org.primefaces.model.LazyDataModel;
import session.GestionnaireDeCompteBancaire;

/**
 *
 * @author Florian
 */
@Named(value = "CompteBancaireMBean")
@ManagedBean(name="dtSortView")
@ViewScoped
public class CompteBancaireMBean implements Serializable {  
    private List<CompteBancaire> compteBancaireList; 
    private Long idCompteAcrediter;
    private String message;

    private LazyCompteDataModel lazyModel;

    /**
     * Get the value of string
     *
     * @return the value of string
     */
    public LazyDataModel<CompteBancaire> getLazyModel() {
        return lazyModel;
    }

    /**
     * Set the value of string
     *
     * @param string new value of string
     */
    public void setLazyModel(LazyCompteDataModel string) {
        this.lazyModel = string;
    }


    private Long idCompteADebiter;
    private double soldeACrediter;
    private double soldeADebiter;
    private Long idCompteDepart;
    private Long idCompteArrive;
    private double montantATransferer;
    private Long idASupprimer;
    
     @EJB  
    private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire; 



     /**
     * Get the value of message
     *
     * @return the value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the value of message
     *
     * @param message new value of message
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * Get the value of idASupprimer
     *
     * @return the value of idASupprimer
     */
    public Long getIdASupprimer() {
        return idASupprimer;
    }

    /**
     * Set the value of idASupprimer
     *
     * @param idASupprimer new value of idASupprimer
     */
    public void setIdASupprimer(Long idASupprimer) {
        this.idASupprimer = idASupprimer;
    }

   
    /**
     * Get the value of soldeADebiter
     *
     * @return the value of soldeADebiter
     */
    public double getSoldeADebiter() {
        return soldeADebiter;
    }

    /**
     * Set the value of soldeADebiter
     *
     * @param soldeADebiter new value of soldeADebiter
     */
    public void setSoldeADebiter(double soldeADebiter) {
        this.soldeADebiter = soldeADebiter;
    }

    
      

    /**
     * Get the value of idCompteADebiter
     *
     * @return the value of idCompteADebiter
     */
    public Long getIdCompteADebiter() {
        return idCompteADebiter;
    }

    /**
     * Set the value of idCompteADebiter
     *
     * @param idCompteADebiter new value of idCompteADebiter
     */
    public void setIdCompteADebiter(Long idCompteADebiter) {
        this.idCompteADebiter = idCompteADebiter;
    }

  

    /**
     * Get the value of soldeACrediter
     *
     * @return the value of soldeACrediter
     */
    public double getSoldeACrediter() {
        return soldeACrediter;
    }

    /**
     * Set the value of soldeACrediter
     *
     * @param soldeACrediter new value of soldeACrediter
     */
    public void setSoldeACrediter(double soldeACrediter) {
        this.soldeACrediter = soldeACrediter;
    }

  
    
    public CompteBancaireMBean() {  
    }  
    
    @PostConstruct
    public void init() {
     
        //this.compteBancaireList = this.gestionnaireDeCompteBancaire.getAllComptes();
        //gestionnaireDeCompteBancaire.getAllComptes();
        this.lazyModel = new LazyCompteDataModel(this.gestionnaireDeCompteBancaire);
    }
    
    /** 
     * Renvoie la liste des comptes bancaires pour affichage dans une DataTable 
     * @return 
     */  
    public List<CompteBancaire>getComptes() {  
        return this.compteBancaireList;
    }
  

    /**
     * Get the value of idCompteAcrediter
     *
     * @return the value of idCompteAcrediter
     */
    public Long getIdCompteAcrediter() {
        return idCompteAcrediter;
    }

    /**
     * Set the value of idCompteAcrediter
     *
     * @param idCompteAcrediter new value of idCompteAcrediter
     */
    public void setIdCompteAcrediter(Long idCompteAcrediter) {
        this.idCompteAcrediter = idCompteAcrediter;
    }
  
    
    public void creerComptesDeTest() {
        this.gestionnaireDeCompteBancaire.creerComptesTest();
       // this.compteBancaireList = this.gestionnaireDeCompteBancaire.getAllComptes();
        try {
            this.message = "Création des utilisateurs de test réussie";
            System.out.println(this.message);
            FacesContext.getCurrentInstance().getExternalContext().redirect("accueil.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CompteBancaireMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void crediterCompte(){
        this.gestionnaireDeCompteBancaire.crediterCompte(this.idCompteAcrediter, this.soldeACrediter);
        //this.compteBancaireList = this.gestionnaireDeCompteBancaire.getAllComptes();
        try {
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("accueil.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CompteBancaireMBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void debiterCompte(){
        System.out.println(this.message);
        this.gestionnaireDeCompteBancaire.debiterCompte(this.getIdCompteADebiter(), this.soldeADebiter);
        //this.compteBancaireList = this.gestionnaireDeCompteBancaire.getAllComptes();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("accueil.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CompteBancaireMBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public void transferSolde(){
        this.gestionnaireDeCompteBancaire.transferer(this.idCompteDepart, this.idCompteArrive, this.montantATransferer);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("accueil.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CompteBancaireMBean.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    

    /**
     * Get the value of montantATransferer
     *
     * @return the value of montantATransferer
     */
    public double getMontantATransferer() {
        return montantATransferer; 
    }

    /**
     * Set the value of montantATransferer
     *
     * @param montantATransferer new value of montantATransferer
     */
    public void setMontantATransferer(double montantATransferer) {
        this.montantATransferer = montantATransferer;
    }

    

    /**
     * Get the value of idCompteArrive
     *
     * @return the value of idCompteArrive
     */
    public Long getIdCompteArrive() {
        return idCompteArrive;
    }

    /**
     * Set the value of idCompteArrive
     *
     * @param idCompteArrive new value of idCompteArrive
     */
    public void setIdCompteArrive(Long idCompteArrive) {
        this.idCompteArrive = idCompteArrive;
    }



    /**
     * Get the value of idCompteDepart
     *
     * @return the value of idCompteDepart
     */
    public Long getIdCompteDepart() {
        return idCompteDepart;
    }

    /**
     * Set the value of idCompteDepart
     *
     * @param idCompteDepart new value of idCompteDepart
     */
    public void setIdCompteDepart(Long idCompteDepart) {
        this.idCompteDepart = idCompteDepart;
    }
    
    public void supprimerCompte(Long id){
        System.out.println("id->"+id);
        this.gestionnaireDeCompteBancaire.supprimerCompter(id);
         try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("accueil.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CompteBancaireMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void genener10000Operations(){
        this.gestionnaireDeCompteBancaire.generer10000Operations();
          try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("accueil.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CompteBancaireMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}