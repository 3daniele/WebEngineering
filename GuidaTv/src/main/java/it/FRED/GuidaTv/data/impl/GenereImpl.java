/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.FRED.GuidaTv.data.impl;

import it.FRED.GuidaTv.data.model.Genere;
import it.FRED.GuidaTv.framework.data.DataItemImpl;

/**
 *
 * @author Francesca
 */
public class GenereImpl extends DataItemImpl<Integer> implements Genere{
    private String nome; 
    
    public GenereImpl( String nome){
    this.nome= nome;
    }
    @Override
    public String getNome(){
        return this.nome;
    }
    @Override
    public void setNome(String g){
        this.nome = g;
    }
    
}
