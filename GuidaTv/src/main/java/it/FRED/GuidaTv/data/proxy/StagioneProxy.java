package it.FRED.GuidaTv.data.proxy;
import freemarker.log.Logger;
import it.FRED.GuidaTv.data.dao.ProgrammaDAO;
import it.FRED.GuidaTv.data.impl.StagioneImpl;
import it.FRED.GuidaTv.data.model.Programma;
import it.FRED.GuidaTv.framework.data.DataItemProxy;
import it.FRED.GuidaTv.framework.data.DataLayer;
import it.FRED.GuidaTv.framework.data.DataException;
import java.util.List;
import java.util.logging.Level;

public class StagioneProxy extends StagioneImpl implements DataItemProxy{
    protected boolean modified;
    protected DataLayer dataLayer;
    protected int programma_key;
    
    public StagioneProxy(DataLayer d){
        super();
        this.dataLayer = d;
        this.modified = false;
        this.programma_key = 0;
    }
    
    @Override
    public void setNumero(int n) {
        super.setNumero(n);
        this.modified = true;
    }
    
    @Override
    public void setDescrizione(String n) {
        super.setDescrizione(n);
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
