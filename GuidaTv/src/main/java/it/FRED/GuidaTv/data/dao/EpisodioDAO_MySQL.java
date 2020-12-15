package it.FRED.GuidaTv.data.dao;

import it.FRED.GuidaTv.framework.data.DAO;
import it.FRED.GuidaTv.framework.data.DataLayer;
import it.FRED.GuidaTv.framework.data.DataItemProxy;
import it.FRED.GuidaTv.framework.data.DataException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import it.FRED.GuidaTv.data.proxy.EpisodioProxy;
import it.FRED.GuidaTv.data.impl.EpisodioImpl;
import it.FRED.GuidaTv.data.model.Episodio;
import it.FRED.GuidaTv.data.model.Stagione;

public class EpisodioDAO_MySQL extends DAO implements EpisodioDAO{
    
    private PreparedStatement sEpisodioByID, sEpisodi, sEpisodiByStagione;
    private PreparedStatement iEpisodio, uEpisodio, dEpisodio;
    
    public EpisodioDAO_MySQL(DataLayer d){
        super(d);
    }
    
    @Override
    public void init() throws DataException{
        
        try{
            super.init();
            sEpisodi = connection.prepareStatement("SELECT *FROM Episodio");
            sEpisodioByID = connection.prepareStatement("SELECT *FROM Episodio WHERE id_episodio = ?");
            iEpisodio = connection.prepareStatement("INSERT INTO Episodio(numero, descrizione, stagione, durata) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            uEpisodio = connection.prepareStatement("UPDATE Episodio SET numero = ?, descrizione = ?, stagione = ?, durata = ? WHERE id_episodio = ?");
            dEpisodio = connection.prepareStatement("DELETE FROM Episodio WHERE id_episodio = ?");
            sEpisodiByStagione = connection.prepareStatement("SELECT *FROM episodio WHERE stagione = ?");
        }catch(SQLException ex){
            throw new DataException("Errore dataLayer ", ex);
        }
    }
    
    @Override
    public void destroy() throws DataException{
        try{
            sEpisodi.close();
            sEpisodioByID.close();
            iEpisodio.close();
            uEpisodio.close();
            dEpisodio.close();
        }catch(SQLException ex){
            throw new DataException("Errore nella chiusura dei preparedStatment", ex);
        }
        super.destroy();
    }
    
    @Override
    public EpisodioProxy createEpisodio(){
        return new EpisodioProxy(getDataLayer());
    }
    
    private EpisodioProxy createEpisodio(ResultSet rs) throws DataException{
        
        EpisodioProxy e = createEpisodio();
        
        try{
            e.setKey(rs.getInt("id_episodio"));
            e.setDescrizione(rs.getString("descrizione"));
            e.setDurata(rs.getInt("durata"));
            e.setNumero(rs.getInt("numero"));
            //usare metodo getStagioneByID e.setStagione();
        }catch(SQLException ex){
            throw new DataException("Errore nella creazione ", ex);
        }
        
        return e;
    }
    
    @Override
    public Episodio getEpisodio(int episodio_key) throws DataException{
        Episodio e = null;
        
        if(dataLayer.getCache().has(Episodio.class, episodio_key)){
            e = dataLayer.getCache().get(Episodio.class, episodio_key);
        }else{
            try{
                sEpisodioByID.setInt(1, episodio_key);
                try(ResultSet rs = sEpisodioByID.executeQuery()){
                    if(rs.next()){
                        e = createEpisodio(rs);
                        dataLayer.getCache().add(Episodio.class, e);
                    }
                }
            }catch(SQLException ex){
                throw new DataException("Impossibile caricare", ex);
            }
        }
        
        return e;
        
    }
    
    @Override
    public List<Episodio> getEpisodi()throws DataException{
        
        List<Episodio> lista = new ArrayList();
        //numero, descrizione, stagione, durata
        try(ResultSet rs = sEpisodi.executeQuery()){
            while(rs.next()){
                int numero  = rs.getInt("numero");
                String descrizione = rs.getString("descrizione");
                int durata = rs.getInt("durata");
                int stagione = rs.getInt("stagione");
                
                /*
                StagioneDAO_MySQL pop = new StagioneDAO_MySQL();
                Stagione c = pop.getStagioneByID(stagione)
                */
                
                //lista.add();
            }
        }catch(SQLException ex){
            throw new DataException("Impossibile caricare", ex);
        }
        
        return lista;
        
    }
    
    @Override
    public void storeEpisodio(Episodio episodio) throws DataException{
        
        try{
            if(episodio.getKey() != null && episodio.getKey() > 0){
                if(episodio instanceof DataItemProxy && !((DataItemProxy) episodio).isModified()){
                    return;
                }
                
                //update
                //uEpisodio = connection.prepareStatement("UPDATE Episodio SET numero = ?, descrizione = ?, stagione = ?, durata = ?");
                uEpisodio.setInt(5, episodio.getKey());
                uEpisodio.setInt(1, episodio.getNumero());
                uEpisodio.setString(2, episodio.getDescrizione());
                uEpisodio.setInt(3, episodio.getStagione().getKey());
                uEpisodio.setInt(4, episodio.getDurata());
                
                if(uEpisodio.executeUpdate() == 0){
                    throw new DataException("Errore nell'update");
                }
            }else{
                //insert
                iEpisodio.setInt(1, episodio.getNumero());
                iEpisodio.setString(2, episodio.getDescrizione());
                iEpisodio.setInt(3, episodio.getStagione().getKey());
                iEpisodio.setInt(4, episodio.getDurata());
                
                if(iEpisodio.executeUpdate() == 1){
                    try(ResultSet keys = iEpisodio.getGeneratedKeys()){
                        if(keys.next()){
                            int key = keys.getInt(1);
                            episodio.setKey(key);
                            dataLayer.getCache().add(Episodio.class, episodio);
                        }
                        
                    }
                }
            }
            
            if(episodio instanceof DataItemProxy)
                ((DataItemProxy) episodio).setModified(false);
            
        }catch(SQLException ex){
            throw new DataException("Impossibile eseguire la store", ex);
        }
    }
    
    @Override
    public List<Episodio> getEpisodi(Stagione s) throws DataException{
        List<Episodio> lista = new ArrayList<Episodio>();
        
        //numero, descrizione, stagione, durata
        try{
            sEpisodiByStagione.setInt(1, s.getKey());
        }catch(SQLException ex){
            
        }
        
        try(ResultSet rs = sEpisodi.executeQuery()){
            while(rs.next()){
                int numero  = rs.getInt("numero");
                String descrizione = rs.getString("descrizione");
                int durata = rs.getInt("durata");
                //Stagione stagione, int numero, String descrizione, int durata
                Episodio e= new EpisodioImpl(s,numero,descrizione,durata);
                
                lista.add(e);
            }
        }catch(SQLException ex){
            throw new DataException("Impossibile caricare", ex);
        }
        
        return lista;
    }
    
    
    
}
