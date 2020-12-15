package it.FRED.GuidaTv.data.proxy;

import it.FRED.GuidaTv.data.impl.EpisodioImpl;
import it.FRED.GuidaTv.data.model.Episodio;
import freemarker.log.Logger;
import it.FRED.GuidaTv.framework.data.DataItemProxy;
import it.FRED.GuidaTv.framework.data.DataLayer;
import it.FRED.GuidaTv.framework.data.DataException;
import java.util.List;
import java.util.logging.Level;
import it.FRED.GuidaTv.data.model.Stagione;

import it.FRED.GuidaTv.data.dao.EpisodioDAO;
import it.FRED.GuidaTv.data.dao.StagioneDAO;

public class EpisodioProxy extends EpisodioImpl implements DataItemProxy{
    protected boolean modified;
    protected DataLayer dataLayer;
    protected int stagione_key;
    
    public EpisodioProxy(DataLayer d){
        super();
        this.dataLayer = d;
        this.modified = false;
        this.stagione_key = 0;
    }
    
    @Override
    public void setNome(String nome) {
        super.setNome(nome);
        this.modified = true;
    }
    
    @Override
    public void setKey(Integer key){
        super.setKey(key);
        this.modified = true;
    }
    
    @Override
    public void setNumero(int n){
        super.setNumero(n);
        this.modified = true;
    }
    
    @Override
    public void setDurata(int d){
        super.setDurata(d);
        this.modified = true;
    }
    
    @Override
    public void setDescrizione(String s){
        super.setDescrizione(s);
        this.modified = true;
    }
    
    @Override
    public Stagione getStagione(){
        if(super.getStagione() == null && stagione_key > 0){
            try{
               super.setStagione(((StagioneDAO) dataLayer.getDAO(Stagione.class)).getStagione(stagione_key));
            }catch(DataException ex){
                System.out.print(ex);
            }
        }
        
        return super.getStagione();
    }
    
    @Override
    public void setStagione(Stagione s){
        super.setStagione(s);
        this.stagione_key = s.getKey();
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
    
    public void setStagione_key(int key){
        this.stagione_key = key;
        super.setStagione(null);
    }
    
    
}
