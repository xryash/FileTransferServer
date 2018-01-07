/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.cryptography;

/**
 *
 * @author NoID
 */
public class Token {
    public static String generateToken(String password,String salt) {
        String codedPassword = SHA256.hash(password);
        String codedSalt = SHA256.hash(salt);
        String token = SHA256.hash(codedPassword + codedSalt);
        return token;
    }
    
}
