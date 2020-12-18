package it.FRED.GuidaTv.data.impl;

import it.FRED.GuidaTv.data.model.Canale;
import it.FRED.GuidaTv.data.model.Palinsesto;
import it.FRED.GuidaTv.framework.data.DataItemImpl;
import java.time.LocalDateTime;
import it.FRED.GuidaTv.data.model.Programma;
import java.time.LocalDate;
import java.time.LocalTime;


public class PalinsestoImpl extends DataItemImpl<Integer> implements Palinsesto{
    private Canale canale;
    private LocalDate data;
    private LocalTime ora;
    private Programma programma;
    
    
    public PalinsestoImpl(){
        this.canale = null;
        this.data = null;
        this.ora = null;
        this.programma = null;
    }
    
    public PalinsestoImpl (Canale canale, LocalDate Data, Programma programma, LocalTime Ora){
        this.canale = canale;
        this.data = Data;
        this.ora = Ora;
        this.programma = programma;
    }
    
    @Override
    public Canale getCanale(){
         return this.canale;
    }
    @Override
    public void setCanale(Canale c){
         this.canale = c;
    }
    
    @Override
    public LocalDate getData(){
         return this.data;
    }
    @Override
    public  void setData(LocalDate l){
         this.data = l;
    }
   
    @Override
    public LocalTime getOra(){
         return this.ora;
    }
    @Override
    public  void setOra(LocalTime l){
         this.ora = l;
    }
    
    @Override
    public  Programma getProgramma(){
         return this.programma;
    }
    @Override
    public  void setProgramma(Programma s){
         this.programma = s;
    }
}
