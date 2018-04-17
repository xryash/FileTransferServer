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
public class LocalStorageFileUploadHandler implements IFileUploadHandler {

    private final RootPathProvider rootPathProvider;
    private final IFileRepository fileRepository;
    private final IAccountRepository accountRepository;

    public LocalStorageFileUploadHandler() {
        this.rootPathProvider = new RootPathProvider();
        this.fileRepository = new LocalStorageFileRepository();
        this.accountRepository = new LocalStorageAccountRepository();
    }

    public FileUploadResponse handle(FileUploadRequest request) {
        System.out.println("Запрос обрабатывается");

        if (request == null) {
            throw new FileUploadException(new ServiceError("missingFile", "Missing File data request"), String.format("Missing Parameter: request"));
        }

        String login = request.getLogin();

        if (login == null) {
            throw new FileUploadException(new ServiceError("missingFile", "Missing File data request.login"), String.format("Missing Parameter: request.login"));
        }

        final HttpFile httpFile = request.getHttpFile();

        verifyHttpFile(httpFile);

        final String targetFileName = UUID.randomUUID().toString();

        Account account = accountRepository.getByLogin(login);
        int host = account.getId();

        LocalFile localFile = new LocalFile(host, targetFileName, httpFile.getSubmittedFileName(), httpFile.getDirectory());

        if (!fileRepository.save(localFile)) {
            throw new FileUploadException(new ServiceError("savingFileError", "Error saving"), String.format("Saving File failed"));
        }

        internalWriteFile(httpFile.getStream(), targetFileName);

        return new FileUploadResponse(targetFileName);
    }

    private void verifyHttpFile(HttpFile httpFile) {

        if (httpFile == null) {
            throw new FileUploadException(new ServiceError("missingFile", "Missing File data request.httpFile"), String.format("Missing Parameter: request.httpFile"));
        }

        String directory = httpFile.getDirectory();
        long size = httpFile.getSize();
        String submittedFileName = httpFile.getSubmittedFileName();
        InputStream stream = httpFile.getStream();

        if (directory == null) {
            throw new FileUploadException(new ServiceError("missingFile", "Missing File data httpFile.directory"), String.format("Missing Parameter: httpFile.directory"));
        }

        if (size <= 0) {
            throw new FileUploadException(new ServiceError("missingFile", "Missing File data httpFile.size"), String.format("Missing Parameter: httpFile.size"));
        }

        if (submittedFileName == null) {
            throw new FileUploadException(new ServiceError("missingFile", "Missing File data httpFile.submittedFileName"), String.format("Missing Parameter: httpFile.submittedFileName"));
        }

        if (stream == null) {
            throw new FileUploadException(new ServiceError("missingFile", "Missing File data httpFile.stream"), String.format("Missing Parameter: httpFile.stream"));
        }
    }

    private void internalWriteFile(InputStream stream, String fileName) {
        try {

            Files.copy(stream, Paths.get(rootPathProvider.getRootPath(), fileName));
            //Files.copy(stream, target, options)

        } catch (Exception e) {
            throw new FileUploadException(new ServiceError("storingFileError", "Error writing file"), String.format("Writing File failed"));
        }
    }

}
