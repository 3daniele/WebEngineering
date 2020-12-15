package it.FRED.GuidaTv.data.proxy;
import freemarker.log.Logger;
import it.FRED.GuidaTv.data.impl.UtenteImpl;
import it.FRED.GuidaTv.framework.data.DataItemProxy;
import it.FRED.GuidaTv.framework.data.DataLayer;
import it.FRED.GuidaTv.framework.data.DataException;
import java.util.List;
import java.util.logging.Level;

public class UtenteProxy extends UtenteImpl implements DataItemProxy{
    protected boolean modified;
    protected DataLayer dataLayer;
    
    public UtenteProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.modified = false;
    }
    
    @Override
    public void setEmail(String email) {
        super.setEmail(email);
        this.modified = true;
    }
    
    @Override
    public void setPassword(String password) {
        super.setPassword(password);
        this.modified = true;
    }
    
    @Override
    public void setStatus(String status) {
        super.setStatus(status);
        this.modified = true;
    }
    
    @Override
    public void setRuolo(String ruolo) {
        super.setRuolo(ruolo);
        this.modified = true;
    }
    
    @Override
    public void setKey(Integer key) {
        super.setKey(key);
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
