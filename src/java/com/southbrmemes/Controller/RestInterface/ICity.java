/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmemes.Controller.RestInterface;

import com.southbrmemes.Model.Entity.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 *  @author joao.vrevangelista
 */
public interface ICity {

    public void insert(City city, Connection conexao, PreparedStatement pst) throws SQLException;

    public int update(City city, Connection conexao, PreparedStatement pst) throws SQLException;

    public void delete(City city, Connection conexao, PreparedStatement pst) throws SQLException;

    //public ResultSet select(Connection conexao, PreparedStatement pst) throws SQLException;

    //public ResultSet selectId(City city, Connection conexao, PreparedStatement pst) throws SQLException;

}
