package it.FRED.GuidaTv.data.model;
import it.FRED.GuidaTv.framework.data.DataItem;

public interface Utente extends DataItem<Integer> {
    
    String getRuolo();
    void setRuolo(String r);
    
    String getEmail();
    void setEmail(String t);
    
    String getPassword();
    void setPassowrd(String p);
    
    String getStatus();
    void setStatus(String s);
    
    
}
