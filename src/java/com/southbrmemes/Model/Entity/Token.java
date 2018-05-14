/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmemes.Model.Entity;

/**
 *
 * @author Kayque Rodrigues esta classe tem como objetivo obter um objeto do
 * token(jwt)
 */
public class Token {

    //atributo de cadas token
    //data de criacao do token
    private long iss;
    //data de expiraçao do token
    private long exp;
    //id do usuario dono do token
    private int id;
    //nome do usuario dono do token
    private String name;

    //metudo construtor do token vazio
    public Token() {
    }

    public Token(long iss, long exp, int id, String name) {
        this.iss = iss;
        this.exp = exp;
        this.id = id;
        this.name = name;
    }

    //getters e setters
    public long getIss() {
        return iss;
    }

    public void setIss(long iss) {
        this.iss = iss;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
