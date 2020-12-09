/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.FRED.GuidaTv.data.impl;

import it.FRED.GuidaTv.data.model.Genere;
import it.FRED.GuidaTv.data.model.SalvataggioGeneri;
import it.FRED.GuidaTv.data.model.Utente;
import it.FRED.GuidaTv.framework.data.DataItemImpl;

/**
 *
 * @author Francesca
 */
public class SalvataggioGeneriImpl extends DataItemImpl<Integer> implements SalvataggioGeneri{
   private Genere genere;
   private Utente utente;
    
   public  SalvataggioGeneriImpl(Genere genere, Utente utente){
       this.genere = genere;
       this.utente = utente;
   } 
    
    @Override
    public Genere getGenere(){
       return this.genere;
    }
    
    @Override
    
    public void setGenere(Genere g){
        this.genere = g;
    }
    
    @Override
    public Utente getUtente(){
        return this.utente;
    }
    
    @Override
    public void setUtente(Utente u){
        this.utente = u;
    }
    
}
