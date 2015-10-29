/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import javax.inject.Named;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import session.GestionnaireUtilisateur;

/**
 *
 * @author Florian
 */
@Named(value = "connexionMBean")
@SessionScoped
public class ConnexionMBean implements Serializable {
    @EJB
    private GestionnaireUtilisateur gestionnaireUtilisateur;

    /**
     * Creates a new instance of ConnexionMBean
     */
    public ConnexionMBean() {
    }
    
}
