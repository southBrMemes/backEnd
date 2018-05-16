/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmemes.Model.DAOs;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.southbrmemes.Controller.AbstractClass.CreatTokenAbstract;
import com.southbrmemes.Model.BussnesRule.CreatToken;
import com.southbrmemes.Model.BussnesRule.DateTime;
import com.southbrmemes.Model.Entity.Return;
import com.southbrmemes.Model.Entity.User;
import com.southbrmemes.Model.SQL.UserSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author dev
 */
public class UserDao extends UserSQL {

    private Connection conexao;
    private PreparedStatement pst;
    private ResultSet rs;

    public String insert(User user) {

        Return retorno = new Return();
        Gson gson = new Gson();

        try {
            conexao = com.southbrmemes.Model.Connet.Connection.conector();
            insert(user, conexao, pst);
            return login(user);
        } catch (SQLException ex) {
            retorno.setAnswer(false);
            retorno.setAnswerText("Erro ao cadastrar usuario, este login já está sendo usado");
            return gson.toJson(retorno);
        }
    }

    public String update(User user) {

        Return retorno = new Return();
        Gson gson = new Gson();

        try {
            conexao = com.southbrmemes.Model.Connet.Connection.conector();
            int quant = update(user, conexao, pst);

            if(quant > 0){
            retorno.setAnswer(true);
            retorno.setAnswerText("usuari o atualizado com sucesso");
            } else {
            retorno.setAnswer(false);
            retorno.setAnswerText("senha incorreta");
            }
            return gson.toJson(retorno);
        } catch (SQLException ex) {
            retorno.setAnswer(false);
            retorno.setAnswerText("Erro ao atualizar usuario");
            return gson.toJson(retorno);
        }
    }

    public String login(User user) {

        CreatTokenAbstract token = new CreatToken();
        Return retorno = new Return();
        Gson gson = new Gson();
        User iuser = new User();

        try {
            conexao = com.southbrmemes.Model.Connet.Connection.conector();

            rs = login(user, conexao, pst);

            if (rs.next()) {
                iuser.setId(rs.getInt(1));
                iuser.setName(rs.getString(2));
                Date dateNow = new Date();
                Date expires = new Date(dateNow.getTime() + 3600);
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("iss", dateNow.getTime());
                jsonObject.addProperty("exp", expires.getTime());
                jsonObject.addProperty("id", iuser.getId());
                jsonObject.addProperty("name", iuser.getName());

                retorno.setToken(token.token(jsonObject));
                retorno.setAnswer(true);
                retorno.setAnswerText("Login realizado com sucesso");
                return gson.toJson(retorno);
            }
            conexao.close();
            retorno.setAnswer(false);
            retorno.setAnswerText("Login ou senha invalido");
            return gson.toJson(retorno);
        } catch (SQLException ex) {
            retorno.setAnswer(false);
            retorno.setAnswerText("Erro de servidor");
            return gson.toJson(retorno);
        }
    }

    public String delete(User user) {

        Return retorno = new Return();
        Gson gson = new Gson();

        try {
            conexao = com.southbrmemes.Model.Connet.Connection.conector();
            delete(user, conexao, pst);

            retorno.setAnswer(true);
            retorno.setAnswerText("usuario deletado com sucesso");
            return gson.toJson(retorno);
        } catch (SQLException ex) {
            retorno.setAnswer(false);
            retorno.setAnswerText("Erro ao deletar usuario");
            return gson.toJson(retorno);
        }
    }
    
    public String data(User user) {

        Return retorno = new Return();
        Gson gson = new Gson();

        try {
            conexao = com.southbrmemes.Model.Connet.Connection.conector();
            rs = data(user, conexao, pst);
            
            if(rs.next()){
                return gson.toJson(new User(0, 
                        rs.getString(1),
                        rs.getString(2),
                        null,
                        rs.getInt(4),
                        DateTime.converterDataDoBanco(rs.getDate(5)),
                        true));
            }

            retorno.setAnswer(true);
            retorno.setAnswerText("usuario deletado com sucesso");
            return gson.toJson(retorno);
        } catch (SQLException ex) {
            retorno.setAnswer(false);
            retorno.setAnswerText("Erro ao deletar usuario");
            return gson.toJson(retorno);
        }
    }
}
