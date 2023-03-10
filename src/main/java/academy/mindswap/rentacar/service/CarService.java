package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.dto.CarCreateDto;
import academy.mindswap.rentacar.dto.CarDto;
import academy.mindswap.rentacar.model.Car;


import java.util.List;

public interface CarService {

    CarDto createCar(CarCreateDto carCreateDto);

    CarDto getCarById(Long carId);

    List<CarDto> getAllCars();

    CarDto updateCar(Long carId, CarDto carDto);

    void deleteCar(Long carId);
}
