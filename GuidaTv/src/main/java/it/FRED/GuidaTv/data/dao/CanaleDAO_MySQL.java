package it.FRED.GuidaTv.data.dao;

import it.FRED.GuidaTv.data.model.Canale;
import it.FRED.GuidaTv.framework.data.DAO;
import it.FRED.GuidaTv.framework.data.DataLayer;
import it.FRED.GuidaTv.framework.data.DataException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class CanaleDAO_MySQL extends DAO implements CanaleDAO {

    private PreparedStatement sCanaleByID, sCanali;
    private PreparedStatement iCanale, uCanale, dCanale;
    
    public CanaleDAO_MySQL (DataLayer d) {
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
            throw new DataException("Error initializing newspaper data layer", ex);
        }
    }
    
    @Override
    public void destroy() throws DataException {
        
        try {
            sCanaleByID.close();
            sCanali.close();
            iCanale.close();
            uCanale.close();
            dCanale.close();

        } catch (SQLException ex) {
            //
        }
        super.destroy();
    }
    
    @Override
    public Canale createCanale() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Canale getCanale(int canale_key) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Canale> getCanali() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void storeCanale(Canale canale) throws DataException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
