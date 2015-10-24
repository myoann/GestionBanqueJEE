/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lazy;

import entity.CompteBancaire;
import entity.OperationBancaire;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import session.GestionnaireDeCompteBancaire;

/**
 *
 * @author Florian
 */
public class LazyOperationModel extends LazyDataModel<OperationBancaire> {
    private GestionnaireDeCompteBancaire gc;
    
    public LazyOperationModel (GestionnaireDeCompteBancaire gc){
        super();
        this.gc = gc;
    }
     public LazyOperationModel (){
         super();
    }
     
    @Override
    public List<OperationBancaire> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        System.out.println("[LazyCompteDataModel.load] debut "+first+ " fin "+ pageSize);
        //filter
       /* if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(Compt.getClass().getField(filterProperty).get(car));
 
                        if(filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                    }
                    else {
                            match = false;
                            break;
                        }
                    } catch(Exception e) {
                        match = false;
                    }
                }
            }*/
        //sort
        if(sortField != null){
            return null;//getComptesTrie(sortField,sortOrder.name(),first,pageSize);
        }else{
            return null;//this.gc.getComptes(first,pageSize);
        }
      
       
    }
    @Override
    public int getRowCount() {
        return this.gc.getNbComptes();
    }
    
    /*@Override
    public Object getRowKey(CompteBancaire c) {
        return c.getId();
    }*/
    
    
}
