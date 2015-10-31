/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Yoann
 */
@Entity
public class CompteCourant extends CompteBancaire implements Serializable {
    
    public CompteCourant() {}
    
    public CompteCourant(String nom, double solde) {
        super(nom,solde);
    }

    @Override
    public String toString() {
        return "entities.CompteCourant[ id=" + id + " ]";
    }
   
    @Override
    public String getNomType(){
        return "Compte courant";
    }
    
}