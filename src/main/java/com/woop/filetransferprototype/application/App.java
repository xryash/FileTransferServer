/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.application;

import com.woop.filetransferprototype.application.config.ApplicationConfig;
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
    
    private static final URI BASE_URI = URI.create("http://192.168.1.7:8080/app/");
    private static final String DATABASE_URI = "jdbc:sqlite:D:/NetBeans 8.2/projects/FileTransferPrototype/src/main/resources/FileTransferDataBase.db";
    private static ResourceConfig getConfig() {
            return new ApplicationConfig();
    
    }
    
    public static void main(String[] args) throws IOException, InterruptedException, SQLException {
            /*
            String username = "vasya";
            String password = "123";
            System.out.println(username);
            System.out.println(password);
            String authorization = username + ":" + password;
            System.out.println(authorization);
            String coded =  "Basic " + new String(Base64.encode(authorization.getBytes()));
            System.out.println(coded);
            String encodedUserPassword = coded.replaceFirst("Basic"+ " ", "");
            String usernameAndPassword = new String(Base64.decode(encodedUserPassword.getBytes()));;
            final StringTokenizer tokenizer = new StringTokenizer(usernameAndPassword, ":");
            username = tokenizer.nextToken();
            password = tokenizer.nextToken();
            System.out.println(username);
            System.out.println(password);
            **/
        
        
        /**
        String login = "igoresha";
        String password = "1234";
        String salt = RandomString.generateRandomString(60);
        String token = Token.generateToken(password,salt);
        String role = "ADMIN";
        Account account = new Account(login,password,token,salt,role);
        System.out.println(account.toString());
        LocalStorageAccountRepository repository = new LocalStorageAccountRepository();
        if (repository.remove("igoresha"))
            System.out.println("Аккаунт удалён");   
        if (repository.save(account))
            System.out.println("Аккаунт сохранён");
        
        System.out.println("getByLogin" + repository.getByLogin(login).toString());
        System.out.println("getByToken" + repository.getByToken(token).toString());
        System.out.println("getByLoginAndPassword" + repository.getByLoginAndPassword(login, password).toString());
        */
        
        
        
        
        
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
