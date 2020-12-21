package it.FRED.GuidaTv.data.dao;

import it.FRED.GuidaTv.data.model.Genere;
import it.FRED.GuidaTv.framework.data.DataException;
import java.util.List;

public interface GenereDAO {
    
    Genere createGenere();
    
    Genere getGenere(int genere_key) throws DataException;
    
    Genere getGenere(String nome) throws DataException;
    
    List<Genere> getGeneri() throws DataException;
    
    void storeGenere(Genere genere) throws DataException;
    
}