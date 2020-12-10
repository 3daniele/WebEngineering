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
public interface Programma extends DataItem<Integer>{
    
    int getDurata();
    void setDurata(int d);
    
    int getAnno();
    void setAnno(int a);
    
    String getDescrizione();
    void setDescrizione(String d);
    
    Utente getEditore();
    void setEditore(Utente d);
    
    String getTipo();
    void setTipo(String t);
    
    String getImmagine();
    void setImmagine(String link);
    
}
