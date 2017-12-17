/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.web.fileupload.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author NoID
 */
public class FileUploadResponse {
    private final String identifier;

    public FileUploadResponse(String identifier) {
        this.identifier = identifier;
    }

    @JsonProperty("identifier")
    public String getIdentifier() {
        return identifier;
    }
}
