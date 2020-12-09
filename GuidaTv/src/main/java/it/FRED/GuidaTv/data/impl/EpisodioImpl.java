/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.FRED.GuidaTv.data.impl;

import it.FRED.GuidaTv.data.model.Episodio;
import it.FRED.GuidaTv.data.model.Stagione;
import it.FRED.GuidaTv.framework.data.DataItemImpl;

/**
 *
 * @author Francesca
 */
public class EpisodioImpl extends DataItemImpl<Integer> implements Episodio {
     
    private Stagione stagione;
    private int numero;
    private int durata;
    private String descrizione;
    
    public EpisodioImpl(Stagione stagione, int numero, String descrizione, int durata){
      this.stagione = stagione;
      this.durata = durata;
      this.descrizione = descrizione;
      this.numero = numero;
    }
    
    @Override
    public Stagione getStagione(){
        return this.stagione;
    }
    
    @Override
    public void setStagione(Stagione s){
        this.stagione = s;  
    }
    
    @Override
    public int getNumero(){
        return this.numero;
    }
    
    @Override
    public void setNumero(int n){
        this.numero = n;
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
}
