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
        
import it.FRED.GuidaTv.data.proxy.StagioneProxy;
import it.FRED.GuidaTv.data.impl.StagioneImpl;
import it.FRED.GuidaTv.data.model.Programma;
import it.FRED.GuidaTv.data.model.Stagione;




public class StagioneDAO_MySQL extends DAO implements StagioneDAO {
     
    private PreparedStatement sStagioneByID, sStagione, sStagioneBySerietv;
    private PreparedStatement iStagione, uStagione, dStagione;
    
    public StagioneDAO_MySQL(DataLayer d){
        super(d);
    }
    
    @Override
    public void init() throws DataException{
        
        try{
            super.init();
            sStagione = connection.prepareStatement("SELECT *FROM Stagione");
            sStagioneByID = connection.prepareStatement("SELECT *FROM Stagione WHERE id_stagione = ?");
            iStagione = connection.prepareStatement("INSERT INTO Stagione(serietv, numero, descrizione, immagine) VALUES (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            uStagione = connection.prepareStatement("UPDATE Stagione SET serietv = ?, numero = ?, descrizione = ?, immagine = ? WHERE id_stagione = ?");
            dStagione = connection.prepareStatement("DELETE FROM Stagione WHERE id_stagione = ?");
            sStagioneBySerietv = connection.prepareStatement("SELECT *FROM stagione WHERE serietv = ?");
        }catch(SQLException ex){
            throw new DataException("Errore dataLayer ", ex);
        }
    }
    
    
    @Override
    public void destroy() throws DataException{
        try{
            sStagione.close();
            sStagioneByID.close();
            iStagione.close();
            uStagione.close();
            dStagione.close();
            sStagioneBySerietv.close();
        }catch(SQLException ex){
            throw new DataException("Errore nella chiusura dei preparedStatment", ex);
        }
        super.destroy();
    }
   
    
     public StagioneProxy createStagione(){
        return new StagioneProxy(getDataLayer());
    }
    
    private StagioneProxy createStagione(ResultSet rs) throws DataException{
        
        StagioneProxy e = createStagione();
        
       //"INSERT INTO Stagione(serietv, numero, descrizione, immagine) VALUES (?,?,?,?)"
       
        try{
            e.setKey(rs.getInt("id_stagione"));
            int id_programma = rs.getInt("serietv");
            ProgrammaDAO_MySQL programma = new ProgrammaDAO_MySQL();
            Programma serietv =programma.getProgramma(id_programma);
            e.setProgramma(serietv);
            e.setNumero(rs.getInt("numero"));
            e.setDescrizione(rs.getString("descrizione"));
            e.setImmagine(rs.getString("immagine"));
            
            
        }catch(SQLException ex){
            throw new DataException("Errore nella creazione ", ex);
        }
        
        return e;
    }
    
   @Override
    public Stagione getStagione(int stagione_key) throws DataException{
        Stagione e = null;
        //"SELECT *FROM Stagione WHERE id_stagione = ?"
        
        if(dataLayer.getCache().has(Programma.class, stagione_key)){
            e = dataLayer.getCache().get(Stagione.class, stagione_key);
        }else{
            try{
                sStagioneByID.setInt(1, stagione_key);
                try(ResultSet rs = sStagioneByID.executeQuery()){
                    if(rs.next()){
                        e = createStagione(rs);
                        dataLayer.getCache().add(Stagione.class, e);
                    }
                }
            }catch(SQLException ex){
                throw new DataException("Impossibile caricare", ex);
            }
        }
        
        return e;
        
    }
    
    @Override
    public List<Stagione> getStagioni()throws DataException{
        
        List<Stagione> lista = new ArrayList();
        //"SELECT *FROM Stagione"
        try(ResultSet rs = sStagione.executeQuery()){
            while(rs.next()){
                int numero  = rs.getInt("numero");
                String descrizione = rs.getString("descrizione");
                int id_programma = rs.getInt("programma");
                ProgrammaDAO_MySQL programma= new ProgrammaDAO_MySQL();
                Programma serietv = programma.getProgramma(id_programma);
                String immagine = rs.getString("immagine");
                
                
                Stagione stagione= new StagioneImpl(serietv, numero, descrizione, immagine);
                lista.add(stagione);
            }
        }catch(SQLException ex){
            throw new DataException("Impossibile caricare", ex);
        }
        
        return lista;
        
    }
   
    @Override
    public List<Stagione> getStagioni(Programma serietv)throws DataException{
        //SELECT *FROM stagione WHERE serietv = ?"
        List<Stagione> lista = new ArrayList();
        try{
            sStagioneBySerietv.setInt(1, serietv.getKey());
        }catch(SQLException ex){
            System.out.println("Problemi con la query");
        }
        try(ResultSet rs = sStagioneBySerietv.executeQuery()){
            while(rs.next()){
               int numero  = rs.getInt("numero");
                String descrizione = rs.getString("descrizione");
                int id_programma = rs.getInt("programma");
                String immagine = rs.getString("immagine");
                
                
                Stagione stagione= new StagioneImpl(serietv, numero, descrizione, immagine);
                lista.add(stagione);
            }
        }catch(SQLException ex){
            throw new DataException("Impossibile caricare", ex);
        }
        
        return lista;
        
    }
    
    @Override
    public void storeStagione(Stagione stagione) throws DataException{
        //"UPDATE Stagione SET serietv = ?, numero = ?, descrizione = ?, immagine = ? WHERE id_stagione = ?"
        try{
            if(stagione.getKey() != null && stagione.getKey() > 0){
                if(stagione instanceof DataItemProxy && !((DataItemProxy) stagione).isModified()){
                    return;
                }
                
                
                uStagione.setInt(5, stagione.getKey());
                uStagione.setInt(1, stagione.getProgramma().getKey());
                uStagione.setInt(2, stagione.getNumero());
                uStagione.setString(3, stagione.getDescrizione());
                uStagione.setString(4, stagione.getImmagine());
               
              
                
                if(uStagione.executeUpdate() == 0){
                    throw new DataException("Errore nell'update");
                }
            }else{
                //insert
               
                uStagione.setInt(5, stagione.getKey());
                uStagione.setInt(1, stagione.getProgramma().getKey());
                uStagione.setInt(2, stagione.getNumero());
                uStagione.setString(3, stagione.getDescrizione());
                uStagione.setString(4, stagione.getImmagine());
                
                if(iStagione.executeUpdate() == 1){
                    try(ResultSet keys = iStagione.getGeneratedKeys()){
                        if(keys.next()){
                            int key = keys.getInt(1);
                            stagione.setKey(key);
                            dataLayer.getCache().add(Stagione.class, stagione);
                        }
                        
                    }
                }
            }
            
            if(stagione instanceof DataItemProxy)
                ((DataItemProxy) stagione).setModified(false);
            
        }catch(SQLException ex){
            throw new DataException("Impossibile eseguire la store", ex);
        }
    }
    
    
    
}
