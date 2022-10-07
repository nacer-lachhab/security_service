package laur.spilca.tuto.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import laur.spilca.tuto.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findUserByUsername(String username);
}
