/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.application;

import com.woop.filetransferprototype.application.config.ApplicationConfig;
import com.woop.filetransferprototype.cryptography.SHA256;
import java.io.IOException;
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
    
    private static final URI BASE_URI = URI.create("http://localhost:8080/app/"); 
    //private static final String DATABASE_URI = "jdbc:sqlite:" + System.getProperty("user.dir" +"/src/main/resources/FileTransferDataBase.db");
    private static final String DATABASE_URI = "jdbc:sqlite:D:/NetBeans 8.2/projects/FileTransferPrototype/src/main/resources/FileTransferDataBase.db";
    private static ResourceConfig getConfig() {
        return new ApplicationConfig();
    
    }
    
    
    
    
    public static void main(String[] args) throws IOException, InterruptedException, SQLException {
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
        //Thread.sleep(1000);
        //server.shutdown();
        
        
    }
           
}
