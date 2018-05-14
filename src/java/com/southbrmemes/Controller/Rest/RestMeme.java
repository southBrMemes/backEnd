/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmemes.Controller.Rest;

import com.google.gson.Gson;
import com.southbrmemes.Controller.AbstractClass.CreatTokenAbstract;
import com.southbrmemes.Model.BussnesRule.CreatToken;
import com.southbrmemes.Model.DAOs.MemeDao;
import com.southbrmemes.Model.DAOs.UserDao;
import com.southbrmemes.Model.Entity.Meme;
import com.southbrmemes.Model.Entity.Return;
import com.southbrmemes.Model.Entity.Token;
import com.southbrmemes.Model.Entity.User;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author joao.vrevangelista
 */
@Path("meme")
public class RestMeme {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public RestMeme() {
    }

    /**
     * Retrieves representation of an instance of memes.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public String getList() {
        Gson gson = new Gson();
        MemeDao memeDao = new MemeDao();
        return gson.toJson(memeDao.getMeme());
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("mylist")
    public String getMyList(@HeaderParam("Authorization") String header) {
       
        Gson gson = new Gson();

        MemeDao memeDao = new MemeDao();
        
        CreatTokenAbstract creatToken = new CreatToken();
        Token token = (Token) creatToken.decodeToken(header);
        Meme meme = new Meme();
        meme.setIduser(token.getId());
        
        return gson.toJson(memeDao.getMyMeme(meme));
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("insert")
    public String insert(String content,@HeaderParam("Authorization") String header) {

        Gson gson = new Gson();

        MemeDao memeDao = new MemeDao();
        Meme meme = (Meme) gson.fromJson(content, Meme.class);
        
        CreatTokenAbstract creatToken = new CreatToken();
        Token token = (Token) creatToken.decodeToken(header);
        meme.setIduser(token.getId());

        return memeDao.insert(meme);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("update")
    public String update(String content,@HeaderParam("Authorization") String header) {

        Gson gson = new Gson();

        MemeDao memeDao = new MemeDao();
        Meme meme = (Meme) gson.fromJson(content, Meme.class);
        
        CreatTokenAbstract creatToken = new CreatToken();
        Token token = (Token) creatToken.decodeToken(header);
        meme.setIduser(token.getId());
        
        return memeDao.update(meme);
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("delete/{id}")
    public String delete(@PathParam("id") int id,@HeaderParam("Authorization") String header) {

        Gson gson = new Gson();

        MemeDao memeDao = new MemeDao();
        Meme meme = new Meme();
        
        CreatTokenAbstract creatToken = new CreatToken();
        Token token = (Token) creatToken.decodeToken(header);
        meme.setIduser(token.getId());
        meme.setId(id);
        
        return memeDao.delete(meme);
    }

}
