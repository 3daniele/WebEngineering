package it.FRED.GuidaTv.data.dao;

import it.FRED.GuidaTv.data.model.Stagione;
import it.FRED.GuidaTv.data.model.Programma;
import it.FRED.GuidaTv.framework.data.DataException;
import java.util.List;

public interface StagioneDAO {

    Stagione createStagione();
    
    Stagione getStagione(int stagione_key) throws DataException;
    
    List<Stagione> getStagioni();
    
    List<Stagione> getStagioni(Programma serietv) throws DataException;
    
    void storeStagione(Stagione stagione) throws DataException;
    
}
