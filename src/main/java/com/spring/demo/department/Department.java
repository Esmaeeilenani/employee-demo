package com.spring.demo.department;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.demo.employee.Employee;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @JsonIgnore
    private Employee manager;


    @ManyToMany
    @JoinTable(name = "department_employee",
            joinColumns = {@JoinColumn(name = "dep_id")},
            inverseJoinColumns = {@JoinColumn(name = "emp_id")}

    )
    private Set<Employee> employees = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
