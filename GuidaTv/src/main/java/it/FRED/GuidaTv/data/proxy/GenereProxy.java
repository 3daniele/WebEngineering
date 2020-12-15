package it.FRED.GuidaTv.data.proxy;
import freemarker.log.Logger;
import it.FRED.GuidaTv.data.impl.GenereImpl;
import it.FRED.GuidaTv.framework.data.DataItemProxy;
import it.FRED.GuidaTv.framework.data.DataLayer;
import it.FRED.GuidaTv.framework.data.DataException;
import java.util.List;
import java.util.logging.Level;

public class GenereProxy extends GenereImpl implements DataItemProxy{
    protected boolean modified;
    protected DataLayer dataLayer;
    
    public GenereProxy(DataLayer d){
        super();
        this.dataLayer = d;
        this.modified = false;
    }
    
    @Override
    public void setNome(String nome) {
        super.setNome(nome);
        this.modified = true;
    }
    
    @Override
    public void setKey(Integer key) {
        super.setKey(key);
        this.modified = true;
    }
    
    //METODI DEL PROXY
    //PROXY-ONLY METHODS
    @Override
    public void setModified(boolean dirty) {
        this.modified = dirty;
    }
    
    @Override
    public boolean isModified() {
        return modified;
    }
    
}
