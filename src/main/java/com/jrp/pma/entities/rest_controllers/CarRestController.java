package com.jrp.pma.entities.rest_controllers;

import com.jrp.pma.dao.CarRepository;
import com.jrp.pma.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/app-api/car")
public class CarRestController {

    @Autowired
    CarRepository carRepository;

    @GetMapping
    public Iterable<Car> getCars() {
        return carRepository.findAll();
    }

    @GetMapping(params = {"page" , "size"})
    @ResponseStatus(HttpStatus.OK)
    public Iterable<Car> findPaginatedEmployees(@RequestParam("page") int page,
                                                @RequestParam("size") int size) {
        Pageable pageAndSize = PageRequest.of(page, size);

        return carRepository.findAll(pageAndSize) ;
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable long id) {
        return carRepository.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Car saveCar(@RequestBody @Valid Car car) {
        return carRepository.save(car);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Car patchCar(@PathVariable long id, @RequestBody Car car) {
        Car selectedCar = carRepository.findById(id).get();

        if (car.getModel() != null) {
            selectedCar.setModel(car.getModel());
        }
        if (car.getHorsePower() != 0) {
            selectedCar.setHorsePower(car.getHorsePower());
        }
        if (car.getColor() != null) {
            selectedCar.setColor(car.getColor());
        }
        if (car.getTheEmployee() != null) {
            selectedCar.setTheEmployee(car.getTheEmployee());
        }

        return selectedCar;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable long id) {
        try{
            carRepository.deleteById(id);
        }catch (EmptyResultDataAccessException e ){

        }
    }
}
