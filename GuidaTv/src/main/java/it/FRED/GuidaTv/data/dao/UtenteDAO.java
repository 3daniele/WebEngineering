package it.FRED.GuidaTv.data.dao;

import it.FRED.GuidaTv.data.model.Utente;
import it.FRED.GuidaTv.framework.data.DataException;
import java.util.List;

public interface UtenteDAO {

    Utente createUtente();
    
    Utente getUtente(int utente_key) throws DataException;
    
    List<Utente> getUtenti();
    
    void storeUtente(Utente utente) throws DataException;
    
}
