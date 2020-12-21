package it.FRED.GuidaTv.data.dao;
import it.FRED.GuidaTv.framework.data.DAO;
import it.FRED.GuidaTv.framework.data.DataLayer;
import it.FRED.GuidaTv.framework.data.DataItemProxy;
import it.FRED.GuidaTv.framework.data.DataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import it.FRED.GuidaTv.data.proxy.SalvataggioProxy;
import it.FRED.GuidaTv.data.impl.SalvataggioImpl;
import it.FRED.GuidaTv.data.model.Salvataggio;
import it.FRED.GuidaTv.data.model.Canale;
import it.FRED.GuidaTv.data.dao.CanaleDAO_MySQL;
import it.FRED.GuidaTv.data.model.Programma;
import it.FRED.GuidaTv.data.dao.ProgrammaDAO_MySQL;
import it.FRED.GuidaTv.data.model.Genere;
import it.FRED.GuidaTv.data.dao.GenereDAO_MySQL;
import it.FRED.GuidaTv.data.model.Utente;



public class SalvataggioDAO_MySQL extends DAO implements SalvataggioDAO {
    
    private PreparedStatement sSalvataggi, sSalavataggioByID, sSalvataggiByUtente;
    //necessari solo se ci sono problemi con i null
    //private PreparedStatement iSalvataggioCanale, iSalvataggioGenere, iSalvataggioProgramma;
    private PreparedStatement iSalvataggio, dSalvataggio;

    public SalvataggioDAO_MySQL(DataLayer d){
        super(d);
    }

    @Override
    public void init() throws DataException{

        try{
            super.init();
            sSalvataggi = connection.prepareStatement("SELECT *FROM Salvataggio");
            sSalavataggioByID = connection.prepareStatement("SELECT *FROM Salvataggio WHERE id_salvataggio = ?");
            sSalvataggiByUtente = connection.prepareStatement("SELECT *FROM Salvataggio WHERE utente = ?");
            //iSalavataggioCanale = connection.prepareStatement("INSERT INTO Salvataggio utente = ?, canale = ?");
            //iSalavataggioGenere = connection.prepareStatement("INSERT INTO Salvataggio utente = ?, genere = ?");
            //iSalavataggioProgramma = connection.prepareStatement("INSERT INTO Salvataggio utente = ?, programma = ?");
            iSalvataggio = connection.prepareStatement("INSERT INTO Salvataggio(utente, genere, canale, programma) VALUES(?,?,?,?)");
            dSalvataggio = connection.prepareStatement("DELETE FROM Salvataggio where id_salvataggio = ?");
        }catch(SQLException ex){
            throw new DataException("Errore datalayer", ex);
        }
    }

    @Override
    public void destroy() throws DataException{
        try{
            sSalvataggi.close();
            sSalavataggioByID.close();
            sSalvataggiByUtente.close();
            iSalvataggio.close();
            dSalvataggio.close();
        }catch(SQLException ex){
            throw new DataException("Errore nella chiusura", ex);
        }

        super.destroy();

    }

    @Override
    public SalvataggioProxy createSalvataggio(){
        return new SalvataggioProxy(getDataLayer());
    }

    private SalvataggioProxy createSalvataggio(ResultSet rs) throws DataException{
        SalvataggioProxy s = createSalvataggio();

        try{
            s.setKey(rs.getInt("id_salvataggio"));
            int id_utente = rs.getInt("utente");
            int id_canale = rs.getInt("canale");
            int id_programma = rs.getInt("programma");
            int id_genere = rs.getInt("genere");

            UtenteDAO_MySQL u = new UtenteDAO_MySQL();
            CanaleDAO_MySQL c = new CanaleDAO_MySQL();
            ProgrammaDAO_MySQL p = new ProgrammaDAO_MySQL();
            GenereDAO_MySQL g = new GenereDAO_MySQL();

            s.setUtente(u.getUtente(id_utente));
            s.setCanale(c.getCanale(id_canale));
            s.setProgramma(p.getProgramma(id_programma));
            s.setGenere(g.getGenere(id_genere));

        }catch(SQLException ex){
            throw new DataException("Errore nella creazione", ex);
        }

        return s;
    }

    //sSalvataggi, sSalavataggioByID, sSalvataggiByUtente

