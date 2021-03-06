/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lazy;

import entity.CompteBancaire;
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
public class LazyCompteDataModel extends LazyDataModel<CompteBancaire> {
    private GestionnaireDeCompteBancaire gc;
    private List<CompteBancaire> buf;
    
    public LazyCompteDataModel (GestionnaireDeCompteBancaire gc){
        super();
        this.gc = gc;
    }
     public LazyCompteDataModel (){
         super();
    }
     
    @Override
    public List<CompteBancaire> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        System.out.println("[LazyCompteDataModel.load] debut "+first+ " fin "+ pageSize);
        this.buf = null;
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
        
        if(!filters.isEmpty()){
            
             this.buf = this.gc.getComptesFiltre(filters,first,pageSize);
        }
        else if(sortField != null){
            this.buf = this.gc.getComptesTrie(sortField,sortOrder.name(),first,pageSize);
        }
        else{
            this.buf = this.gc.getComptes(first,pageSize);
        
        }
        return this.buf;
       
    }
    @Override
    public int getRowCount() {
        return this.gc.getNbComptes();
    }
    
    @Override
    public Object getRowKey(CompteBancaire c) {
        return c.getId();
    }
     @Override
    public CompteBancaire getRowData(String rowKey) {
        for(CompteBancaire c : this.buf) {
            if(c.getId().equals(rowKey))
                return c;
        }
        return null;
    }
    
    
}
