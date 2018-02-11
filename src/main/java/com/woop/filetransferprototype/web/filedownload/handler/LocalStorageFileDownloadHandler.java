/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.filedownload.handler;

import com.woop.filetransferprototype.local.entity.Account;
import com.woop.filetransferprototype.local.entity.LocalFile;
import com.woop.filetransferprototype.local.provider.RootPathProvider;
import com.woop.filetransferprototype.local.sql.repository.IAccountRepository;
import com.woop.filetransferprototype.local.sql.repository.IFileRepository;
import com.woop.filetransferprototype.local.sql.repository.LocalStorageAccountRepository;
import com.woop.filetransferprototype.local.sql.repository.LocalStorageFileRepository;
import com.woop.filetransferprototype.web.filedownload.requests.FileDownloadRequest;
import java.io.File;
import java.nio.file.Files;

/**
 *
 * @author NoID
 */
public class LocalStorageFileDownloadHandler {
    
    private final RootPathProvider rootPathProvider;
    private final IFileRepository fileRepository;
    private final IAccountRepository accountRepository;
    
    public LocalStorageFileDownloadHandler() {
        this.rootPathProvider = new RootPathProvider();
        this.fileRepository = new LocalStorageFileRepository();
        this.accountRepository = new LocalStorageAccountRepository();
    }
    
    public FileDownloadResponse handle(FileDownloadRequest request) {
        
        System.out.println("Запрос обрабатывается");
        
        if(request == null) 
            throw new FileDownloadException(new ServiceError("missingData", "Missing File data"), String.format("Missing Parameter: request"));
        
        String login = request.getLogin();
        
        if(login == null) 
            throw new FileDownloadException(new ServiceError("missingData", "Missing File data"), String.format("Missing Parameter: request.login"));
        
        int fileId = request.getId();
        
        LocalFile localFile = fileRepository.getById(fileId);
        
        if (localFile == null)
            throw new FileDownloadException(new ServiceError("missingData", "Missing File data"), String.format("Missing Parameter: request.localFile"));
        
        Account account = accountRepository.getByLogin(login);
        
        if (account.getId() != localFile.getHost())
            throw new FileDownloadException(new ServiceError("AccessError", "user isnt file owner"), String.format("Access Error"));
        
        File file = new File(rootPathProvider.getRootPath() + localFile.getTargetFileName());
        
        if (file == null)
            throw new FileDownloadException(new ServiceError("MissingFile", "Missing File data"), String.format("Missing File"));
        
        return new FileDownloadResponse(localFile,file);
        
    }
    
    
}
