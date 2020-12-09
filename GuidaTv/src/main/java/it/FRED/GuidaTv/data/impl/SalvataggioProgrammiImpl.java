/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.FRED.GuidaTv.data.impl;

import it.FRED.GuidaTv.data.model.Film;
import it.FRED.GuidaTv.data.model.SalvataggioProgrammi;
import it.FRED.GuidaTv.data.model.Spettacolo;
import it.FRED.GuidaTv.data.model.Utente;
import it.FRED.GuidaTv.framework.data.DataItemImpl;

/**
 *
 * @author Francesca
 */
public class SalvataggioProgrammiImpl extends DataItemImpl<Integer> implements SalvataggioProgrammi {
    private Film film;
    private Spettacolo spettacolo;
    private Utente utente;
    public SalvataggioProgrammiImpl( Film c, Spettacolo s, Utente u){
        this.film = c;
        this.spettacolo = s;
        this.utente = u;
    }
    @Override
    public Film getFilm(){
        return this.film;
    }
    @Override
    public void setFilm(Film c){
        this.film = c;  
    }
    @Override
    public Spettacolo getSpettacolo(){
        return this.spettacolo;
    }
    @Override
    public void setSpettacolo(Spettacolo s){
        this.spettacolo=s;
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
