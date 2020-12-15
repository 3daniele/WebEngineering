package it.FRED.GuidaTv.data.impl;

import it.FRED.GuidaTv.data.model.Utente;
import it.FRED.GuidaTv.framework.data.DataItemImpl;


public class UtenteImpl extends DataItemImpl<Integer> implements Utente{
   
    private String email;
    private String password;
    private String status;
    private String ruolo;
    
    
    public UtenteImpl(String email, String password, String status){
        super();
        this.email=email;
        this.password=password;
        this.status=status;
    }
    
    public UtenteImpl(String email, String password, String status, String ruolo){
        super();
        this.email=email;
        this.password=password;
        this.status=status;
        this.ruolo=ruolo;
    }
    
      @Override
    public String getEmail(){
        return this.email;
    }
      @Override
    public void setEmail(String t){
        this.email = t; 
    }
      @Override
    public String getPassword(){
        return this.password;
    }
      @Override
    public void setPassowrd(String p){
        this.password = p;
    }
      @Override
    public String getStatus(){
        return this.status;
    }
      @Override
    public void setStatus(String s){
        this.status = s;
    }
    
    @Override
    public String getRuolo(){
        return this.ruolo;
    }
      @Override
    public void setRuolo(String r){
        this.ruolo = r;
    }
    
    
}
