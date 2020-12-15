package it.FRED.GuidaTv.data.impl;


import it.FRED.GuidaTv.data.model.Programma;
import it.FRED.GuidaTv.data.model.Stagione;
import it.FRED.GuidaTv.framework.data.DataItemImpl;


public class StagioneImpl extends DataItemImpl<Integer> implements Stagione{
    private Programma programma;
    private Integer numero;
    private Integer n_episodi;
    private String immagine;
    
    public StagioneImpl(Programma s,int n,int n_episodi,String immagine){
        this.programma=s;
        this.numero=n;
        this.n_episodi=n_episodi;
        this.immagine=immagine;
    }
    @Override
    public  Programma getProgramma(){
        return this.programma;
    }
    @Override
     public void setProgramma(Programma s){
        this.programma=s;
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
    
    @Override
    public String getImmagine(){
        return this.immagine;
    }
    @Override
    public void setImmagine(String link){
        this.immagine=link;
    }
    
}
