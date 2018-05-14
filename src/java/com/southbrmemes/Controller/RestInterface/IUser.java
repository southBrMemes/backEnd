/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmemes.Controller.RestInterface;

import com.southbrmemes.Model.Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dev
 */
public interface IUser {

    public void insert(User user, Connection conexao, PreparedStatement pst) throws SQLException;

    public int update(User user, Connection conexao, PreparedStatement pst) throws SQLException;

    public void delete(User user, Connection conexao, PreparedStatement pst) throws SQLException;

    public ResultSet login(User user, Connection conexao, PreparedStatement pst) throws SQLException;

    public ResultSet data(User user, Connection conexao, PreparedStatement pst) throws SQLException;
}
