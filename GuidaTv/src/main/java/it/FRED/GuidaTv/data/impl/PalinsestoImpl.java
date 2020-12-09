/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.FRED.GuidaTv.data.impl;

import it.FRED.GuidaTv.data.model.Canale;
import it.FRED.GuidaTv.data.model.Film;
import it.FRED.GuidaTv.data.model.Palinsesto;
import it.FRED.GuidaTv.data.model.Spettacolo;
import it.FRED.GuidaTv.framework.data.DataItemImpl;
import java.time.LocalDateTime;



/**
 *
 * @author Francesca
 */
public class PalinsestoImpl extends DataItemImpl<Integer> implements Palinsesto{
    private Canale canale;
    private LocalDateTime DataOra;
    private Spettacolo spettacolo;
    private Film film;
    
    
    public PalinsestoImpl (Canale canale, LocalDateTime DataOra, Spettacolo s, Film film){
        this.canale = canale;
        this.DataOra = DataOra;
        this.spettacolo = s;
        this.film =film;
    }
    @Override
    public Canale getCanale(){
         return this.canale;
    }
    @Override
    public void setCanale(Canale c){
         this.canale = c;
    }
    
    @Override
   public LocalDateTime getDataOra(){
         return this.DataOra;
   }
   public  void setDataOra(LocalDateTime l){
         this.DataOra = l;
   }
    @Override
   public Film getFilm(){
         return this.film;
   }
   @Override
   public  void setFilm(Film f){
          this.film = f;
   }
    @Override
    public  Spettacolo getSpettacolo(){
         return this.spettacolo;
    }
    @Override
   public  void setSpettacolo(Spettacolo s){
         this.spettacolo = s;
    }
}
