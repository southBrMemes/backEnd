/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmemes.Model.SQL;

import com.southbrmemes.Controller.RestInterface.IMeme;
import com.southbrmemes.Model.BussnesRule.CodePassword;
import com.southbrmemes.Model.Entity.Meme;
import com.southbrmemes.Model.Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dev
 */
public class MemerSQL implements IMeme{

    @Override
    public void insert(Meme meme, Connection conexao, PreparedStatement pst) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("insert into meme(url,commit,iduser,dateCri) values (?, ?, ?, date(now()))");

        pst = conexao.prepareStatement(sql.toString());

        pst.setString(1, meme.getUrl());
        pst.setString(2, meme.getCommit());
        pst.setInt(3, meme.getIduser());

        pst.execute();
    }

    @Override
    public int update(Meme meme, Connection conexao, PreparedStatement pst) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("update meme set url = ? , commit = ?");
        sql.append(" where id = ? and iduser = ? and active = true");

        pst = conexao.prepareStatement(sql.toString());

        pst.setString(1, meme.getUrl());
        pst.setString(2, meme.getCommit());
        pst.setInt(3, meme.getId());
        pst.setInt(4, meme.getIduser());

        return pst.executeUpdate();
    }

    @Override
    public ResultSet select(Connection conexao, PreparedStatement pst) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("select m.id,m.url,m.commit,m.dateCri,iduser from meme as m");
        sql.append(" inner join user as u on u.id = m.iduser");
        sql.append(" where u.idcity = 1 and m.active = true");
        sql.append(" order by rand() limit 10");

        pst = conexao.prepareStatement(sql.toString());

        return pst.executeQuery();
    }

    @Override
    public ResultSet selectId(Meme meme, Connection conexao, PreparedStatement pst) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("select m.id,m.url,m.commit,m.dateCri,iduser from meme as m");
        sql.append(" inner join user as u on u.id = m.iduser");
        sql.append(" where u.idcity = 1 and u.id = ? and m.active = true");
        sql.append(" order by rand() limit 10");

        pst = conexao.prepareStatement(sql.toString());

        pst.setInt(1, meme.getIduser());

        return pst.executeQuery();
    }

    @Override
    public void delete(Meme meme, Connection conexao, PreparedStatement pst) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("update meme set active = false");
        sql.append(" where id = ? and iduser = ?");

        pst = conexao.prepareStatement(sql.toString());

        pst.setInt(1, meme.getId());
        pst.setInt(2, meme.getIduser());

        pst.execute();
    }
}
