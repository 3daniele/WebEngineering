/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.FRED.GuidaTv.data.impl;

import it.FRED.GuidaTv.data.model.Genere;
import it.FRED.GuidaTv.data.model.Lgs;
import it.FRED.GuidaTv.data.model.Spettacolo;
import it.FRED.GuidaTv.framework.data.DataItemImpl;

/**
 *
 * @author Francesca
 */
public class LgsImpl extends DataItemImpl<Integer> implements Lgs{
    
    private Spettacolo spettacolo;
    private Genere genere;
    
    public LgsImpl(Genere g, Spettacolo s){
        this.genere = g;
        this.spettacolo = s;
    }
    
     @Override
    public Spettacolo getSpettacolo(){
        return this.spettacolo;
    }
     @Override
    public void setSpettacolo(Spettacolo s){
        this.spettacolo = s;
    }
     @Override
    public Genere getGenere(){
        return this.genere;
    }
     @Override
    public void setGenere(Genere g){
         this.genere = g;
    }
}
