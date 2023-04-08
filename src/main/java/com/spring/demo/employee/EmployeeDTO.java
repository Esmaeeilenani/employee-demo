package com.spring.demo.employee;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.demo.department.DepartmentDTO;
import com.spring.demo.role.Role;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmployeeDTO implements Serializable {

    private Long id;


    private String nin;


    private String name;


    private Role role;


    private Set<DepartmentDTO> departments;


    private List<DepartmentDTO> managedDepartments;

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

    public Set<DepartmentDTO> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentDTO> departments) {
        this.departments = departments;
    }

    public List<DepartmentDTO> getManagedDepartments() {
        return managedDepartments;
    }

    public void setManagedDepartments(List<DepartmentDTO> managedDepartments) {
        this.managedDepartments = managedDepartments;
    }

    public static EmployeeDTO toEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setNin(employee.getNin());
        employeeDTO.setName(employee.getName());
        employeeDTO.setRole(employee.getRole());
        return  employeeDTO;

    }
    public static List<EmployeeDTO> toEmployeeDTO(List <Employee> employee){
        return employee.stream()
                .map(EmployeeDTO::toEmployeeDTO)
                .toList();

    }




}
