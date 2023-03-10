package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.controller.RentalController;
import academy.mindswap.rentacar.converter.RentalsConverter;
import academy.mindswap.rentacar.dto.RentalsDto;
import academy.mindswap.rentacar.model.Car;
import academy.mindswap.rentacar.model.Rentals;
import academy.mindswap.rentacar.model.User;
import academy.mindswap.rentacar.repository.CarRepository;
import academy.mindswap.rentacar.repository.RentalsRepository;
import academy.mindswap.rentacar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalsServiceImpl implements RentalsService{

    private RentalsConverter rentalsConverter;
    private RentalsRepository rentalsRepository;

    private CarRepository carRepository;
    private UserRepository userRepository;

    @Autowired
    public RentalsServiceImpl (RentalsRepository rentalsRepository, RentalsConverter rentalsConverter, UserRepository userRepository, CarRepository carRepository){
        this.rentalsRepository = rentalsRepository;
        this.rentalsConverter = rentalsConverter;
        this.carRepository = carRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RentalsDto createRental(RentalsDto rentalDto) {
       Car carToRent = carRepository.getReferenceById(rentalDto.getCarId());
       User userWhoRents = userRepository.getReferenceById(rentalDto.getUserId());
       Rentals rental = rentalsConverter.fromRentalDtoToEntity(rentalDto);

       rental.setCar(carToRent);
       rental.setUser(userWhoRents);

       rentalsRepository.save(rental);

       return rentalsConverter.fromRentalEntityToRentalDto(rental);
    }

    @Override
    public RentalsDto getRentalById(Long rentalId) {
        return null;
    }

    @Override
    public List<RentalsDto> getAllRentals() {
        return null;
    }

    @Override
    public RentalsDto updateRental(RentalsDto rentalDto) {
        return null;
    }

    @Override
    public void deleteRental(Long rentalId) {

    }
}
