package it.FRED.GuidaTv.data.impl;

import it.FRED.GuidaTv.data.model.Canale;
import it.FRED.GuidaTv.framework.data.DataItemImpl;


public class CanaleImpl extends DataItemImpl<Integer> implements Canale{
    private String nome;
    private String gruppo;
    
    public CanaleImpl (String n, String g){
        this.nome = n;
        this.gruppo = g;
    }
    
    public CanaleImpl() {
        this.nome = "";
        this.gruppo = "";
    }
    
    
    @Override
    public String getNome(){
        return this.nome;
    }
    
    
    @Override
    public void setNome(String n){
        this.nome = n;
    }
    
    
    @Override
    public String getGruppo(){
        return this.gruppo;
    }
    
    @Override
    public void setGruppo(String g){
        this.gruppo = g;
    }
}
