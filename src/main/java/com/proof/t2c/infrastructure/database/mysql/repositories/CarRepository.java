package com.proof.t2c.infrastructure.database.mysql.repositories;

import com.proof.t2c.domain.entities.Car;
import com.proof.t2c.domain.filters.SortFilter;
import com.proof.t2c.domain.usecases.cars.*;
import com.proof.t2c.infrastructure.database.mysql.repositories.jpa.JpaCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class CarRepository extends BaseRepository implements
    CreateCarUseCase.CarRepository,
    GetCarUseCase.CarRepository,
    DeleteCarUseCase.CarRepository,
    UpdateCarUseCase.CarRepository,
    FindCarsUseCase.CarRepository {

    @Autowired
    private JpaCarRepository jpaCarRepository;

    @Override
    public Car getCarById(Integer id) {
        if (id == null) {
            return null;
        }
        Optional<Car> carOptional = this.jpaCarRepository.findById(id);
        return carOptional.isPresent() ? carOptional.get() : null;
    }

    @Override
    public void deleteCar(Car car) {
        if (car == null) {
            return;
        }
        this.jpaCarRepository.delete(car);
    }

    @Override
    public Car saveCar(Car car) {
        this.preSave(car);
        return this.jpaCarRepository.save(car);
    }

    @Override
    public List<Car> findCars(SortFilter sortFilter) {
        String sortBy = sortFilter.getSortBy();
        Sort.Direction sortDirection = Sort.Direction.ASC.name().equals(sortFilter.getSortDirection()) ? Sort.Direction.ASC : Sort.Direction.DESC;
        return this.jpaCarRepository.findAll((Sort.by(sortDirection, sortBy)));
    }

}
