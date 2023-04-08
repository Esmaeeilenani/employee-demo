package com.spring.demo;

import com.spring.demo.department.Department;
import com.spring.demo.department.DepartmentRepository;
import com.spring.demo.employee.Employee;
import com.spring.demo.employee.EmployeeRepository;
import com.spring.demo.role.Role;
import com.spring.demo.role.RoleConstant;
import com.spring.demo.role.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);


    }


    @Bean
    CommandLineRunner runner(RoleRepository roleRepository, EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        return arg -> {
            Role CEO = roleRepository.save(new Role(RoleConstant.CEO));
            roleRepository.save(new Role(RoleConstant.MANAGER));
            roleRepository.save(new Role(RoleConstant.EMPLOYEE));

            Employee employee = new Employee();
            employee.setName("ahmad");
            employee.setNin("1234567890");
            employee.setRole(CEO);
            employeeRepository.save(employee);



            Role manager = roleRepository.findByName(RoleConstant.MANAGER).get();
            Employee employee2 = new Employee();
            employee2.setName("ali");
            employee2.setNin("1234567891");
            employee2.setRole(manager);
            employeeRepository.save(employee2);



            Role Emp = roleRepository.findByName(RoleConstant.EMPLOYEE).get();
            Employee employee3 = new Employee();
            employee3.setName("omar");
            employee3.setNin("1234567892");
            employee3.setRole(Emp);
            employeeRepository.save(employee3);




            Department department = new Department();
            department.setName("it");
            department.setManager(employee2);
            department.getEmployees().add(employee3);
            departmentRepository.save(department);









        };
    }


}
