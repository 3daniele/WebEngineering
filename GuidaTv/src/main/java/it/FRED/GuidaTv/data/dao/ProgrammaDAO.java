package it.FRED.GuidaTv.data.dao;

import it.FRED.GuidaTv.data.model.Programma;
import it.FRED.GuidaTv.data.model.Utente;
import it.FRED.GuidaTv.framework.data.DataException;
import java.util.List;

public interface ProgrammaDAO {

    Programma createProgramma();
    
    Programma getProgramma(int programma_key) throws DataException;
    
    List<Programma> getProgrammi();
    
    List<Programma> getProgrammi(Utente editore) throws DataException;
    
    void storeProgramma(Programma programma) throws DataException;
    
}
