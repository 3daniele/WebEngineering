/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.FRED.GuidaTv.data.impl;

import it.FRED.GuidaTv.data.model.Film;
import it.FRED.GuidaTv.data.model.Genere;
import it.FRED.GuidaTv.data.model.Lgf;
import it.FRED.GuidaTv.framework.data.DataItemImpl;

/**
 *
 * @author Francesca
 */
public class LgfImpl extends DataItemImpl<Integer> implements   Lgf{
   private Film film;
   private Genere genere;   
   
   
   public LgfImpl (Film f, Genere g){
       this.film = f;
       this.genere =g;
   }
   @Override
   public Film getFilm(){
       return this.film;
   }
   @Override
    public void setFilm(Film f){
        this.film = f;
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
