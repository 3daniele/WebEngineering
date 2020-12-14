package it.FRED.GuidaTv.data.dao;

import it.FRED.GuidaTv.data.model.Canale;
import it.FRED.GuidaTv.framework.data.DataException;
import java.util.List;

public interface CanaleDAO {

    Canale createCanale();
    
    Canale getCanale(int canale_key) throws DataException;
    
    List<Canale> getCanali();
    
    void storeCanale(Canale canale) throws DataException;
    
}
