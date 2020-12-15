package it.FRED.GuidaTv.data.model;
import it.FRED.GuidaTv.framework.data.DataItem;


public interface Genere extends DataItem<Integer>{
    String getNome();
    void setNome(String g);
}
