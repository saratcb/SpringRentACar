package academy.mindswap.rentacar.dto;

import academy.mindswap.rentacar.model.Car;
import academy.mindswap.rentacar.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RentalsDto {

    private Long id;
    @NotBlank(message = "Must have date")
    private String pickUpDate;
    @NotBlank(message = "Must have date")
    private String deliveryDate;

    @NotNull(message = "Insert carId")
    private Long carId;

    @NotNull(message = "Insert userId")
    private Long userId;
}
