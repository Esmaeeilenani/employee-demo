package com.spring.demo.department;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.demo.employee.EmployeeDTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DepartmentDTO implements Serializable {


    private Long id;

    private String name;


    private EmployeeDTO manager;

    private Set<EmployeeDTO> employees = new HashSet<>();


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

    public EmployeeDTO getManager() {
        return manager;
    }

    public void setManager(EmployeeDTO manager) {
        this.manager = manager;
    }

    public Set<EmployeeDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<EmployeeDTO> employees) {
        this.employees = employees;
    }

    public static DepartmentDTO toDepartmentDTO(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
                dto.setId(department.getId());
                dto.setName(department.getName());

        return dto;


    }

    public static Set<DepartmentDTO> toDepartmentDTOSet(Set<Department> departments) {
        return departments.stream()
                .map(DepartmentDTO::toDepartmentDTO)
                .collect(Collectors.toSet());
    }
    public static List<DepartmentDTO> toDepartmentDTOList(List<Department> departments) {
        return departments.stream()
                .map(DepartmentDTO::toDepartmentDTO)
                .toList();
    }


}
