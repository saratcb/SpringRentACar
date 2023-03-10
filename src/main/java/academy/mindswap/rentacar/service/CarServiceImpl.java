package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.converter.CarConverter;
import academy.mindswap.rentacar.dto.CarCreateDto;
import academy.mindswap.rentacar.dto.CarDto;
import academy.mindswap.rentacar.model.Car;
import academy.mindswap.rentacar.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    private CarRepository carRepository;
    private CarConverter carConverter;

    @Autowired
    public CarServiceImpl (CarRepository carRepository, CarConverter carConverter){
        this.carRepository = carRepository;
        this.carConverter = carConverter;
    }

    @Override
    public CarDto createCar(CarCreateDto carCreateDto) {
       Car car = carConverter.fromCarCreateDtoToCarEntity(carCreateDto);
       car = carRepository.save(car);

        return carConverter.fromCarEntityToCarDto(car);
    }

    @Override
    public CarDto getCarById(Long carId) {
        Car car = carRepository.getReferenceById(carId);
        return carConverter.fromCarEntityToCarDto(car);
    }

    @Override
    public List<CarDto> getAllCars() {
        List<Car> cars = carRepository.findAll();
        List<CarDto> carDtos = cars.parallelStream()
                .map(carConverter ::fromCarEntityToCarDto)
                .toList();

        return carDtos;
    }

    @Override
    public CarDto updateCar(Long id, CarDto carDto) {
        Car existingCar = carRepository.getReferenceById(id);
        if (existingCar == null) {
            throw new IllegalArgumentException("Car not found");
        }
        existingCar.setBrand(carDto.getBrand());
        existingCar.setModel(carDto.getModel());
        existingCar.setDayPrice(carDto.getDayPrice());
        existingCar.setLicencePlate(carDto.getLicencePlate());

        Car updateCar = carRepository.save(existingCar);
        return carConverter.fromCarEntityToCarDto(updateCar);

    }


    @Override
    public void deleteCar(Long carId) {

        Car car = carRepository.getReferenceById(carId);
        if (car == null) {
            throw new IllegalArgumentException("car not found");
        }
    carRepository.delete(car);
    }
}
