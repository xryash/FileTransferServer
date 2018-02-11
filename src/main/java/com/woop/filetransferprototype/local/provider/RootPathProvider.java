/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.local.provider;

/**
 *
 * @author NoID
 */
public class RootPathProvider implements IRootPathProvider{
    private final String ROOT_PATH = "d:\\out\\";
    private final String path;

    public RootPathProvider() {
        this.path = ROOT_PATH;
    }

    @Override
    public String getRootPath() {
        return path;
    }
    
}
