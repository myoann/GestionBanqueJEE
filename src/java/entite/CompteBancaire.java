package entite;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author richard
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll",
    query = "SELECT c FROM CompteBancaire c"),
    @NamedQuery(name = "CompteBancaire.findById",
    query = "SELECT c FROM CompteBancaire c WHERE c.id = :id"),
    @NamedQuery(name = "CompteBancaire.findByNom",
    query = "SELECT c FROM CompteBancaire c WHERE c.nomProprio = :nomProprio")})
public class CompteBancaire implements Serializable {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String nomProprio;
  private int solde;

  public CompteBancaire() {
  }

  public CompteBancaire(String nomProprio, int solde) {
    this.nomProprio = nomProprio;
    this.solde = solde;
  }
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Get the value of nomProp
   *
   * @return the value of nomProp
   */
  public String getNomProprio() {
    return nomProprio;
  }

  /**
   * Set the value of nomProp
   *
   * @param nomProp new value of nomProp
   */
  public void setNomProprio(String nomProp) {
    this.nomProprio = nomProp;
  }

  /**
   * Get the value of solde
   *
   * @return the value of solde
   */
  public int getSolde() {
    return solde;
  }

  /**
   * Set the value of solde
   *
   * @param solde new value of solde
   */
  public void setSolde(int montant) {
    this.solde = solde;
  }

  public void deposer(int montant) {
    solde += montant;
  }

  /**
   * Retrait d'un certain montant.
   * @param montant le montant à retirer
   * @return le montant retiré
   */
  public int retirer(int montant) {
    if (montant <= solde) {
      solde -= montant;
      return montant;
    } else {
      return 0;
    }
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof CompteBancaire)) {
      return false;
    }
    CompteBancaire other = (CompteBancaire) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "entite.CompteBancaire[ id=" + id + " ]";
  }
}
