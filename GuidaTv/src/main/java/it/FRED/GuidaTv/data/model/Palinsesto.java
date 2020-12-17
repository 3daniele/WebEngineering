package it.FRED.GuidaTv.data.model;
import it.FRED.GuidaTv.framework.data.DataItem;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public interface Palinsesto extends DataItem<Integer>{
    Canale getCanale();
    void setCanale(Canale c);
    
    //data 
    LocalDate getData();
    void setData(LocalDate l);
    
    Programma getProgramma();
    void setProgramma(Programma s);
    
    LocalTime getOra();
    void setOra(LocalTime l);
}
