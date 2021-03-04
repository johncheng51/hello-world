package com.jm.dto.spring;

import com.jm.dto.AbsModel;

public class EntityDTO extends AbsModel{
   
    private String lname       ="XXXXXXX";
    private String uname       ="XXXXXXX";

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getLname() {
        return lname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUname() {
        return uname;
    }


}

