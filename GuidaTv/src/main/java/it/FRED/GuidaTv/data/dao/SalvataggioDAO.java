package it.FRED.GuidaTv.data.dao;

import it.FRED.GuidaTv.data.model.Salvataggio;
import it.FRED.GuidaTv.data.model.Canale;
import it.FRED.GuidaTv.data.model.Genere;
import it.FRED.GuidaTv.data.model.Programma;
import it.FRED.GuidaTv.data.model.Utente;
import it.FRED.GuidaTv.framework.data.DataException;
import java.util.List;

public interface SalvataggioDAO {
    
    Salvataggio createSalvataggio();
    
    Salvataggio getSalvataggio(int salvataggio_key) throws DataException;
    
    List<Salvataggio> getSalvataggi() throws DataException;
    
    //List<Salvataggio> getSalvataggi(Canale canale) throws DataException;
    
    //List<Salvataggio> getSalvataggi(Genere genere) throws DataException;
    
    //List<Salvataggio> getSalvataggi(Programma programma)throws DataException;
    
    List<Salvataggio> getSalvataggi(Utente utente) throws DataException;
    
    void storeSalvataggio(Salvataggio salvataggio) throws DataException;

    boolean deleteSalvataggio(Salvataggio salvataggio) throws DataException;
    
}
