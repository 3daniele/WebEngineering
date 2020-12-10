/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.FRED.GuidaTv.data.model;
import it.FRED.GuidaTv.framework.data.DataItem;
/**
 *
 * @author Daniele
 */
public interface Utente extends DataItem<Integer> {
    
    String getRuolo();
    void setRuolo(String r);
    
    String getEmail();
    void setEmail(String t);
    
    String getPassword();
    void setPassowrd(String p);
    
    String getStatus();
    void setStatus(String s);
    
    
}
