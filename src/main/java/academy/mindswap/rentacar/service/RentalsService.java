package academy.mindswap.rentacar.service;

import academy.mindswap.rentacar.dto.RentalsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RentalsService {

    RentalsDto createRental(RentalsDto rentalDto);

    RentalsDto getRentalById(Long rentalId);

    List<RentalsDto> getAllRentals();

    RentalsDto updateRental(RentalsDto rentalDto);

    void deleteRental(Long rentalId);

}
