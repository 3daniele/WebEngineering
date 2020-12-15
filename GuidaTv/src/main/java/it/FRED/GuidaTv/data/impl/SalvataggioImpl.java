package it.FRED.GuidaTv.data.impl;

import it.FRED.GuidaTv.data.model.Salvataggio;
import it.FRED.GuidaTv.data.model.Canale;
import it.FRED.GuidaTv.data.model.Genere;
import it.FRED.GuidaTv.data.model.Programma;
import it.FRED.GuidaTv.data.model.Utente;
import it.FRED.GuidaTv.framework.data.DataItemImpl;

public class SalvataggioImpl extends DataItemImpl<Integer> implements Salvataggio {
    
    private Canale canale;
    private Genere genere;
    private Programma programma;
    private Utente utente;
    
    public SalvataggioImpl(Canale canale, Genere genere, Programma programma, Utente utente) {
        this.canale = canale;
        this.genere = genere;
        this.programma = programma;
        this.utente = utente;
    }
    
    public SalvataggioImpl(Canale canale, Utente utente) {
        this.canale = canale;
        this.utente = utente;
    }
    
    public SalvataggioImpl(Genere genere, Utente utente) {
        this.genere = genere;
        this.utente = utente;
    }
    
    public SalvataggioImpl(Programma programma, Utente utente) {
        this.programma = programma;
        this.utente = utente;
    }
    
    
    @Override
    public Canale getCanale() {
       return this.canale;
    }
    @Override
    public void setCanale(Canale c) {
       this.canale = c; 
    }
    
    
    @Override
    public Genere getGenere() {
       return this.genere;
    }
    @Override
    public void setGenere(Genere g) {
       this.genere = g; 
    }
    
    
    @Override
    public Programma getProgramma() {
       return this.programma;
    }
    @Override
    public void setProgramma(Programma p) {
       this.programma = p; 
    }
    
    
    @Override
    public Utente getUtente() {
       return this.utente;
    }
    @Override
    public void setUtente(Utente u) {
       this.utente = u; 
    }
}
