package com.spring.demo.employee;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.demo.department.Department;
import com.spring.demo.role.Role;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 10)
    private String nin;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;

    @ManyToMany(mappedBy = "employees")
    @JsonIgnore
    private Set<Department> departments = new HashSet<>();

    @OneToMany(mappedBy = "manager")
    private List<Department> managedDepartments;

    public Employee() {

    }

    public Employee(Long id, String nin, String name) {
        this.id = id;
        this.nin = nin;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNin() {
        return nin;
    }

    public void setNin(String nin) {
        this.nin = nin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<Department> departments) {
        this.departments = departments;
    }


    public List<Department> getManagedDepartments() {
        return managedDepartments;
    }

    public void setManagedDepartments(List<Department> managedDepartments) {
        this.managedDepartments = managedDepartments;
    }
}
