package it.FRED.GuidaTv.data.model;
import it.FRED.GuidaTv.framework.data.DataItem;

public interface Stagione extends DataItem<Integer>{
    
    Programma getProgramma();
    void setProgramma(Programma s);
    
    int getNumero();
    void setNumero(int n);
    
    int getNEpisodi();
    void setNEpisodi(int n);
    
    String getImmagine();
    void setImmagine(String link);
    
}
