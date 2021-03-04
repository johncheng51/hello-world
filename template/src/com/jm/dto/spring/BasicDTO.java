package com.jm.dto.spring;

import com.jm.dto.AbsModel;

public class BasicDTO extends AbsModel{
   
    private String port        ="8080";
    private String pomName     ="XXXXXXX";
    private String pomVersion  ="XXXXXXX";
    private String appTitle    ="XXXXXXX";
    private String swaggerTitle="XXXXXXX";
    private String userName    ="XXXXXXX";
    private String database    ="XXXXXXX";


    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDatabase() {
        return database;
    }

    public BasicDTO(){}
    
    public void setAppTitle(String appTitle) {
        this.appTitle = appTitle;
    }

    public String getAppTitle() {
        return appTitle;
    }

    public void setSwaggerTitle(String swaggerTitle) {
        this.swaggerTitle = swaggerTitle;
    }

    public String getSwaggerTitle() {
        return swaggerTitle;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPort() {
        return port;
    }

    public void setPomName(String pomName) {
        this.pomName = pomName;
    }

    public String getPomName() {
        return pomName;
    }

    public void setPomVersion(String pomVersion) {
        this.pomVersion = pomVersion;
    }

    public String getPomVersion() {
        return pomVersion;
    }
    
    
    
    

    
}

