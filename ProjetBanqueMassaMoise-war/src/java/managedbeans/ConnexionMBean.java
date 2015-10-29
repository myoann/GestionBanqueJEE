/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entity.Utilisateur;
import java.io.IOException;
import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import session.GestionnaireUtilisateur;

/**
 *
 * @author Florian
 */
@ManagedBean(name="connexionMBean")
@SessionScoped

public class ConnexionMBean implements Serializable {
    @EJB
    private GestionnaireUtilisateur gestionnaireUtilisateur;
    private String mdpCourant;
    private String loginUtilisateurCourant;
    private boolean estConnecte;
    /**
     * Get the value of mdpCourant
     *
     * @return the value of mdpCourant
     */
    public String getMdpCourant() {
        return mdpCourant;
    }

    /**
     * Set the value of mdpCourant
     *
     * @param mdpCourant new value of mdpCourant
     */
    public void setMdpCourant(String mdpCourant) {
        this.mdpCourant = mdpCourant;
    }
 

    /**
     * Get the value of idUtilisateurCourant
     *
     * @return the value of idUtilisateurCourant
     */
    public String getLoginUtilisateurCourant() {
        return this.loginUtilisateurCourant;
    }

    /**
     * Set the value of idUtilisateurCourant
     *
     * @param idUtilisateurCourant new value of idUtilisateurCourant
     */
    public void setLoginUtilisateurCourant(String idUtilisateurCourant) {
        this.loginUtilisateurCourant = idUtilisateurCourant;
    }

    /**
     * Creates a new instance of ConnexionMBean
     */
    public ConnexionMBean() {
      
    }
    
    public void seConnecter(){
        Utilisateur u = this.gestionnaireUtilisateur.seConnecter(this.loginUtilisateurCourant, this.mdpCourant);
        if (u!=null){
            this.estConnecte = true;
            System.out.println("Connexion pour "+this.loginUtilisateurCourant);
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/accueil.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(CompteBancaireMBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,"Mauvais identifiant ou mot de passe",""));
  
    }
    
    public void seDeconnecter(){
        this.estConnecte = false;
        this.mdpCourant ="";
        this.loginUtilisateurCourant="";
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(CompteBancaireMBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void checkConnexion(){
        if(!this.estConnecte){
           try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/login.xhtml");
            } catch (IOException ex) {
            Logger.getLogger(CompteBancaireMBean.class.getName()).log(Level.SEVERE, null, ex);
            }  
        }
    }
   
}
