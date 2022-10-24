package com.jrp.pma.controllers;

import com.jrp.pma.dao.CarRepository;
import com.jrp.pma.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    CarRepository carRepository ;

    @GetMapping
    public String deployCarList(Model model){
       List<Car> carList = carRepository.findAll() ;
       model.addAttribute("cars" , carList );
       return "car/list-car" ;
    }

    @GetMapping("/new")
    public String deployCarForm(Model model){
        Car car = new Car() ;
        model.addAttribute("car" , car );
        return "/car/new-car" ;
    }

    @PostMapping("/save")
    public String saveCar(Model model , Car car ){
        carRepository.save(car);


        return "redirect:/cars/new" ;
    }

}
