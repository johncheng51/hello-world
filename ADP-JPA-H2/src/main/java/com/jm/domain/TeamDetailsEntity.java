package com.jm.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

@Entity(name="TeamDetails")
@Table(name = "TeamDetails")
public class TeamDetailsEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id", unique = true,nullable = false)
    private Long id;

    @Column(name = "team_name", length = 30, nullable = false, unique = true)
    private String teamName;
    
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false, updatable = false)
    @org.hibernate.annotations.ForeignKey(name = "FK_project_id")
    private ProjectDetailsEntity projectDetailsEntity;
    
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "team")
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private Set<EmployeeEntity> employees = new HashSet<EmployeeEntity>();


    public void setEmployees(Set<EmployeeEntity> employees) {
        this.employees = employees;
    }

    public Set<EmployeeEntity> getEmployees() {
        return employees;
    }

    public void setProjectDetailsEntity(ProjectDetailsEntity projectDetailsEntity) {
        this.projectDetailsEntity = projectDetailsEntity;
    }

    public ProjectDetailsEntity getProjectDetailsEntity() {
        return projectDetailsEntity;
    }

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

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createDate;

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

    
    public TeamDetailsEntity() {}

    

}
