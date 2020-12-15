package it.FRED.GuidaTv.data.proxy;

import it.FRED.GuidaTv.data.impl.CanaleImpl;
import it.FRED.GuidaTv.framework.data.DataItemProxy;
import it.FRED.GuidaTv.framework.data.DataLayer;

public class CanaleProxy extends CanaleImpl implements DataItemProxy {
    
    protected boolean modified;
    protected DataLayer dataLayer;
    
    public CanaleProxy(DataLayer d) {
        super();
        this.dataLayer = d;
        this.modified = false;
    }
    
    @Override
    public void setKey(Integer key) {
        super.setKey(key);
        this.modified = true;
    }
    
    @Override
    public void setNome(String nome) {
        super.setNome(nome);
        this.modified = true;
    }
    
    @Override
    public void setGruppo(String gruppo) {
        super.setGruppo(gruppo);
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
