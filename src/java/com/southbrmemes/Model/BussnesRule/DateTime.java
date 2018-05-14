/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmemes.Model.BussnesRule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kayque Rodrigues
 */
public class DateTime {

    public static boolean compareData(String dataSql) {
        if (dataSql.equals(dataSistema())) {
            return true;
        } else {
            return false;
        }
    }

    public static String dataSistema() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(new Date(System.currentTimeMillis()));
    }

    public static String converterDataDoBanco(Date data) {
        if (data == null) {
            data = new Date(System.currentTimeMillis());
        }
        SimpleDateFormat formatoDesejado = new SimpleDateFormat("dd/MM/yyyy");
        return formatoDesejado.format(data);
    }
    
    public static Long getDateLong(String data){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date date = null;
        try {
            return format.parse(data).getTime();
             }catch (ParseException e) {
            return 0l;
        }
    }

    public static String dia(String data) {
        return data.substring(0, 2);
    }

    public static String mes(String data) {
        return data.substring(3, 5);
    }

    public static String ano(String data) {
        return data.substring(6, 10);
    }

    public static String dataBanco(String data) {
        return ano(data)+"-"+mes(data)+"-"+dia(data);
    }
}
