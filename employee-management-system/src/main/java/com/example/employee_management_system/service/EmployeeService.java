package com.example.employee_management_system.service;

import com.example.employee_management_system.domain.Employee;
import com.example.employee_management_system.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public void saveEmployee(Employee emp){
        employeeRepository.save(emp);
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with ID!"));

    }

    public String deleteEmployeeById(Long id){
        Optional<Employee> emp = employeeRepository.findById(id);
        if(emp.isEmpty()){
            throw new RuntimeException("User not found!");
        }
        else {
            employeeRepository.deleteById(id);
            return "Deleted: " + emp.get().getFirstName() + " " + emp.get().getLastName();
        }

    }


}
