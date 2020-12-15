package it.FRED.GuidaTv.data.proxy;
import freemarker.log.Logger;
import it.FRED.GuidaTv.data.dao.CanaleDAO;
import it.FRED.GuidaTv.data.dao.GenereDAO;
import it.FRED.GuidaTv.data.dao.ProgrammaDAO;
import it.FRED.GuidaTv.data.dao.UtenteDAO;
import it.FRED.GuidaTv.data.impl.SalvataggioImpl;
import it.FRED.GuidaTv.data.model.Canale;
import it.FRED.GuidaTv.data.model.Genere;
import it.FRED.GuidaTv.data.model.Programma;
import it.FRED.GuidaTv.data.model.Utente;
import it.FRED.GuidaTv.framework.data.DataItemProxy;
import it.FRED.GuidaTv.framework.data.DataLayer;
import it.FRED.GuidaTv.framework.data.DataException;
import java.util.List;
import java.util.logging.Level;

public class SalvataggioProxy extends SalvataggioImpl implements DataItemProxy{
    protected boolean modified;
    protected DataLayer dataLayer;
    protected int genere_key, canale_key, programma_key, utente_key;
    
    public SalvataggioProxy(DataLayer d){
        super();
        this.dataLayer = d;
        this.modified = false;
        this.genere_key = 0;
        this.canale_key = 0;
        this.programma_key = 0;
        this.utente_key = 0;
    }
    
    @Override
    public void setKey(Integer key) {
        super.setKey(key);
        this.modified = true;
    }
    
    @Override
    public Programma getProgramma(){
        if(super.getProgramma() == null && programma_key > 0){
            try{
                super.setProgramma(((ProgrammaDAO)dataLayer.getDAO(Programma.class)).getProgramma(programma_key));
            }catch(DataException ex){
                System.out.println(ex);
            }
        }
        return super.getProgramma();
    }
    
    @Override
    public void setProgramma(Programma e){
        super.setProgramma(e);
        this.programma_key = e.getKey();
        this.modified = true;
    }
    
    @Override
    public Canale getCanale(){
        if(super.getCanale() == null && canale_key > 0){
            try{
                super.setCanale(((CanaleDAO)dataLayer.getDAO(Canale.class)).getCanale(canale_key));
            }catch(DataException ex){
                System.out.println(ex);
            }
        }
        return super.getCanale();
    }
    
    @Override
    public void setCanale(Canale e){
        super.setCanale(e);
        this.canale_key = e.getKey();
        this.modified = true;
    }
    
    @Override
    public Utente getUtente(){
        if(super.getUtente() == null && utente_key > 0){
            try{
                super.setUtente(((UtenteDAO)dataLayer.getDAO(Utente.class)).getUtente(utente_key));
            }catch(DataException ex){
                System.out.println(ex);
            }
        }
        return super.getUtente();
    }
    
    @Override
    public void setUtente(Utente e){
        super.setUtente(e);
        this.utente_key = e.getKey();
        this.modified = true;
    }
    
    @Override
    public Genere getGenere(){
        if(super.getGenere() == null && genere_key > 0){
            try{
                super.setGenere(((GenereDAO)dataLayer.getDAO(Genere.class)).getGenere(genere_key));
            }catch(DataException ex){
                System.out.println(ex);
            }
        }
        return super.getGenere();
    }
    
    @Override
    public void setGenere(Genere e){
        super.setGenere(e);
        this.genere_key = e.getKey();
        this.modified = true;
    }
    
    // Metodi del proxy 
    @Override
    public void setModified(boolean dirty) {
        this.modified = dirty;
    }
    @Override
    public boolean isModified() {
        return modified;
    }
}
