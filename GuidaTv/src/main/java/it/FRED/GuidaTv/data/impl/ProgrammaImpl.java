/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.FRED.GuidaTv.data.impl;

import it.FRED.GuidaTv.data.model.Utente;
import it.FRED.GuidaTv.framework.data.DataItemImpl;
import it.FRED.GuidaTv.data.model.Programma;

/**
 *
 * @author Francesca
 */
public class ProgrammaImpl  extends DataItemImpl<Integer> implements Programma {
   private int durata;
   private int anno;
   private String descrizione;
   private String tipo;
   private Utente editore;
   
   public ProgrammaImpl(String tipo,Utente editore){
       this.editore = editore;
       this.tipo=tipo;
   }
   
   public ProgrammaImpl(int durata,int anno, String descrizione,Utente editore, String tipo){
       this.durata = durata;
       this.anno = anno;
       this.descrizione = descrizione;
       this.editore = editore;
       this.tipo=tipo;
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
    public  int getAnno(){
       return this.anno;
    }
    @Override
    public void setAnno(int a){
        this.anno = a;
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
    public Utente getEditore(){
        return this.editore;
    }
    @Override
    public void setEditore(Utente d){
        this.editore = d;
    }
    
    @Override
    public String getTipo(){
        return this.tipo;
    }
    @Override
    public void setTipo(String d){
        this.tipo = d;
    }
    
    
}
