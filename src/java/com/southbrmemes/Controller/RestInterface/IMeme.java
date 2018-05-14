/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmemes.Controller.RestInterface;

import com.southbrmemes.Model.Entity.Meme;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dev
 */
public interface IMeme {

    public void insert(Meme meme, Connection conexao, PreparedStatement pst) throws SQLException;

    public int update(Meme meme, Connection conexao, PreparedStatement pst) throws SQLException;

    public void delete(Meme meme, Connection conexao, PreparedStatement pst) throws SQLException;

    public ResultSet select(Connection conexao, PreparedStatement pst) throws SQLException;

    public ResultSet selectId(Meme meme, Connection conexao, PreparedStatement pst) throws SQLException;

}
