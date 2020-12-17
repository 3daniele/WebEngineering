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



import it.FRED.GuidaTv.data.proxy.PalinsestoProxy;
import it.FRED.GuidaTv.data.impl.PalinsestoImpl;
import it.FRED.GuidaTv.data.model.Palinsesto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
public class PalinsestoDAO_MySQL extends DAO implements PalinsestoDao{
    
    private PreparedStatement sPalinsestoByID, sPalinsesti, sPalinsestoByCanale,sPalinsestoByProgramma;
    private PreparedStatement iPalinsesto, uPalinsesto, dPalinsesto;
    
    public PalinsestoDAO_MySQL(DataLayer d){
        super(d);
    }
    
    
    
    @Override
    public void init() throws DataException{
        
        try{
            super.init();
            sPalinsestoByID = connection.prepareStatement("SELECT *FROM palinsesto where id_palinsesto = ?");
            sPalinsesti = connection.prepareStatement("SELECT *FROM palinsesto");
            sPalinsestoByCanale = connection.prepareStatement("SELECT *FROM palinsesto WHERE canale = ?");
            sPalinsestoByProgramma = connection.prepareStatement("SELECT *FROM palinsesto WHERE programma = ?");
            iPalinsesto = connection.prepareStatement("INSERT INTO palinsesto(canale, data, ora, programma) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            uPalinsesto = connection.prepareStatement("UPDATE palinsesto SET canale = ?, data = ?, ora = ? WHERE id_palinsesto = ?");
            dPalinsesto = connection.prepareStatement("DELETE FROM palinsesto where id_palinsesto = ?");
        }catch(SQLException ex){
            throw new DataException("Errore dataLayer", ex);
        }
    }
    
    
    
    @Override
    public void destroy() throws DataException{
        try{
            sPalinsestoByID.close();
            sPalinsesti.close();
            sPalinsestoByCanale.close();
            sPalinsestoByProgramma.close();
            iPalinsesto.close();
            uPalinsesto.close();
            dPalinsesto.close();
        }catch(SQLException ex){
            throw new DataException("Errore nella chiusura", ex);
        }
        super.destroy();
    }
  
    
    
    
    
    @Override
    public PalinsestoProxy createPalinsesto(){
        return new PalinsestoProxy(getDataLayer());
    }
    
    
    
    
    private PalinsestoProxy createPalinsesto(ResultSet rs)throws DataException{
        PalinsestoProxy e = createPalinsesto();
        
        try{
            e.setKey(rs.getInt("id_palinsesto"));
             // e.setCanale(rs.getInt("canale"));
            
            e.setOra(rs.getObject("ora", LocalTime.class));
            e.setData(rs.getObject("data", LocalTime.class));
            //e.setProgramma(rs.getInt("programma"));
            
     
        }catch(SQLException ex){
            throw new DataException("Errore nella creazione", ex);
        }
        
        return e;
    }
    
    
    
    
    public Palinsesto getEpisodio(int palinsesto_key) throws DataException{
        Palinsesto e = null;
        
        if(dataLayer.getCache().has(Palinsesto.class, palinsesto_key)){
            e = dataLayer.getCache().get(Palinsesto.class, palinsesto_key);
        }else{
            try{
                sPalinsestoByID.setInt(1, palinsesto_key);
                try(ResultSet rs = sPalinsestoByID.executeQuery()){
                    if(rs.next()){
                        e = createPalinsesto(rs);
                        dataLayer.getCache().add(Palinsesto.class, e);
                    }
                }
            }catch(SQLException ex){
                throw new DataException("Impossibile caricare", ex);
            }
        }
        
        return e;
        
    }
    
    
    
    
    
    
    
    
    
}
