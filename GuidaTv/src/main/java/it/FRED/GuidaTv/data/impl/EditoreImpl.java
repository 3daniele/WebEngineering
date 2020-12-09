/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.FRED.GuidaTv.data.impl;

import it.FRED.GuidaTv.data.model.Editore;
import it.FRED.GuidaTv.framework.data.DataItemImpl;

/**
 *
 * @author Francesca
 */
public class EditoreImpl extends DataItemImpl<Integer> implements Editore{
    
    private String email;
    private String password;
    
    public EditoreImpl (String email, String password){
        this.email = email;
        this.password = password;
    }
    @Override
    public String getEmail(){
         return this.email;
    }
    @Override
    public void setEmail(String e){
         this.email = e;
    }
    @Override
    public String getPassword(){
         return this.password;
    }
    @Override
    public void setPassword(String p){
         this.password = p;
    }
}
