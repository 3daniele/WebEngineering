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
public interface Editore extends DataItem<Integer> {
    String getEmail();
    void setEmail(String e);
    
    String getPassword();
    void setPassword(String p);
}
