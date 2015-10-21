package jsf;

import ejb.GestionnaireDeCompteBancaire;
import entite.CompteBancaire;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;

/**
 *
 * @author richard
 */
@Named(value = "ajoutRetraitController")
//@RequestScoped
//@ManagedBean
@ViewScoped
public class AjoutRetraitController implements Serializable {

  @EJB
  private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;
  private long idCompte;
  /**
   * Le compte bancaire d'id idCompte.
   */
  private CompteBancaire compte;
  /**
   * Dépôt ou retrait d'argent sur le compte.
   */
  private String typeOperation;
  private int montant;

  /** Creates a new instance of AjoutRetraitController */
  public AjoutRetraitController() {
  }

  /**
   * Charge les données sur le compte d'id idCompte.
   * Et démarre la conversation.
   */
  public void chargeClient() {
   
    if (compte == null) {
      compte = gestionnaireDeCompteBancaire.findById(idCompte);
      System.out.println("****Récupération du compte de "
              + compte.getNomProprio() + " par " + this);
    }
  }

  public CompteBancaire getCompte() {
    return compte;
  }

//  public void setCompte(CompteBancaire compte) {
//    this.compte = compte;
//  }
  public long getIdCompte() {
    return idCompte;
  }

  public void setIdCompte(long idCompte) {
    this.idCompte = idCompte;
  }

  public int getMontant() {
    return montant;
  }

  public void setMontant(int montant) {
    this.montant = montant;
  }

  public String getTypeOperation() {
    return typeOperation;
  }

  public void setTypeOperation(String typeOperation) {
    this.typeOperation = typeOperation;
  }

  public String operation() {
    System.out.println("Exécution de operation par "
            + this + "; type opération=" + typeOperation);
    System.out.println("montant=" + montant);
    if (typeOperation.equals("depot")) {
      compte.deposer(montant);
    } else {
      compte.retirer(montant);
    }
    gestionnaireDeCompteBancaire.update(compte);
    return null;
  }
}
