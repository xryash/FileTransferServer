/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.cryptography;

import java.security.SecureRandom;
import java.util.Locale;

/**
 *
 * @author NoID
 */
public class RandomString {
    
    public static String generateRandomString(int length) {
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = upper.toLowerCase(Locale.ROOT);
        String digits = "1234567890";
        String symbols = upper + lower + digits;
        char[] symbolsArray = symbols.toCharArray();
        char[] buff = new char[length];
        SecureRandom rnd = new SecureRandom();
        for (int i = 0; i< buff.length; ++i) {
            buff[i] = symbolsArray[rnd.nextInt(symbolsArray.length)];
        }
        return new String(buff);
    }
}
