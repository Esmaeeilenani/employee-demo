package com.spring.demo.department;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @PostMapping
    public ResponseEntity<Void> addDepartment(@RequestBody @Valid DepartmentRequest departmentRequest) {
        departmentService.addNewDepartment(departmentRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .build();
    }


    @PutMapping("{id}/update-manager")
    public ResponseEntity<Void> setDepartmentManager(@PathVariable Long id, @RequestBody LinkDepartmentEmployeeRequest linkDepartmentEmployeeRequest) {

        departmentService.updateDeptManager(id, linkDepartmentEmployeeRequest);

        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("{id}/add-employee")
    public ResponseEntity<Void> addEmployee(@PathVariable Long id, @RequestBody LinkDepartmentEmployeeRequest linkDepartmentEmployeeRequest) {

        departmentService.addEmployee(id, linkDepartmentEmployeeRequest);

        return ResponseEntity.ok()
                .build();
    }




}
