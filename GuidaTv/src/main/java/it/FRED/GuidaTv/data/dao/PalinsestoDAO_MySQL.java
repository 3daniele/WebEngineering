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
import it.FRED.GuidaTv.data.model.Canale;
import it.FRED.GuidaTv.data.dao.CanaleDAO_MySQL;
import it.FRED.GuidaTv.data.model.Programma;
import it.FRED.GuidaTv.data.dao.ProgrammaDAO_MySQL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class PalinsestoDAO_MySQL extends DAO implements PalinsestoDao{
    
    private PreparedStatement sPalinsestoByID, sPalinsesti, sPalinsestoByCanale, sPalinsestoByProgramma, sPalinsestoByDate, sPalinsestoByDateTime;
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
            sPalinsestoByDate = connection.prepareStatement("SELECT *FROM palinsesto WHERE data = ?");
            sPalinsestoByDateTime = connection.prepareStatement("SELECT *FROM palinsesto WHERE data = ? AND ora = ?");
            iPalinsesto = connection.prepareStatement("INSERT INTO palinsesto(canale, data, ora, programma) VALUES(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            uPalinsesto = connection.prepareStatement("UPDATE palinsesto SET canale = ?, data = ?, ora = ?, programma = ? WHERE id_palinsesto = ?");
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
            sPalinsestoByDate.close();
            sPalinsestoByDateTime.close();
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
        PalinsestoProxy p = createPalinsesto();
        
        try{
            p.setKey(rs.getInt("id_palinsesto"));
            p.setOra(rs.getObject("ora", LocalTime.class));
            p.setData(rs.getObject("data", LocalDate.class));

            int id_canale = rs.getInt("canale");
            int id_programma = rs.getInt("programma");
            CanaleDAO_MySQL dCanale = new CanaleDAO_MySQL();
            ProgrammaDAO_MySQL dProgramma = new ProgrammaDAO_MySQL();
            Canale canale = dCanale.getCanale(id_canale);
            Programma programma = dProgramma.getProgramma(id_programma);
            
            p.setCanale(canale);
            p.setProgramma(programma);
        }catch(SQLException ex){
            throw new DataException("Errore nella creazione", ex);
        }
        
        return p;
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

    @Override
    public List<Palinsesto> getPalinsesti() throws DataException{
        
        List<Palinsesto> lista = new ArrayList();

        try (ResultSet rs = sPalinsesti.executeQuery()){
            while(rs.next()){
                int id_canale = rs.getInt("canale");
                int id_programma = rs.getInt("programma");
                CanaleDAO_MySQL dCanale = new CanaleDAO_MySQL();
                ProgrammaDAO_MySQL dProgramma = new ProgrammaDAO_MySQL();
                
                Canale canale = dCanale.getCanale(id_canale);
                Programma programma = dProgramma.getProgramma(id_programma);
                LocalDate data = rs.getObject("data", LocalDate.class);
                LocalTime ora = rs.getObject("ora", LocalTime.class);

                Palinsesto p = new PalinsestoImpl(canale, data, programma, ora);
                
                lista.add(p);
            }
        }catch(SQLException ex){
            throw new DataException("Impossibile caricare", ex);
        }
        return lista;
    }

    @Override
    public List<Palinsesto> getPalinsesti(Canale c) throws DataException{
        
        List<Palinsesto> lista = new ArrayList();

        try {
            sPalinsestoByCanale.setInt(1, c.getKey());
        } catch(SQLException ex) {
            
        }
        try (ResultSet rs = sPalinsestoByCanale.executeQuery()){
            while(rs.next()){
                int id_programma = rs.getInt("programma");
                ProgrammaDAO_MySQL dProgramma = new ProgrammaDAO_MySQL();
                
                Programma programma = dProgramma.getProgramma(id_programma);
                LocalDate data = rs.getObject("data", LocalDate.class);
                LocalTime ora = rs.getObject("ora", LocalTime.class);

                Palinsesto p = new PalinsestoImpl(c, data, programma, ora);
                
                lista.add(p);
            }
        } catch(SQLException ex) {
            throw new DataException("Impossibile caricare", ex);
        }
        return lista;
    }

    @Override
    public List<Palinsesto> getPalinsesti(LocalDate data) throws DataException{
        
        List<Palinsesto> lista = new ArrayList();

        try {
            sPalinsestoByDate.setObject(1, data);
        } catch(SQLException ex) {
            
        }
        try (ResultSet rs = sPalinsestoByDate.executeQuery()){
            while(rs.next()){
                int id_canale = rs.getInt("canale");
                int id_programma = rs.getInt("programma");
                ProgrammaDAO_MySQL dProgramma = new ProgrammaDAO_MySQL();
                CanaleDAO_MySQL dCanale = new CanaleDAO_MySQL();

                Canale canale = dCanale.getCanale(id_canale);
                Programma programma = dProgramma.getProgramma(id_programma);
                LocalTime ora = rs.getObject("ora", LocalTime.class);

                Palinsesto p = new PalinsestoImpl(canale, data, programma, ora);
                
                lista.add(p);
            }
        } catch(SQLException ex) {
            throw new DataException("Impossibile caricare", ex);
        }
        return lista;
    }

    @Override
    public List<Palinsesto> getPalinsesti(LocalDate data, LocalTime ora) throws DataException{
        
        List<Palinsesto> lista = new ArrayList();

        try {
            sPalinsestoByDateTime.setObject(1, data);
            sPalinsestoByDateTime.setObject(2, ora);
        } catch(SQLException ex) {
            
        }
        try (ResultSet rs = sPalinsestoByDateTime.executeQuery()){
            while(rs.next()){
                int id_canale = rs.getInt("canale");
                int id_programma = rs.getInt("programma");
                ProgrammaDAO_MySQL dProgramma = new ProgrammaDAO_MySQL();
                CanaleDAO_MySQL dCanale = new CanaleDAO_MySQL();

                Canale canale = dCanale.getCanale(id_canale);
                Programma programma = dProgramma.getProgramma(id_programma);

                Palinsesto p = new PalinsestoImpl(canale, data, programma, ora);
                
                lista.add(p);
            }
        } catch(SQLException ex) {
            throw new DataException("Impossibile caricare", ex);
        }
        return lista;
    }
    

    @Override
    public List<Palinsesto> getPalinsesti(Programma pr) throws DataException{
        
        List<Palinsesto> lista = new ArrayList();

        try {
            sPalinsestoByProgramma.setInt(1, pr.getKey());
        } catch(SQLException ex) {
            
        }
        try (ResultSet rs = sPalinsestoByCanale.executeQuery()){
            while(rs.next()){
                int id_canale = rs.getInt("canale");
                CanaleDAO_MySQL dCanale = new CanaleDAO_MySQL();
                
                Canale canale = dCanale.getCanale(id_canale);
                LocalDate data = rs.getObject("data", LocalDate.class);
                LocalTime ora = rs.getObject("ora", LocalTime.class);

                Palinsesto p = new PalinsestoImpl(c, data, pr, ora);
                
                lista.add(p);
            }
        } catch(SQLException ex) {
            throw new DataException("Impossibile caricare", ex);
        }
        return lista;
    }
    

    @Override
    public void storePalinsesto(Palinsesto palinsesto) throws DataException{
        
        try{
            if(palinsesto.getKey() != null && palinsesto.getKey() > 0){
                if(palinsesto instanceof DataItemProxy && !((DataItemProxy) palinsesto).isModified()){
                    return;
                }
                //update
                uPalinsesto.setInt(5, palinsesto.getKey());
                uPalinsesto.setInt(1, palinsesto.getCanale().getKey());
                uPalinsesto.setObject(2, palinsesto.getData());
                uPalinsesto.setObject(3, palinsesto.getOra());
                uPalinsesto.setInt(4, palinsesto.getProgramma().getKey());
                
                if(uPalinsesto.executeUpdate() == 0){
                    throw new DataException("Errore nell'update");
                }
            }else{
                //insert
                iPalinsesto.setInt(1, palinsesto.getCanale().getKey());
                iPalinsesto.setObject(2, palinsesto.getData());
                iPalinsesto.setObject(3, palinsesto.getOra());
                iPalinsesto.setInt(4, palinsesto.getProgramma().getKey());

                if(iPalinsesto.executeUpdate() == 1){
                    try(ResultSet keys = iPalinsesto.getGeneratedKeys()){
                        if(keys.next()){
                            int key = keys.getInt(1);
                            palinsesto.setKey(key);
                            dataLayer.getCache().add(Palinsesto.class, palinsesto);
                        }
                        
                    }
                }
            }
            
            if(palinsesto instanceof DataItemProxy)
                ((DataItemProxy) palinsesto).setModified(false);
            
        }catch(SQLException ex){
            throw new DataException("Impossibile eseguire la store", ex);
        }
    }
}