    @Override
    public Salvataggio getSalvataggio(int salvataggio_key) throws DataException{
        Salvataggio s = null;

        if(dataLayer.getCache().has(Salvataggio.class, salvataggio_key)){
            s = dataLayer.getCache().get(Salvataggio.class, salvataggio_key);
        }else{
            try(ResultSet rs = sSalavataggioByID.executeQuery()){
                if(rs.next()){
                    s = createSalvataggio(rs);
                    dataLayer.getCache().add(Salvataggio.class, s);
                }
            }catch(SQLException ex){
                throw new DataException("Impossibile caricare", ex);
            }
        }

        return s;
    }

    @Override
    public List<Salvataggio> getSalvataggi() throws DataException {

        List<Salvataggio> lista = new ArrayList();
        
        try(ResultSet rs = sSalvataggi.executeQuery()){
            while(rs.next()){
                int id_utente = rs.getInt("utente");
                int id_canale = rs.getInt("canale");
                int id_programma = rs.getInt("programma");
                int id_genere = rs.getInt("genere");

                UtenteDAO_MySQL u = new UtenteDAO_MySQL();
                CanaleDAO_MySQL c = new CanaleDAO_MySQL();
                ProgrammaDAO_MySQL p = new ProgrammaDAO_MySQL();
                GenereDAO_MySQL g = new GenereDAO_MySQL();

                Utente utente = u.getUtente(id_utente);
                Canale canale = c.getCanale(id_canale);
                Programma programma = p.getProgramma(id_programma);
                Genere genere = g.getGenere(id_genere);
                
                Salvataggio s = new SalvataggioImpl(canale, genere, programma, utente);

                lista.add(s);
            }

        }catch(SQLException ex){
            throw new DataException("Impossibile caricare", ex);
        }
 
        return lista;

    }

    @Override
    public List<Salvataggio> getSalvataggi(Utente a) throws DataException {

        List<Salvataggio> lista = new ArrayList();

        try{
            sSalvataggiByUtente.setInt(1, a.getKey());
        }catch(SQLException ex){

        }

        try(ResultSet rs = sSalvataggiByUtente.executeQuery()){
            while(rs.next()){
                int id_salvataggio = rs.getInt("id_salvataggio");
                int id_utente = rs.getInt("utente");
                int id_canale = rs.getInt("canale");
                int id_programma = rs.getInt("programma");
                int id_genere = rs.getInt("genere");

                UtenteDAO_MySQL u = new UtenteDAO_MySQL();
                CanaleDAO_MySQL c = new CanaleDAO_MySQL();
                ProgrammaDAO_MySQL p = new ProgrammaDAO_MySQL();
                GenereDAO_MySQL g = new GenereDAO_MySQL();

                Utente utente = u.getUtente(id_utente);
                Canale canale = c.getCanale(id_canale);
                Programma programma = p.getProgramma(id_programma);
                Genere genere = g.getGenere(id_genere);

                Salvataggio s = new SalvataggioImpl(canale, genere, programma, utente);

                lista.add(s);
            }

        }catch(SQLException ex){
            throw new DataException("Impossibile caricare", ex);
        }
        return lista;
    }

    

    @Override
    public boolean deleteSalvataggio(Salvataggio salvataggio)throws DataException{

        try{
            dSalvataggio.setInt(1, salvataggio.getKey());
            if(dSalvataggio.executeUpdate()==0){
                return false;
            }else{
                return true;
            }
            
        }catch(SQLException ex){
            throw new DataException("Errore nella DELETE", ex);
        }

    }

    @Override
    public void storeSalvataggio(Salvataggio salvataggio) throws DataException{

        try{
            //utente, genere, canale, programma
            iSalvataggio.setInt(1, salvataggio.getUtente().getKey());
            iSalvataggio.setInt(2, salvataggio.getGenere().getKey());
            iSalvataggio.setInt(3, salvataggio.getCanale().getKey());
            iSalvataggio.setInt(4, salvataggio.getProgramma().getKey());

            if(iSalvataggio.executeUpdate() == 1){
                try(ResultSet keys = iSalvataggio.getGeneratedKeys()){
                    if(keys.next()){
                        int key = keys.getInt(1);
                        salvataggio.setKey(key);
                        dataLayer.getCache().add(Salvataggio.class, salvataggio);
                    }
                }
            }
        }catch(SQLException ex){
            throw new DataException("Impossibile eseguire la store", ex);
        }

    }

}
