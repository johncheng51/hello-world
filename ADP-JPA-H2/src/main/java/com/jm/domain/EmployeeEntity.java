package com.jm.domain;

import java.util.Date;
import javax.persistence.*;

@Entity(name="Employee")
@Table(name = "Employee")
public class EmployeeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", unique = true,nullable = false)
    private Long id;

    @Column(name = "employee_first_name", length = 30, nullable = false, unique = true)
    private String firstName;

    @Column(name = "employee_last_name", length = 30, nullable = false, unique = true)
    private String lastName;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "manager_id", nullable = true)
    @org.hibernate.annotations.ForeignKey(name = "fk_manager_id")
    private EmployeeEntity manager;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_id", nullable = true)
    @org.hibernate.annotations.ForeignKey(name = "fk_team_id")
    private TeamDetailsEntity team;

    public void setTeam(TeamDetailsEntity team) {
        this.team = team;
    }

    public TeamDetailsEntity getTeam() {
        return team;
    }

    public void setManager(EmployeeEntity manager) {
        this.manager = manager;
    }

    public EmployeeEntity getManager() {
        return manager;
    }

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

   
    
    public EmployeeEntity() {}

   

}
