package it.FRED.GuidaTv.data.model;
import it.FRED.GuidaTv.framework.data.DataItem;
import java.time.LocalDateTime;


public interface Palinsesto extends DataItem<Integer>{
    Canale getCanale();
    void setCanale(Canale c);
    
    //data e ora
    LocalDateTime getDataOra();
    void setDataOra(LocalDateTime l);
    
    Programma getProgramma();
    void setProgramma(Programma s);
}
