package it.FRED.GuidaTv.data.proxy;
import freemarker.log.Logger;
import it.FRED.GuidaTv.data.dao.UtenteDAO;
import it.FRED.GuidaTv.data.impl.ProgrammaImpl;
import it.FRED.GuidaTv.data.model.Utente;
import it.FRED.GuidaTv.framework.data.DataItemProxy;
import it.FRED.GuidaTv.framework.data.DataLayer;
import it.FRED.GuidaTv.framework.data.DataException;
import java.util.List;
import java.util.logging.Level;


public class ProgrammaProxy extends ProgrammaImpl implements DataItemProxy{
    protected boolean modified;
    protected DataLayer dataLayer;
    protected int editore_key;
    
    public ProgrammaProxy(DataLayer d){
        super();
        this.dataLayer = d;
        this.modified = false;
        this.editore_key = 0;
    }
    
    @Override
    public void setNome(String nome) {
        super.setNome(nome);
        this.modified = true;
    }
    
    @Override
    public void setDurata(int d) {
        super.setDurata(d);
        this.modified = true;
    }
    
    @Override
    public void setAnno(int anno) {
        super.setAnno(anno);
        this.modified = true;
    }
    
    @Override
    public void setDescrizione(String descrizione) {
        super.setDescrizione(descrizione);
        this.modified = true;
    }
    
    @Override
    public void setTipo(String tipo) {
        super.setTipo(tipo);
        this.modified = true;
    }
    
    @Override
    public void setImmagine(String url) {
        super.setImmagine(url);
        this.modified = true;
    }
    
    @Override
    public void setKey(Integer key) {
        super.setKey(key);
        this.modified = true;
    }
    
    @Override
    public Utente getEditore(){
        if(super.getEditore() == null && editore_key > 0){
            try{
                super.setEditore(((UtenteDAO)dataLayer.getDAO(Utente.class)).getUtente(editore_key));
            }catch(DataException ex){
                System.out.println(ex);
            }
        }
        return super.getEditore();
    }
    
    @Override
    public void setEditore(Utente e){
        super.setEditore(e);
        this.editore_key = e.getKey();
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
