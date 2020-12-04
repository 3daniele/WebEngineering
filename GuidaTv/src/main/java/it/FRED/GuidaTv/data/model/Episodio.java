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
public interface Episodio extends DataItem<Integer>{
    
    Stagione getStagione();
    void setStagione(Stagione s);
    
    int getNumero();
    void setNumero(int n);
    
    int getDurata();
    void setDurata(int d);
    
    String getDescrizione();
    void setDescrizione(String d);
    
}