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
public interface Canale extends DataItem<Integer>{
    String getNome();
    void setNome(String n);
    
    String getGruppo();
    void setGruppo(String g);
}
