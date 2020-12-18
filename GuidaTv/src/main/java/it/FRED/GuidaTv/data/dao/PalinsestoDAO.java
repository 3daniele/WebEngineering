package it.FRED.GuidaTv.data.dao;

import it.FRED.GuidaTv.data.model.Palinsesto;
import it.FRED.GuidaTv.data.model.Canale;
import it.FRED.GuidaTv.data.model.Programma;
import it.FRED.GuidaTv.framework.data.DataException;
import java.util.List;

public interface PalinsestoDAO {
    
    Palinsesto createPalinsesto();
    
    Palinsesto getPalinsesto(int palinsesto_key) throws DataException;
    
    List<Palinsesto> getPalinsesti() throws DataException;
    
    List<Palinsesto> getPalinsesti(Canale canale) throws DataException;

    List<Palinsesto> getPalinsestiByDate(LocalDate data) throws DataException;

    List<Palinsesto> getPalinsestiByTime(LocalDate data, LocalTime ora) throws DataException;

    List<Palinsesto> getPalinsesti(Programma programma) throws DataException;
    
    void storePalinsesto(Palinsesto palinsesto) throws DataException;
    
}
