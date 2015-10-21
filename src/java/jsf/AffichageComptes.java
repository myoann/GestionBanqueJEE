/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import ejb.GestionnaireDeCompteBancaire;
import entite.CompteBancaire;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author richard
 */
@Named(value = "affichageComptes")
@RequestScoped
public class AffichageComptes {
  @EJB
  private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;

  /** Creates a new instance of AffichageComptes */
  public AffichageComptes() {
  }
  
  public List<CompteBancaire> findAllComptes() {
    return gestionnaireDeCompteBancaire.findAll();
  }
}
