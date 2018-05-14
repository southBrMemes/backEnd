package com.southbrmemes.Model.Connet;

import java.sql.*;

/*
*esta entidade tem como objetivo estabelecer conexao com o banco de dados
 */
public class Connection {

    public static java.sql.Connection conector() {
        Connection conexao = null;

        String driver = "";
        String url = "";
        String user = "";
        String password = "";

        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            return null;
        }
    }
}
