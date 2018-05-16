/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmemes.Model.SQL;

import com.southbrmemes.Controller.RestInterface.ICity;
import com.southbrmemes.Model.Entity.City;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author joao.vrevangelista
 */
public class CitySQL implements ICity {
    
    @Override
    public void insert(City city, Connection conexao, PreparedStatement pst) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("insert into city(city) values (?)");

        pst = conexao.prepareStatement(sql.toString());

        pst.setString(1, city.getCity());
        

        pst.execute();
    }
    
    @Override
    public int update(City city, Connection conexao, PreparedStatement pst) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("update city set city = ?");
        sql.append(" where id = ? and active = true");

        pst = conexao.prepareStatement(sql.toString());

        pst.setString(1, city.getCity());
        pst.setInt(2, city.getId());
        

        return pst.executeUpdate();
    }
    
    @Override
    public void delete(City city, Connection conexao, PreparedStatement pst) throws SQLException {

        StringBuilder sql = new StringBuilder();
        sql.append("update city set active = false");
        sql.append(" where id = ?");

        pst = conexao.prepareStatement(sql.toString());

        pst.setInt(1, city.getId());
        
        pst.execute();
    }
}
    
     
