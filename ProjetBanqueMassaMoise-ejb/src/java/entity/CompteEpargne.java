/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.CompteBancaire;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import javax.persistence.Entity;

/**
 *
 * @author Yoann
 */
@Entity
public class CompteEpargne extends CompteBancaire implements Serializable {
	private double tauxEpargne;

	public CompteEpargne() {}

	public CompteEpargne(String nom, double solde, double taux) {
		super(nom, solde);
		this.tauxEpargne = taux;
                this.description = "Epargne";
	}

	public void appliquerTaux() {
            DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.UK);
            otherSymbols.setDecimalSeparator('.');
            otherSymbols.setGroupingSeparator('.'); 
            DecimalFormat df = new DecimalFormat("#.##",otherSymbols);
            df.setDecimalFormatSymbols(null);
            double gain = solde*tauxEpargne;
            gain = Double.valueOf(df.format(gain));
            if(gain<0){
                gain*=-1;
            }
            solde += gain;
            this.addOperation("Application du taux "+this.tauxEpargne+" pour le compte épargne", gain);
            System.out.println("Compte id = "+id+" solde "+solde);
	}

	public double getTauxEpargne() {
		return tauxEpargne;
	}

	public void setTauxEpargne (double taux) {

	}

        @Override
        public String getNomType(){
            return "Compte épargne";
        }
        
        @Override
        public String getNomType_en(){
            return "Savings account";
        }
    
	@Override 
        public String toString() {
            return "entities.CompteEpargne[ id=" + id + " ]";
        }



}
