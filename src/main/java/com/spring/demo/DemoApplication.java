package com.spring.demo;

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
    CommandLineRunner runner(RoleRepository roleRepository, EmployeeRepository employeeRepository) {
        return arg -> {
            Role CEO = roleRepository.save(new Role(RoleConstant.CEO));
            roleRepository.save(new Role(RoleConstant.MANAGER));
            roleRepository.save(new Role(RoleConstant.EMPLOYEE));

            Employee employee = new Employee();
            employee.setName("ahmad");
            employee.setNin("1234567890");
            employee.setRole(CEO);
            employeeRepository.save(employee);



        };
    }


}
