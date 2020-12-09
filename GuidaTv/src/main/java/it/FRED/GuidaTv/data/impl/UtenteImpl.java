/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.FRED.GuidaTv.data.impl;
import it.FRED.GuidaTV.framework.data.DataItemImpl;
import it.FRED.GuidaTV.data.model.Utente;
/**
 *
 * @author Francesca
 */
public class UtenteImpl extends DataItemImpl<Integer> implements Utente{
   
    private String email;
    private String password;
    private String status;
    
    
    public UtenteImpl(String email, String password, String status){
        super();
        this.email=email;
        this.password=password;
        this.status=status;
    }
    
    
    String getEmail(){
        return this.email;
    }
    void setEmail(String t){
        this.email = t; 
    }
    
    String getPassword(){
        return this.password;
    }
    void setPassowrd(String p){
        this.password = p;
    }
    
    String getStatus(){
        return this.status;
    }
    void setStatus(String s){
        this.status = s;
    }
    
    
    
}
