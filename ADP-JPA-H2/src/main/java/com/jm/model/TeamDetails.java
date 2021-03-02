package com.jm.model;

import java.util.Date;
public class TeamDetails extends AbsModel{
    private String teamName;
    private Date createDate;
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    
    public TeamDetails() {}

    

}
