package com.jrp.pma.controllers;

import com.jrp.pma.dao.CarRepository;
import com.jrp.pma.dao.EmployeeRepository;
import com.jrp.pma.entities.Car;
import com.jrp.pma.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository ;

    @Autowired
    CarRepository carRepository;

    @GetMapping("")
    public String displayEmployeeList(Model model){
        List<Employee> employeeList = employeeRepository.findAll() ;
        model.addAttribute("employees" , employeeList );
        return "employee/list-employee";
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model){
        Employee employee = new Employee() ;
        List<Car> cars = carRepository.findAll() ;
        model.addAttribute("employee", employee );
        model.addAttribute("AllCars" , cars);
        return "employee/new-employee" ;
    }

    @PostMapping("/save")
    public String saveEmployee(@Valid Employee employee, @RequestParam(required = false) List<Long> cars ){
        employeeRepository.save(employee) ;
        if (cars != null) {
            Iterable<Car> chosenCars = carRepository.findAllById(cars);

            for (Car car : chosenCars ) {
                car.setTheEmployee(employee);
                carRepository.save(car);
            }
        }
        return "redirect:/employees/new" ;
    }

}
