package it.FRED.GuidaTv.data.model;
import it.FRED.GuidaTv.framework.data.DataItem;

public interface Programma extends DataItem<Integer>{
    
    String getNome();
    void setNome(String n);
    
    int getDurata();
    void setDurata(int d);
    
    int getAnno();
    void setAnno(int a);
    
    String getDescrizione();
    void setDescrizione(String d);
    
    Utente getEditore();
    void setEditore(Utente d);
    
    String getTipo();
    void setTipo(String t);
    
    String getImmagine();
    void setImmagine(String link);
    
}
