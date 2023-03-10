package academy.mindswap.rentacar.converter;

import academy.mindswap.rentacar.dto.RentalsDto;
import academy.mindswap.rentacar.model.Rentals;
import org.springframework.stereotype.Component;

@Component
public class RentalsConverter {

    public RentalsDto fromRentalEntityToRentalDto(Rentals rental) {
        return RentalsDto.builder()
                .id(rental.getId())
                .pickUpDate(rental.getPickUpDate())
                .deliveryDate(rental.getDeliveryDate())
                .carId(rental.getCar().getId())
                .userId(rental.getUser().getId())
                .build();
    }

    public Rentals fromRentalDtoToEntity(RentalsDto rentalDto) {
        return Rentals.builder()
                .id(rentalDto.getId())
                .pickUpDate(rentalDto.getPickUpDate())
                .deliveryDate(rentalDto.getDeliveryDate())
                .build();

    }
}
