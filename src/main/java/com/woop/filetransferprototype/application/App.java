/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.application;

import com.woop.filetransferprototype.application.config.ApplicationConfig;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.URI;
import java.sql.SQLException;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

//import org.sqlite.SQLite
/**
 *
 * @author NoID
 */
public class App {
    
    private static URI BASE_URI;
    private static String DATABASE_URI;
    private static ResourceConfig getConfig() {
            return new ApplicationConfig();
    
    }
    
    public static void main(String[] args) throws IOException, InterruptedException, SQLException {     
        
        BASE_URI = URI.create("http://" + Inet4Address.getLocalHost().getHostAddress() +  ":8080/app/");
        DATABASE_URI = "jdbc:sqlite:D:/NetBeans 8.2/projects/FileTransferPrototype/src/main/resources/FileTransferDataBase.db";
        System.out.println("App is starting....");
        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI,getConfig());
        //HttpServer server2 = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, ContainerFactory.createContainer(HttpHandler.class,getConfig()), true, new SSLEngineConfigurator(sslCon));
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    server.shutdownNow();
                }
            }));
        server.start();  
        System.out.println("App started....");   
        
    }       
}
