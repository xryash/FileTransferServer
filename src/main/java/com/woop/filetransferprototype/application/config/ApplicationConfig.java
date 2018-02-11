/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.woop.filetransferprototype.application.config;

import com.woop.filetransferprototype.web.account.exceptions.AccountExceptionMapper;
import com.woop.filetransferprototype.web.account.resources.AccountCreateResource;
import com.woop.filetransferprototype.web.account.resources.AccountVerifyResource;
import com.woop.filetransferprototype.web.fileupload.exceptions.FileUploadExceptionMapper;
import com.woop.filetransferprototype.web.account.resources.ExtraResource;
import com.woop.filetransferprototype.web.fileupload.resources.FileUploadResource;
import com.woop.filetransferprototype.web.provider.AuthenticationFilter;
import com.woop.filetransferprototype.web.provider.LogFilter;
import com.woop.filetransferprototype.web.provider.exceptions.ProviderExceptionMapper;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

/**
 *
 * @author NoID
 */

public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig() {
        super(FileUploadResource.class,
                AccountCreateResource.class,
                AccountVerifyResource.class,
                ExtraResource.class,
                LogFilter.class,
                RolesAllowedDynamicFeature.class,
                FileUploadExceptionMapper.class,
                AccountExceptionMapper.class,
                ProviderExceptionMapper.class,
                MultiPartFeature.class,
                AuthenticationFilter.class
        
        );
    }
    
    
}
