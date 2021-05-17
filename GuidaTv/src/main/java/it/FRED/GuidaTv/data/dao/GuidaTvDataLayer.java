package it.FRED.GuidaTv.data.dao;
import it.FRED.GuidaTv.framework.data.DataLayer;
import it.FRED.GuidaTv.framework.data.DataException;
import java.sql.SQLException;
import javax.sql.DataSource;
import it.FRED.GuidaTv.data.model.*;

public class GuidaTvDataLayer extends DataLayer{
    
    public GuidaTvDataLayer(DataSource datasource) throws SQLException {
        super(datasource);
    }

    @Override
    public void init() throws DataException {
        //registriamo i nostri dao
        //register our daos
        registerDAO(Canale.class, new CanaleDAO_MySQL(this));
        registerDAO(Episodio.class, new EpisodioDAO_MySQL(this));
        registerDAO(Genere.class, new GenereDAO_MySQL(this));
        registerDAO(Palinsesto.class, new PalinsestoDAO_MySQL(this));
        registerDAO(Programma.class, new ProgrammaDAO_MySQL(this));
        registerDAO(Salvataggio.class, new SalvataggioDAO_MySQL(this));
        registerDAO(Stagione.class, new StagioneDAO_MySQL(this));
        registerDAO(Utente.class, new UtenteDAO_MySQL(this));
    }

    //helpers    
    public CanaleDAO getCanaleDAO() {
        return (CanaleDAO) getDAO(Canale.class);
    }

    public EpisodioDAO getEpisodioDAO() {
        return (EpisodioDAO) getDAO(Episodio.class);
    }

    public GenereDAO getGenereDAO() {
        return (GenereDAO) getDAO(Genere.class);
    }

    public PalinsestoDAO getPalinsestoDAO() {
        return (PalinsestoDAO) getDAO(Palinsesto.class);
    }
    
    public ProgrammaDAO getProgrammaDAO() {
        return (ProgrammaDAO) getDAO(Programma.class);
    }
    
    public SalvataggioDAO getSalvataggioDAO() {
        return (SalvataggioDAO) getDAO(Salvataggio.class);
    }
    
    public StagioneDAO getStagioneDAO() {
        return (StagioneDAO) getDAO(Stagione.class);
    }
    
    public UtenteDAO getUtenteDAO() {
        return (UtenteDAO) getDAO(Utente.class);
    }
}
