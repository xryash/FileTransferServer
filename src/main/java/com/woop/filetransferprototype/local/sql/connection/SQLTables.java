/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.local.sql.connection;

/**
 *
 * @author NoID
 */
public class SQLTables {
    public final String SQL_ACCOUNT_TABLE = "CREATE TABLE `Accounts` (\n" +
"	`Id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
"	`Login`	TEXT NOT NULL,\n" +
"	`Password`	TEXT NOT NULL,\n" +
"	`Token`	TEXT NOT NULL,\n" +
"	`Directory`	TEXT NOT NULL\n" +
");";
    
    
    
}
