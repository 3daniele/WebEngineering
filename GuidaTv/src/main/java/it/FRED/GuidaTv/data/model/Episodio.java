package it.FRED.GuidaTv.data.model;

import it.FRED.GuidaTv.framework.data.DataItem;


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