package com.spring.demo.employee;

import com.spring.demo.role.Role;
import com.spring.demo.role.RoleConstant;
import com.spring.demo.role.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;


    public EmployeeService(EmployeeRepository employeeRepository, RoleRepository roleRepository) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
    }

    public void saveEmployee(AddEmployeeRequest addEmployeeRequest) {
        Long loggedInUserId = addEmployeeRequest.getUserId();
        Employee newEmployee = addEmployeeRequest.getEmployee();
        Employee currentUser = employeeRepository
                .findById(loggedInUserId)
                .orElseThrow(() -> new IllegalArgumentException("user with id " + loggedInUserId + " is not found"));


        if (currentUser.getRole() == null || !currentUser.getRole().getName().equals(RoleConstant.CEO)) {
            throw new IllegalArgumentException("not CEO to Add new User");
        }

        Role empRole = roleRepository
                .findByName(addEmployeeRequest.getRoleName().toUpperCase().trim())
                .orElseThrow(() -> new IllegalArgumentException("Role name " + addEmployeeRequest.getRoleName() + " is not exists"));

        newEmployee.setRole(empRole);

        this.employeeRepository.save(newEmployee);
    }

    public void saveEmployees(List<Employee> employees) {
        this.employeeRepository.saveAll(employees);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

}
