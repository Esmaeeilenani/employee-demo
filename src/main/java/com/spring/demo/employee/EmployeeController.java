package com.spring.demo.employee;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {




    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping
    public ResponseEntity<List<Employee>> getEmployee() {
        return ResponseEntity.ok(employeeService.getAllEmployees());

    }

//    @GetMapping("{id}")
//    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
//        return ResponseEntity.ok(employees.stream().filter(e -> e.getId().equals(id)).findFirst().get());
//
//    }

    @PostMapping
    public ResponseEntity<Void> addEmployee(@RequestBody @Valid AddEmployeeRequest addEmployeeRequest) {
        this.employeeService.saveEmployee(addEmployeeRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("batch")
    public ResponseEntity<Void> addEmployees(@RequestBody List<Employee> employees) {
        this.employeeService.saveEmployees(employees);
        return ResponseEntity.ok().build();
    }


}
