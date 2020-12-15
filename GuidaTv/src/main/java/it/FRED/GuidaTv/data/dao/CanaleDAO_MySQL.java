package it.FRED.GuidaTv.data.dao;

import it.FRED.GuidaTv.data.proxy.CanaleProxy;
import it.FRED.GuidaTv.data.impl.CanaleImpl;
import it.FRED.GuidaTv.data.model.Canale;
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

public class CanaleDAO_MySQL extends DAO implements CanaleDAO {

    private PreparedStatement sCanaleByID, sCanali;
    private PreparedStatement iCanale, uCanale, dCanale;
    
    public CanaleDAO_MySQL(DataLayer d) {
        super(d);
    }
    
    @Override
    public void init() throws DataException {
        
        try {
            super.init();
            
            sCanali = connection.prepareStatement("SELECT * FROM Canale");
            sCanaleByID = connection.prepareStatement("SELECT * FROM Canale WHERE numero = ?");
            iCanale = connection.prepareStatement("INSERT INTO Canale (nome, gruppo) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            uCanale = connection.prepareStatement("UPDATE Canale SET nome = ?, gruppo = ? WHERE numero = ?");
            dCanale = connection.prepareStatement("DELETE FROM Canale WHERE numero = ?");
        } catch (SQLException ex) {
            throw new DataException("Error initializing data layer", ex);
        }
    }
    
    @Override
    public void destroy() throws DataException {
        
        // chiusura dei preparedStatements
        try {
            sCanaleByID.close();
            sCanali.close();
            iCanale.close();
            uCanale.close();
            dCanale.close();
        } catch (SQLException ex) {
            throw new DataException("Error closing preparedStatements", ex);
        }
        super.destroy();
    }
    
    @Override
    public CanaleProxy createCanale() {
        return new CanaleProxy(getDataLayer());
    }
    private CanaleProxy createCanale(ResultSet rs) throws DataException {
        
        CanaleProxy c = createCanale();
        
        try {
            c.setKey(rs.getInt("numero"));
            c.setNome(rs.getString("nome"));
            c.setGruppo(rs.getString("gruppo"));
        } catch (SQLException ex) {
            throw new DataException("Unable to create the object \"Canale\"", ex);
        }
        
        return c;
    }
    
    
    @Override
    public Canale getCanale(int canale_key) throws DataException {
        
        Canale c = null;
        
        // verifica se l'oggetto è già stato caricato nella cache
        if (dataLayer.getCache().has(Canale.class, canale_key)) {
            c = dataLayer.getCache().get(Canale.class, canale_key);
        } else {
            // altrimenti viene caricato dal db e messo in cache
            try {
                sCanaleByID.setInt(1, canale_key);
                try (ResultSet rs = sCanaleByID.executeQuery()) {
                    if (rs.next()) {
                        c = createCanale(rs);
                        dataLayer.getCache().add(Canale.class, c);
                    }
                }
            } catch (SQLException ex) {
                throw new DataException("Unable to load \"Canale\"", ex);
            }
        }
        
        return c;
        
    }

    @Override
    public List<Canale> getCanali() throws DataException {
        
        List<Canale> canali = new ArrayList();
        
        try (ResultSet rs = sCanali.executeQuery()) {
            while (rs.next()) {
                String nome = rs.getString("nome");
                String gruppo = rs.getString("gruppo");
                Canale c = new CanaleImpl(nome, gruppo);
                
                canali.add(c);
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to load \"Canale\"", ex);
        }

        return canali;
    }

    @Override
    public void storeCanale(Canale canale) throws DataException {
        
        try {
            if (canale.getKey() != null && canale.getKey() > 0) {
                // non fa nulla se l'oggetto è un proxy e non ha subito modifiche
                if (canale instanceof DataItemProxy && !((DataItemProxy) canale).isModified()) {
                    return;
                }
                // update
                uCanale.setString(1, canale.getNome());
                uCanale.setString(2, canale.getGruppo());
                uCanale.setInt(3, canale.getKey());
                
                if (uCanale.executeUpdate() == 0) {
                    throw new DataException("Update did not affect any row");
                }
            } else {
                // insert
                iCanale.setString(1, canale.getNome());
                iCanale.setString(2, canale.getGruppo());
                
                if (iCanale.executeUpdate() == 1) {
                    // prende la chiave generata dalla insert del nuovo record
                    try (ResultSet keys = iCanale.getGeneratedKeys()) {
                        if (keys.next()) {
                            int key = keys.getInt(1);
                            // aggiorna la chiave
                            canale.setKey(key);
                            // inserisce il nuovo oggetto nella cache
                            dataLayer.getCache().add(Canale.class, canale);
                        }
                    }
                }
            }
            // resetta l'attributo di modifca se è un proxy 
            if (canale instanceof DataItemProxy) {
                ((DataItemProxy) canale).setModified(false);
            }
        } catch (SQLException ex) {
            throw new DataException("Unable to store \"Canale\"", ex);
        }
    }
    
}
