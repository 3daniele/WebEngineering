/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.FRED.GuidaTv.data.model;

import it.FRED.GuidaTv.framework.data.DataItem;

/**
 *
 * @author DanieleDD
 */
public interface SalvataggioGeneri extends DataItem<Integer>{
    Genere getGenere();
    void setGenere(Genere g);
    
    Utente getUtente();
    void setUtente(Utente u);
    
}