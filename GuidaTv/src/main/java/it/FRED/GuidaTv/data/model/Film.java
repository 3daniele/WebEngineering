/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.FRED.GuidaTv.data.model;
import it.FRED.GuidaTv.framework.data.DataItem;
/**
 *
 * @author DanieleDD
 */
public interface Film extends DataItem<Integer>{
    
    int getDurata();
    void setDurata(int d);
    
    int getAnno();
    void setAnno(int a);
    
    String getDescrizione();
    void setDescrizione(String d);
    
    Editore getEditore();
    void setEditore(Editore d);
    
}