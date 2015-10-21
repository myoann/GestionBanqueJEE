package jsf;

import ejb.GestionnaireDeCompteBancaire;
import entite.CompteBancaire;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author richard
 */
@Named(value = "gestionCompte")
@RequestScoped
public class GestionCompte {
  @EJB
  private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;
  

  /** Creates a new instance of GestionCompte */
  public GestionCompte() {
  }
  
  public String supprimer(CompteBancaire compteBancaire) {
    gestionnaireDeCompteBancaire.supprimer(compteBancaire);
    return null;
  }
}
