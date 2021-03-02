package com.jm.domain;

import java.util.Date;
import javax.persistence.*;

@Entity(name="ProjectDetails")
@Table(name = "ProjectDetails")
public class ProjectDetailsEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id", unique = true)
    private Long id;

    @Column(name = "project_name", length = 30, nullable = false, unique = true)
    private String projectName;
    
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "project_start_date",nullable = false)
    private Date projectStartDate;

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_project_id", nullable = true)
    @org.hibernate.annotations.ForeignKey(name = "fk_parent_project_id")
    private ProjectDetailsEntity parentProject;


    public void setParentProject(ProjectDetailsEntity parentProject) {
        this.parentProject = parentProject;
    }

    public ProjectDetailsEntity getParentProject() {
        return parentProject;
    }

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

    

    @PrePersist
    protected void onCreate() {
        createDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        createDate = new Date();
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public ProjectDetailsEntity() {}

    

}
