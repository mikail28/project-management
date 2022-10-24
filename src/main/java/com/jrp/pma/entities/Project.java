package com.jrp.pma.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;
    private String name ;
    private String stage ;
    private String description ;

//    @OneToMany(mappedBy = "theProject")
@ManyToMany(cascade = {CascadeType.REFRESH , CascadeType.PERSIST , CascadeType.MERGE , CascadeType.DETACH},
        fetch =  FetchType.LAZY)
@JoinTable(name = "project_employee",
        joinColumns = @JoinColumn(name = "project_id"),
        inverseJoinColumns = @JoinColumn(name = "employee_id"))
@JsonIgnore
    private List<Employee> employees ;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Project(){

    }

    public Project(String name, String stage, String description) {
        this.name = name;
        this.stage = stage;
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStage() {
        return stage;
    }

    public String getDescription() {
        return description;
    }
}
