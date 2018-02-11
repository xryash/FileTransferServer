/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.filedownload.responses;

import com.woop.filetransferprototype.local.entity.LocalFile;
import java.io.File;

/**
 *
 * @author NoID
 */
public class FileDownloadResponse {
    private final LocalFile localFile;
    private final File file;

    public FileDownloadResponse(LocalFile localFile, File file) {
        this.localFile = localFile;
        this.file = file;
    }

    public LocalFile getLocalFile() {
        return localFile;
    }

    public File getFile() {
        return file;
    }
    
    
            
}
