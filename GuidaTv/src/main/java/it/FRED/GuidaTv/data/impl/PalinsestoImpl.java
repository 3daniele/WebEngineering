package it.FRED.GuidaTv.data.impl;

import it.FRED.GuidaTv.data.model.Canale;
import it.FRED.GuidaTv.data.model.Palinsesto;
import it.FRED.GuidaTv.framework.data.DataItemImpl;
import java.time.LocalDateTime;
import it.FRED.GuidaTv.data.model.Programma;


public class PalinsestoImpl extends DataItemImpl<Integer> implements Palinsesto{
    private Canale canale;
    private LocalDateTime DataOra;
    private Programma programma;
    
    
    public PalinsestoImpl (Canale canale, LocalDateTime DataOra, Programma programma){
        this.canale = canale;
        this.DataOra = DataOra;
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
    public LocalDateTime getDataOra(){
         return this.DataOra;
    }
    @Override
    public  void setDataOra(LocalDateTime l){
         this.DataOra = l;
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
