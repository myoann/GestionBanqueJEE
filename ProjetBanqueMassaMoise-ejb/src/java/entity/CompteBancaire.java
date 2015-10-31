/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.EJBException;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Florian
 */
@Entity
@Table(name="CompteBancaire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompteBancaire.findAll", query = "SELECT c FROM CompteBancaire c order by c.id ASC"),
    @NamedQuery(name = "CompteBancaire.count", query = "SELECT count(c) FROM CompteBancaire c ")
})
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType=DiscriminatorType.STRING)//, columnDefinition="type")
public class CompteBancaire implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idCompte")
    public Long id;
    @Column(name="solde")
    public double solde;
    @Column(name="nom")
    private String nom;
    @OneToMany(cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
    // A ESSAYER :     @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @OrderBy("dateOperation ASC")
    private Collection<OperationBancaire> operations = new ArrayList<>();
    public String description;
    
    public Collection<OperationBancaire> getOperations() {
        return operations;
    }
    
    public CompteBancaire(){

    }
    
    public CompteBancaire(String nom, double solde) {
        this.solde = solde;
        this.nom = nom;
        this.addOperation("Creation de compte", solde);
    }
    
    
    
    /**
     * Get the value of solde
     *
     * @return the value of solde
     */
    public double getSolde() {
        return solde;
    }

    /**
     * Set the value of solde
     *
     * @param solde new value of solde
     */
    public void setSolde(double solde) {
        this.solde = solde;
    }
    

  

    /**
     * Get the value of nom
     *
     * @return the value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the value of nom
     *
     * @param nom new value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    
    public void deposer(double montant) {
    solde += montant;
    this.addOperation("Compte crédité", montant);
    }
  
    public void retirer(double montant) {
     /* if (montant < solde) {
        solde -= montant;
        this.addOperation("Compte débité", montant);
      } else {
        throw new EJBException();
      }*/
        solde -= montant;
        this.addOperation("Compte débité", montant);
    }
    public void addOperation(String description, double solde){
        OperationBancaire op = new OperationBancaire(description, solde);
        this.operations.add(op);
    }
    
    
    public String getNomType(){
        return "Compte bancaire";
    }
  
    @Override
    public String toString() {
        return "entity.CompteBancaire[ id=" + id + " ]";
    }
    
 
}
