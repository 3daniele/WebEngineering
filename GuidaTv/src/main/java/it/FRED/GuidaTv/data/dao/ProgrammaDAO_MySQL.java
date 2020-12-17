
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


import it.FRED.GuidaTv.data.proxy.ProgrammaProxy;
import it.FRED.GuidaTv.data.impl.ProgrammaImpl;
import it.FRED.GuidaTv.data.model.Programma;
import it.FRED.GuidaTv.data.model.Utente;
public class ProgrammaDAO_MySQL extends DAO implements ProgrammaDAO{
    
    private PreparedStatement sProgrammaByID, sProgramma, sProgrammaByEditore;
    private PreparedStatement iProgramma, uProgramma, dProgramma;
    
    public ProgrammaDAO_MySQL(DataLayer d){
        super(d);
    }

    public ProgrammaDAO_MySQL() {
     }
    
    @Override
    public void init() throws DataException{
        
        try{
            super.init();
            sProgramma = connection.prepareStatement("SELECT *FROM Programma");
            sProgrammaByID = connection.prepareStatement("SELECT *FROM Programma WHERE id_programma = ?");
            iProgramma = connection.prepareStatement("INSERT INTO Programma(anno, durata, descrizione, tipo, editore, immagine, nome) VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            uProgramma = connection.prepareStatement("UPDATE Programma SET anno = ?, durata = ?, descrizione = ?, tipo = ?, editore = ?, immagine = ?, nome = ? WHERE id_programma = ?");
            dProgramma = connection.prepareStatement("DELETE FROM Programma WHERE id_programma = ?");
            sProgrammaByEditore = connection.prepareStatement("SELECT *FROM programma WHERE editore = ?");
        }catch(SQLException ex){
            throw new DataException("Errore dataLayer ", ex);
        }
    }
    
    
    @Override
    public void destroy() throws DataException{
        try{
            sProgramma.close();
            sProgrammaByID.close();
            iProgramma.close();
            uProgramma.close();
            dProgramma.close();
            sProgrammaByEditore.close();
        }catch(SQLException ex){
            throw new DataException("Errore nella chiusura dei preparedStatment", ex);
        }
        super.destroy();
    }
   
    
     public ProgrammaProxy createProgramma(){
        return new ProgrammaProxy(getDataLayer());
    }
    
    private ProgrammaProxy createProgramma(ResultSet rs) throws DataException{
        
        ProgrammaProxy e = createProgramma();
        
       //("INSERT INTO Programma(anno, durata, descrizione, tipo, editore, immagine, nome) VALUES (?,?,?,?,?,?,?)"
       
        try{
            e.setKey(rs.getInt("id_programma"));
            e.setAnno(rs.getInt("anno"));
            e.setDurata(rs.getInt("durata"));
            e.setDescrizione(rs.getString("descrizione"));
            e.setTipo(rs.getString("tipo"));
            int id_editore = rs.getInt("editore");
            UtenteDAO_MySQL utente= new UtenteDAO_MySQL();
            Utente editore= utente.getUtente(id_editore);
            e.setEditore(editore);
            e.setImmagine(rs.getString("immagine"));
            e.setNome(rs.getString("nome"));
            
        }catch(SQLException ex){
            throw new DataException("Errore nella creazione ", ex);
        }
        
        return e;
    }
    
   @Override
    public Programma getProgramma(int programma_key) throws DataException{
        Programma e = null;
        //"SELECT *FROM Programma WHERE id_programma = ?"
        
        if(dataLayer.getCache().has(Programma.class, programma_key)){
            e = dataLayer.getCache().get(Programma.class, programma_key);
        }else{
            try{
                sProgrammaByID.setInt(1, programma_key);
                try(ResultSet rs = sProgrammaByID.executeQuery()){
                    if(rs.next()){
                        e = createProgramma(rs);
                        dataLayer.getCache().add(Programma.class, e);
                    }
                }
            }catch(SQLException ex){
                throw new DataException("Impossibile caricare", ex);
            }
        }
        
        return e;
        
    }
    
    @Override
    public List<Programma> getProgrammi()throws DataException{
        
        List<Programma> lista = new ArrayList();
        //"SELECT *FROM Programma"
        try(ResultSet rs = sProgramma.executeQuery()){
            while(rs.next()){
                int anno  = rs.getInt("anno");
                int durata  = rs.getInt("durata");
                String descrizione = rs.getString("descrizione");
                String tipo = rs.getString("tipo");
                int id_editore = rs.getInt("editore");
                UtenteDAO_MySQL utente= new UtenteDAO_MySQL();
                Utente editore= utente.getUtente(id_editore);
                String immagine = rs.getString("immagine");
                String nome = rs.getString("nome");
                
                Programma programma= new ProgrammaImpl(nome, durata, anno, descrizione, editore, tipo, immagine);
                lista.add(programma);
            }
        }catch(SQLException ex){
            throw new DataException("Impossibile caricare", ex);
        }
        
        return lista;
        
    }
   
    @Override
    public List<Programma> getProgrammi(Utente editore)throws DataException{
        //SELECT *FROM programma WHERE editore = ?"
        List<Programma> lista = new ArrayList();
        try{
            sProgrammaByEditore.setInt(1, editore.getKey());
        }catch(SQLException ex){
            System.out.println("Problemi con la query");
        }
        try(ResultSet rs = sProgrammaByEditore.executeQuery()){
            while(rs.next()){
                int anno  = rs.getInt("anno");
                int durata  = rs.getInt("durata");
                String descrizione = rs.getString("descrizione");
                String tipo = rs.getString("tipo");
                String immagine = rs.getString("immagine");
                String nome = rs.getString("nome");
                
                Programma programma= new ProgrammaImpl(nome, durata, anno, descrizione, editore, tipo, immagine);
                lista.add(programma);
            }
        }catch(SQLException ex){
            throw new DataException("Impossibile caricare", ex);
        }
        
        return lista;
        
    }
    
    @Override
    public void storeProgramma(Programma programma) throws DataException{
        //UPDATE Programma SET anno = ?, durata = ?, descrizione = ?, tipo = ?, editore = ?, immagine = ?, nome = ? WHERE id_programma = ?");
        try{
            if(programma.getKey() != null && programma.getKey() > 0){
                if(programma instanceof DataItemProxy && !((DataItemProxy) programma).isModified()){
                    return;
                }
                
                
                uProgramma.setInt(8, programma.getKey());
                uProgramma.setInt(1, programma.getAnno());
                uProgramma.setInt(2, programma.getDurata());
                uProgramma.setString(3, programma.getDescrizione());
                uProgramma.setString(4, programma.getTipo());
                uProgramma.setInt(5, programma.getEditore().getKey());
                uProgramma.setString(6, programma.getImmagine());
                uProgramma.setString(7, programma.getNome());
              
                
                if(uProgramma.executeUpdate() == 0){
                    throw new DataException("Errore nell'update");
                }
            }else{
                //insert
                iProgramma.setInt(1, programma.getAnno());
                iProgramma.setInt(2,programma.getDurata());
                iProgramma.setString(3, programma.getDescrizione());
                iProgramma.setString(4, programma.getTipo());
                iProgramma.setInt(5, programma.getEditore().getKey());
                iProgramma.setString(6, programma.getImmagine());
                iProgramma.setString(7, programma.getNome());
               
                
                if(iProgramma.executeUpdate() == 1){
                    try(ResultSet keys = iProgramma.getGeneratedKeys()){
                        if(keys.next()){
                            int key = keys.getInt(1);
                            programma.setKey(key);
                            dataLayer.getCache().add(Programma.class, programma);
                        }
                        
                    }
                }
            }
            
            if(programma instanceof DataItemProxy)
                ((DataItemProxy) programma).setModified(false);
            
        }catch(SQLException ex){
            throw new DataException("Impossibile eseguire la store", ex);
        }
    }
    
    
}
