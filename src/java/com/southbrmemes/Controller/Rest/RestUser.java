/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmemes.Controller.Rest;

import com.google.gson.Gson;
import com.southbrmemes.Controller.AbstractClass.CreatTokenAbstract;
import com.southbrmemes.Model.BussnesRule.CreatToken;
import com.southbrmemes.Model.DAOs.UserDao;
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
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author joao.vrevangelista
 */
@Path("user")
public class RestUser {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public RestUser() {
    }

    /**
     * Retrieves representation of an instance of memes.GenericResource
     * @param content
     * @return an instance of java.lang.String
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("login")
    public String login(String content) {
        Gson gson = new Gson();

        UserDao userDao = new UserDao();
        User user = (User) gson.fromJson(content, User.class);

        return userDao.login(user);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("insert")
    public String insert(String content) {
        Gson gson = new Gson();

        UserDao userDao = new UserDao();
        User user = (User) gson.fromJson(content, User.class);

        return userDao.insert(user);
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     * @param content representation for the resource
     */
    @Seguro
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("update")
    public String update(String content,@HeaderParam("Authorization") String header) {
        Gson gson = new Gson();
        
        CreatTokenAbstract creatToken = new CreatToken();
        Token token = (Token) creatToken.decodeToken(header);

        UserDao userDao = new UserDao();
        User user = (User) gson.fromJson(content, User.class);
        user.setId(token.getId());

        return userDao.update(user);
    }
    
    @Seguro
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("delete")
    public String delete(@HeaderParam("Authorization") String header) {
        
        Gson gson = new Gson();
        
        CreatTokenAbstract creatToken = new CreatToken();
        Token token = (Token) creatToken.decodeToken(header);

        UserDao userDao = new UserDao();
        
        User user = new User();
        user.setId(token.getId());

        return userDao.delete(user);
    }
    
    @Seguro
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("data")
    public String data(@HeaderParam("Authorization") String header){
        
        Gson gson = new Gson();
        
        CreatTokenAbstract creatToken = new CreatToken();
        Token token = (Token) creatToken.decodeToken(header);

        UserDao userDao = new UserDao();
        
        User user = new User();
        user.setId(token.getId());

        return userDao.data(user);
    }
    
    
    
    
    
    
    
}
