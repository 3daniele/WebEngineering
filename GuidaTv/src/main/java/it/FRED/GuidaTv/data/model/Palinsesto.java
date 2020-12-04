/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.FRED.GuidaTv.data.model;
import it.FRED.GuidaTv.framework.data.DataItem;
import java.time.LocalDateTime;
/**
 *
 * @author DanieleDD
 */
public interface Palinsesto extends DataItem<Integer>{
    Canale getCanale();
    void setCanale(Canale c);
    
    //data e ora
    LocalDateTime getDataOra();
    void setDataOra(LocalDateTime l);
    
    Film getFilm();
    void setFilm(Film f);
    
    Spettacolo getSpettacolo();
    void setSpettacolo(Spettacolo s);
}
