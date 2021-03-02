package com.jm.model;
import java.util.Date;




public class ProjectDetails extends AbsModel{
       

    
    private String projectName;
    private Date createDate;


    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }


    public ProjectDetails() {}

    

}
