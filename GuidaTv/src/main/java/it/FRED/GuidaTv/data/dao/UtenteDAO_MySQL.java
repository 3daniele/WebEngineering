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

import it.FRED.GuidaTv.data.proxy.UtenteProxy;
import it.FRED.GuidaTv.data.impl.UtenteImpl;
import it.FRED.GuidaTv.data.model.Utente;

public class UtenteDAO_MySQL  extends DAO implements UtenteDAO{
    
    private PreparedStatement sUtenteByID, sUtentiByRuolo, sUtenti;
    private PreparedStatement iUtente, uUtente, dUtente;
    
    public UtenteDAO_MySQL(DataLayer d){
        super(d);
    }
    
    // aggiunto costruttore per dao
    public UtenteDAO_MySQL(){
        super();
    }
    
    @Override
    public void init() throws DataException{
        
        try{
            super.init();
            sUtenteByID = connection.prepareStatement("SELECT *FROM utente where id_utente = ?");
            sUtenti = connection.prepareStatement("SELECT *FROM utente");
            sUtentiByRuolo = connection.prepareStatement("SELECT *FROM utente WHERE ruolo = ?");
            iUtente = connection.prepareStatement("INSERT INTO utente(email, password, status, ruolo) VALUES(?,?,?,?)");
            uUtente = connection.prepareStatement("UPDATE utente SET email = ?, password = ?, status = ?, ruolo = ?  WHERE id_utente = ?");
            dUtente = connection.prepareStatement("DELETE FROM utente where id_utente = ?");
        }catch(SQLException ex){
            throw new DataException("Errore dataLayer", ex);
        }
    }
    
    @Override
    public void destroy() throws DataException{
        try{
            sUtenteByID.close();
            sUtenti.close();
            sUtentiByRuolo.close();
            iUtente.close();
            uUtente.close();
            dUtente.close();
        }catch(SQLException ex){
            throw new DataException("Errore nella chiusura", ex);
        }
        super.destroy();
    }
    
    @Override
    public UtenteProxy createUtente(){
        return new UtenteProxy(getDataLayer());
    }
    
    private UtenteProxy createUtente(ResultSet rs)throws DataException{
        UtenteProxy e = createUtente();
        
        try{
            e.setKey(rs.getInt("id_utente"));
            e.setEmail(rs.getString("email"));
            e.setPassword(rs.getString("password"));
            e.setRuolo(rs.getString("ruolo"));
            e.setStatus(rs.getString("status"));
        }catch(SQLException ex){
            throw new DataException("Errore nella creazione", ex);
        }
        
        return e;
    }
    
    @Override
    public Utente getUtente(int utente_key)throws DataException{
        Utente g = null;
        
        if(dataLayer.getCache().has(Utente.class, utente_key)){
            g = dataLayer.getCache().get(Utente.class, utente_key);
        }else{
            try{
                sUtenteByID.setInt(1, utente_key);
                try(ResultSet rs = sUtenteByID.executeQuery()){
                    if(rs.next()){
                        g = createUtente(rs);
                        dataLayer.getCache().add(Utente.class, g);
                    }
                }
            }catch(SQLException ex){
               throw new DataException("Impossibile caricare", ex); 
            }
        }
        
        return g;
    }
    
    @Override
    public List<Utente> getUtenti()throws DataException{
        
        List<Utente> lista = new ArrayList();
        try(ResultSet rs = sUtenti.executeQuery()){
            while(rs.next()){
                String email= rs.getString("email");
                String password= rs.getString("password");
                String status= rs.getString("status");
                String ruolo= rs.getString("ruolo");
                //String email, String password, String status, String ruolo
                Utente u = new UtenteImpl(email,password,status,ruolo);
                
                lista.add(u);
            }
        }catch(SQLException ex){
            throw new DataException("Impossibile caricare", ex);
        }
        
        return lista;
        
    }
    
    @Override
    public List<Utente> getUtenti(String ruolo)throws DataException{
        
        List<Utente> lista = new ArrayList();
        try{
            sUtentiByRuolo.setString(1, ruolo);
        }catch(SQLException ex){
            System.out.println("Problemi con la query");
        }
        
        try(ResultSet rs = sUtentiByRuolo.executeQuery()){
            while(rs.next()){
                String email= rs.getString("email");
                String password= rs.getString("password");
                String status= rs.getString("status");
                //String email, String password, String status, String ruolo
                Utente u = new UtenteImpl(email,password,status,ruolo);
                
                lista.add(u);
            }
        }catch(SQLException ex){
            throw new DataException("Impossibile caricare", ex);
        }
        
        return lista;
        
    }
    
    @Override
    public void storeUtente(Utente utente) throws DataException{
        
        try{
            if(utente.getKey() != null && utente.getKey() > 0){
                if(utente instanceof DataItemProxy && !((DataItemProxy) utente).isModified()){
                    return;
                }
                
                //update
                //uUtente = connection.prepareStatement("UPDATE utente SET email = ?, password = ?, status = ?, ruolo = ?  WHERE id_utente = ?");
                uUtente.setInt(5, utente.getKey());
                uUtente.setString(1, utente.getEmail());
                uUtente.setString(2, utente.getPassword());
                uUtente.setString(3, utente.getStatus());
                uUtente.setString(4, utente.getRuolo());
                if(uUtente.executeUpdate() == 0){
                    throw new DataException("Errore nell'update");
                }
            }else{
                //insert
                iUtente.setString(1, utente.getEmail());
                iUtente.setString(2, utente.getPassword());
                iUtente.setString(3, utente.getStatus());
                iUtente.setString(4, utente.getRuolo());
                
                if(iUtente.executeUpdate() == 1){
                    try(ResultSet keys = iUtente.getGeneratedKeys()){
                        if(keys.next()){
                            int key = keys.getInt(1);
                            utente.setKey(key);
                            dataLayer.getCache().add(Utente.class, utente);
                        }
                        
                    }
                }
            }
            
            if(utente instanceof DataItemProxy)
                ((DataItemProxy) utente).setModified(false);
            
        }catch(SQLException ex){
            throw new DataException("Impossibile eseguire la store", ex);
        }
    }
    
}
