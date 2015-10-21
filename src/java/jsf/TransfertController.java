package jsf;

import ejb.GestionnaireDeCompteBancaire;
import entite.CompteBancaire;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Backing bean pour transfert d'argent.
 * @author richard
 */
@Named(value = "transfertController")
@RequestScoped
public class TransfertController {

  @EJB
  private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;
  private long sourceId;
  private long destinationId;
  private int montantTransfert;

  public TransfertController() {
  }

  public long getDestinationId() {
    return destinationId;
  }

  public void setDestinationId(long destinationId) {
    this.destinationId = destinationId;
  }

  public int getMontant() {
    return montantTransfert;
  }

  public void setMontant(int montant) {
    this.montantTransfert = montant;
  }

  public long getSourceId() {
    return sourceId;
  }

  public void setSourceId(long sourceId) {
    this.sourceId = sourceId;
  }

  public String transferer() {
    // vrai si on peut faire le transfert
    // permet d'afficher toutes les messages d'erreur en 1 fois
    boolean ok = true;
    CompteBancaire compteSource =
            gestionnaireDeCompteBancaire.findById(sourceId);
    if (compteSource == null) {
      String msg = "Compte source n'existe pas";
      FacesMessage facesMsg =
              new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
      FacesContext.getCurrentInstance().addMessage("transfert:source", facesMsg);
      ok = false;
    }
    CompteBancaire compteDestination =
            gestionnaireDeCompteBancaire.findById((long) destinationId);
    if (compteDestination == null) {
      String msg = "Compte destination n'existe pas";
      FacesMessage facesMsg =
              new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
      FacesContext.getCurrentInstance().addMessage("transfert:destination", facesMsg);
      ok = false;
    }
    // Tester s'il y a assez d'argent sur le compte source
    if (compteSource != null) {
      double soldeCompteSource = compteSource.getSolde();
      if (soldeCompteSource < montantTransfert) {
        String msg = "Pas assez d'argent sur le compte de "
                + compteSource.getNomProprio();
        FacesMessage facesMsg =
                new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage("transfert:montant", facesMsg);
        ok = false;
      }
    }
    if (ok) {
      gestionnaireDeCompteBancaire.transferer(compteSource, compteDestination,
              montantTransfert);
    }
    return null;
  }
}
