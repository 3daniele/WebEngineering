package it.FRED.GuidaTv.data.dao;

import it.FRED.GuidaTv.data.model.Episodio;
import it.FRED.GuidaTv.data.model.Stagione;
import it.FRED.GuidaTv.framework.data.DataException;
import java.util.List;

public interface EpisodioDAO {
    
    Episodio createEpisodio();
    
    Episodio getEpisodio(int episodio_key) throws DataException;
    
    List<Episodio> getEpisodi() throws DataException;
    
    List<Episodio> getEpisodi(Stagione stagione) throws DataException;
    
    void storeEpisodio(Episodio episodio) throws DataException;
    
}
