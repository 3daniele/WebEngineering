package it.FRED.GuidaTv.data.dao;

import it.FRED.GuidaTv.data.model.Palinsesto;
import it.FRED.GuidaTv.data.model.Canale;
import it.FRED.GuidaTv.data.model.Programma;
import it.FRED.GuidaTv.framework.data.DataException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public interface PalinsestoDAO {
    
    Palinsesto createPalinsesto();
    
    Palinsesto getPalinsesto(int palinsesto_key) throws DataException;
    
    List<Palinsesto> getPalinsesti() throws DataException;
    
    List<Palinsesto> getPalinsesti(Canale canale) throws DataException;

    List<Palinsesto> getPalinsesti(LocalDate data) throws DataException;

    List<Palinsesto> getPalinsesti(LocalDate data, LocalTime ora) throws DataException;

    List<Palinsesto> getPalinsesti(Programma programma) throws DataException;
    
    void storePalinsesto(Palinsesto palinsesto) throws DataException;
    
}
