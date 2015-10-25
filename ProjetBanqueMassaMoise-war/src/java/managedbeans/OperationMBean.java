/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entity.CompteBancaire;
import entity.OperationBancaire;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import lazy.LazyCompteDataModel;
import lazy.LazyOperationModel;
import session.GestionnaireDeCompteBancaire;

/**
 *
 * @author Florian
 */
@Named(value = "OperationMBean")
@ManagedBean(name="dtSortView")
@ViewScoped
public class OperationMBean implements Serializable {
    @EJB
    private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;

    private List<OperationBancaire> operationList; 
    private Long idCompte;

    private LazyOperationModel lazyModel;

    public LazyOperationModel getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyOperationModel lazyModel) {
        this.lazyModel = lazyModel;
    }

    public GestionnaireDeCompteBancaire getGestionnaireDeCompteBancaire() {
        return gestionnaireDeCompteBancaire;
    }

    public void setGestionnaireDeCompteBancaire(GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire) {
        this.gestionnaireDeCompteBancaire = gestionnaireDeCompteBancaire;
    }

    public List<OperationBancaire> getOperationList() {
        return (List<OperationBancaire>) this.gestionnaireDeCompteBancaire.getCompte(idCompte).getOperations();
    }

    public void setOperationList(List<OperationBancaire> OperationList) {
        this.operationList = OperationList;
    }


    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Long getIdCompte() {
        return idCompte;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setIdCompte(Long id) {
        this.idCompte = id;
    }

    /**
     * Creates a new instance of OperationMBean
     */
    public OperationMBean() {
    }
    @PostConstruct //pour que cela soit executé apres l'injection de code
    public void init(){

    }
    
    public LazyOperationModel getOperations(){
        /*CompteBancaire c = this.gestionnaireDeCompteBancaire.getCompte(this.idCompte);
        this.operationList = (List<OperationBancaire>) c.getOperations();
        return this.operationList;*/
        this.lazyModel = new LazyOperationModel(this.gestionnaireDeCompteBancaire);
        this.lazyModel.setIdCompteCourant(this.idCompte);
        return this.lazyModel;
    }
   
    
    
}
