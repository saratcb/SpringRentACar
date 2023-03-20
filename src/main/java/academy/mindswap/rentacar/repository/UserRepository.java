package academy.mindswap.rentacar.repository;

import academy.mindswap.rentacar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByFirstName(String firstName);


   // User getUserByLastName(String lastName);

    Optional<User> findByEmail(String email);

}
