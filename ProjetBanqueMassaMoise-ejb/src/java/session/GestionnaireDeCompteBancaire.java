/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.CompteBancaire;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Florian
 */
@Stateless
@LocalBean
public class GestionnaireDeCompteBancaire {
   @PersistenceContext(unitName = "TP3BanqueMassa-ejbPU")
   private EntityManager em;
   public static String[] TABNOM ={"Massa", "Moise", "Korfed","Jauvat","Linares","El hadi","Bond","Silima","Buffa","Gauche","Comba","Garro"};
   public static String[] TABPRENOMS ={"Florian","Wissam","Yoann","Elmahdi","Fabrice","Tibault","Michel","Toto","Maxime","Mazen","Lisa"}; 
   
   public GestionnaireDeCompteBancaire(){
       
   }
   public void creerCompte(CompteBancaire c) {
       em.persist(c);
   }
   public List<CompteBancaire> getAllComptes() {
       Query q = em.createNamedQuery("CompteBancaire.findAll");
       if(q==null){
           return null;
       }
       return q.getResultList();   
   }
   public List<CompteBancaire> getComptes(int start, int nombreDeComptes){
       Query q = em.createNamedQuery("CompteBancaire.findAll");
       q.setFirstResult(start);
       q.setMaxResults(nombreDeComptes);
       if(q==null){
           return null;
       }
       return q.getResultList();
   }
   
    public void creerComptesTest() {
       creerCompte(new CompteBancaire("John Lennon", 150000));
       creerCompte(new CompteBancaire("Paul McCartney", 950000));
       creerCompte(new CompteBancaire("Ringo Starr", 20000));
       creerCompte(new CompteBancaire("Georges Harrisson", 100000));
    }
    public void crediterCompte(Long id, double montant){
        CompteBancaire c = this.em.find(CompteBancaire.class, id);
        c.deposer(montant);
    }
    public void debiterCompte(Long id, double montant){
        CompteBancaire c = this.em.find(CompteBancaire.class, id);
        c.retirer(montant);
    }
    
    public void transferer(Long id1, Long id2, double montant){
        this.debiterCompte(id1, montant); 
        this.crediterCompte(id2, montant);
    }
    
    public void supprimerCompter(Long id){
        System.out.println("suppression");
        CompteBancaire c =this.em.find(CompteBancaire.class, id);
        em.remove(em.merge(c));//le compte n'est peut etre pas connecté
    } 

    public void persist(Object object) {
        em.persist(object);
    }
    
    public CompteBancaire getCompte(Long id) {
        return em.find(CompteBancaire.class, id);
    }
    public int getNbComptes(){
        Query q = this.em.createQuery("select count(c) from CompteBancaire c");
        return ((Long) q.getSingleResult()).intValue();
    }

    public void creer1000Comptes() {
        for(int i=0; i<1000; i++){
            int indice1 = (int)(Math.random()*TABNOM.length);
            int indice2 = (int)(Math.random()*TABPRENOMS.length);
            this.creerCompte(new CompteBancaire(TABNOM[indice1]+" "+TABPRENOMS[indice2], (int)(Math.random()*10000)));
        }
    }
    public List<CompteBancaire> getComptesTrie(String champ, String order, int depart, int nb){
        String orderValue = "";
        if(order.equals("ASCENDING")) {
            orderValue = "ASC";
        } else {
            orderValue = "DESC";
        }
        String r="";
        if(champ.equals("nom")){
            r = "select c from CompteBancaire c order by c.nom " 
               + orderValue;
            System.out.println("TRI PAR NOM: " + r);  
        }else if(champ.equals("id")){
            r = "select c from CompteBancaire c order by c.id " 
               + orderValue;
            System.out.println("TRI PAR ID: " + r);  
        }else if(champ.equals("solde")){
            r = "select c from CompteBancaire c order by c.solde " 
               + orderValue;
            System.out.println("TRI PAR SOLDE: " + r);  
        }
        
        Query q = em.createQuery(r);
        q.setFirstResult(depart);
        q.setMaxResults(nb);
        return q.getResultList();
    }
    
    
}
