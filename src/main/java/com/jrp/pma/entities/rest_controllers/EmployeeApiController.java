package com.jrp.pma.entities.rest_controllers;

import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public Iterable<Employee> getEmployees() {
       return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") long id ){
        return employeeRepository.findById(id).get() ;
    }

    @PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody @Valid Employee employee){
        return employeeRepository.save(employee);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@RequestBody @Valid Employee employee){
        return employeeRepository.save(employee) ;
    }

    @PatchMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee patchEmployee(@RequestBody @Valid Employee employee , @PathVariable long id){
        Employee chosenEmployee = employeeRepository.findById(id).get() ;
        if (employee.getFirstName() != null){
            chosenEmployee.setFirstName(employee.getFirstName());
        }
        if (employee.getLastName() != null){
            chosenEmployee.setLastName(employee.getLastName());
        }
        if (employee.getAge() != 0 ){
            chosenEmployee.setAge(employee.getAge());
        }
        if (employee.getEmail() != null ){
            chosenEmployee.setEmail(employee.getEmail());
        }
        if (employee.getCars() != null ){
            chosenEmployee.setCars(employee.getCars());
        }
        return employeeRepository.save(chosenEmployee);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable long id){
        try{
            employeeRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e ){
            System.out.println(e);
        }
    }
}
