package it.FRED.GuidaTv.data.impl;


import it.FRED.GuidaTv.data.model.Programma;
import it.FRED.GuidaTv.data.model.Stagione;
import it.FRED.GuidaTv.framework.data.DataItemImpl;


public class StagioneImpl extends DataItemImpl<Integer> implements Stagione{
    private Programma programma;
    private int numero;
    private String descrizione;
    private String immagine;
    
    public StagioneImpl(){
        this.programma = null;
        this.numero = 0;
        this.descrizione = null;
        this.immagine = null;
    }
    
    public StagioneImpl(Programma s,int n,String descrizione,String immagine){
        this.programma = s;
        this.numero = n;
        this.descrizione = descrizione;
        this.immagine = immagine;
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
    public String getDescrizione(){
        return this.descrizione;
    }
    
    @Override
    public  void setDescrizione(String  n){
        this.descrizione=n;
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
