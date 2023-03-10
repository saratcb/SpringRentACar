package academy.mindswap.rentacar.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

    @Getter
    @Setter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public class CarCreateDto {

        @NotBlank(message = "Must have model")
        private String model;
        @NotBlank(message = "Must have last brand")
        private String brand;
        @NotBlank(message = "Must have licence plate")
        @Pattern(regexp = "[0-9]{2}[\\s-]{0,1}[0-9]{2}[\\s-]{0,1}[A-IK-PR-VZ]{2}|[0-9]{2}[\\s-]{0,1}[A-IK-PR-VZ]{2}[\\s-]{0,1}[0-9]{2}|[A-IK-PR-WYZ]{2}[\\s-]{0,1}[0-9]{2}[\\s-]{0,1}[A-IK-PR-WYZ]{2}", message = "Invalid plate")
        private String licencePlate;

        @NotNull(message = "Must have price")
        private int dayPrice;

    }
