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
public interface Stagione extends DataItem<Integer>{
    
    Programma getProgramma();
    void setProgramma(Programma s);
    
    int getNumero();
    void setNumero(int n);
    
    int getNEpisodi();
    void setNEpisodi(int n);
    
    String getImmagine();
    void setImmagine(String link);
    
}
