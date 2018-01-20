/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.local.log;

/**
 *
 * @author NoID
 */
public class Log {
    public static void log(String className, String cause) {
        System.out.println(String.format("%s : %s ", className, cause));
    }
    
    
}
