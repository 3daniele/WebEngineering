package it.FRED.GuidaTv.data.proxy;
import freemarker.log.Logger;
import it.FRED.GuidaTv.data.dao.CanaleDAO;
import it.FRED.GuidaTv.data.dao.ProgrammaDAO;
import it.FRED.GuidaTv.data.impl.PalinsestoImpl;
import it.FRED.GuidaTv.data.model.Canale;
import it.FRED.GuidaTv.data.model.Programma;
import it.FRED.GuidaTv.framework.data.DataItemProxy;
import it.FRED.GuidaTv.framework.data.DataLayer;
import it.FRED.GuidaTv.framework.data.DataException;
import java.util.List;
import java.util.logging.Level;
import java.time.LocalDateTime;

public class PalinsestoProxy extends PalinsestoImpl implements DataItemProxy{
    protected boolean modified;
    protected DataLayer dataLayer;
    protected int canale_key;
    protected int programma_key;
    
    public PalinsestoProxy(DataLayer d){
        super();
        this.dataLayer = d;
        this.modified = false;
        this.canale_key = 0;
        this.programma_key = 0;
    }
    
    @Override
    public void setDataOra(LocalDateTime t) {
        super.setDataOra(t);
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
