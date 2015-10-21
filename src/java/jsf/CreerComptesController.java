/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Named(value = "creerComptesController")
@RequestScoped
public class CreerComptesController {
  @EJB
  private GestionnaireDeCompteBancaire gestionnaireDeCompteBancaire;

  /** Creates a new instance of CreerComptesController */
  public CreerComptesController() {
  }
  
  public String creerComptesTest() {
    CompteBancaire[] comptesTest = {
      new CompteBancaire("John Lennon", 150000),
      new CompteBancaire("Paul Mac Cartney", 950000),
      new CompteBancaire("Ringo Starr", 20000),
      new CompteBancaire("Georges Harrisson", 100000)
    };
    gestionnaireDeCompteBancaire.creerComptes(comptesTest);
    return "affichageComptes?faces-redirect=true";
  }
}
