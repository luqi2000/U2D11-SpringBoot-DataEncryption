package luqmanmohammad.U2D11SpringBootDataEncryption.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import luqmanmohammad.U2D11SpringBootDataEncryption.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	 Optional<User> findByEmail(String email);
	 Optional<User> findByUsername(String username);
}
