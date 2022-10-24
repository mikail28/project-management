package com.jrp.pma.dao;

import com.jrp.pma.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CarRepository extends PagingAndSortingRepository<Car , Long> {

    @Override
    List<Car> findAll() ;
}
