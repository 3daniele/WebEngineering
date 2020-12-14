package it.FRED.GuidaTv.data.dao;

import it.FRED.GuidaTv.data.model.Palinsesto;
import it.FRED.GuidaTv.data.model.Canale;
import it.FRED.GuidaTv.data.model.Programma;
import it.FRED.GuidaTv.framework.data.DataException;
import java.util.List;

public interface PalinsestoDAO {
    
    Palinsesto createPalinsesto();
    
    Palinsesto getPalinsesto(int palinsesto_key) throws DataException;
    
    List<Palinsesto> getPalinsesti();
    
    List<Palinsesto> getPalinsesti(Canale canale) throws DataException;
    
    List<Palinsesto> getPalinsesto(Programma programma) throws DataException;
    
    void storePalinsesto(Palinsesto palinsesto) throws DataException;
    
}
