/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.southbrmemes.Model.BussnesRule;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.southbrmemes.Controller.AbstractClass.CreatTokenAbstract;
import com.southbrmemes.Model.Entity.Token;
import com.southbrmemes.Model.Entity.User;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Base64;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Kayque Rodrigues esta classe tem como onjetivo criar um token (JWT)
 */
public class CreatToken extends CreatTokenAbstract {

    private static final String KEY = "";

    public CreatToken() {
        super();
    }

    @Override
    public boolean codificarToken(String token) {
        JsonParser parser = new JsonParser();
        int indexof = token.indexOf(".");
        int lasttindexof = token.lastIndexOf(".");
        String tokenPayload = token.substring(indexof + 1, lasttindexof);
        String tokenDecoded = null;
        try {
            tokenDecoded = new String(DatatypeConverter.parseBase64Binary(tokenPayload), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }
        JsonObject jsonObject = parser.parse(tokenDecoded).getAsJsonObject();
        String TesteToken = token(jsonObject);
        return TesteToken.equals(token);
    }

    @Override
    public User tokemUser(String token) {
        int indexof = token.indexOf(".");
        int lasttindexof = token.lastIndexOf(".");
        String tokenPayload = token.substring(indexof + 1, lasttindexof);
        String tokenDecoded = null;
        try {
            tokenDecoded = new String(DatatypeConverter.parseBase64Binary(tokenPayload), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }
        Gson gson = new Gson();
        return gson.fromJson(tokenDecoded, User.class);
    }

    @Override
    public String header() {
        //type de token
        String typ = "jwt";
        String alg = "alg";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty(typ, "JWT");
        jsonObject.addProperty(alg, "HS256");
        Gson gson = new Gson();
        String header = gson.toJson(jsonObject);
        return Base64.getEncoder().encodeToString(header.getBytes());
    }

    @Override
    public String payload(JsonObject object) {
        Gson gson = new Gson();
        String payload = gson.toJson(object);
        return Base64.getEncoder().encodeToString(payload.getBytes());
    }

    @Override
    public String computeSignature(String baseString, String keyString) throws GeneralSecurityException, UnsupportedEncodingException {
        SecretKey secretKey = null;
        byte[] keyBytes = keyString.getBytes();
        secretKey = new SecretKeySpec(keyBytes, "HMACSHA256");
        Mac mac = Mac.getInstance("HMACSHA256");
        mac.init(secretKey);
        byte[] text = baseString.getBytes();
        return new String(Base64.getEncoder().encode(mac.doFinal(text))).trim();
    }

    @Override
    public String token(JsonObject payloadObject) {
        Gson gson = new Gson();
        String header = header();
        String payload = payload(payloadObject);
        try {
            String estrutura = header + "." + payload;
            String computeSignature = computeSignature(estrutura, KEY);
            return "Bearer " + estrutura + "." + computeSignature;
        } catch (GeneralSecurityException | UnsupportedEncodingException e) {
            return "erro ao processar";
        }
    }

    @Override
    public Token decodeToken(String token) {
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        int indexOne = token.indexOf(".");
        int indexTwo = token.lastIndexOf(".");
        String tokenjwt = null;
        try {
            tokenjwt = new String(DatatypeConverter.parseBase64Binary(token.substring(indexOne + 1, indexTwo)), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }
        JsonObject jsonObject = parser.parse(tokenjwt).getAsJsonObject();
        return gson.fromJson(jsonObject, Token.class);
    }

}
