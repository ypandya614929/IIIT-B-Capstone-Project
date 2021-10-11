package bookmyconsultation.paymentservice.security;

import bookmyconsultation.paymentservice.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, String> {
}
