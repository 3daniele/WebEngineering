/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.FRED.GuidaTv.data.impl;


import it.FRED.GuidaTv.data.model.Spettacolo;
import it.FRED.GuidaTv.data.model.Stagione;
import it.FRED.GuidaTv.framework.data.DataItemImpl;

/**
 *
 * @author Francesca
 */
public class StagioneImpl extends DataItemImpl<Integer> implements Stagione{
    private Spettacolo spettacolo;
    private Integer numero;
    private Integer n_episodi;
    
    public StagioneImpl(Spettacolo s,int n,int n_episodi){
        this.spettacolo=s;
        this.numero=n;
        this.n_episodi=n_episodi;
    }
    @Override
    public  Spettacolo getSpettacolo(){
        return this.spettacolo;
    }
    @Override
     public void setSpettacolo(Spettacolo s){
        this.spettacolo=s;
    }
    @Override
    public int getNumero(){
        return this.numero;
    }
    @Override
    public void setNumero(int n){
       this.numero=n; 
    }
    @Override
    public int getNEpisodi(){
        return this.n_episodi;
    }
    @Override
   public  void setNEpisodi(int n){
        this.n_episodi=n;
    }
    
}
