/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.fileupload.hadler;

import com.woop.filetransferprototype.web.fileupload.requests.FileUploadRequest;
import com.woop.filetransferprototype.web.fileupload.responses.FileUploadResponse;
import com.woop.filetransferprototype.local.entity.HttpFile;
import com.woop.filetransferprototype.errors.ServiceError;
import com.woop.filetransferprototype.local.entity.Account;
import com.woop.filetransferprototype.local.entity.LocalFile;
import com.woop.filetransferprototype.web.fileupload.exceptions.FileUploadException;
import com.woop.filetransferprototype.local.provider.RootPathProvider;
import com.woop.filetransferprototype.local.sql.repository.IAccountRepository;
import com.woop.filetransferprototype.local.sql.repository.IFileRepository;
import com.woop.filetransferprototype.local.sql.repository.LocalStorageAccountRepository;
import com.woop.filetransferprototype.local.sql.repository.LocalStorageFileRepository;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

/**
 *
 * @author NoID
 */
public class LocalStorageFileUploadHandler implements IFileUploadHandler  {
    
   private final RootPathProvider rootPathProvider;
   private final IFileRepository fileRepository;
   private final IAccountRepository accountRepository;

    public LocalStorageFileUploadHandler(RootPathProvider rootPathProvider) {
        this.rootPathProvider = rootPathProvider;
        this.fileRepository = new LocalStorageFileRepository();
        this.accountRepository = new LocalStorageAccountRepository();
    }
    
    
    public FileUploadResponse handle(FileUploadRequest request) {
        String token = request.getToken();
        String directory = request.getDirectory();
        HttpFile httpFile = request.getHttpFile();
        Account account = accountRepository.getByToken(token);
        
        if(request == null) 
            throw new FileUploadException(new ServiceError("missingFile", "Missing File data"), String.format("Missing Parameter: request"));
        
        if(httpFile == null) 
            throw new FileUploadException(new ServiceError("missingFile", "Missing File data"), String.format("Missing Parameter: request.httpFile"));
        
        if(token == null) 
            throw new FileUploadException(new ServiceError("missingFile", "Missing File data"), String.format("Missing Parameter: request.token"));
        
        if(directory == null) 
            throw new FileUploadException(new ServiceError("missingFile", "Missing File data"), String.format("Missing Parameter: request.directory"));
        
        if (account == null)
            throw new FileUploadException(new ServiceError("verifyingTokenError", "Error verifying token"), String.format("Verifying Token failed"));
        
        String targetFileName = UUID.randomUUID().toString();
        String submittedFileName = httpFile.getSubmittedFileName();
        int host = account.getId();
        
        LocalFile localFile = new LocalFile(targetFileName,submittedFileName,host,directory);
        
        internalWriteFile(httpFile.getStream(), targetFileName);

        return new FileUploadResponse(targetFileName);
    }

    
    private void internalWriteFile(InputStream stream, String fileName) {
        try {
            Files.copy(stream, Paths.get(rootPathProvider.getRootPath(), fileName));
            //Files.copy(stream, target, options)
            
        } catch(Exception e) {
            throw new FileUploadException(new ServiceError("storingFileError", "Error writing file"), String.format("Writing File failed"));
        }
    }
    
}
