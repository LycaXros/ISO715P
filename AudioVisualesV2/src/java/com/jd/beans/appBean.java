/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jd.beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import utils.MyUtil;

/**
 *
 * @author Jesus Dicent
 */
@ManagedBean
@ApplicationScoped
public class appBean {

    public appBean() {
    }

    public String getBaseUrl()
    {
        return MyUtil.baseUrl();
    }
    public String getBasePath()
    {
        return MyUtil.basePath();
    }
    public String getResourcePath(){
        return MyUtil.resourcePath();
    }
}