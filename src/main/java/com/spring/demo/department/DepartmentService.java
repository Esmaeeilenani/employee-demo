package com.spring.demo.department;

import com.spring.demo.employee.Employee;
import com.spring.demo.employee.EmployeeService;
import com.spring.demo.role.RoleConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final EmployeeService employeeService;


    public Department findDepartmentById(Long id) {

        return departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("""
                        department with id %s is not found
                        """.formatted(id)));
    }

    public DepartmentService(DepartmentRepository departmentRepository, EmployeeService employeeService) {
        this.departmentRepository = departmentRepository;
        this.employeeService = employeeService;
    }


    public void addNewDepartment(DepartmentRequest departmentRequest) {

        employeeService.validateUserIsCEO(departmentRequest.getUserId());

        if (departmentRequest.getDepartment() == null) {
            throw new IllegalArgumentException("must add department information");
        }


        departmentRepository.save(departmentRequest.getDepartment());


    }

    public void updateDeptManager(Long departmentId, LinkDepartmentEmployeeRequest linkDepartmentEmployeeRequest) {
        employeeService.validateUserIsCEO(linkDepartmentEmployeeRequest.getUserId());

        Employee manager = employeeService.validateUserIsManager(linkDepartmentEmployeeRequest.getEmployeeId());

        Department department = findDepartmentById(departmentId);

        department.setManager(manager);
        departmentRepository.save(department);
    }

    public void addEmployee(Long id, LinkDepartmentEmployeeRequest linkDepartmentEmployeeRequest) {
        Employee currentUser = employeeService.getEmployee(linkDepartmentEmployeeRequest.getUserId());

        if (currentUser.getRole().getName().equals(RoleConstant.EMPLOYEE)) {
            throw new IllegalArgumentException("you are not manager to change employee department");
        }

        Department department = findDepartmentById(id);

        if (currentUser.getRole().getName().equals(RoleConstant.MANAGER) && !department.getManager().getId().equals(currentUser.getId())) {
            throw new IllegalArgumentException("not department manager to add employee");
        }

        Employee employee = employeeService.getEmployee(linkDepartmentEmployeeRequest.getEmployeeId());


        department.getEmployees().add(employee);

        departmentRepository.save(department);


    }
}
