/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.fileupload.hadler;

import com.woop.filetransferprototype.web.fileupload.requests.FileUploadRequest;
import com.woop.filetransferprototype.web.fileupload.responses.FileUploadResponse;

/**
 *
 * @author NoID
 */
public interface IFileUploadHandler {
        FileUploadResponse handle(FileUploadRequest request);
}
