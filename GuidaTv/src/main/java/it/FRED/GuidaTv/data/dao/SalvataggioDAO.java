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
    
    List<Salvataggio> getSalvataggi();
    
    List<Salvataggio> getSalvataggi(Canale canale);
    
    List<Salvataggio> getSalvataggi(Genere genere);
    
    List<Salvataggio> getSalvataggi(Programma programma);
    
    List<Salvataggio> getSalvataggi(Utente utente);
    
    void storeSalvataggio(Salvataggio salvataggio) throws DataException;
    
}
