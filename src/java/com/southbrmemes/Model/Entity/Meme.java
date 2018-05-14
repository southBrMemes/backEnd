/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmemes.Model.Entity;

/**
 *
 * @author dev
 */
public class Meme {
    
    private int id;
    private String url;
    private String commit;
    private String dateCri;
    private int iduser;
    private boolean active;

    public Meme(int id, String url, String commit, String dateCri, int iduser, boolean active) {
        this.id = id;
        this.url = url;
        this.commit = commit;
        this.dateCri = dateCri;
        this.iduser = iduser;
        this.active = active;
    }

    public Meme() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public String getDateCri() {
        return dateCri;
    }

    public void setDateCri(String dateCri) {
        this.dateCri = dateCri;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    
}
