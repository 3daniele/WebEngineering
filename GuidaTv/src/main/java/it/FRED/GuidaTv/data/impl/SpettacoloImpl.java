/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.FRED.GuidaTv.data.impl;

import it.FRED.GuidaTv.data.model.Editore;
import it.FRED.GuidaTv.data.model.Spettacolo;
import it.FRED.GuidaTv.framework.data.DataItemImpl;

/**
 *
 * @author Francesca
 */
public class SpettacoloImpl extends DataItemImpl<Integer> implements Spettacolo {
    
    private int durata;
    private String descrizione;
    private Editore editore;
    
    
    public SpettacoloImpl(int durata,String desrizione,Editore editore){
      this.durata = durata;
      this.descrizione = descrizione;
      this.editore= editore;
    }
    @Override
    public int getDurata(){
        return this.durata;
    } 
    @Override
   public void setDurata(int d){
        this.durata = d;
    }
    @Override 
   public String getDescrizione(){
       return this.descrizione;
   }
    @Override
   public void setDescrizione(String d){
       this.descrizione = d;
   }
    @Override
    public Editore getEditore(){
        return this.editore;
    }
    @Override
    public void setEditore(Editore d){
        this.editore = d;
    }
}
