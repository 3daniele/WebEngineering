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

import it.FRED.GuidaTv.data.proxy.GenereProxy;
import it.FRED.GuidaTv.data.impl.GenereImpl;
import it.FRED.GuidaTv.data.model.Genere;

public class GenereDAO_MySQL extends DAO implements GenereDAO{
    
    private PreparedStatement sGenereByID, sGeneri, sGenereByNome;
    private PreparedStatement iGenere, uGenere, dGenere;
    
    public GenereDAO_MySQL(DataLayer d){
        super(d);
    }

    public GenereDAO_MySQL() {
        super();
    }
    
    @Override
    public void init() throws DataException{
        
        try{
            super.init();
            sGenereByID = connection.prepareStatement("SELECT *FROM genere where id_genere = ?");
            sGeneri = connection.prepareStatement("SELECT *FROM genere");
            sGenereByNome = connection.prepareStatement("SELECT *FROM genere WHERE nome = ?");
            iGenere = connection.prepareStatement("INSERT INTO genere(nome) VALUES(?)");
            uGenere = connection.prepareStatement("UPDATE genere SET nome = ? WHERE id_genere = ?");
            dGenere = connection.prepareStatement("DELETE FROM genere where id_genere = ?");
        }catch(SQLException ex){
            throw new DataException("Errore dataLayer", ex);
        }
    }
    
    @Override
    public void destroy() throws DataException{
        try{
            sGenereByID.close();
            sGeneri.close();
            sGenereByNome.close();
            iGenere.close();
            uGenere.close();
            dGenere.close();
        }catch(SQLException ex){
            throw new DataException("Errore nella chiusura", ex);
        }
        super.destroy();
    }
    
    @Override
    public GenereProxy createGenere(){
        return new GenereProxy(getDataLayer());
    }
    
    private GenereProxy createGenere(ResultSet rs)throws DataException{
        GenereProxy e = createGenere();
        
        try{
            e.setKey(rs.getInt("id_genere"));
            e.setNome(rs.getString("nome"));
        }catch(SQLException ex){
            throw new DataException("Errore nella creazione", ex);
        }
        
        return e;
    }
    
    @Override
    public Genere getGenere(int genere_key)throws DataException{
        Genere g = null;
        
        if(dataLayer.getCache().has(Genere.class, genere_key)){
            g = dataLayer.getCache().get(Genere.class, genere_key);
        }else{
            try{
                sGenereByID.setInt(1, genere_key);
                try(ResultSet rs = sGenereByID.executeQuery()){
                    if(rs.next()){
                        g = createGenere(rs);
                        dataLayer.getCache().add(Genere.class, g);
                    }
                }
            }catch(SQLException ex){
               throw new DataException("Impossibile caricare", ex); 
            }
        }
        
        return g;
    }
    
    @Override
    public Genere getGenere(String nome)throws DataException{
        Genere g = null;
        
        if(dataLayer.getCache().has(Genere.class, nome)){
            g = dataLayer.getCache().get(Genere.class, nome);
        }else{
            try{
                sGenereByNome.setString(1, nome);
                try(ResultSet rs = sGenereByNome.executeQuery()){
                    if(rs.next()){
                        g = createGenere(rs);
                        dataLayer.getCache().add(Genere.class, g);
                    }
                }
            }catch(SQLException ex){
               throw new DataException("Impossibile caricare", ex); 
            }
        }
        
        return g;
    }
    
    @Override
    public List<Genere> getGeneri()throws DataException{
        
        List<Genere> lista = new ArrayList();
        try(ResultSet rs = sGeneri.executeQuery()){
            while(rs.next()){
                int id = rs.getInt("id_genere");
                String nome= rs.getString("nome");
                Genere g = new GenereImpl(nome);
                
                lista.add(g);
            }
        }catch(SQLException ex){
            throw new DataException("Impossibile caricare", ex);
        }
        
        return lista;
        
    }
    
     @Override
    public void storeGenere(Genere genere) throws DataException{
        
        try{
            if(genere.getKey() != null && genere.getKey() > 0){
                if(genere instanceof DataItemProxy && !((DataItemProxy) genere).isModified()){
                    return;
                }
                
                //update
                //uGenere = connection.prepareStatement("UPDATE genere SET nome = ? WHERE id_genere = ?");
                uGenere.setInt(2, genere.getKey());
                uGenere.setString(1, genere.getNome());
                if(uGenere.executeUpdate() == 0){
                    throw new DataException("Errore nell'update");
                }
            }else{
                //insert
                iGenere.setString(1, genere.getNome());
                
                if(iGenere.executeUpdate() == 1){
                    try(ResultSet keys = iGenere.getGeneratedKeys()){
                        if(keys.next()){
                            int key = keys.getInt(1);
                            genere.setKey(key);
                            dataLayer.getCache().add(Genere.class, genere);
                        }
                        
                    }
                }
            }
            
            if(genere instanceof DataItemProxy)
                ((DataItemProxy) genere).setModified(false);
            
        }catch(SQLException ex){
            throw new DataException("Impossibile eseguire la store", ex);
        }
    }
    
    
}
