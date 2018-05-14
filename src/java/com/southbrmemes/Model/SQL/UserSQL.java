/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmemes.Model.SQL;

import com.southbrmemes.Controller.RestInterface.IUser;
import com.southbrmemes.Model.BussnesRule.CodePassword;
import com.southbrmemes.Model.Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dev
 */
public class UserSQL implements IUser{

    @Override
    public void insert(User user, Connection conexao, PreparedStatement pst) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("insert into user(name,login,password,idcity,dateCri) values (?, ?, ?, ?,date(now()))");

        pst = conexao.prepareStatement(sql.toString());

        pst.setString(1, user.getName());
        pst.setString(2, user.getLogin());
        pst.setString(3, CodePassword.encode(user.getPassword()));
        pst.setInt(4, 1);

        pst.execute();
    }

    @Override
    public int update(User user, Connection conexao, PreparedStatement pst) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("update user set name = ? , login = ? , password = ? ");
        sql.append(" where id = ? and password = ? ");

        pst = conexao.prepareStatement(sql.toString());

        pst.setString(1, user.getName());
        pst.setString(2, user.getLogin());
        pst.setString(3, CodePassword.encode(user.getNewPassword()));
        pst.setInt(4, user.getId());
        pst.setString(5, CodePassword.encode(user.getPassword()));

        return pst.executeUpdate();
    }

    @Override
    public ResultSet login(User user, Connection conexao, PreparedStatement pst) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("select id,name from user");
        sql.append(" where login = ? and password = ?");
        sql.append(" and active = true");

        pst = conexao.prepareStatement(sql.toString());

        pst.setString(1, user.getLogin());
        pst.setString(2, CodePassword.encode(user.getPassword()));

        return pst.executeQuery();
    }

    @Override
    public ResultSet data(User user, Connection conexao, PreparedStatement pst) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("select name,login,password,idcity,dateCri from user");
        sql.append(" where id = ?");
        sql.append(" and active = true");

        pst = conexao.prepareStatement(sql.toString());

        pst.setInt(1, user.getId());

        return pst.executeQuery();
    }

    @Override
    public void delete(User user, Connection conexao, PreparedStatement pst) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("update user set active = false");
        sql.append(" where id = ?");

        pst = conexao.prepareStatement(sql.toString());

        pst.setInt(1, user.getId());

        pst.execute();
    }
}
