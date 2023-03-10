package academy.mindswap.rentacar.repository;

import academy.mindswap.rentacar.model.Rentals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalsRepository extends JpaRepository<Rentals, Long> {
}
