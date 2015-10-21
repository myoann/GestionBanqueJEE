/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entite.CompteBancaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author richard
 */
@Stateless
@LocalBean
public class GestionnaireDeCompteBancaire {
  @PersistenceContext
  private EntityManager em;

  public void creerCompte(CompteBancaire compteBancaire) {
    persist(compteBancaire);
  }

  public void creerComptes(CompteBancaire[] comptesBancaires) {
    for (CompteBancaire comptebancaire : comptesBancaires) {
      creerCompte(comptebancaire);
    }
  }
  
  public List<CompteBancaire> findAll() {
    return (List<CompteBancaire>)em.createNamedQuery("CompteBancaire.findAll").getResultList();
  }

  public void persist(CompteBancaire compteBancaire) {
    em.persist(compteBancaire);
  }
  
  public void update(CompteBancaire compteBancaire) {
    em.merge(compteBancaire);
  }
  
  public CompteBancaire findById(long id) {
    return em.find(CompteBancaire.class, id);
  }
  
  public void transferer(CompteBancaire source, CompteBancaire destination, 
          int montant) {
    int val = source.retirer(montant);
    if (val == 0) {
      // La source n'a plus assez d'argent !!
      // Il faudrait afficher un message d'erreur.
      return;
    }
    destination.deposer(montant);
    update(source);
    update(destination);
  }
  
  public void supprimer(CompteBancaire compteBancaire) {
    em.remove(em.merge(compteBancaire));
  }
}
