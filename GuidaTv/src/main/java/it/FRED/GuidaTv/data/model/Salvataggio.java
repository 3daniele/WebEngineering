package it.FRED.GuidaTv.data.model;

import it.FRED.GuidaTv.framework.data.DataItem;


public interface Salvataggio extends DataItem<Integer>{
    Programma getProgramma();
    void setProgramma(Programma s);
    
    Genere getGenere();
    void setGenere(Genere g);
    
    Canale getCanale();
    void setCanale(Canale c);
    
    Utente getUtente();
    void setUtente(Utente u);
}