/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmemes.Model.DAOs;

import com.google.gson.Gson;
import com.southbrmemes.Model.BussnesRule.DateTime;
import com.southbrmemes.Model.Entity.Meme;
import com.southbrmemes.Model.Entity.Return;
import com.southbrmemes.Model.SQL.MemerSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dev
 */
public class MemeDao extends MemerSQL {

    private Connection conexao;
    private PreparedStatement pst;
    private ResultSet rs;

    public String insert(Meme meme) {

        Return retorno = new Return();
        Gson gson = new Gson();

        try {
            conexao = com.southbrmemes.Model.Connet.Connection.conector();
            insert(meme, conexao, pst);
            
            retorno.setAnswer(false);
            retorno.setAnswerText("meme cadastrado com sucesso");
            return gson.toJson(retorno);
        } catch (SQLException ex) {
            retorno.setAnswer(false);
            retorno.setAnswerText("Erro ao cadastrar meme");
            return gson.toJson(retorno);
        }
    }

    public String update(Meme meme) {

        Return retorno = new Return();
        Gson gson = new Gson();

        try {
            conexao = com.southbrmemes.Model.Connet.Connection.conector();
            update(meme, conexao, pst);

            retorno.setAnswer(true);
            retorno.setAnswerText("meme atualizado com sucesso");
            return gson.toJson(retorno);
        } catch (SQLException ex) {
            retorno.setAnswer(false);
            retorno.setAnswerText("Erro ao atualizar meme");
            return gson.toJson(retorno);
        }
    }

    public String delete(Meme meme) {

        Return retorno = new Return();
        Gson gson = new Gson();

        try {
            conexao = com.southbrmemes.Model.Connet.Connection.conector();
            delete(meme, conexao, pst);

            retorno.setAnswer(true);
            retorno.setAnswerText("meme deletado com sucesso");
            return gson.toJson(retorno);
        } catch (SQLException ex) {
            retorno.setAnswer(false);
            retorno.setAnswerText("Erro ao deletar meme");
            return gson.toJson(retorno);
        }
    }
    
    public List getMyMeme(Meme meme) {
        List<Meme> list = new ArrayList<Meme>();
        try {
            conexao = com.southbrmemes.Model.Connet.Connection.conector();

            rs = selectId(meme, conexao, pst);

            while (rs.next()) {
                list.add(new Meme(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        DateTime.converterDataDoBanco(rs.getDate(4)),
                        rs.getInt(5),
                        true));
            }

            conexao.close();
            return list;
        } catch (SQLException e) {
            return null;
        }
    }
    
    public List getMeme() {
        List<Meme> list = new ArrayList<Meme>();
        try {
            conexao = com.southbrmemes.Model.Connet.Connection.conector();

            rs = select(conexao, pst);

            while (rs.next()) {
                list.add(new Meme(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        DateTime.converterDataDoBanco(rs.getDate(4)),
                        rs.getInt(5),
                        true));
            }

            conexao.close();
            return list;
        } catch (SQLException e) {
            return null;
        }
    }
}
