package com.jm.model;
import java.util.Date;



public class Employee extends AbsModel {
   
    private String firstName;
    private String lastName;
    private Long manager_id;
    private Date createDate;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setManager_id(Long manager_id) {
        this.manager_id = manager_id;
    }

    public Long getManager_id() {
        return manager_id;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    


    

   
    
    

   

}
