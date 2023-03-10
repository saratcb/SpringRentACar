package academy.mindswap.rentacar.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentals")
public class Rentals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String pickUpDate;
    @Column(nullable = false)
    private String deliveryDate;
    @ManyToOne(targetEntity = Car.class)
    @JoinColumn(name = "car_id")
    private Car car;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

}
