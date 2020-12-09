/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.FRED.GuidaTv.data.impl;

import it.FRED.GuidaTv.data.model.Canale;
import it.FRED.GuidaTv.data.model.SalvataggioCanali;
import it.FRED.GuidaTv.data.model.Utente;
import it.FRED.GuidaTv.framework.data.DataItemImpl;

/**
 *
 * @author Francesca
 */
public class SalvataggioCanaliImpl extends DataItemImpl<Integer> implements SalvataggioCanali {
    private Canale canale;
    private Utente utente;
    
    public SalvataggioCanaliImpl(Canale c, Utente u){
        this.canale = c;
        this.utente = u;
    }
    @Override
    public Canale getCanali(){
        return this.canale;
    }
    @Override
    public void setCanale(Canale c){
         this.canale = c;
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
