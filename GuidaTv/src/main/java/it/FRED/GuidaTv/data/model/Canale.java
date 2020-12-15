package it.FRED.GuidaTv.data.model;
import it.FRED.GuidaTv.framework.data.DataItem;


public interface Canale extends DataItem<Integer>{
    String getNome();
    void setNome(String n);
    
    String getGruppo();
    void setGruppo(String g);
}
